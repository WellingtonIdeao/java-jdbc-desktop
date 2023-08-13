package com.ideao.controller;

import java.sql.Connection;
import java.util.List;

import com.ideao.dao.CategoriaDAO;
import com.ideao.factory.ConnectionFactory;
import com.ideao.model.Categoria;


public class CategoriaController {

	private CategoriaDAO categoriaDAO;

	public CategoriaController(){
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.categoriaDAO = new CategoriaDAO(connection);
	}

	public List<Categoria> listar() {
		return this.categoriaDAO.listar();
	}
}
