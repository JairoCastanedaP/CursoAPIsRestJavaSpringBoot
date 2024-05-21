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
	
	private String descripcion;

	public OperacionCuenta() {
		super();
	}

	public OperacionCuenta(Long id, Date fechaOperacion, double monto, TipoOperacion tipoOperacion,
			CuentaBancaria cuentaBancaria) {
		super();
		this.id = id;
		this.fechaOperacion = fechaOperacion;
		this.monto = monto;
		this.tipoOperacion = tipoOperacion;
		this.cuentaBancaria = cuentaBancaria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}
	
	
}
