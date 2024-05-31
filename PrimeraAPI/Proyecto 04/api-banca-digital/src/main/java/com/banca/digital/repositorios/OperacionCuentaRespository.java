package com.banca.digital.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banca.digital.entidades.OperacionCuenta;

@Repository
public interface OperacionCuentaRespository extends JpaRepository<OperacionCuenta, String>{
	List<OperacionCuenta> findByCuentaBancaria(String cuentaId);
	Page<OperacionCuenta> findByCuentaBancaria(String cuentaId, Pageable pageable);
	Page<OperacionCuenta> finByCuentaBancariaIdOrderByFechaOperacionDesc(String cuentaId, Pageable pageable);
	
}
