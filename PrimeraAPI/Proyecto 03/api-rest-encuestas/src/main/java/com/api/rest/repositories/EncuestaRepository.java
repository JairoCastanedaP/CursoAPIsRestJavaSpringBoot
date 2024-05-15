package com.api.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.model.Encuesta;

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta, Long>{
	
	

}
