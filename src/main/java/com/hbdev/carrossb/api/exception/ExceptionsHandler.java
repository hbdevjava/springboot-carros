package com.hbdev.carrossb.api.exception;



import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;




/**
 * A anotação @ControllerAdvice é uma especialização da anotação @Component de
 * Spring Framework que permite manipular exceções em todo o aplicativo em um
 * componente global. Você pode usar essa anotação para lidar com exceções
 * lançadas em qualquer lugar da sua aplicação, não só pelo controller. É como
 * se fosse uma barreira que intercepta todas as exceções que acontecem dentro
 * de um método/classe anotado com @RequestMapping e permite que você faça algum
 * tratamento antes ou depois da exceção ser lançada .
 */
@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity errorNotFound(Exception e) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity errorBadRequest(Exception e) {
		return ResponseEntity.badRequest().build();
	}
	


}
