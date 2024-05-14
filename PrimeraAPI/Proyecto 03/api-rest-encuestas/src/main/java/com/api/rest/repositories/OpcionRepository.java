package com.api.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.model.Encuesta;
import com.api.rest.model.Opcion;

@Repository
public interface OpcionRepository extends CrudRepository<Opcion, Long>{
	
	

}
