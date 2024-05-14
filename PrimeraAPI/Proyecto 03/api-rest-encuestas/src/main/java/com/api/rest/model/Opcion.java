package com.api.rest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Data
//@Table(name= "cuentas")
@AllArgsConstructor
@NoArgsConstructor

public class Opcion {

	@Id
	@GeneratedValue
	@Column(name="OPCION_ID")
	private Long id;
	
	private String value;

	public Opcion(Long id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	
	

	public Opcion() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
