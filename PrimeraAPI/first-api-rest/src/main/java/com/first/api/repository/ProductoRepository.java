package com.first.api.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.first.api.model.Producto;

@Repository
public class ProductoRepository {

	private List<Producto> productos = new ArrayList<>();
	
	public void createProductos() {
		productos= List.of(
				new Producto(1,"Producto 1", 10,1500),
				new Producto(2,"Producto 1", 10,1500),
				new Producto(3,"Producto 1", 10,1500),
				new Producto(4,"Producto 1", 10,1500),
				new Producto(5,"Producto 1", 10,1500),
				new Producto(6,"Producto 1", 10,1500),
				new Producto(7,"Producto 1", 10,1500)
				);
	}
	
	public List<Producto> getAllProducts(){
		return productos;
	}
	
	public Producto findById(int id) {
		for(int i=0;i<productos.size();i++) {
			if(productos.get(i).getId()==id) {
				return productos.get(i);
			}
		}
		return null;
	}
	
	public List<Producto> search(String nombre){
		return productos.stream()
				.filter(x -> x.getNombre().startsWith(nombre))
				.collect(Collectors.toList());
	}
	
	public Producto saveProduct(Producto p) {
		Producto producto = new Producto();
		producto.setId(p.getId());
		producto.setNombre(p.getNombre());
		producto.setCantidad(p.getCantidad());
		producto.setPrecio(p.getPrecio());
		
		productos.add(producto);
		
		return producto;
	}
	
	public String deleteProduct(Integer id) {
		productos.removeIf(x -> x.getId() == id);
		
		return null;
	}
	
	public Producto updateProduct(Producto producto) {
		int idPos=0;
		int id=0;
		
		for(int i=0;i<productos.size();i++) {
			if(productos.get(i).getId() == producto.getId()) {
				id= producto.getId();
				idPos=i;
				break;
			}
		}
		
		Producto producto1 = new Producto();
		producto1.setId(producto.getId());
		producto1.setNombre(producto.getNombre());
		producto1.setCantidad(producto.getCantidad());
		producto1.setPrecio(producto.getPrecio());
		
		productos.set(idPos, producto);
		return producto1;
	}
	
}
