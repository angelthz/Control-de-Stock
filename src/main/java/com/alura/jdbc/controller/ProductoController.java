package com.alura.jdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.alura.jdbc.connection.ConnectionFactory;

public class ProductoController {

	public void modificar(Integer id, String nombre, String descripcion, Integer cantidad) throws SQLException  {
		// TODO
		//CONEXION 
		final Connection con = new ConnectionFactory().getConection();
		try(con){
			final PreparedStatement updateQuery = con.prepareStatement("UPDATE producto SET nombre = ?, descripcion = ?, cantidad = ? WHERE id = ?");
			try {
				updateQuery.setString(1, nombre);
				updateQuery.setString(2, descripcion);
				updateQuery.setInt(3, cantidad);
				updateQuery.setInt(4, id);
				updateQuery.execute();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void eliminar(Integer id) throws SQLException {
		// TODO
		final Connection con = new ConnectionFactory().getConection();
		
		try(con){
			final PreparedStatement deleteQuery = con.prepareStatement("DELETE FROM producto WHERE id = ?");
			try(deleteQuery){
				deleteQuery.setInt(1, id);
				deleteQuery.execute();
			} 
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	public List<Map<String, String>> listar() throws SQLException{
		//creamos una conexion
		
		List<Map<String, String>> resultados = new ArrayList<>();
		
		final Connection con = new ConnectionFactory().getConection();
		
		try(con) {
			final Statement query = con.createStatement();
			try {
				query.execute("SELECT *FROM producto");
				ResultSet res = query.getResultSet();
				while(res.next()) {
					Map<String, String> fila = new HashMap<>();
					fila.put("ID", String.valueOf( res.getInt("ID")) );
					fila.put("Nombre", res.getString("NOMBRE"));
					fila.put("Descripcion", res.getString("DESCRIPCION"));
					fila.put("Cantidad", String.valueOf(res.getInt("CANTIDAD")));
					resultados.add(fila);
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultados;
	}

    public void guardar(Map<String, String> producto) throws SQLException {
		// TODO
    	//obtenemos una conexion
    	Connection con = new ConnectionFactory().getConection();
    	//creamos nuestro statement (query)
    	PreparedStatement insertQuery = con.prepareStatement
    			("INSERT INTO producto (nombre, descripcion, cantidad) values(?,?,?)",
    					Statement.RETURN_GENERATED_KEYS);
    	
    	String nombre = producto.get("Nombre");
    	String desc = producto.get("Descripcion");
    	Integer cant = Integer.valueOf(producto.get("Cantidad"));
    	int max = 50;
    	
    	//deshabilitamos el autocommit desde java
    	con.setAutoCommit(false);
    	
    	try {
    		//insertamos los productos de 50 en 50
    		do {
        		int actual = Math.min(max, cant);
        		insertProducto(nombre, desc, actual, insertQuery);
        		cant -= max;
        	}while( cant > 0);
    		//si no ocurre algun error con el inserte hacemos el commit (terminamos la transaccion)
    		con.commit();
    	} catch( Exception e ) {
    		//si ocurre un error revertimos la consulta
    		con.rollback();
    		System.out.println(e);
    	}
    	//cerramos recursos
    	con.close();
    	insertQuery.close();
	}
    
    private void insertProducto(String nombre, String desc, Integer cant, PreparedStatement query) throws SQLException {
    	//hace el insert desde java hacia la base de datos
    	query.setString(1, nombre);
    	query.setString(2, desc);
    	query.setInt(3, cant);
    	//ejecutamos la consulta
    	query.execute();
    	//si se muestra el ID del producto la consulta fue exitosa
    	ResultSet res = query.getGeneratedKeys();
    	while(res.next()) {
    		System.out.println("Se ha insertado con exito, ID: " + res.getInt(1));
    	}
    }

}
