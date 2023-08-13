package com.ideao;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.ideao.view.ProdutoCategoriaFrame;

public class TestaOperacaoComView {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> createAndShowGUI());
	}

	private static void createAndShowGUI() {
		ProdutoCategoriaFrame produtoCategoriaFrame = new ProdutoCategoriaFrame();
		produtoCategoriaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
