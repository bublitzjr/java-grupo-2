package com.gestaotreinamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gestaotreinamento.model.EspacoCafe;
import com.gestaotreinamento.repository.EspacoCafeRepository;
import com.gestaotreinamento.repository.SalaRepository;

/**
 * EspacoCafeController recebe as requisições relacionadas aos espaço de café da
 * aplicação e responde de volta ao View.
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
 * 
 * @author Adriano Warmling
 * @author Jefferson Bublitz
 * @author Lorran dos Santos
 * @author Nádia Hansen
 * @author Yuri Piffer
 */
@Controller
public class EspacoCafeController {

	/**
	 * Retorna os objetos mantidos no banco de dados a partir dos filtros passados
	 * {espaco_cafe} e {sala}.
	 */
	@Autowired
	private EspacoCafeRepository espacoCafeRepository;

	@Autowired
	private SalaRepository salaRepository;

	/**
	 * A partir do mapeamento get da url "/paginaespacocafe", é checado se há pelo
	 * menos duas salas cadastradas usando um if. Em caso positivo, retorna as duas
	 * salas.
	 * 
	 * @param totalSalas instanciamento do número total de salas encontradas no
	 *                   banco de dados.
	 * 
	 * @return Retorna todas os cafés por .findALL no espacoCafeRepository e envia
	 *         para o modelAndView que será redirecionado para a url
	 *         "/cadastrosala". No caso de o total de salas ser menor que dois,
	 *         pede-se que o usuário cadastre mais salas.
	 */
	@GetMapping(value = "/paginaespacocafe")
	public ModelAndView inicioEspacoCafe() {

		ModelAndView modelAndView;

		int totalSalas = salaRepository.findTotalSalas();

		if (totalSalas < 2) {
			modelAndView = new ModelAndView("paginas/cadastrosala");
			modelAndView.addObject("msg", "Cadastre pelo menos duas salas no sistema para que você possa"
					+ " cadastrar os espaços para café");
			modelAndView.addObject("salas", salaRepository.findAll());
			return modelAndView;
		}

		modelAndView = new ModelAndView("paginas/cadastroespacocafe");
		modelAndView.addObject("espacosCafes", espacoCafeRepository.findAll());

		return modelAndView;
	}

	/**
	 * A partir do mapeamento post da url "/cadastrarEspacoCafe", avalia se o número
	 * total de cafés é igual a 2 e avisa que o limite de total de cafés já foi
	 * atingido, retornando estes cafés pelo modelAndView. Caso tenham menos que
	 * dois cafés cadastrados, permite o cadastro de um novo café. A variável
	 * totalLocaisCafe conta o número de cafés e não deixa o id ser 0. Uma vez
	 * cadastrado um novo café, o elemento é persistido pelo espacoCafeRepository. É
	 * impresso na tela uma mensagem de sucesso e depois todos os cafés são listados
	 * no espacoCafeRepository por .findAll e retorna o modelAndView.
	 */
	@PostMapping(value = "/cadastrarEspacoCafe")
	public ModelAndView cadastroEspacoCafe(EspacoCafe espacoCafe) {

		int totalLocaisCafe = espacoCafeRepository.findTotalEspacosCafe();

		ModelAndView modelAndView = new ModelAndView("paginas/cadastroespacocafe");

		if (totalLocaisCafe == 2) {
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