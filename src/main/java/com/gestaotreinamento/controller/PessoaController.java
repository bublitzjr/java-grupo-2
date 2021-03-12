package com.gestaotreinamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gestaotreinamento.model.EspacoCafe;
import com.gestaotreinamento.model.Pessoa;
import com.gestaotreinamento.model.Sala;
import com.gestaotreinamento.repository.EspacoCafeRepository;
import com.gestaotreinamento.repository.PessoaRepository;
import com.gestaotreinamento.repository.SalaRepository;

/**
 * Controller da sala
 * 
 * * ANNOTATIONS utilizadas no código: "@Controller": "@Autowired":
 * "@GetMapping": "@PostMapping":
 * 
 * 
 * @author Adriano Warmling
 * @author Jefferson Bublitz
 * @author Lorran dos Santos
 * @author Nádia Hansen
 * @author Yuri Piffer
 */

@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private SalaRepository salaRepository;

	@Autowired
	private EspacoCafeRepository espacoCafeRepository;

	@GetMapping(value = "/cadastropessoa")
	public ModelAndView inicioPessoa() {
		ModelAndView modelAndView;
		int qtdEspacoCafe = espacoCafeRepository.findTotalEspacosCafe();

		// Para fazer com que haja pelo menos duas salas já cadastradas
		if (qtdEspacoCafe < 2) {
			modelAndView = new ModelAndView("paginas/cadastroespacocafe");
			modelAndView.addObject("msg",
					"Cadastre dois espaços para café no sistema para que você possa" + " cadastrar uma pessoa");
			return modelAndView;
		}
		modelAndView = new ModelAndView("paginas/cadastropessoa");

		return modelAndView;
	}

	@PostMapping(value = "/cadastrarpessoa")
	public ModelAndView cadastroPessoa(Pessoa pessoa) {

		int totalPessoas = pessoaRepository.findTotalPessoas();

		/*
		 * Aqui vai ser preciso eu fazer uma busca no BD pelo menor valor de lotação que
		 * tiver na sala pra atribuir no lugar desse 20
		 */
		if (totalPessoas > 20) {
			ModelAndView modelAndView = new ModelAndView("paginas/cadastropessoa");
			modelAndView.addObject("msg", "Pessoa não cadastrada!!" + " Limite de 20 pessoas por sala!");
		}

		totalPessoas++;
		pessoa.setId(totalPessoas);

		pessoaRepository.save(pessoa);

		ModelAndView modelAndView = new ModelAndView("paginas/cadastropessoa");
		modelAndView.addObject("msg", "Pessoa cadastrada com sucesso!");
		return modelAndView;
	}

	@GetMapping(value = "/listaDeCadastrados")
	public ModelAndView pessoasCadastradas() {
		ModelAndView modelAndView = new ModelAndView("paginas/listacadastrados");
		Iterable<Pessoa> totalPessoas = pessoaRepository.findAll();
		modelAndView.addObject("pessoas", totalPessoas);
		return modelAndView;
	}

	@GetMapping(value = "/distribuir")
	public ModelAndView distribuirPessoas() {

		ModelAndView modelAndView;
		int totalPessoas = pessoaRepository.findTotalPessoas();
		List<Pessoa> pessoasCadastradas = pessoaRepository.findAllOrderById();
		List<Integer> salasCadastradas = salaRepository.findAllId();

		if (totalPessoas < salasCadastradas.size()) {
			modelAndView = new ModelAndView("paginas/cadastropessoa");
			modelAndView.addObject("msg", "Você precisa cadastrar ao menos 2 pessoas por sala no sistema!");
		} else {

			pessoasCadastradas = distribuirTodasPessoas();

			for (int i = 0; i < pessoasCadastradas.size(); i++) {
				pessoaRepository.save(pessoasCadastradas.get(i));
			}

			/* Talvez eu tenha que fazer um FOR para cadastrar as etapas no BD */

			modelAndView = new ModelAndView("paginas/listacadastrados");
			modelAndView.addObject("pessoas", pessoaRepository.findAllOrderById());
			modelAndView.addObject("msg", "DISTRIBUIÇÃO FEITA COM SUCESSO!");
		}

		return modelAndView;
	}

	@PostMapping("**/pesquisarpessoa")
	public ModelAndView pesquisarPorNome(@RequestParam("primeironome") String primeironome) {

		ModelAndView modelAndView = new ModelAndView("paginas/listacadastrados");
		modelAndView.addObject("pessoas", pessoaRepository.findPessoaPeloNome(primeironome));

		return modelAndView;
	}

	/*
	 * Essa aqui talvez seja uma função a ser adaptada. Didaticamente é interessante
	 * que haja uma exclusão por cascata, da sala e das pessoas e depois a do local
	 * do café. Agora que foi criada a classe para Evento, INCLUIR ela nesse Apagar
	 */
	@GetMapping(value = "/apagarTudo")
	public ModelAndView apagarTudo() {
		pessoaRepository.deleteAll();
		salaRepository.deleteAll();
		espacoCafeRepository.deleteAll();

		ModelAndView modelAndView = new ModelAndView("paginas/cadastrosala");
		modelAndView.addObject("msg",
				"Salas, Espaços de Café e Usuários foram removidos" + " com sucesso do Banco de Dados!");

		return modelAndView;
	}

	private List<Pessoa> distribuirTodasPessoas() {

		List<Pessoa> totalPessoas = (List<Pessoa>) pessoaRepository.findAll();
		List<Sala> totalSalas = (List<Sala>) salaRepository.findAll();
		List<EspacoCafe> totalEspacoCafe = (List<EspacoCafe>) espacoCafeRepository.findAll();

		int media = totalPessoas.size() / 2;

		// Distribuindo para os espaços de café OK
		for (int i = 0; i < totalPessoas.size(); i++) {

			if (totalPessoas.get(i).getId() % 2 != 0) {
				totalPessoas.get(i).setEspacocafe(totalEspacoCafe.get(0));
			} else {
				totalPessoas.get(i).setEspacocafe(totalEspacoCafe.get(1));
			}
		}

		int vetSala = 0;

		// Distribuindo para as salas na primeira etapa

		for (int i = 0; i < totalPessoas.size(); i++) {
			if (vetSala == 0) {
				totalPessoas.get(i).setSala1(vetSala);
				vetSala++;
			}

			else if (vetSala == totalSalas.size() - 1) {
				totalPessoas.get(i).setSala1(vetSala);
				vetSala = 0;
			} else {
				totalPessoas.get(i).setSala1(vetSala);
				vetSala++;
			}
		}

		// Distribuindo para as salas na segunda etapa

		for (int i = 0; i < totalPessoas.size(); i++) {
			if (vetSala == 0 && totalPessoas.get(i).getId() < media) {
				totalPessoas.get(i).setSala2(vetSala);
				vetSala++;
			} 			
			
			else {
				totalPessoas.get(i).setSala2(vetSala + 1);
				vetSala = 0;
			}

		}

		return totalPessoas;
	}

}