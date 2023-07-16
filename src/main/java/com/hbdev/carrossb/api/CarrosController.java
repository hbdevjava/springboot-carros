package com.hbdev.carrossb.api;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hbdev.carrossb.domain.Carro;
import com.hbdev.carrossb.domain.CarroService;

@RestController //-> Tranforma esse obj em um web service rest
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
	@Autowired
	CarroService carroService;

	
	@GetMapping()
	private ResponseEntity<Iterable<Carro>> get() {
		return ResponseEntity.ok(carroService.getCarros());//se passar um obj pra dentro do Ok nao precisa o .build()
		//return new ResponseEntity<>(carroService.getCarros(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Carro> getById(@PathVariable Long id){
		Optional<Carro> carro = carroService.getCarroById(id);
		
		return carro.map(ResponseEntity::ok)//-> carro.map(c -> ResponseEntity.ok(c))
				.orElse(ResponseEntity.notFound().build());
		
//		(TERNARIO)
//		return carro.isPresent() ? 
//				ResponseEntity.ok(carro.get()) :
//					ResponseEntity.notFound().build();
		
//		(IF)
//		if(carro.isPresent()){
//			return ResponseEntity.ok(carro.get());
//		}else {
//			return ResponseEntity.notFound().build();
//		}
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarrosByTipo(@PathVariable String tipo){
		List<Carro> carros = carroService.getCarrosByTipo(tipo);
		return carros.isEmpty() ? 
				ResponseEntity.noContent().build() :
					ResponseEntity.ok(carros);
	}
		
		
		
	
	@PostMapping
	public String postCarro(@RequestBody Carro carro) {//-> @RequestBody sem ele resultado da NULL
		Carro c = carroService.savaCarro(carro);
		return "Carro Salvo com sucesso: " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String putCarro(@PathVariable Long id,  @RequestBody Carro carro) {//-> @RequestBody sem ele resultado da NULL
		Carro c = carroService.upDateCarro(id, carro);
		return "Atualizado com sucesso " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		carroService.deleteById(id);
		
	}
	
	
}
