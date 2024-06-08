package com.api.gestion.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWrapper {


    private Integer id;
    private String nombre;
    private String email;
    private String numeroDeContacto;
    private String status;
	public UserWrapper() {
		super();
	}
	public UserWrapper(Integer id, String nombre, String email, String numeroDeContacto, String status) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.numeroDeContacto = numeroDeContacto;
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getNumeroDeContacto() {
		return numeroDeContacto;
	}
	public void setNumeroDeContacto(String numeroDeContacto) {
		this.numeroDeContacto = numeroDeContacto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    

}
