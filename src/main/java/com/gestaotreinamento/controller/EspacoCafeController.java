package com.gestaotreinamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gestaotreinamento.model.EspacoCafe;
import com.gestaotreinamento.repository.EspacoCafeRepository;
import com.gestaotreinamento.repository.SalaRepository;

@Controller
public class EspacoCafeController {

	@Autowired
	private EspacoCafeRepository espacoCafeRepository;
	
	@Autowired
	private SalaRepository salaRepository;
	
	@GetMapping(value = "/paginaespacocafe")
	public ModelAndView inicioEspacoCafe() {
		
		ModelAndView modelAndView;
		
		int totalSalas = salaRepository.findTotalSalas();
		
		if(totalSalas < 2) {
			modelAndView = new ModelAndView("paginas/cadastrosala");
			modelAndView.addObject(
					"msg", "Cadastre pelo menos duas salas no sistema para que você possa" 
							+ " cadastrar os espaços para café");
			modelAndView.addObject("salas", salaRepository.findAll());
			return modelAndView;
		}
		
		modelAndView = new ModelAndView("paginas/cadastroespacocafe");
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
}