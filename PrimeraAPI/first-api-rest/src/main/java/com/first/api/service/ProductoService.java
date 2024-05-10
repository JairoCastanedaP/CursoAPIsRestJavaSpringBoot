package com.first.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.api.model.Producto;
import com.first.api.repository.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public Producto saveProducto(Producto producto) {
		return productoRepository.saveProduct(producto);
	}
	
	public List<Producto> getProductos(){
		return productoRepository.getAllProducts();
	}
	public Producto getProductoById(int id) {
		return productoRepository.findById(id);
	}
	
	public String deleteProducto(int id) {
		productoRepository.deleteProduct(id);
		return "producto eliminado: "+id;
	}
	
	public Producto updateProducto(Producto producto) {
		return productoRepository.updateProduct(producto);
	}

}
