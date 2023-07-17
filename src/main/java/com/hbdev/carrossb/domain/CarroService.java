package com.hbdev.carrossb.domain;


import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {

	@Autowired
	CarroRepository carroRepository;

	public List<CarroDTO> getCarros() {
		return carroRepository.findAll().stream().map(c -> CarroDTO.create(c))
				.collect(Collectors.toList());
	}

	public Optional<CarroDTO> getCarroById(Long id) {
		return carroRepository.findById(id).map(c -> CarroDTO.create(c));
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {
		return carroRepository.findByTipo(tipo).stream().map(c -> CarroDTO.create(c))
				.collect(Collectors.toList());
	}

//	public List<Carro> getCarfake() {
//		List<Carro> carros = new ArrayList<>();
//		// SOBRE O L: tem que colocar o "L" caso não java entendo como inteiro
//		carros.add(new Carro(1L, "Argo Cinza", null));
//		carros.add(new Carro(2L, "Argo Vermelho", null));
//		carros.add(new Carro(3L, "Argo Preto", null));
//
//		return carros;
//	}

	public Carro savaCarro(Carro carro) {
		return carroRepository.save(carro);
	}

	public CarroDTO upDateCarro(Long id, Carro carro) {
		Assert.notNull(id, "Nao é Possivel Atualizar o registro");

		Optional<CarroDTO> optional = getCarroById(id);
		if (optional.isPresent()) {
			CarroDTO novoCarro = optional.get();
			novoCarro.setName(carro.getName());
			novoCarro.setTipo(carro.getTipo());
			System.out.println("Carro id: " + novoCarro.getId());

			carroRepository.save(novoCarro);
			return novoCarro;
		} else {
			throw new RuntimeException("Nao é Possivel Atualizar o registro");
		}

	}

	public void deleteById(Long id) {
		Optional<CarroDTO> optional = getCarroById(id);
		if (optional.isPresent()) {
			carroRepository.deleteById(id);
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
