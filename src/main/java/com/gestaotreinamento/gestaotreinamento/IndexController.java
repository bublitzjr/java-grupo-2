package com.gestaotreinamento.gestaotreinamento;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * O IndexController será a primeira URL a ser encontrada pela aplicação ao subirmos
 * o projeto.
 * 
 *  
 * ANNOTATION utilizada no código:
 * 
 * @Controller indica que uma classe é um controller.
 * @GetMapping Mapeamento da url com método get.
 * 
 * @author Adriano Warmling
 * @author Jefferson Bublitz
 * @author Lorran dos Santos
 * @author Nádia Hansen
 * @author Yuri Piffer
 */

@Controller
public class IndexController {
	
	@GetMapping(value = "/")
	public String index() {
		return "index";
	}
}