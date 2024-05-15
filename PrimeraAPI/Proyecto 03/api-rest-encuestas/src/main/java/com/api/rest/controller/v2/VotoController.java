package com.api.rest.controller.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.rest.model.Voto;
import com.api.rest.repositories.VotoRepository;

@RestController("VotoControllerV2")
@RequestMapping("/v2")
public class VotoController {

	@Autowired
	private VotoRepository votoRepository;
	
	
	@PostMapping("/encuestas/{encuestaId}/votos")
	public ResponseEntity<?> crearVoto(@PathVariable Long encuestaId, @RequestBody Voto voto){
		voto= votoRepository.save(voto);
		
		HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(voto.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders,HttpStatus.CREATED);
	}
	
	@GetMapping("/encuestas/{encuestaId}/votos")
	public Iterable<Voto> listarTodosLosVotos(@PathVariable Long encuestaId){
		return votoRepository.findBysEncuesta(encuestaId);		
	}
}
