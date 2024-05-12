package com.api.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.hateoas.model.Cuenta;

@Repository

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

}
