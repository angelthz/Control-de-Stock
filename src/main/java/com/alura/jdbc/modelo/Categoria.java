package com.alura.jdbc.modelo;

public class Categoria {
	private Integer id;
	private String nombre;
	
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


	public String toString() {
		return String.format("{  id:%d,\n   nombre:%s\n},",this.id, this.nombre);
	}
}
