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

    public Optional<CarroDTO> getById(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        return carroRepository.findById(id).map(c -> CarroDTO.create(c));
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return carroRepository.findByTipo(tipo).stream().map(c -> CarroDTO.create(c))
        		.collect(Collectors.toList());
    }

    public CarroDTO insert(Carro carro) {
    	Assert.isNull(carro.getId(), "Nao é Possivel Inserir o registro");
    	//NAO PODE SER NULL -> Assert.noNull
       return CarroDTO.create(carroRepository.save(carro)); // AQUI ELE CONVERTE O CARRO SALVO PARA CARRO DTO                  
    }

    public CarroDTO upDateCarro(Carro carro, Long id) {
		Assert.notNull(id, "Nao é Possivel Atualizar o registro");

		//PROCURA O CARRO NO BD
		Optional<Carro> optional = carroRepository.findById(id);
		if (optional.isPresent()) {
			Carro novoCarro = optional.get();
			
			//COPIA OS ATRIBUTOS DO VEICULO
			novoCarro.setName(carro.getName());
			novoCarro.setTipo(carro.getTipo());
			System.out.println("Carro id: " + novoCarro.getId());
			
			//ATUALIZA O CARRO
			carroRepository.save(novoCarro);
			
			return CarroDTO.create(novoCarro);
		}else {
			return null;
			//throw new RuntimeException("Nao é Possivel Atualizar o registro");
		}


	}

    public boolean  delete(Long id) {
		if (getById(id).isPresent()) {
    		carroRepository.deleteById(id);
    		return true;
		}
       return false;
}
	
    
    
    
    
    
    
	
	
	
	
	
    
	
    
	
	
	
	
	
	
	
	
	
	
	
	
	
}
