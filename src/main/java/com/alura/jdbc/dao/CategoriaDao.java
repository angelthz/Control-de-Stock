package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.alura.jdbc.modelo.Categoria;

public class CategoriaDao {
	//Definimos un atributo privado de tipo Connection
	private Connection con;
	
	//Cream0os el constructor de la clase
	public CategoriaDao(Connection con) {
		this.con = con;
	}
	
	//TODO
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
					System.out.println(categoria);
				}
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return categorias;
	}
}
