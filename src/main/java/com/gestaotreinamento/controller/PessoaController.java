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
 * PessoaController recebe as requisões relacionadas às pessoas cadastradas no
 * evento e responde de volta ao View.
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
public class PessoaController {

	/**
	 * Retorna os objetos mantidos no banco de dados a partir dos filtros {pessoa},
	 * {sala} e {espaco_cafe} passados.
	 */
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private SalaRepository salaRepository;

	@Autowired
	private EspacoCafeRepository espacoCafeRepository;

	/**
	 * A partir do mapeamento get da url "/cadastropessoa", o total de espaços de
	 * café é carregado no qtdEspacoCafe. Depois é checado se o número de espaços de
	 * café {qtdEspacoCafe} é menor que 2. Caso seja, é impresso na tela uma pedido
	 * para que se cadastre o total de espaços de café. Quando qtdEspacoCafe é igual
	 * a 2, retorna e renderiza a url "paginas/cadastropessoa".
	 * 
	 * @return modelAndView direcionando para o cadastro de pessoas. @see url
	 *         "paginas/cadastropessoa"
	 */
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

	/**
	 * A partir do mapeamento post da url "/cadastrarpessoa" é carregada na variável
	 * totalPessoas o número total de pessoas já cadastradas. Este número é
	 * incrementado e atribuído como id da nova pessoa. O dado é inserido e mantido
	 * no banco de dados. Apresenta-se na tela que houve cadastro com sucesso e o
	 * ModelAndView retorna para a possibilidade de cadastrar mais pessoas.
	 * 
	 * @param pessoa é um objeto importado do model Pessoa. @see Pessoa.java
	 * 
	 * @return modelAndView da tela de cadastro de pessoas.
	 */
	@PostMapping(value = "/cadastrarpessoa")
	public ModelAndView cadastroPessoa(Pessoa pessoa) {

		int totalPessoas = pessoaRepository.findTotalPessoas();

		totalPessoas++;
		pessoa.setId(totalPessoas);

		pessoaRepository.save(pessoa);

		ModelAndView modelAndView = new ModelAndView("paginas/cadastropessoa");
		modelAndView.addObject("msg", "Pessoa cadastrada com sucesso!");
		return modelAndView;
	}

	/**
	 * A partir do mapeamento get da url "/listaDeCadastrados", a lista de pessoas
	 * ordenadas por id é carregada em totalPessoas e retornada no modelAndView para
	 * a View.
	 * 
	 * @return modelAndView de pessoas ordenadas por id.
	 */
	@GetMapping(value = "/listaDeCadastrados")
	public ModelAndView pessoasCadastradas() {
		ModelAndView modelAndView = new ModelAndView("paginas/listacadastrados");
		List<Pessoa> totalPessoas = pessoaRepository.findAllOrderById();
		modelAndView.addObject("pessoas", totalPessoas);
		return modelAndView;
	}

	/**
	 * A partir do mapeamento get da url "/distribuir", a lista de pessoas ordenadas
	 * por id é carregada em pessoasCadastradas. A var. menorSala recebe a sala com
	 * menor lotação entre as salas cadastradas. A var. mediaPessoas recebe a razão
	 * entre o total de pessoas e salas cadastradas.
	 * 
	 * A primeira condição avaliada é se o número total de pessoas é menor que o
	 * total de salas cadastradas. Neste caso é impresso na tela que o usuário
	 * precisa cadastrar pelo menos duas pessoas por sala.
	 * 
	 * A segunda condição avaliada é se a razão entre pessoas e salas é maior que a
	 * sala com menor capacidade. Novamente é impresso na tela que o usuário precisa
	 * cadastrar pelo menos duas pessoas por sala.
	 * 
	 * Descartadas as duas conições acima, é feita a distribuição de sala com return
	 * para a View. @see função distribuirTodasPessoas() nas linhas abaixo.
	 * 
	 * @return modelAndView de pessoas ordenadas por id.
	 */
	@GetMapping(value = "/distribuir")
	public ModelAndView distribuirPessoas() {

		ModelAndView modelAndView;
		int totalPessoas = pessoaRepository.findTotalPessoas();
		List<Pessoa> pessoasCadastradas = pessoaRepository.findAllOrderById();
		List<Integer> salasCadastradas = salaRepository.findAllId();
		int menorSala = salaRepository.findMenorLotacao();

		// ?? salasCadastradas.size() poderia ser modificado por totalPessoas ??
		int mediaPessoas = pessoasCadastradas.size() / salasCadastradas.size();

		// CONDIÇÃO 1
		if (totalPessoas < salasCadastradas.size()) {
			modelAndView = new ModelAndView("paginas/cadastropessoa");
			modelAndView.addObject("msg", "Você precisa cadastrar ao menos 2 pessoas por sala no sistema!");
		}

		// CONDIÇÃO 2
		else if (mediaPessoas > menorSala) {
			modelAndView = new ModelAndView("paginas/cadastrosala");
			modelAndView.addObject("msg", "Pessoa NÃO cadastrada! Você estourou a lotação, cadastre "
					+ "outra sala e redistribua novamente as pessoas pelas salas!");
		}

		// DISTRIBUIÇÃO
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

	/**
	 * A partir do mapeamento post da url "/pesquisarpessoa", o @RequestParam
	 * captura o que foi digitado no campo e carrega na variável primeironome como
	 * uma string. Esse valor atuará como filtro no pessoaRepository e o resultado
	 * dessa busca será salvo em uma lista pessoas, que é retornada à View.
	 * 
	 * @return modelAndView de pessoas pelo filtro do primeiro nome.
	 */
	@PostMapping("**/pesquisarpessoa")
	public ModelAndView campoFiltro(@RequestParam("campoFiltro") String campoFiltro,
			@RequestParam("filtro") Integer filtro) {

		ModelAndView modelAndView = new ModelAndView("paginas/listacadastrados");

		if (filtro == 1) {
			modelAndView.addObject("pessoas", pessoaRepository.findPessoaPeloNome(campoFiltro));
		} else if (filtro == 2) {
			modelAndView.addObject("pessoas", pessoaRepository.findSalaEpata1(Integer.parseInt(campoFiltro)));
		} else if (filtro == 3) {
			modelAndView.addObject("pessoas", pessoaRepository.findSalaEpata2(Integer.parseInt(campoFiltro)));
		}else if(filtro == 4) {
			modelAndView.addObject("pessoas", pessoaRepository.findEspacoCafe(Integer.parseInt(campoFiltro)));
		}
		return modelAndView;
	}

	/**
	 * A partir do mapeamento get da url "/apagarTudo", todos os dados mantidos no
	 * repositório são apagados e é impresso uma mensagem na tela avisando o
	 * ocorrido.
	 * 
	 * @return modelAndView com a mensagem de sucesso da execução de exclusão de
	 *         dados.
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

	/**
	 * Função com a lógica de distribuição de pessoas de acordo com as regras de
	 * négócios apresentadas pelo cliente.
	 * 
	 * @return ??????????????????????????????
	 */
	private List<Pessoa> distribuirTodasPessoas() {

		List<Pessoa> totalPessoas = (List<Pessoa>) pessoaRepository.findAll();
		List<Sala> totalSalas = (List<Sala>) salaRepository.findAll();
		List<EspacoCafe> totalEspacoCafe = (List<EspacoCafe>) espacoCafeRepository.findAll();

		int media = totalPessoas.size() / 2;

		// Distribuindo as pessoas pelos locais de café
				for (int i = 0; i < totalPessoas.size(); i++) 
				{
					if (totalPessoas.get(i).getId() % 2 != 0) { // SE idUsuario == IMPAR, ESPAÇO IMPAR
						totalPessoas.get(i).setNomeCafe(totalEspacoCafe.get(0).getNomeDoLocal());
						totalPessoas.get(i).setEspacocafe(totalEspacoCafe.get(0));						
						totalPessoas.get(i).setLocalCafe(1);
						
					} else { // SE idUsuario == PAR, ESPAÇO PAR
						totalPessoas.get(i).setNomeCafe(totalEspacoCafe.get(1).getNomeDoLocal());
						totalPessoas.get(i).setEspacocafe(totalEspacoCafe.get(1));
						totalPessoas.get(i).setLocalCafe(2);				
					}
				}


		// Distribuindo as pessoas pela sala na 1ª etapa
		int idSala = 1;
		int j = 0;

		for (int i = 0; i < totalPessoas.size(); i++) {	
			
			//totalPessoas.get(i).setNomeSala1(totalSalas.get(j).getNomeSala());
			
			if (idSala < totalSalas.size()) { // SE o idSala < do totalDeSalas
				totalPessoas.get(i).setSalaEtapa1(idSala);
				totalPessoas.get(i).setSala(totalSalas.get(idSala - 1));
				totalPessoas.get(i).setNomeSala1(totalSalas.get(j).getNomeSala());
				idSala++; // Ele adiciona ao objeto e soma + 1 ao idSala
				j++;
			} else { // SE idSala == totalDeSalas, é o limite de salas para ele não ficar sem sala
				totalPessoas.get(i).setSalaEtapa1(idSala);
				totalPessoas.get(i).setSala(totalSalas.get(idSala - 1));
				totalPessoas.get(i).setNomeSala1(totalSalas.get(j).getNomeSala());
				idSala = 1;
				j = 0;
			}
			
			//totalPessoas.get(i).setNomeSala1(totalSalas.get(j).getNomeSala());
		}

		// Distribuindo para as salas na segunda etapa
		idSala = 1;
		int h = 0;

		for (int i = 0; i < totalPessoas.size(); i++) {
			
			//totalPessoas.get(i).setNomeSala2(totalSalas.get(h).getNomeSala());

			// Se o idSala for menor que a qtd total de salas & idUsuario <= média total de
			// usuários
			if (idSala < totalSalas.size() && totalPessoas.get(i).getId() <= media) {

				// Ele fica na mesma sala da primeira etapa
				totalPessoas.get(i).setSalaEtapa2(totalPessoas.get(i).getSalaEtapa1());
				totalPessoas.get(i).setSala(totalSalas.get(idSala - 1));
				totalPessoas.get(i).setNomeSala2(totalSalas.get(h).getNomeSala());
				idSala++;
				h++;

			}

			// SE idSala for menor que a qtd total de salas & idUsuario > média total de
			// usuários
			else if (idSala < totalSalas.size() && totalPessoas.get(i).getId() > media) {
				h++;
				totalPessoas.get(i).setSalaEtapa2(totalPessoas.get(i).getSalaEtapa1() + 1);
				totalPessoas.get(i).setSala(totalSalas.get(idSala));
				totalPessoas.get(i).setNomeSala2(totalSalas.get(h).getNomeSala());
				idSala++;
			}

			// SE idSala for igual à qtd total de salas & idUsuario > média total de
			// usuários
			else if (idSala == totalSalas.size() && totalPessoas.get(i).getId() > media) {
				h=0;
				totalPessoas.get(i).setSalaEtapa2(1);
				totalPessoas.get(i).setSala(totalSalas.get(0));
				totalPessoas.get(i).setNomeSala2(totalSalas.get(h).getNomeSala());
				idSala = 1;

			}

			else { // SE idSala for igual à qtd total de salas & idUsuario <= média total de
					// usuários
				totalPessoas.get(i).setSalaEtapa2(totalPessoas.get(i).getSalaEtapa1());
				totalPessoas.get(i).setSala(totalSalas.get(idSala - 1));
				totalPessoas.get(i).setNomeSala2(totalSalas.get(h).getNomeSala());
				idSala = 1;
				h=0;
			}
		}

		return totalPessoas;
	}

}