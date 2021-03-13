package com.gestaotreinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gestaotreinamento.model.EspacoCafe;

/**
 * EspacoRepository - Retorna os objetos mantidos no banco de dados a partir dos
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
public interface EspacoCafeRepository extends CrudRepository<EspacoCafe, Integer> {

	@Query("select count(id) from EspacoCafe e")
	Integer findTotalEspacosCafe();

	@Query("select e.id from EspacoCafe e")
	List<Integer> findAllId();

	@Query("select e from EspacoCafe e where nomeDoLocal like %?1%")
	List<EspacoCafe> findEspacoPorNome(String nomeDoLocal);
}
