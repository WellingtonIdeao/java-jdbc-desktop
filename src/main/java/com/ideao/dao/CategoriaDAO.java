package com.ideao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideao.model.Categoria;
import com.ideao.model.Produto;

public class CategoriaDAO {

	private Connection connection;

	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Categoria> listar() {
		List<Categoria> categorias = new ArrayList<>();
		String sql = "SELECT id, nome FROM categoria";
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
					categorias.add(categoria);
				}
			}
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		return categorias;
	}

	public List<Categoria> listarComProduto() {
		Categoria ultima = null;
		List<Categoria> categorias = new ArrayList<>();

		String sql = "SELECT C.id, C.nome, P.id, P.nome, P.descricao " + "FROM categoria C "
				+ "INNER JOIN produto P ON C.id = P.categoria_id";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					if (ultima == null || !ultima.getNome().equals(rst.getString(2))) {
						Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));

						categorias.add(categoria);
						ultima = categoria;
					}
					Produto produto = new Produto(rst.getInt(3), rst.getString(4), rst.getString(5));
					ultima.adicionar(produto);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return categorias;

	}
}
