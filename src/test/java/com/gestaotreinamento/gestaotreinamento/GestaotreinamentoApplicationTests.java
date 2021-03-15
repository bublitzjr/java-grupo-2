package com.gestaotreinamento.gestaotreinamento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import com.gestaotreinamento.controller.EspacoCafeController;
import com.gestaotreinamento.controller.PessoaController;
import com.gestaotreinamento.controller.SalaController;
import com.gestaotreinamento.model.EspacoCafe;
import com.gestaotreinamento.model.Pessoa;
import com.gestaotreinamento.model.Sala;

@SpringBootTest
class GestaotreinamentoApplicationTests {

	@Autowired
	private SalaController salaController;

	@Autowired
	private EspacoCafeController espacoCafeController;

	@Autowired
	private PessoaController pessoaController;

	@Test
	public void testeCadastrarSala() {

		ModelAndView modelAndView = new ModelAndView();

		Sala sala = new Sala();
		sala.setNomeSala("Sala Teste");
		sala.setLotacao(23);

		modelAndView = this.salaController.cadastroSala(sala);
		boolean msgValidacaoSala = modelAndView.getModel().containsValue("Sala cadastrada com sucesso!");

		assertEquals(true, msgValidacaoSala);
	}

	@Test
	public void testeCadastrarEspacoCafe() {

		ModelAndView modelAndView = new ModelAndView();

		EspacoCafe espacoCafe = new EspacoCafe();
		espacoCafe.setNomeDoLocal("Cantina Teste");

		modelAndView = this.espacoCafeController.cadastroEspacoCafe(espacoCafe);
		boolean msgValidacaoCafe = modelAndView.getModel().containsValue("Espaço de Café foi cadastrado com sucesso!");

		assertEquals(true, msgValidacaoCafe);
	}

	@Test
	public void testeCadastrarPessoa() {

		ModelAndView modelAndView = new ModelAndView();

		Pessoa pessoa = new Pessoa();
		pessoa.setNomePessoa("Steve");
		pessoa.setSobrenomePessoa("Jobs");

		modelAndView = this.pessoaController.cadastroPessoa(pessoa);

		boolean msgValidacaoPessoa = modelAndView.getModel().containsValue("Pessoa cadastrada com sucesso!");

		assertEquals(true, msgValidacaoPessoa);
	}

	@Test
	public void testeConsultarPessoa() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView = this.pessoaController.campoFiltro("Ste", 1);

		boolean pessoaEncontrada = modelAndView.getModel().toString().contains("Ste") ? true : false;

		assertEquals(true, pessoaEncontrada);
	}

	@Test
	public void testeConsultarSala() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView = this.salaController.pesquisarSalaPorNome("Test");

		boolean salaEncontrada = modelAndView.getModel().toString().contains("Test") ? true : false;

		assertEquals(true, salaEncontrada);
	}
}
