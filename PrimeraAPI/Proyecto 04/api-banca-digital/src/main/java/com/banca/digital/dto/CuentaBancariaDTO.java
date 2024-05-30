package com.banca.digital.dto;

import lombok.Data;

@Data
public class CuentaBancariaDTO {

	private String tipo;

	public CuentaBancariaDTO() {
		super();
	}

	public CuentaBancariaDTO(String tipo) {
		super();
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
