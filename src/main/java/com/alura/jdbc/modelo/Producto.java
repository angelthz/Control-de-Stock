package com.alura.jdbc.modelo;

public class Producto {
	private Integer id;
	private String nombre;
	private String descripcion;
	private Integer cantidad;
	
	public Producto(Integer id, String nombre, String descripcion, Integer cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public Producto(String nombre, String desc, Integer cantidad) {
		this.nombre = nombre;
		this.descripcion = desc;
		this.cantidad = cantidad;
	}

	public Integer getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}
	
	@Override
	public String toString() {
		return String.format("{  id: %d,\n   nombre: %s,\n   descripcion: %s,\n   cantidad: %d\n}",this.id, this.nombre, this.descripcion, this.cantidad);
	}
}
