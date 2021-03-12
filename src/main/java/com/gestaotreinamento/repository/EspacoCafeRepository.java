package com.gestaotreinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gestaotreinamento.model.EspacoCafe;

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
