package com.gestaotreinamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
