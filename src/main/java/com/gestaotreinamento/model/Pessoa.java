package com.gestaotreinamento.model;

import java.io.Serializable;

import javax.persistence.*;

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

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nomePessoa=" + nomePessoa + ", sobrenomePessoa=" + sobrenomePessoa
				+ ", salaEtapa1=" + salaEtapa1 + ", salaEtapa2=" + salaEtapa2 + ", cafeEtapa1=" + cafeEtapa1
				+ ", cafeEtapa2=" + cafeEtapa2 + "]";
	}

}
