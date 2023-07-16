package com.hbdev.carrossb.api;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}")
	public Optional<Carro> getById(@PathVariable Long id){
		return carroService.getCarroById(id);
	}
	
	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> getCarrosByTipo(@PathVariable String tipo){
		return carroService.getCarrosByTipo(tipo);
	}
	
	
	
	
	
}
