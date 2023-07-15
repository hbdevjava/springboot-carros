package com.hbdev.carrossb.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //-> Tranforma esse obj em um web service rest
@RequestMapping("/")
public class IndexController {

	@GetMapping
	private String get() {
		return "GET SPRING BOOT!!!";
	}
	
	@PostMapping
	private String post() {
		return "POST SPRING BOOT!!!";
	}	
	@PutMapping
	private String put() {
		return "PUT SPRING BOOT!!!";
	}	
	@DeleteMapping
	private String delete() {
		return "DELETE SPRING BOOT!!!";
	}	
}
