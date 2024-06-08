package com.api.gestion.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoWrapper {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private String status;
    private Integer categoriaId;
    private String nombreCategoria;

    public ProductoWrapper(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    

    public ProductoWrapper() {
		super();
	}
    



	public ProductoWrapper(Integer id, String nombre, String descripcion, Integer precio, String status,
			Integer categoriaId, String nombreCategoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.status = status;
		this.categoriaId = categoriaId;
		this.nombreCategoria = nombreCategoria;
	}



	public ProductoWrapper(Integer id, String nombre, String descripcion, Integer precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
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



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Integer getPrecio() {
		return precio;
	}



	public void setPrecio(Integer precio) {
		this.precio = precio;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Integer getCategoriaId() {
		return categoriaId;
	}



	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}



	public String getNombreCategoria() {
		return nombreCategoria;
	}



	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
	
}
