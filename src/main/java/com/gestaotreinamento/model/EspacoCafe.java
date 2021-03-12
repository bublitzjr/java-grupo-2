package com.gestaotreinamento.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class EspacoCafe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int idEspaco;

	@Column(length = 20, nullable = false)
	private String nomeDoLocal;

	@OneToMany(mappedBy = "espacocafe")
	private List<Pessoa> pessoas;

	public int getIdEspaco() {
		return idEspaco;
	}

	public void setIdEspaco(int idEspaco) {
		this.idEspaco = idEspaco;
	}

	public String getNomeDoLocal() {
		return nomeDoLocal;
	}

	public void setNomeDoLocal(String nomeDoLocal) {
		this.nomeDoLocal = nomeDoLocal;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@Override
	public String toString() {
		return "EspacoCafe [idEspaco=" + idEspaco + ", nomeDoLocal=" + nomeDoLocal + ", pessoas=" + pessoas + "]";
	}

	

}
