package com.banca.digital.entidades;

import java.util.Date;

import com.banca.digital.enums.TipoOperacion;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor



public class OperacionCuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date fechaOperacion;
	
	private double monto;
	
	@Enumerated(EnumType.STRING)
	private TipoOperacion tipoOperacion;
	
	@ManyToOne
	private CuentaBancaria cuentaBancaria;
}
