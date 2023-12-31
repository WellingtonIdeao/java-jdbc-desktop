package com.ideao.controller;

import java.sql.Connection;
import java.util.List;

import com.ideao.dao.ProdutoDAO;
import com.ideao.factory.ConnectionFactory;
import com.ideao.model.Produto;

public class ProdutoController {

	private ProdutoDAO produtoDAO;

	public ProdutoController(){
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.produtoDAO = new ProdutoDAO(connection);
	}

	public void deletar(Integer id) {
		this.produtoDAO.deletar(id);
	}

	public void salvar(Produto produto) {
		if( produto.getCategoriaId() == 0){
			this.produtoDAO.salvar(produto);
		} else {
			this.produtoDAO.salvarComCategoria(produto);
		}	
	}

	public List<Produto> listar() {
		return this.produtoDAO.listar();
	}

	public void alterar(String nome, String descricao, Integer id) {
		this.produtoDAO.alterar(nome, descricao, id);
	}
}
