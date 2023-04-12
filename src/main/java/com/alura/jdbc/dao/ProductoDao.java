package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.connection.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

/**
 * Esta clase contiene todos los atributos y metodos para trabajar con las
 * operaciones de persistencia y acces a los datos de la entidad Producto de la
 * Base de datos
 * 
 * @author Angel
 *
 */

public class ProductoDao {
	//Almacena una referencia a una conexion hacia una base de datos
	private Connection con;
	
	/**
	 * Crea una nueva instancia del productoDao
	 * @param con Una conexion a una base de datos
	 */
	public ProductoDao(Connection con) {
		this.con = con;
	}
	
	/**
	 * Consulta todos los registros de la tabla Producto de la base de datos.
	 * @return ArrayList<Producto> Un ArrayList con objetos de tipo Producto
	 * que representan todos los registros de la tabla Producto en la base de datos.
	 */
	public List<Producto> listar() {

		List<Producto> resultados = new ArrayList<>();

		try {
			final Statement query = con.createStatement();
			try (query) {
				query.execute("SELECT *FROM producto");
				
				ResultSet res = query.getResultSet();
				
				while (res.next()) {
					Producto producto = new Producto(res.getInt("ID"),
							res.getString("NOMBRE"),
							res.getString("DESCRIPCION"), 
							res.getInt("CANTIDAD")
					);
					resultados.add(producto);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultados;
	}
	
	/**
	 * Elimina un registro de la tabla Producto de la base de datos.
	 * @param id Integer: El id del producto a eliminar.
	 */
	public void eliminar(Integer id) {
		try {
			PreparedStatement deleteQuery = this.con.prepareStatement("DELETE FROM producto "
					+ "WHERE id = ?;",
					Statement.RETURN_GENERATED_KEYS);
			
			deleteQuery.setInt(1, id);
			deleteQuery.execute();
			
			System.out.println("Se ha elimiando con exito res: " +deleteQuery.getUpdateCount());
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Actualiza el registro seleccionado en la tabla Producto de la base de datos.
	 * @param producto Un objeto de tipo producto
	 */
	public void actualizar(Producto producto) {
		try {
			final PreparedStatement updateQuery = con.prepareStatement("UPDATE producto SET "
					+ "nombre = ?, "
					+ "descripcion = ?, "
					+ "cantidad = ? "
					+ "WHERE id = ?");
			try(updateQuery){
				updateQuery.setString(1, producto.getNombre());
				updateQuery.setString(2, producto.getDescripcion());
				updateQuery.setInt(3, producto.getCantidad());
				updateQuery.setInt(4, producto.getId());
				
				updateQuery.execute();
				
				int res = updateQuery.getUpdateCount();
				System.out.println(res >= 1 ? "Registro actualizado" : "No se pudo actualizar");
			}
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Inserta un nuevo registro en la tabla Producto de la base de datos.
	 * @param producto Un objeto de tipo Producto que representa el registro nuevo a insertar
	 * en la tabla Producto.
	 */
	public void guardar(Producto producto) {
		
		try {
			final PreparedStatement insertQuery = this.con.prepareStatement("INSERT INTO producto "
					+ "(nombre, descripcion, cantidad) "
					+ "values (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			try(insertQuery){
				this.ejecutarInsert(producto, insertQuery);
			}
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Realiza el insert en la base de datos
	 * @param prod Objeto de tipo producto a insertar en DB.
	 * @param stm Statement a ejecutar.
	 */
	private void ejecutarInsert(Producto prod, PreparedStatement stm) {
		try {
			stm.setString(1, prod.getNombre());
			stm.setString(2, prod.getDescripcion());
			stm.setInt(3, prod.getCantidad());
			stm.execute();
			
			ResultSet res = stm.getGeneratedKeys();
			while(res.next()) {
				System.out.println("Registro guardado: " +res.getInt(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	
}
