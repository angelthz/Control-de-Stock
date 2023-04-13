package com.alura.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.connection.ConnectionFactory;
import com.alura.jdbc.dao.CategoriaDao;
import com.alura.jdbc.modelo.Categoria;

public class CategoriaController {
	//Atributo de tipo CategoriaDao para tener acceso a todas las operaciones de la DB.
	private CategoriaDao categoriaDao;
	/**
	 * Constructor, devuelve un objeto de tipo CategoriaController;
	 */
	public CategoriaController() {
		this.categoriaDao = new CategoriaDao(new ConnectionFactory().getConection());
	}
	
	/**
	 * Llama al metodo listar de la clase CategoriaDao, devuelve un arraylist con
	 * las categorias de la tabla categoria de la DB.
	 * @return ArrayList de objetos de tipo Categoria.
	 */
	public List<Categoria> listar() {
		return this.categoriaDao.listar();
	}
	
	/**
	 * Llama al metodo listarReporte de la clase CategoriaDao, devuelve un arraylist con
	 * las categorias filtradas con sus productos correspondientes.
	 * @return ArrayList de objetos de tipo Categoria.
	 */
    public List<Categoria> cargaReporte() {
        return this.categoriaDao.listarReporte();
    }

}
