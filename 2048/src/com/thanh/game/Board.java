package com.thanh.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Board extends Canvas {
	public void paint(Graphics g) {
		Matrix m = new Matrix();
		Tile t;
		setBackground(Color.decode("#FAF8EF"));
		g.setColor(Color.decode("#BBADA0"));
		g.fillRoundRect(50, 50, 400, 400, 10, 10);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				t = new Tile(m.matrix[i][j],j,i);
				t.drawTile(g);
			}
		}
	}
	public void update() {
		this.repaint();
	}
}
