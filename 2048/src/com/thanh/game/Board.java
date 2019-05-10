package com.thanh.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Board extends Canvas {
	public void paint(Graphics g) {
		setBackground(Color.decode("#FAF8EF"));
		g.setColor(Color.decode("#BBADA0"));
		g.fillRoundRect(50, 50, 400, 400, 10, 10);
	}
}
