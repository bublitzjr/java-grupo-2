package com.gestaotreinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gestaotreinamento.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
	
	@Query("select count(p.id) from Pessoa p")
	Integer findTotalPessoasSala();
	
	@Query("select p from Pessoa p where p.nomePessoa like %?1%")
	List<Pessoa> findPessoaPeloNome(String nome);
	
	@Query("select p from Pessoa p order by p.id")
	List<Pessoa> findAllOrderById();
	
}