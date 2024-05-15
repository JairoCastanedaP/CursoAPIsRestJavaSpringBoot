package com.api.rest.controller.v2;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.rest.exception.ResourceNotFoundException;
import com.api.rest.model.Encuesta;
import com.api.rest.repositories.EncuestaRepository;

import jakarta.validation.Valid;

@RestController("EncuestaControllerV2")
@RequestMapping("/v2")
public class EncuestaController {
	
	@Autowired
	private EncuestaRepository encuestRespository;
	
	@GetMapping("/encuestas")
	public ResponseEntity<Iterable<Encuesta>> listarTodasLasEncuestas(Pageable pageable){
		Page<Encuesta> encuestas = encuestRespository.findAll(pageable);
		return new ResponseEntity<>(encuestas,HttpStatus.OK);
	}
	
	@PostMapping("/encuestas")
	public ResponseEntity<?> crearEncuesta(@Valid @RequestBody Encuesta encuesta){
		
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
		
		verifyEncuesta(encuestaId);
		Optional<Encuesta> encuesta = encuestRespository.findById(encuestaId);
		if(encuesta.isPresent()) {
			return new ResponseEntity<>(encuesta, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(encuesta,HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/encuestas/{encuestaId}")
	public ResponseEntity<?> actualizarEncuesta(@Valid @RequestBody Encuesta encuesta, @PathVariable Long encuestaId){
		verifyEncuesta(encuestaId);
		
		encuesta.setId(encuestaId);
		encuestRespository.save(encuesta);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}	
	
	@DeleteMapping("/encuestas/{encuestaId}")
	public ResponseEntity<?> eliminarEncuesta(@PathVariable Long encuestaId){
		verifyEncuesta(encuestaId);
		try {
			encuestRespository.deleteById(encuestaId);
			return ResponseEntity.noContent().build();
		}catch(Exception exception) {
			return ResponseEntity.notFound().build();
		}
	}
	
	protected void verifyEncuesta(Long encuestaId) {
		Optional<Encuesta> encuesta = encuestRespository.findById(encuestaId);
		
		if(!encuesta.isPresent()) {
			throw new ResourceNotFoundException("Encuesta con el ID: "+ encuestaId+" no econtrada");
		}
	}
}
