package com.first.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class HelloController {

	
	@GetMapping("/hello")
	public String saludar() {
		return "hola mundo con Rest en Spring Boot";
	}
	
	@GetMapping
	public String calcularMayor() {
		int a=10;
		int b=20;
		
		if(a>b) {
			return "El mayor es : "+a;
		}
		else if(b>a) {
			return "El mayor es: "+b;
		}
		else {
			return "son iguales";
		}
	}
}
