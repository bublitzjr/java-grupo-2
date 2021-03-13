package com.gestaotreinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestaotreinamento.model.Pessoa;

/**
 * PessoaRepository - Retorna os objetos mantidos no banco de dados a partir dos
 * filtros passados.
 * 
 * ANNOTATIONS utilizadas no código
 * 
 * @Repository Especifica que a interface é um repositório e permite a
 *             integração de subinterfaces
 * @Transactional Oficializa a transação de dados para o banco de dados
 * @Query Declara consultas do localizador diretamente nos métodos do
 *        repositório.
 * 
 * @author Adriano Warmling
 * @author Jefferson Bublitz
 * @author Lorran dos Santos
 * @author Nádia Hansen
 * @author Yuri Piffer
 *
 */

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

	@Query("select count(p.id) from Pessoa p")
	Integer findTotalPessoas();

	@Query("select p from Pessoa p where p.nomePessoa like %?1%")
	List<Pessoa> findPessoaPeloNome(String nome);
	
	@Query("select p from Pessoa p where p.salaEtapa1 = :campoFiltro")
	List<Pessoa> findSalaEpata1(Integer campoFiltro);
	
	@Query("select p from Pessoa p where p.salaEtapa2 = :campoFiltro")
	List<Pessoa> findSalaEpata2(Integer campoFiltro);
	
	@Query("select p from Pessoa p where p.localCafe = :campoFiltro")
	List<Pessoa> findEspacoCafe(Integer campoFiltro);

	@Query("select p from Pessoa p order by p.id")
	List<Pessoa> findAllOrderById();
	
}