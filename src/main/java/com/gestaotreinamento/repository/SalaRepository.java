package com.gestaotreinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gestaotreinamento.model.Sala;

@Repository
@Transactional
public interface SalaRepository extends CrudRepository<Sala, Integer> {

	@Query("select count(id) from Sala s")
	Integer findTotalSalas();
	
	@Query("select s.id from Sala s")
	List<Integer> findAllId();
	
	@Query("select p from Sala p where nomeSala like %?1%")
	List<Sala> findSalaPorNome(String nomeSala);
}
