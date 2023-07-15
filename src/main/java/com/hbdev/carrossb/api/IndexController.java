package com.hbdev.carrossb.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //-> Tranforma esse obj em um web service rest
@RequestMapping("/")
public class IndexController {

	@GetMapping
	private String get() {
		return "GET SPRING BOOT!!!";
	}
	
//	@GetMapping("/login")//nao é boa pratica se passar senha no GET (sim POST)
//	private String login(@PathVariable("login") String login,@PathVariable("senha") String senha) {
//		return "Login:" + login + " Senha:" + senha;
//	}
	
	@GetMapping("/login/{login}/{senha}")//nao é boa pratica se passar senha no GET (sim POST)
	private String login(@PathVariable("login") String login,@PathVariable("senha") String senha) {
		return "Login:" + login + " Senha:" + senha;
	}
	
	@GetMapping("/carros/{id}")
	public String getCarById(@PathVariable  Long id) {
		return "Carro " + id;
	}
	
	@GetMapping("/carros/tipo/{tipo}")
	public String getCarByTipo(@PathVariable  String  tipo) {
		return "Modelo do Carro: " + tipo;
	}
		
		
		
		
		
		
		
		
		
		
		
		
	
	
}
