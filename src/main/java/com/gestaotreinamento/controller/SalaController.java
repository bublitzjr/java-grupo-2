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

/**
 * SalaController recebe as requisões relacionadas às salas do evento e responde
 * de volta ao View.
 * 
 * O ModelAndView é um construtor que possibilita a comunicação com o model e o
 * retorno da view no mesmo return, portanto faz a interlocução entre backend e
 * frontend.
 * 
 * ANNOTATIONS utilizadas no código:
 * 
 * @Controller indica que uma classe é um controller.
 * @Autowired injeção de dependência.
 * @GetMapping Mapeamento da url com método get.
 * @PostMapping Mapeamento da url com método post.
 * @RequestParam Requisição de parâmetros da View.
 * 
 * @author Adriano Warmling
 * @author Jefferson Bublitz
 * @author Lorran dos Santos
 * @author Nádia Hansen
 * @author Yuri Piffer
 */
@Controller
public class SalaController {

	/**
	 * Retorna o objeto mantido no banco de dados a partir do filtro {sala} passado.
	 */
	@Autowired
	private SalaRepository salaRepository;

	/**
	 * A partir do mapeamento get da url "/paginaSala", carrega-se no modelAndView
	 * todas as salas contidas no salaRepository.
	 * 
	 * @return modelAndView com todas as salas.
	 */
	@GetMapping(value = "/paginaSala")
	public ModelAndView inicioSala() {
		ModelAndView modelAndView = new ModelAndView("paginas/cadastrosala");
		modelAndView.addObject("salas", salaRepository.findAll());

		return modelAndView;
	}

	/**
	 * A partir do mapeamento post da url ""/cadastrarSala"", cria-se uma nova sala.
	 * A variável totalSalas recebe o total de salas que constam no repositório,
	 * depois é incrementada e seta o id da próxima sala sendo cadastrada pelo
	 * .setId.
	 * 
	 * Uma vez cadastrada a nova sala, o elemento é persistido pelo salaRepository.
	 * É impresso na tela uma mensagem de sucesso e depois todas as salas são
	 * listadas no salaRepository por .findAll e retorna o modelAndView.
	 * 
	 * @param sala é um objeto importado do model Sala. @see Sala.java
	 * 
	 * @return modelAndView com todas as salas.
	 */
	@PostMapping(value = "/cadastrarSala")
	public ModelAndView cadastroSala(Sala sala) {

		int totalSalas = salaRepository.findTotalSalas(); // QUEBROU AQUI
		totalSalas++;
		sala.setId(totalSalas);

		salaRepository.save(sala);

		ModelAndView modelAndView = new ModelAndView("paginas/cadastrosala");
		Iterable<Sala> nomeSalas = salaRepository.findAll();
		modelAndView.addObject("msg", "Sala cadastrada com sucesso!");
		modelAndView.addObject("salas", nomeSalas);

		return modelAndView;
	}

	/**
	 * A partir do mapeamento post da url ""/pesquisarSala"" o @RequestParam captura
	 * o que foi digitado no campo e carrega na variável pesqNomeSalas como uma
	 * string. Esse valor atuará como filtro no salaRepository e o resultado dessa
	 * busca será salvo em uma lista salasEncontradas. É feito uma verificação se
	 * salasEncontradas está vazia ou não. Caso esteja vazia, retorna uma mensagem
	 * de "Nenhuma sala encontrada". Quando existe pelo menos uma sala,
	 * salasEncontradas é carregado no View.
	 * 
	 * @param pesqNomeSalas Parâmetro string que é utilizado para filtrar as salas é
	 *                      carregado em uma lista.
	 * 
	 * @return modelAndView com todas as salas filtradas.
	 */
	@PostMapping(value = "**/pesquisarSala")

	  public ModelAndView pesquisarSalaPorNome(@RequestParam("pesqNomeSalas") String pesqNomeSalas) {

    List<Sala> salasEncontradas = salaRepository.findSalaPorNome(pesqNomeSalas);
		ModelAndView modelAndView = new ModelAndView("paginas/cadastrosala");

		if (salasEncontradas.isEmpty()) {
			modelAndView.addObject("erro", "Nenhuma sala encontrada");
		} else {
			modelAndView.addObject("salas", salasEncontradas);
		}

		return modelAndView;
	}
}