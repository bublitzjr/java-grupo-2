package com.gestaotreinamento.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Sala implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(nullable = false, length = 25)
	private String nomeSala;

	private Byte lotacao;

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

	public Byte getLotacao() {
		return lotacao;
	}

	public void setLotacao(Byte lotacao) {
		this.lotacao = lotacao;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", nomeSala=" + nomeSala + ", lotacao=" + lotacao + "]";
	}

}
