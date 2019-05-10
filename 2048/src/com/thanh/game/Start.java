package com.thanh.game;

import javax.swing.JFrame;

public class Start {

	public static void main(String[] args) {
		JFrame window = new JFrame("2048");
		Board board = new Board();
		window.add(board);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setSize(500, 500);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

}
