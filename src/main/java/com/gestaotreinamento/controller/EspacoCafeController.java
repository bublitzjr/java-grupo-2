package com.gestaotreinamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gestaotreinamento.model.EspacoCafe;
import com.gestaotreinamento.repository.EspacoCafeRepository;

@Controller
public class EspacoCafeController {

	@Autowired
	private EspacoCafeRepository espacoCafeRepository;
	
	@GetMapping(value = "/paginaespacocafe")
	public ModelAndView inicioEspacoCafe() {
		ModelAndView modelAndView = new ModelAndView("paginas/cadastroespacocafe");
		modelAndView.addObject("espacosCafes", espacoCafeRepository.findAll());
		
		return modelAndView;
	}
	
	@PostMapping(value = "/cadastrarEspacoCafe")
	public ModelAndView cadastroSala(EspacoCafe espacoCafe) {
		
		int totalLocaisCafe = espacoCafeRepository.findTotalEspacosCafe();
		
		ModelAndView modelAndView = new ModelAndView("paginas/cadastroespacocafe");
		
		if(totalLocaisCafe == 2) {
			modelAndView.addObject("msg", "Você cadastrou o limite de dois espaços para café."
					+ " Agora você já pode cadastrar os usuários do evento");
			modelAndView.addObject("espacosCafes", espacoCafeRepository.findAll());
			return modelAndView;
		}
		
		totalLocaisCafe++;
		espacoCafe.setIdEspaco(totalLocaisCafe);
		espacoCafeRepository.save(espacoCafe);
		
		Iterable<EspacoCafe> nomesEspacosCafes = espacoCafeRepository.findAll();
		modelAndView.addObject("msg", "Espaço de Café foi cadastrado com sucesso!");			
		modelAndView.addObject("espacosCafes", nomesEspacosCafes);
		
		return modelAndView;
	}
	
	/* VOCÊS ENTENDEM QUE SEJA IMPORTANTE TER UMA BARRA DE PESQUISA AQUI???
	
	@PostMapping(value = "**///pesquisarSala")
	/* public ModelAndView pesquisarSalaPorNome(@RequestParam("pesqNomeSalas") String pesqNomeSalas) {
		
		List<Sala> salasEncontradas = salaRepository.findSalaPorNome(pesqNomeSalas);
		ModelAndView modelAndView = new ModelAndView("paginas/cadastrosala");
		
		if(salasEncontradas.isEmpty()) {
			modelAndView.addObject("erro", " Nenhuma sala encontrada");
		} else {
			modelAndView.addObject("salas", salasEncontradas);
		}
		
		return modelAndView;
	}
	*/
}