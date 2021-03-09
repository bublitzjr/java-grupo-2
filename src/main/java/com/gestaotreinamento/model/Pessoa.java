package com.gestaotreinamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "gen_idpessoa", strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 25)
	private String nomePessoa;

	@Column(nullable = false, length = 25)
	private String sobrenomePessoa;

	private Integer salaEtapa1;
	private Integer salaEtapa2;

	private String cafeEtapa1;
	private String cafeEtapa2;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nomePessoa=" + nomePessoa + ", sobrenomePessoa=" + sobrenomePessoa
				+ ", salaEtapa1=" + salaEtapa1 + ", salaEtapa2=" + salaEtapa2 + ", cafeEtapa1=" + cafeEtapa1
				+ ", cafeEtapa2=" + cafeEtapa2 + "]";
	}

}
