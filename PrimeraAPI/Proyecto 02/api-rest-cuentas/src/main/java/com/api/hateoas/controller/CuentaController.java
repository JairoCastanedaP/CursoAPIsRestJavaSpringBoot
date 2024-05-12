package com.api.hateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.hateoas.model.Cuenta;
import com.api.hateoas.service.CuentaService;
import java.util.*;

@RestController
@RequestMapping("/api/cuentas")

public class CuentaController {
	
	@Autowired
	private CuentaService cuentaService;
	
	@GetMapping
	public ResponseEntity<List<Cuenta>> listarCuentas(){
		List<Cuenta> cuentas = cuentaService.listAll();
		if(cuentas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return new ResponseEntity<>(cuentas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cuenta> listarCuentas(@PathVariable Integer id){
		try {
			Cuenta cuenta= cuentaService.get(id);
			return new ResponseEntity<>(cuenta, HttpStatus.OK);
			
		}catch(Exception ex) {
			return ResponseEntity.notFound().build();
					}
	}
	@PostMapping
	public ResponseEntity<Cuenta> guardarCuenta(@RequestBody Cuenta cuenta){
		Cuenta cuentaBBDD = cuentaService.save(cuenta);
		return new ResponseEntity<>(cuenta, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Cuenta> editarCuenta(@RequestBody Cuenta cuenta){
		Cuenta cuentaBBDD = cuentaService.save(cuenta);
		return new ResponseEntity<>(cuenta, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCuenta(@PathVariable Integer id){
		try {
			cuentaService.delete(id);
			return ResponseEntity.noContent().build();
		}catch(Exception exception) {
			return ResponseEntity.notFound().build();
		}
	}
}
