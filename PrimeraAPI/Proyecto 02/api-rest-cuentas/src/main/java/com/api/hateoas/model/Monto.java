package com.api.hateoas.model;

import lombok.Data;

@Data
public class Monto {
	
	private float monto;

	public Monto() {
		
	}
	public Monto(float monto) {
		super();
		this.monto = monto;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	

}
