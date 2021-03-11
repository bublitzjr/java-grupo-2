package com.gestaotreinamento.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Pessoa - Parte da camada de Model
 * Construtor da "Pessoa": cria um objeto que recebe 
 * o nome e sobrenome da pessoa, gera um id e recebe 
 * as salas da etapa 1 e 2 e cafés da etapa 1 e 2
 * a partir da distribuição gerada pelo sistema
 * mantidas (CRUD) no banco de dados. 
 * 
 * @param id: Chave primária de identificação da sala.
 * @param nomePessoa: String com o nome da pessoa.
 * @param sobrenomePessoa: String com o sobrenome da pessoa.
 * @param salaEtapa1: Recebe a String com o nome da sala para a 1ª etapa do evento. @see com.gestaotreinamento.controller.distribuirPessoasPorSala()
 * @param salaEtapa2: Recebe a String com o nome da sala para a 2ª etapa do evento. @see com.gestaotreinamento.controller.distribuirPessoasPorSala() 
 * @param cafeEtapa1: Recebe a String com o nome do café para a 1ª etapa do evento. @see com.gestaotreinamento.controller.distribuirPessoasPorSala()
 * @param cafeEtapa2: Recebe a String com o nome do café para a 2ª etapa do evento. @see com.gestaotreinamento.controller.distribuirPessoasPorSala()
 * 
 * @return Retorna para a camada do controller os atributos 
 * do objeto concatenados em uma String (id, nomePessoa, 
 * sobrenomePessoa, salaEtapa1, salaEtapa2, cafeEtapa1, cafeEtapa2). 
 * 
 * ANNOTATIONS utilizadas no código:
 * "@Entity": Especifica que a classe é uma entidade. @see &&&PUXAR REFERÊNICA&&&
 * "@Id": Especifica uma chave primária. @see &&&PUXAR REFERÊNICA&&&
 * "@Column": Especifica condições para a persistência dos dados. @see &&&PUXAR REFERÊNICA&&&
 * 										
 * 																				
 * @author Adriano Warmling 
 * @author Jefferson Bublitz
 * @author Lorran dos Santos
 * @author Nádia Hansen
 * @author Yuri Piffer 
 */

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(nullable = false, length = 25)
	private String nomePessoa;

	@Column(nullable = false, length = 25)
	private String sobrenomePessoa;

	private Integer salaEtapa1;
	private Integer salaEtapa2;

	private String cafeEtapa1;
	private String cafeEtapa2;

	
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getSobrenomePessoa() {
		return sobrenomePessoa;
	}

	public void setSobrenomePessoa(String sobrenomePessoa) {
		this.sobrenomePessoa = sobrenomePessoa;
	}

	public Integer getSalaEtapa1() {
		return salaEtapa1;
	}

	public void setSalaEtapa1(Integer salaEtapa1) {
		this.salaEtapa1 = salaEtapa1;
	}

	public Integer getSalaEtapa2() {
		return salaEtapa2;
	}

	public void setSalaEtapa2(Integer salaEtapa2) {
		this.salaEtapa2 = salaEtapa2;
	}

	public String getCafeEtapa1() {
		return cafeEtapa1;
	}

	public void setCafeEtapa1(String cafeEtapa1) {
		this.cafeEtapa1 = cafeEtapa1;
	}

	public String getCafeEtapa2() {
		return cafeEtapa2;
	}

	public void setCafeEtapa2(String cafeEtapa2) {
		this.cafeEtapa2 = cafeEtapa2;
	}

	
	//RETURN
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nomePessoa=" + nomePessoa + ", sobrenomePessoa=" + sobrenomePessoa
				+ ", salaEtapa1=" + salaEtapa1 + ", salaEtapa2=" + salaEtapa2 + ", cafeEtapa1=" + cafeEtapa1
				+ ", cafeEtapa2=" + cafeEtapa2 + "]";
	}

}
