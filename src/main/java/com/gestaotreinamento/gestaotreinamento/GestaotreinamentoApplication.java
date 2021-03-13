package com.gestaotreinamento.gestaotreinamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Método Main - Sistema para manter dados de um evento.
 * 
 * ANNOTATIONS utilizadas no código
 * 
 * @SpringBootApplication Engloba três annotations
 *                        -> @Configuration, @EnableAutoConfiguration
 *                        e @ComponentScan
 * @EntityScan Configura os pacotes base usados pela auto-configuração quando
 *             escaneando por entidades de classe
 * @CompenentScan Configura diretivas de varredura de componente para uso com
 *                classes @Configuration. Fornece suporte paralelo ao elemento
 *                Spring XML <context: component-scan>
 * @EnableJpaRepositories Habilita o uso do JPA, permitindo assim a utilização
 *                        do Crud
 * @EnableTransactionManagment Habilita o recurso de gerenciamento de transações
 *                             baseado em anotações do Spring
 * 
 * @author Adriano Warmling
 * @author Jefferson Bublitz
 * @author Lorran dos Santos
 * @author Nádia Hansen
 * @author Yuri Piffer
 *
 */

@SpringBootApplication
@EntityScan(basePackages = "com.gestaotreinamento.model")
@ComponentScan(basePackages = { "com.*" })
@EnableJpaRepositories(basePackages = { "com.gestaotreinamento.repository" })
@EnableTransactionManagement

public class GestaotreinamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaotreinamentoApplication.class, args);
	}

}