package com.banca.digital.entidades;

import java.util.List;

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
	private String id;
	
	private String nombre;
	
	private String email;
	
	//Un cliente tiene muchas cuentas bancarias
	
	@OneToMany(mappedBy = "cliente")	
	private List<CuentaBancaria> cuentasBancarias;
	
}
