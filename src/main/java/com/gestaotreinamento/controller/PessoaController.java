package com.gestaotreinamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gestaotreinamento.model.Pessoa;
import com.gestaotreinamento.repository.PessoaRepository;
import com.gestaotreinamento.repository.SalaRepository;

@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private SalaRepository salaRepository;

	@GetMapping(value = "/cadastropessoa")
	public ModelAndView inicioPessoa() {
		ModelAndView modelAndView;
		int qtdSalas = salaRepository.findTotalSalas();

		// Para fazer com que haja pelo menos duas salas já cadastradas
		if (qtdSalas < 2) {
			modelAndView = new ModelAndView("index");
			modelAndView.addObject(
					"msg", "Cadastre pelo menos duas salas no sistema para que você possa" 
							+ " cadastrar uma pessoa");
			return modelAndView;
		}
		modelAndView = new ModelAndView("paginas/cadastropessoa");

		return modelAndView;
	}

	@PostMapping(value = "/cadastrarpessoa")
	public ModelAndView cadastroPessoa(Pessoa pessoa) {

		int totalPessoas = pessoaRepository.findTotalPessoasSala();

		if (totalPessoas > 20) {
			ModelAndView modelAndView = new ModelAndView("paginas/cadastropessoa");
			modelAndView.addObject("msg", "Pessoa não cadastrada!!" 
					+ " Limite de 20 pessoas por sala!");
		}

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
		int totalPessoas = pessoaRepository.findTotalPessoasSala();
		List<Pessoa> pessoasCadastradas = pessoaRepository.findAllOrderById();
		List<Long> salasCadastradas = salaRepository.findAllId();

		if (totalPessoas < salasCadastradas.size()) {
			modelAndView = new ModelAndView("paginas/cadastropessoa");
			modelAndView.addObject(
					"msg", "Você precisa cadastrar ao menos 2 pessoas por sala no sistema!");
		} else {
			
			pessoasCadastradas = distribuirPessoasPorSala();

			for (int i = 0; i < pessoasCadastradas.size(); i++) {
				pessoaRepository.save(pessoasCadastradas.get(i));
			}

			modelAndView = new ModelAndView("paginas/listacadastrados");
			modelAndView.addObject("pessoas", pessoaRepository.findAllOrderById());
			modelAndView.addObject(
					"msg", "DISTRIBUIÇÃO FEITA COM SUCESSO!");
		}

		return modelAndView;
	}
	
	@PostMapping("**/pesquisarpessoa")
	public ModelAndView pesquisarPorNome(@RequestParam("primeironome") String primeironome) {
		
		ModelAndView modelAndView = new ModelAndView("paginas/listacadastrados");
		modelAndView.addObject("pessoas", pessoaRepository.findPessoaPeloNome(primeironome));
		
		return modelAndView;
	}
	
	@GetMapping(value = "/apagarTudo")
	public ModelAndView apagarTudo() {
		pessoaRepository.deleteAll();
		salaRepository.deleteAll();
		
		ModelAndView modelAndView = new ModelAndView("paginas/cadastrosala");
		modelAndView.addObject("msg", "Salas e Usuários foram removidos com sucesso!");
		
		return modelAndView;
	}
	
	private List<Pessoa> distribuirPessoasPorSala() {

		List<Pessoa> pessoasCadastradas = pessoaRepository.findAllOrderById();
		List<Long> salasCadastradas = salaRepository.findAllId();

		// Por aqui eu consigo tirar a média
		int media = pessoasCadastradas.size() / 2;
		int idSala = 1;
		
			for(int i = 1; i < pessoasCadastradas.size(); i++) {
				pessoasCadastradas.get(i).setSalaEtapa1(0);
				pessoasCadastradas.get(i).setSalaEtapa2(0);
			}
		
		for (int i = 0; i < pessoasCadastradas.size(); i++) {
			
			if (idSala > salasCadastradas.size()) {
				idSala = 1;
			}

			// id de usuário ímpar = espaço de café ímpar (e vice-versa)
			pessoasCadastradas.get(i).setSalaEtapa1(idSala);
			idSala++;
			if (pessoasCadastradas.get(i).getId() % 2 == 1) {
				pessoasCadastradas.get(i).setCafeEtapa1("Espaço para Café 1");
				pessoasCadastradas.get(i).setCafeEtapa2("Espaço para Café 1");
			} else {
				pessoasCadastradas.get(i).setCafeEtapa1("Espaço para Café 2");
				pessoasCadastradas.get(i).setCafeEtapa2("Espaço para Café 2");
			}

			/* Caso o id da pessoa esteja abaixo da média total de pessoas por sala, ela irá
				continuar nesta mesma sala */
			if (pessoasCadastradas.get(i).getId() <= media) {
				pessoasCadastradas.get(i).setSalaEtapa2(pessoasCadastradas.get(i).getSalaEtapa1());
			}

			/* Caso o id da pessoa esteja acima da média total de pessoas por sala, ela
				avançará 1 sala */
						
			else {
				if (pessoasCadastradas.get(i).getSalaEtapa1() == salasCadastradas.size()) {
					int novoId = 1;
					pessoasCadastradas.get(i).setSalaEtapa2(novoId);
				} else {
					pessoasCadastradas.get(i).setSalaEtapa2(pessoasCadastradas.get(i).getSalaEtapa1() + 1);
				}
			}
			
		}
		return pessoasCadastradas;
	}
}