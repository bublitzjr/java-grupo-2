package com.gestaotreinamento.gestaotreinamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.gestaotreinamento.model")
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.gestaotreinamento.repository"})
@EnableTransactionManagement
public class GestaotreinamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaotreinamentoApplication.class, args);
	}

}