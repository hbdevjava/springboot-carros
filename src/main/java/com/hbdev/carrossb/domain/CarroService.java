package com.hbdev.carrossb.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {

	@Autowired
	CarroRepository carroRepository;

	public Iterable<Carro> getCarros() {
		return carroRepository.findAll();
	}

	public Optional<Carro> getCarroById(Long id) {
		return carroRepository.findById(id);
	}

	public Iterable<Carro> getCarrosByTipo(String tipo) {
		return carroRepository.findByTipo(tipo);
	}

	public List<Carro> getCarfake() {
		List<Carro> carros = new ArrayList<>();
		// SOBRE O L: tem que colocar o "L" caso não java entendo como inteiro
		carros.add(new Carro(1L, "Argo Cinza"));
		carros.add(new Carro(2L, "Argo Vermelho"));
		carros.add(new Carro(3L, "Argo Preto"));

		return carros;
	}

	public Carro savaCarro(Carro carro) {
		return carroRepository.save(carro);
	}

	public Carro upDateCarro(Long id, Carro carro) {
		Assert.notNull(id, "Nao é Possivel Atualizar o registro");
		
		Optional<Carro> optional = getCarroById(id);
		if(optional.isPresent()) {
			Carro novoCarro = optional.get();
			novoCarro.setName(carro.getName());
			novoCarro.setTipo(carro.getTipo());
			System.out.println("Carro id: " + novoCarro.getId());
			
			carroRepository.save(novoCarro);
			return novoCarro;
		}else {
			throw new RuntimeException("Nao é Possivel Atualizar o registro");
		}
		
		
	}

//	public Carro upDateCarro(Long id, Carro carro) {
//		Assert.notNull(id, "Nao é Possivel Atualizar o registro");
//
//		Optional<Carro> optional = getCarroById(id).map(novoCarro -> {
//			novoCarro.setName(carro.getName());
//			novoCarro.setTipo(carro.getTipo());
//			System.out.println("Carro id: " + novoCarro.getId());
//
//			carroRepository.save(novoCarro);
//			return novoCarro;
//		}).orElseThrow(() -> new RuntimeException("Nao é Possivel Atualizar o registro"));
//
//	}

}
