package com.alura.jdbc.modelo;

public class Producto {
	private Integer id;
	private String nombre;
	private String descripcion;
	private Integer cantidad;
	//Nuevo atributo para almacenar el id de la categoria
	private Integer idCategoria;
	
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
	
	//Nuevo constructor para incluir el ID de la Categoria
	public Producto(String nombre, String desc, Integer cantidad, int idCategoria) {
		this.nombre = nombre;
		this.descripcion = desc;
		this.cantidad = cantidad;
		this.idCategoria = idCategoria;
	}

	public Producto(int id, String nombre, int cantidad) {
		this.id = id;
		this.nombre = nombre;
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
	
	//Nuevo metodo para obtener el id de la categoria
	public int getIdCategoria() {
		return this.idCategoria;
	}
	
	@Override
	public String toString() {
		return String.format("{  id: %d,\n   nombre: %s,\n   descripcion: %s,\n   cantidad: %d,\n   id_categoria: %d\n}",this.id, this.nombre, this.descripcion, this.cantidad, this.idCategoria);
	}
	
	
}
