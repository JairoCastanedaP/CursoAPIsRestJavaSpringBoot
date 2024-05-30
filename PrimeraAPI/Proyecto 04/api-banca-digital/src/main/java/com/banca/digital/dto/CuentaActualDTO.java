package com.banca.digital.dto;

import java.util.Date;

import com.banca.digital.enums.EstadoCuenta;

import lombok.Data;

@Data
public class CuentaActualDTO extends CuentaBancariaDTO {

	private String id;
	private double balance;
	private Date fechaCreacion;
	private EstadoCuenta estadoCuenta;
	private ClienteDTO clienteDTO;
	private double tasaInteres;
	public CuentaActualDTO() {
		super();
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
	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}
	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}
	public double getTasaInteres() {
		return tasaInteres;
	}
	public void setTasaInteres(double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
	
	
	
}
