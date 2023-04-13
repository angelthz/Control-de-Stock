package com.alura.jdbc.modelo;

import java.util.ArrayList;

public class Categoria {
	private Integer id;
	private String nombre;
	private ArrayList <Producto> productos;
	
	public Categoria(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	public void addProducto(Producto product) {
		if(this.productos == null) {
			this.productos = new ArrayList<>();
		}
		this.productos.add(product);
	}
	
	@Override
	public String toString() {
		return String.format("{  id:%d,\n   nombre:%s\n},",this.id, this.nombre);
	}


	public ArrayList <Producto> getProductos() {
		return this.productos;
	}
}
