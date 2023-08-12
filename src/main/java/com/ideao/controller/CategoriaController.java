package com.ideao.controller;

import java.util.ArrayList;
import java.util.List;

import com.ideao.model.Categoria;


public class CategoriaController {

	public List<Categoria> listar() {
		List<Categoria> categorias = 
				new ArrayList<Categoria>();
		categorias.add(new Categoria(1, "Categoria de teste")); 
		return categorias;
	}
}
