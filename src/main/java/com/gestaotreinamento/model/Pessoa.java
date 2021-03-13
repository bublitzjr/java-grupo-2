package com.gestaotreinamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Classe Pessoa da camada MODEL: Cria um objeto que recebe o nome e sobrenome
 * da pessoa, gera um id e recebe as salas da etapa 1 e 2 e cafés da etapa 1 e 2
 * a partir da distribuição gerada pelo sistema e mantém no banco de dados
 * (tabela 'pessoa').
 * 
 * @param id              Chave primária de identificação da sala.
 * @param nomePessoa      String com o nome da pessoa.
 * @param sobrenomePessoa String com o sobrenome da pessoa.
 * @param salaEtapa1      Recebe a String com o nome da sala para a 1ª etapa do
 *                        evento.
 *                        com.gestaotreinamento.controller.distribuirPessoasPorSala()
 * @param salaEtapa2      Recebe a String com o nome da sala para a 2ª etapa do
 *                        evento.
 *                        com.gestaotreinamento.controller.distribuirPessoasPorSala()
 * @param cafeEtapa1      Recebe a String com o nome do café para a 1ª etapa do
 *                        evento.
 *                        com.gestaotreinamento.controller.distribuirPessoasPorSala()
 * @param cafeEtapa2      Recebe a String com o nome do café para a 2ª etapa do
 *                        evento.
 *                        com.gestaotreinamento.controller.distribuirPessoasPorSala()
 * 
 * @return Retorna para a camada do controller os atributos do objeto
 *         concatenados em uma String (id, nomePessoa, sobrenomePessoa,
 *         salaEtapa1, salaEtapa2, cafeEtapa1, cafeEtapa2).
 * 
 *         ANNOTATIONS utilizadas no código:
 * 
 * @Entity Especifica que a classe é uma entidade.
 * @Id Especifica uma chave primária.
 * @Column Especifica condições para a persistência dos dados.
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

	private int localCafe;

	private int salaEtapa1;

	private int salaEtapa2;

	@SuppressWarnings("deprecation")
	@org.hibernate.annotations.ForeignKey(name = "espacoCafe_id")
	@ManyToOne
	private EspacoCafe espacocafe;

	@SuppressWarnings("deprecation")
	@org.hibernate.annotations.ForeignKey(name = "sala_id")
	@ManyToOne
	private Sala sala;

	//GETTERS & SETTERS
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

	public int getLocalCafe() {
		return localCafe;
	}

	public void setLocalCafe(int localCafe) {
		this.localCafe = localCafe;
	}

	public int getSalaEtapa1() {
		return salaEtapa1;
	}

	public void setSalaEtapa1(int salaEtapa1) {
		this.salaEtapa1 = salaEtapa1;
	}

	public int getSalaEtapa2() {
		return salaEtapa2;
	}

	public void setSalaEtapa2(int salaEtapa2) {
		this.salaEtapa2 = salaEtapa2;
	}

	public EspacoCafe getEspacocafe() {
		return espacocafe;
	}

	public void setEspacocafe(EspacoCafe espacocafe) {
		this.espacocafe = espacocafe;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	//RETURN 
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nomePessoa=" + nomePessoa + ", sobrenomePessoa=" + sobrenomePessoa
				+ ", localCafe=" + localCafe + ", salaEtapa1=" + salaEtapa1 + ", salaEtapa2=" + salaEtapa2
				+ ", espacocafe=" + espacocafe + ", sala=" + sala + "]";
	}

}
