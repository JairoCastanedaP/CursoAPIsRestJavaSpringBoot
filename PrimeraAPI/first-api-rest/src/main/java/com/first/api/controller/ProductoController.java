package com.first.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.api.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

	
	@Autowired
	private ProductoService productoService;
}
