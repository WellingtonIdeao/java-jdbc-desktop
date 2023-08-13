package com.ideao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ideao.model.Categoria;
import com.ideao.model.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Produto produto) {
		String sql = "INSERT INTO produto (nome, descricao) VALUES (?, ?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	public void salvarComCategoria(Produto produto) {
		String sql = "INSERT INTO produto (nome, descricao, categoria_id) VALUES (?, ?, ?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			pstm.setInt(3, produto.getCategoriaId());

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}
		} catch (SQLException e){
			throw new RuntimeException(e);
		}	
	}

	public List<Produto> listar() {
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "SELECT id, nome, descricao FROM produto";
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();

			trasformarResultSetEmProduto(produtos, pstm);
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
		return produtos;	
	}

	public List<Produto> buscar(Categoria ct) {
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "SELECT id, nome, descrcricao FROM produto WHERE category_id = ?";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setInt(1, ct.getId());
			pstm.execute();

			trasformarResultSetEmProduto(produtos, pstm);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return produtos; 
		
	}

	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM produto WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}

	public void alterar(String nome, String descricao, Integer id) {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE produto P SET P.nome = ?, P.descricao = ? WHERE id = ?")) {
			stm.setString(1, nome);
			stm.setString(2, descricao);
			stm.setInt(3, id);
			stm.execute();
		} catch( SQLException e){
			throw new RuntimeException(e);
		}
	}

	private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
				produtos.add(produto);
			}
		}
	}
}
