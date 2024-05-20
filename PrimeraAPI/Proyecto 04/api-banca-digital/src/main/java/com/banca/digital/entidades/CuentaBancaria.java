package com.banca.digital.entidades;

import java.util.Date;
import java.util.List;

import com.banca.digital.enums.EstadoCuenta;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO", length = 4)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CuentaBancaria {

	@Id
	private String id;
	
	private double balance;
	private Date fechaCreacion;
	
	@Enumerated(EnumType.STRING)
	private EstadoCuenta estadoCuenta;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy = "cuentaBancaria")
	private List<OperacionCuenta> operacionCuenta;
	
	
}
