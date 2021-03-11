package com.gestaotreinamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gestaotreinamento.model.Sala;
import com.gestaotreinamento.repository.SalaRepository;

@Controller
public class SalaController {

	@Autowired
	private SalaRepository salaRepository;
	
	@GetMapping(value = "/paginaSala")
	public ModelAndView inicioSala() {
		ModelAndView modelAndView = new ModelAndView("paginas/cadastrosala");
		modelAndView.addObject("salas", salaRepository.findAll());
		
		return modelAndView;
	}
	
	@PostMapping(value = "/cadastrarSala")
	public ModelAndView cadastroSala(Sala sala) {
		
		int totalSalas = salaRepository.findTotalSalas(); //contagem de linhas da quantidade de salas
		totalSalas++;
		sala.setId(totalSalas); //vai setar o total de salas + 1 e colocar no id 
		
		salaRepository.save(sala); //aqui salva no banco de dados
		
		ModelAndView modelAndView = new ModelAndView("paginas/cadastrosala");
		Iterable<Sala> nomeSalas = salaRepository.findAll();
		modelAndView.addObject("msg", "Sala cadastrada com sucesso!");
		modelAndView.addObject("salas", nomeSalas);
		
		return modelAndView;
	}
	
	@PostMapping(value = "**/pesquisarSala")
	public ModelAndView pesquisarSalaPorNome(@RequestParam("pesqNomeSalas") String pesqNomeSalas) {
		
		List<Sala> salasEncontradas = salaRepository.findSalaPorNome(pesqNomeSalas);
		ModelAndView modelAndView = new ModelAndView("paginas/cadastrosala");
		
		if(salasEncontradas.isEmpty()) {
			modelAndView.addObject("erro", " Nenhuma sala encontrada");
		} else {
			modelAndView.addObject("salas", salasEncontradas);
		}
		
		return modelAndView;
	}
}