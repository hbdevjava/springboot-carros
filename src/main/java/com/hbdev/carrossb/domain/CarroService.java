package com.hbdev.carrossb.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {
	
	@Autowired
	CarroRepository carroRepository;
	
	
	public List<CarroDTO> getCarros() {
        List<CarroDTO> list = carroRepository.findAll().stream().map(c -> CarroDTO.create(c))
        		.collect(Collectors.toList());
        return list;
    }

    public Optional<CarroDTO> getCarroById(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        return carroRepository.findById(id).map(c -> CarroDTO.create(c));
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return carroRepository.findByTipo(tipo).stream().map(c -> CarroDTO.create(c))
        		.collect(Collectors.toList());
    }

    public Carro saveCarro(Carro carro) {
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
			return novoCarro;
		}else {
			throw new RuntimeException("Nao é Possivel Atualizar o registro");
		}


	}

    public void  deleteByid(Long id) {
    	Optional<CarroDTO> optional = getCarroById(id);
		if (optional.isPresent()) {
    		carroRepository.deleteById(id);
		}
       
}
	
    
    
    
    
    
    
	
	
	
	
	
    
	
    
	
	
	
	
	
	
	
	
	
	
	
	
	
}
