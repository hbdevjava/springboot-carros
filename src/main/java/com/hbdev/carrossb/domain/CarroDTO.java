package com.hbdev.carrossb.domain;

import org.modelmapper.ModelMapper;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CarroDTO {
	
	private Long id;
	
	@Column(name = "nome")
	private String name;
	
	private String tipo;
	
	
	public static CarroDTO create(Carro carro) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(carro, CarroDTO.class);
	}
}
