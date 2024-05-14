package com.api.hateoas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;

@Entity
@Table(name= "cuentas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cuenta extends RepresentationModel<Cuenta>{
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 20, nullable=false, unique = true)
	private String numero_cuenta;	
	
	private float monto;

	public Cuenta(Integer id, String numero_cuenta, float monto) {
		super();
		this.id = id;
		this.numero_cuenta = numero_cuenta;
		this.monto = monto;
	}

	public Cuenta() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero_cuenta() {
		return numero_cuenta;
	}

	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	

}
