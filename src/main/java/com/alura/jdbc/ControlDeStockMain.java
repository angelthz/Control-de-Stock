package com.alura.jdbc;

import javax.swing.JFrame;

import com.alura.jdbc.view.ControlDeStockFrame;
import com.formdev.flatlaf.FlatLightLaf;

public class ControlDeStockMain {

	public static void main(String[] args) {
		FlatLightLaf.setup();

		ControlDeStockFrame produtoCategoriaFrame = new ControlDeStockFrame();
		produtoCategoriaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
