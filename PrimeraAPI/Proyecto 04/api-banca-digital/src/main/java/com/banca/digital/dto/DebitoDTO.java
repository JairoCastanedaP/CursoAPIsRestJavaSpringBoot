package com.banca.digital.dto;

import lombok.Data;

@Data
public class DebitoDTO {

	
	private String cuentaID;
	private double monto;
	private String descripcion;
	public DebitoDTO() {
		super();
	}
	public String getCuentaID() {
		return cuentaID;
	}
	public void setCuentaID(String cuentaID) {
		this.cuentaID = cuentaID;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
