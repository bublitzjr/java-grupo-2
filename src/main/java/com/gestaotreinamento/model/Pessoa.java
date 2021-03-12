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

	@SuppressWarnings("deprecation")
	@org.hibernate.annotations.ForeignKey(name = "sala_id")
	@ManyToOne
	private Sala sala;

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

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public EspacoCafe getEspacocafe() {
		return espacocafe;
	}

	public void setEspacocafe(EspacoCafe espacocafe) {
		this.espacocafe = espacocafe;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nomePessoa=" + nomePessoa + ", sobrenomePessoa=" + sobrenomePessoa + ", sala="
				+ sala + ", espacocafe=" + espacocafe + "]";
	}

}
