package com.hbdev.carrossb.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //-> Tranforma esse obj em um web service rest
@RequestMapping("/")
public class IndexController {

	@GetMapping
	private String get() {
		return "API dos Carros";
	}
	
	
	
	
	
}
