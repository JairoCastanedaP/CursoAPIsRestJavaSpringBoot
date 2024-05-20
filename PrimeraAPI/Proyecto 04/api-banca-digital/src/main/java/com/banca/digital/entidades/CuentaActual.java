package com.banca.digital.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@DiscriminatorValue("CA") //Current Accout
@NoArgsConstructor
@AllArgsConstructor

public class CuentaActual extends CuentaBancaria{
	private double sobregiro;

	
	
	public CuentaActual() {
		super();
	}

	public CuentaActual(double sobregiro) {
		super();
		this.sobregiro = sobregiro;
	}

	public double getSobregiro() {
		return sobregiro;
	}

	public void setSobregiro(double sobregiro) {
		this.sobregiro = sobregiro;
	}
	
	

}
