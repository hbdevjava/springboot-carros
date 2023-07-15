package com.hbdev.carrossb.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class CarroService {
	
	public List<Carro> getCar(){
		List<Carro> carros = new ArrayList<>();
		//SOBRE O L: tem que colocar o "L" caso não java entendo como inteiro
		carros.add(new Carro(1L, "Argo Cinza"));
		carros.add(new Carro(2L, "Argo Vermelho"));
		carros.add(new Carro(3L, "Argo Preto"));
		
		
		return carros;
	}

}
