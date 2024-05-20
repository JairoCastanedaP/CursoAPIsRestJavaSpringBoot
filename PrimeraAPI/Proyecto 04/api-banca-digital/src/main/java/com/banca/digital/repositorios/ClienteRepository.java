package com.banca.digital.repositorios;

import org.springframework.stereotype.Repository;

import com.banca.digital.entidades.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, String>{

}
