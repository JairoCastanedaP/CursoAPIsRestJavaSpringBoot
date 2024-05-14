package com.api.hateoas.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.hateoas.exception.CuentaNotFoundException;
import com.api.hateoas.model.Cuenta;
import com.api.hateoas.repository.CuentaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;
	
	public List<Cuenta> listAll(){
		return cuentaRepository.findAll();
	}
	
	public Cuenta get(Integer id){
		return cuentaRepository.findById(id).get();
	}
	
	public Cuenta save(Cuenta cuenta) {
		return cuentaRepository.save(cuenta);
	}
	
	public void delete(Integer id) throws CuentaNotFoundException  {
		cuentaRepository.deleteById(id);
		if(cuentaRepository.existsById(id)) {
			throw new CuentaNotFoundException("Cuenta no encontrada con el ID: "+id);
		}
	}
	public Cuenta despositar(float monto, Integer id) {
		cuentaRepository.actualizarMonto(monto, id);
		return cuentaRepository.findById(id).get();
	}
	
	public Cuenta retirar(float monto, Integer id) {
		cuentaRepository.actualizarMonto(-monto, id);
		return cuentaRepository.findById(id).get();
	}
}
