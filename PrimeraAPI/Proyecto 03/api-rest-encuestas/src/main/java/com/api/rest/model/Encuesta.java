package com.api.rest.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Encuesta {

	@Id
	@GeneratedValue
	@Column(name="ENCUESTA_ID")
	private Long id;
	
	@Column(name="pregunta")
	@NotEmpty
	private String pregunta;
	
	@OneToMany(cascade= CascadeType.ALL)// una entidad Encuesta puede tener varias opciones
	@JoinColumn(name="ENCUESTA_ID")
	@OrderBy
	@Size(min=2, max=6)
	private Set<Opcion> opciones;

	public Encuesta(Long id, String pregunta, Set<Opcion> opciones) {
		super();
		this.id = id;
		this.pregunta = pregunta;
		this.opciones = opciones;
	}

	
	public Encuesta() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Set<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(Set<Opcion> opciones) {
		this.opciones = opciones;
	}
	
	
}
