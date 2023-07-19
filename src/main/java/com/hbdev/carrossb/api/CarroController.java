package com.hbdev.carrossb.api;



import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hbdev.carrossb.domain.Carro;
import com.hbdev.carrossb.domain.CarroDTO;
import com.hbdev.carrossb.domain.CarroService;

@RestController //-> Tranforma esse obj em um web service rest
@RequestMapping("/api/v1/carros")
public class CarroController {
	
	@Autowired
	CarroService carroService;

	
	@GetMapping()
	private ResponseEntity get() {
		return ResponseEntity.ok(carroService.getCarros());//se passar um obj pra dentro do Ok nao precisa o .build()
		//return new ResponseEntity<>(carroService.getCarros(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroDTO> getById(@PathVariable Long id){
		Optional<CarroDTO> carro = carroService.getById(id);
		
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
		List<CarroDTO> carros = carroService.getCarrosByTipo(tipo);
		return carros.isEmpty() ? 
				ResponseEntity.noContent().build() :
					ResponseEntity.ok(carros);//se passar um obj pra dentro do Ok nao precisa o .build()
	}
		
	
	
		
	
	@PostMapping()
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity post(@RequestBody Carro carro) {//-> @RequestBody sem ele resultado da NULL
		try {
			CarroDTO c = carroService.insert(carro);
			
			URI location = getURI(c.getId());
			return ResponseEntity.created(null).build();
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
			
		}
	}
	
	
	private URI getURI(Long id) {
		return  ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(id).toUri();
	}
	
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity putCarro(@PathVariable Long id,  @RequestBody Carro carro) {//-> @RequestBody sem ele resultado da NULL
		carro.setId(id);
		CarroDTO c = carroService.upDateCarro(carro, id);
		return c != null ?
				ResponseEntity.ok(c) :
					ResponseEntity.notFound().build();
		
		
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable Long id) {
		carroService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
}
