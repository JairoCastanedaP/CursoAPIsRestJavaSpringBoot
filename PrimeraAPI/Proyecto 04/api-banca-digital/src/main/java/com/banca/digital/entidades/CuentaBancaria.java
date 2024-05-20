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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //JOINED
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

	public CuentaBancaria() {
		super();
	}

	public CuentaBancaria(String id, double balance, Date fechaCreacion, EstadoCuenta estadoCuenta, Cliente cliente,
			List<OperacionCuenta> operacionCuenta) {
		super();
		this.id = id;
		this.balance = balance;
		this.fechaCreacion = fechaCreacion;
		this.estadoCuenta = estadoCuenta;
		this.cliente = cliente;
		this.operacionCuenta = operacionCuenta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public EstadoCuenta getEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<OperacionCuenta> getOperacionCuenta() {
		return operacionCuenta;
	}

	public void setOperacionCuenta(List<OperacionCuenta> operacionCuenta) {
		this.operacionCuenta = operacionCuenta;
	}
	
	
	
}
