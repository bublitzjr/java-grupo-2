package com.gestaotreinamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Pessoa - Parte da camada de Model Construtor da "Pessoa": cria um objeto que
 * recebe o nome e sobrenome da pessoa, gera um id e recebe as salas da etapa 1
 * e 2 e cafés da etapa 1 e 2 a partir da distribuição gerada pelo sistema
 * mantidas (CRUD) no banco de dados.
 * 
 * @param id:              Chave primária de identificação da sala.
 * @param nomePessoa:      String com o nome da pessoa.
 * @param sobrenomePessoa: String com o sobrenome da pessoa.
 * @param salaEtapa1:      Recebe a String com o nome da sala para a 1ª etapa do
 *                         evento. @see
 *                         com.gestaotreinamento.controller.distribuirPessoasPorSala()
 * @param salaEtapa2:      Recebe a String com o nome da sala para a 2ª etapa do
 *                         evento. @see
 *                         com.gestaotreinamento.controller.distribuirPessoasPorSala()
 * @param cafeEtapa1:      Recebe a String com o nome do café para a 1ª etapa do
 *                         evento. @see
 *                         com.gestaotreinamento.controller.distribuirPessoasPorSala()
 * @param cafeEtapa2:      Recebe a String com o nome do café para a 2ª etapa do
 *                         evento. @see
 *                         com.gestaotreinamento.controller.distribuirPessoasPorSala()
 * 
 * @return Retorna para a camada do controller os atributos do objeto
 *         concatenados em uma String (id, nomePessoa, sobrenomePessoa,
 *         salaEtapa1, salaEtapa2, cafeEtapa1, cafeEtapa2).
 * 
 *         ANNOTATIONS utilizadas no código: "@Entity": Especifica que a classe
 *         é uma entidade. @see &&&PUXAR REFERÊNICA&&& "@Id": Especifica uma
 *         chave primária. @see &&&PUXAR REFERÊNICA&&& "@Column": Especifica
 *         condições para a persistência dos dados. @see &&&PUXAR REFERÊNICA&&&
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

	private int espacoCafe;

	private int sala1;

	private int sala2;

	@SuppressWarnings("deprecation")
	@org.hibernate.annotations.ForeignKey(name = "espacoCafe_id")
	@ManyToOne
	private EspacoCafe espacocafe;

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

	public int getEspacoCafe() {
		return espacoCafe;
	}

	public void setEspacoCafe(int espacoCafe) {
		this.espacoCafe = espacoCafe;
	}

	public int getSala1() {
		return sala1;
	}

	public void setSala1(int sala1) {
		this.sala1 = sala1;
	}

	public int getSala2() {
		return sala2;
	}

	public void setSala2(int sala2) {
		this.sala2 = sala2;
	}

	public EspacoCafe getEspacocafe() {
		return espacocafe;
	}

	public void setEspacocafe(EspacoCafe espacocafe) {
		this.espacocafe = espacocafe;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nomePessoa=" + nomePessoa + ", sobrenomePessoa=" + sobrenomePessoa
				+ ", espacoCafe=" + espacoCafe + ", sala1=" + sala1 + ", sala2=" + sala2 + ", espacocafe=" + espacocafe
				+ "]";
	}

}
