package com.alura.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.connection.ConnectionFactory;
import com.alura.jdbc.dao.CategoriaDao;
import com.alura.jdbc.modelo.Categoria;

public class CategoriaController {
	private CategoriaDao categoriaDao;
	
	public CategoriaController() {
		this.categoriaDao = new CategoriaDao(new ConnectionFactory().getConection());
	}
	
	public List<Categoria> listar() {
		return this.categoriaDao.listar();
	}

    public List<Categoria> cargaReporte() {
        // TODO
        return new ArrayList<>();
    }

}
