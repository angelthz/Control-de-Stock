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
import com.alura.jdbc.dao.ProductoDao;
import com.alura.jdbc.modelo.Producto;

/**
 * Esta clase contiene todos los atributos y metodos para implementar la logica de negocio
 * necesaria ademas de hacer la comunicacion entre las Vistas y los DAO's
 * @author Angel
 *
 */
public class ProductoController {
	//Almacena una nueva instancia de tipo ProductoDao
	private ProductoDao productDAO;
	
	/**
	 * Crea una nueva instancia de Tipo ProductoController, esta instancia almacena una unica
	 * conexion a la base de datos, por lo que nuevas instancias almacenaran su propia conexion
	 * a la base de datos y seran independientes entre ellas.
	 * Esta instancia se encarga de llamar a las operaciones de acceso a la base de datos
	 * del ProductoDao y podemos implementar nuevas reglas de negocio aqui mismo.
	 */
	public ProductoController() {
		this.productDAO = new ProductoDao(new ConnectionFactory().getConection());
	}
	
	/**
	 * Llama al metodo actualizar del ProductoDao
	 * @param producto
	 */
	public void modificar(Producto producto) {
		this.productDAO.actualizar(producto);
	}
	
	/**
	 * Llama al metodo eliminar del ProductoDao
	 * @param id
	 */
	public void eliminar(Integer id) {
		this.productDAO.eliminar(id);
	}
	
	/**
	 * Llama al metodo listar del ProductoDao
	 * @return
	 */
	public List<Producto> listar() {
		return this.productDAO.listar();
	}
	
	/**
	 * Llama al metodo guardar del ProductoDao
	 * @param producto
	 */
	public void guardar(Producto producto) {
		this.productDAO.guardar(producto);
	}

}
