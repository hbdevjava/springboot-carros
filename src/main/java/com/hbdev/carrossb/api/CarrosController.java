package com.hbdev.carrossb.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbdev.carrossb.domain.Carro;
import com.hbdev.carrossb.domain.CarroService;

@RestController //-> Tranforma esse obj em um web service rest
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	@Autowired
	CarroService carroService;

	@GetMapping()
	private Iterable<Carro> get() {
		return carroService.getCarros();
		
	}
	
	
	
	
	
}
