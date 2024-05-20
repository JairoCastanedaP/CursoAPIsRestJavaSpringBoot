package com.banca.digital.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banca.digital.entidades.OperacionCuenta;

@Repository
public interface OperacionCuentaRespository extends JpaRepository<OperacionCuenta, Long>{

}
