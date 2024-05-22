package com.banca.digital.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String email;
	
	//Un cliente tiene muchas cuentas bancarias
	
	@OneToMany(mappedBy = "cliente")	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<CuentaBancaria> cuentasBancarias;

	public Cliente() {
		super();
	}
	



	public Cliente(Long id, String nombre, String email, List<CuentaBancaria> cuentasBancarias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.cuentasBancarias = cuentasBancarias;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<CuentaBancaria> getCuentasBancarias() {
		return cuentasBancarias;
	}

	public void setCuentasBancarias(List<CuentaBancaria> cuentasBancarias) {
		this.cuentasBancarias = cuentasBancarias;
	}
	
	
}
