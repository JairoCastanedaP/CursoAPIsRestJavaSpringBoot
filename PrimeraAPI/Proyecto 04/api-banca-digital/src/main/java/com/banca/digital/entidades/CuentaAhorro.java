package com.banca.digital.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@DiscriminatorValue("SA")
@NoArgsConstructor
@AllArgsConstructor

public class CuentaAhorro extends CuentaBancaria{
	
	private double tasaInteres;

	public CuentaAhorro() {
		super();
	}

	public CuentaAhorro(double tasaInteres) {
		super();
		this.tasaInteres = tasaInteres;
	}

	public double getTasaInteres() {
		return tasaInteres;
	}

	public void setTasaInteres(double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
	

}
