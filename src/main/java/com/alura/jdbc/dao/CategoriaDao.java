package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;

public class CategoriaDao {
	//Definimos un atributo privado de tipo Connection
	private Connection con;
	
	//Cream0os el constructor de la clase
	public CategoriaDao(Connection con) {
		this.con = con;
	}
	
	//1. Definir el acceso a la tabla Categoria de la DB
	//
	public List<Categoria> listar(){
		List<Categoria> categorias = new ArrayList<>();
		try {
			final PreparedStatement selectQuery = this.con.prepareStatement("SELECT *FROM categoria");
			try(selectQuery){
				selectQuery.execute();
				ResultSet results = selectQuery.getResultSet();
				
				while(results.next()) {
					Categoria categoria = new Categoria(results.getInt("ID"), results.getString("NOMBRE"));
					categorias.add(categoria);
					//System.out.println(categoria);
				}
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return categorias;
	}

	/**
	 * Realiza un consulta entre las tablas Categoria y Producto de la base de datos y devuelve
	 * un ArrayList de categorias con los productos filtrados por cada una de estas.
	 * @return
	 */
	public List<Categoria> listarReporte(){
		List <Categoria> resultado = new ArrayList<>();
		try {
			final PreparedStatement query = this.con.prepareStatement("SELECT "
					+ "cat.id, cat.nombre, prod.id, prod.nombre, prod.cantidad "
					+ "FROM categoria cat "
					+ "INNER JOIN producto prod "
					+ "ON cat.id = prod.categoria_id");
			
			try(query){
				query.execute();
				ResultSet res = query.getResultSet();
				
				while(res.next()) {
					//System.out.println(res.getInt("cat.id")+" "+res.getString("cat.nombre")+" "+res.getString("prod.nombre"));
					Integer categoriaId = res.getInt("cat.id");
					String categoriaNombre = res.getString("cat.nombre");
					
					Categoria categoria = resultado
							.stream()
							.filter(cat -> cat.getId().equals(categoriaId))
							.findAny().orElseGet( () -> {
								Categoria cat = new Categoria(categoriaId, categoriaNombre);
								resultado.add(cat);
								return cat;
							});
					Producto producto = new Producto(res.getInt("prod.id"),
							res.getString("prod.nombre"),
							res.getInt("prod.cantidad")
					);
					categoria.addProducto(producto);
				}
			}
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
	
	
}
