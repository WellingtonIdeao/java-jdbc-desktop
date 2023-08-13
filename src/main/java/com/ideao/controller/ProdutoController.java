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
		System.out.println("Deletando produto");
	}

	public void salvar(Produto produto) {
		this.produtoDAO.salvarComCategoria(produto);
	}

	public List<Produto> listar() {
		return this.produtoDAO.listar();
	}

	public void alterar(String nome, String descricao, Integer id) {
		System.out.println("Alterando produto");
	}
}
