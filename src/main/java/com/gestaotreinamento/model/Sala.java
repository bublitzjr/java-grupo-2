package com.gestaotreinamento.model;

import java.io.Serializable;

import javax.persistence.*;


/**
* Sala - Parte da camada de Model 
* Construtor da "Sala": Cria um objeto que recebe o
* nome da sala, lotação, gera um id e persiste no
* banco de dados (tabela XXXX). 
* 
* @param id: Chave primária de identificação da sala. 
* @param nomeSala: String com o nome da sala.
* @param lotacao: Número de pessaas que a sala comporta.
* 
* @return Retorna para a camada controller os atributos do objeto concatenados 
* em uma String (id,nomeSala, lotacao).
* 
* ANNOTATIONS utilizadas no código:
* "@Entity": Especifica que a classe é uma entidade. @see &&&PUXAR REFERÊNICA&&&
* "@Id": Especifica uma chave primária. @see &&&PUXAR REFERÊNICA&&&
* "@Column": Especifica condições para a persistência dos dados. @see &&&PUXAR REFERÊNICA&&&
* 
* @author Adriano Warmling 
* @author Jefferson Bublitz
* @author Lorran dos Santos
* @author Nádia Hansen
* @author Yuri Piffer
*/


@Entity
public class Sala implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column(nullable = false, length = 25)
	private String nomeSala;

	private int lotacao;

	
	//GETTERS AND SETTERS
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

	
	//RETURN
	@Override
	public String toString() {
		return "Sala [id=" + id + ", nomeSala=" + nomeSala + ", lotacao=" + lotacao + "]";
	}

}
