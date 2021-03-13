package com.gestaotreinamento.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Classe Sala da Camada MODEL: Cria um objeto que recebe o nome da sala,
 * lotação, gera um id e mantém no banco de dados (tabela 'sala').
 * 
 * @param id       Chave primária de identificação da sala.
 * @param nomeSala String com o nome da sala.
 * @param lotacao  Número de pessaas que a sala comporta.
 * 
 * @return Retorna para a camada controller os atributos do objeto concatenados
 *         em uma String (id,nomeSala, lotacao).
 * 
 *         ANNOTATIONS utilizadas no código
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
public class Sala implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(nullable = false, length = 25)
	private String nomeSala;

	private int lotacao;

	@OneToMany(mappedBy = "sala")
	private List<Pessoa> pessoas;

	//GETTERS & SETTERS
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
