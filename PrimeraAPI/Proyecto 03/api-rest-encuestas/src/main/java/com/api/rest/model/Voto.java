package com.api.rest.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Voto {

	@Id
	@GeneratedValue
	@Column(name="VOTO_ID")
	private long id;
	
	
	@ManyToOne // Muchos Votos pueden pertenecerle a una Opcion
	@JoinColumn(name="OPCION_ID")
	private Opcion opcion;


	public Voto(long id, Opcion opcion) {
		super();
		this.id = id;
		this.opcion = opcion;
	}


	public Voto() {
		super();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Opcion getOpcion() {
		return opcion;
	}


	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}
	
	
	
	
}
