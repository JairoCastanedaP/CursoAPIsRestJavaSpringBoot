package com.api.rest.controller;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.rest.model.Encuesta;
import com.api.rest.repositories.EncuestaRepository;

@RestController
public class EncuestaController {
	
	@Autowired
	private EncuestaRepository encuestRespository;
	
	@GetMapping("/encuestas")
	public ResponseEntity<Iterable<Encuesta>> listarTodasLasEncuestas(){
		
		return new ResponseEntity<>(encuestRespository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/encuestas")
	public ResponseEntity<?> crearEncuesta(@RequestBody Encuesta encuesta){
		
		encuesta= encuestRespository.save(encuesta);
		
		HttpHeaders httpheaders = new HttpHeaders();
		
		URI newEncuestaUri= ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(encuesta.getId()).toUri();
				

		httpheaders.setLocation(newEncuestaUri);
		
		return new ResponseEntity<>(null,HttpStatus.CREATED);
	}
	
	@GetMapping("/encuestas/{encuestaId}")
	public ResponseEntity<?> obtenerEncuesta(@PathVariable Long encuestaId){
		Optional<Encuesta> encuesta = encuestRespository.findById(encuestaId);
		if(encuesta.isPresent()) {
			return new ResponseEntity<>(encuesta, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(encuesta,HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/encuestas/{encuestaId}")
	public ResponseEntity<?> actualizarEncuesta(@RequestBody Encuesta encuesta, @PathVariable Long encuestaId){
		encuesta.setId(encuestaId);
		encuestRespository.save(encuesta);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}	
	
	@DeleteMapping("/encuestas/{encuestaId}")
	public ResponseEntity<?> eliminarEncuesta(@PathVariable Long encuestaId){
		try {
			encuestRespository.deleteById(encuestaId);
			return ResponseEntity.noContent().build();
		}catch(Exception exception) {
			return ResponseEntity.notFound().build();
		}
	}
}
