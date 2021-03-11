package com.gestaotreinamento.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sala implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(nullable = false, length = 25)
	private String nomeSala;

	private int lotacao;

	@OneToMany(mappedBy = "sala")
	private List<Pessoa> pessoas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeSala() {
		return nomeSala;
	}

	public void setNomeSala(String nomeSala) {
		this.nomeSala = nomeSala;
	}

	public int getLotacao() {
		return lotacao;
	}

	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", nomeSala=" + nomeSala + ", lotacao=" + lotacao + ", pessoas=" + pessoas + "]";
	}

}
