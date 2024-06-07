package com.api.gestion.pojo;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NamedQuery(name = "Producto.getAllProductos",query = "select new com.api.gestion.wrapper.ProductoWrapper(p.id,p.nombre,p.descripcion,p.precio,p.status,p.categoria.id,p.categoria.nombre) from Producto p")
@NamedQuery(name = "Producto.updateStatus",query = "update Producto p set p.status=:status where p.id=:id")
@NamedQuery(name = "Producto.getProductoByCategoria",query = "select new com.api.gestion.wrapper.ProductoWrapper(p.id,p.nombre) from Producto p where p.categoria.id=:id and p.status='true'")
@NamedQuery(name = "Producto.getProductoById",query = "select new com.api.gestion.wrapper.ProductoWrapper(p.id,p.nombre,p.descripcion,p.precio) from Producto p where p.id=:id")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_fk",nullable = false)
    private Categoria categoria;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "status")
    private String status;

	public Producto() {
		super();
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
    
    
}
