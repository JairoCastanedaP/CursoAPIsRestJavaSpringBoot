package com.banca.digital.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banca.digital.entidades.OperacionCuenta;

@Repository
public interface OperacionCuentaRepository extends JpaRepository<OperacionCuenta,String> {

	
    List<OperacionCuenta> findByCuentaBancariaId(String cuentaId);

    Page<OperacionCuenta> findByCuentaBancariaId(String cuentaId, Pageable pageable);

    Page<OperacionCuenta> findByCuentaBancariaIdOrderByFechaOperacionDesc(String cuentaId, Pageable pageable);
}

