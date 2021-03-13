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
			modelAndView.addObject("msg", "Pessoa NÃO cadastrada!!" + " Limite de 20 pessoas por sala!");
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
		List<Pessoa> totalPessoas = pessoaRepository.findAllOrderById();
		modelAndView.addObject("pessoas", totalPessoas);
		return modelAndView;
	}

	@GetMapping(value = "/distribuir")
	public ModelAndView distribuirPessoas() {

		ModelAndView modelAndView;
		int totalPessoas = pessoaRepository.findTotalPessoas();
		List<Pessoa> pessoasCadastradas = pessoaRepository.findAllOrderById();
		List<Integer> salasCadastradas = salaRepository.findAllId();
		int menorSala = salaRepository.findMenorLotacao();

		// Média de pessoas por sala
		int mediaPessoas = pessoasCadastradas.size() / salasCadastradas.size();

		if (totalPessoas < salasCadastradas.size()) {
			modelAndView = new ModelAndView("paginas/cadastropessoa");
			modelAndView.addObject("msg", "Você precisa cadastrar ao menos 2 pessoas por sala no sistema!");
		}

		else if (mediaPessoas > menorSala) {
			modelAndView = new ModelAndView("paginas/cadastrosala");
			modelAndView.addObject("msg", "Pessoa NÃO cadastrada! Você estourou a lotação, cadastre "
					+ "outra sala e redistribua novamente as pessoas pelas salas!");
		}

		else {

			pessoasCadastradas = distribuirTodasPessoas();

			for (int i = 0; i < pessoasCadastradas.size(); i++) {
				pessoaRepository.save(pessoasCadastradas.get(i));
			}

			modelAndView = new ModelAndView("paginas/listacadastrados");
			modelAndView.addObject("pessoas", pessoaRepository.findAllOrderById());
			modelAndView.addObject("msg", "DISTRIBUIÇÃO FEITA COM SUCESSO!");
		}
		
		return modelAndView;
	}

	@PostMapping("**/pesquisarpessoa")
	public ModelAndView campoFiltro(@RequestParam("campoFiltro") String campoFiltro, @RequestParam("filtro") Integer filtro) {
				
		ModelAndView modelAndView = new ModelAndView("paginas/listacadastrados");		

		if(filtro == 1)
		{
			modelAndView.addObject("pessoas", pessoaRepository.findPessoaPeloNome(campoFiltro));
		}else if( filtro == 2 ) 
		{
			modelAndView.addObject("pessoas", pessoaRepository.findSalaEpata1(Integer.parseInt(campoFiltro)));
		} else if (filtro == 3) 
		{
			modelAndView.addObject("pessoas", pessoaRepository.findEspacoCafe(Integer.parseInt(campoFiltro)));
		}

		return modelAndView;
	}

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

		// Distribuindo as pessoas pelos locais de café
		for (int i = 0; i < totalPessoas.size(); i++) {

			if (totalPessoas.get(i).getId() % 2 != 0) { // SE idUsuario == IMPAR, ESPAÇO IMPAR
				totalPessoas.get(i).setEspacocafe(totalEspacoCafe.get(0));
				totalPessoas.get(i).setLocalCafe(1);
			} else { // SE idUsuario == PAR, ESPAÇO PAR
				totalPessoas.get(i).setEspacocafe(totalEspacoCafe.get(1));
				totalPessoas.get(i).setLocalCafe(2);
			}
		}

		// Distribuindo as pessoas pela sala na 1ª etapa
		int idSala = 1;

		for (int i = 0; i < totalPessoas.size(); i++) {
			if (idSala < totalSalas.size()) { // SE o idSala < do totalDeSalas
				totalPessoas.get(i).setSalaEtapa1(idSala);
				totalPessoas.get(i).setSala(totalSalas.get(idSala - 1));
				idSala++; // Ele adiciona ao objeto e soma + 1 ao idSala
			} else { // SE idSala == totalDeSalas, é o limite de salas para ele não ficar sem sala
				totalPessoas.get(i).setSalaEtapa1(idSala);
				totalPessoas.get(i).setSala(totalSalas.get(idSala - 1));
				idSala = 1;
			}
		}

		// Distribuindo para as salas na segunda etapa
		idSala = 1;

		for (int i = 0; i < totalPessoas.size(); i++) {

			// Se o idSala for menor que a qtd total de salas & idUsuario <= média total de
			// usuários
			if (idSala < totalSalas.size() && totalPessoas.get(i).getId() <= media) {

				// Ele fica na mesma sala da primeira etapa
				totalPessoas.get(i).setSalaEtapa2(totalPessoas.get(i).getSalaEtapa1());
				totalPessoas.get(i).setSala(totalSalas.get(idSala - 1));
				idSala++;

			}

			// SE idSala for menor que a qtd total de salas & idUsuario > média total de
			// usuários
			else if (idSala < totalSalas.size() && totalPessoas.get(i).getId() > media) {
				totalPessoas.get(i).setSalaEtapa2(totalPessoas.get(i).getSalaEtapa1() + 1);
				totalPessoas.get(i).setSala(totalSalas.get(idSala));
				idSala++;
			}

			// SE idSala for igual à qtd total de salas & idUsuario > média total de
			// usuários
			else if (idSala == totalSalas.size() && totalPessoas.get(i).getId() > media) {
				totalPessoas.get(i).setSalaEtapa2(1);
				totalPessoas.get(i).setSala(totalSalas.get(0));
				idSala = 1;

			}

			else { // SE idSala for igual à qtd total de salas & idUsuario <= média total de
					// usuários
				totalPessoas.get(i).setSalaEtapa2(totalPessoas.get(i).getSalaEtapa1());
				totalPessoas.get(i).setSala(totalSalas.get(idSala - 1));
				idSala = 1;
			}
		}

		return totalPessoas;
	}

}