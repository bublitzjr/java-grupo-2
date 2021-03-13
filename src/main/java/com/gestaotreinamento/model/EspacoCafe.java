package com.gestaotreinamento.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Classe EspacoCafe da camada MODEL: Cria um objeto que recebe o número de
 * identificação do espaço e como o café é nomeado. TEM? A? QUESTÃO? NO
 * ONE_TO_MANY, O QUE COMENTAR? ... e mantém no banco de dados (tabela
 * 'espaco_cafe').
 * 
 * @param idEspaco variável int de identificação do Espaço de Café.
 * @param nomeDoLocal String com o nome do Espaço de Café.
 * @param pessoas Lista de pessoas no Espaço de Café.
 * 
 * @return Retorna para a camada do controller os atributos do objeto
 *         concatenados em uma String (idEspaco, nomeDoLocal, pessoas).
 * 
 *         ANNOTATIONS utilizadas no código:
 * @Entity Especifica que a classe é uma entidade.
 * @Id Especifica uma chave primária.
 * @Column Especifica condições para a persistência dos dados.
 * @OneToMany ?????????????????????????.
 * 
 * @author Adriano Warmling
 * @author Jefferson Bublitz
 * @author Lorran dos Santos
 * @author Nádia Hansen
 * @author Yuri Piffer
 */

@Entity
public class EspacoCafe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int idEspaco;

	@Column(length = 20, nullable = false)
	private String nomeDoLocal;

	@OneToMany(mappedBy = "espacocafe")
	private List<Pessoa> pessoas;

	//GETTERS & SETTERS
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

	//RETURN
	@Override
	public String toString() {
		return "EspacoCafe [idEspaco=" + idEspaco + ", nomeDoLocal=" + nomeDoLocal + ", pessoas=" + pessoas + "]";
	}
}
