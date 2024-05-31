package com.banca.digital.dto;

import lombok.Data;

@Data
public class CreditoDTO {

	private String cuentaId;
	private double monto;
	private String descripcion;
	public CreditoDTO() {
		super();
	}
	public String getCuentaId() {
		return cuentaId;
	}
	public void setCuentaId(String cuentaId) {
		this.cuentaId = cuentaId;
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
