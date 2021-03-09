package com.gestaotreinamento.gestaotreinamento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class GestaotreinamentoApplicationTests {

	private WebDriver navegador;

	@BeforeEach
	public void setUp() {

		// Abrindo o navegador
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Drivers\\chromedriver.exe");
		navegador = new ChromeDriver();

		// Aumentando o tempo para poder fazer a leitura
		navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Maximizar a tela
		navegador.manage().window().maximize();

		// Navegando para o localhost (lembrar de subir o projeto antes de executar o teste)
		navegador.get("http://localhost:8080/");
		
		// Clicar no link que possui o texto 'CLIQUE AQUI'
		navegador.findElement(By.linkText("CLIQUE AQUI")).click();
	}

	@Test
	public void testCadastrarSala() {

		// Digitar no campo com name "nomeSala" os nomes "Primeira Sala" e "Segunda Sala"
		WebElement nomeSalas = navegador.findElement(By.name("nomeSala"));
		nomeSalas.sendKeys("SalaTeste");

		// Clicar no botão com id "btnCadastrar"
		navegador.findElement(By.id("btnCadastrar")).click();

		// Validar a mensagem "Sala cadastrada com sucesso!"
		WebElement validarCadastroSala = navegador.findElement(By.id("salaCadastrada"));
		String textoValidacao = validarCadastroSala.getText();

		// Validação
		assertEquals("SALA CADASTRADA COM SUCESSO!", textoValidacao);

	}

	@Test
	public void testCadastrarPessoas() {
		
		// Clicar no link com o id lnkCadastrarPessoa
		navegador.findElement(By.id("lnkCadastrarPessoa")).click();
		
		// Digitar o nome da pessoa no campo onde tem o name "nomePessoa"
		WebElement nomePessoa = navegador.findElement(By.name("nomePessoa"));
		nomePessoa.sendKeys("Nome Teste");
		
		// Digitar o sobrenome da pessoa no campo onde tem o name "sobrenomePessoa"
		WebElement sobrenomePessoa = navegador.findElement(By.name("sobrenomePessoa"));
		sobrenomePessoa.sendKeys("Sobrenome Teste");
		
		// Clicar no button com id "btnCadastrar"
		navegador.findElement(By.id("btnCadastrar")).click();
		
		// Validar a mensagem "Pessoa cadastrada com sucesso!"
		WebElement validarCadastroPessoa = navegador.findElement(By.id("pessoaCadastrada"));
		String textoValidacao = validarCadastroPessoa.getText();
		assertEquals("Pessoa cadastrada com sucesso!", textoValidacao);

	}

	@Test
	public void testConsultarCadaPessoa() {
		
		// Clicar no link com o id lnkCadastrarPessoa
		navegador.findElement(By.id("lnkCadastrarPessoa")).click();
		
		// Clicar no link com o id lnkConsultas
		navegador.findElement(By.id("lnkConsultas")).click();
		
		// Digitar o nome da pessoa no campo onde tem o name "nomePessoa"
		WebElement pesqNomePessoa = navegador.findElement(By.name("primeironome"));
		pesqNomePessoa.sendKeys("Nome Teste");
		
		// Clicar no button com id "btnBuscar"
		navegador.findElement(By.id("btnBuscar")).click();
		
	}

	@Test
	public void testConsultarCadaSala() {
				
		// Digitar o nome da sala no campo onde tem o name "nomePessoa"
		WebElement pesqNomeSala = navegador.findElement(By.name("pesqNomeSalas"));
		pesqNomeSala.sendKeys("SalaTeste");
				
		// Clicar no button com id "btnBuscar"
		navegador.findElement(By.id("btnBuscar")).click();
	}
	
	@AfterEach
	public void tearDown() {
		
		// Fechar o navegador
		navegador.quit();
	}

}
