package com.banca.digital.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banca.digital.entidades.CuentaBancaria;
import com.banca.digital.entidades.OperacionCuenta;

@Repository
public interface CuentaBancariaRespository extends JpaRepository<CuentaBancaria, String>{

	
}
