package com.thanh.game;

import java.awt.*;

public class Board extends Canvas {
	public void paint(Graphics g) {
		setBackground(Color.decode("#FAF8EF"));
		g.setColor(Color.decode("#BBADA0"));
		g.fillRoundRect(50, 50, 400, 400, 10, 10);
		// draw grid

		g.setColor(Color.decode("#CDC1B4"));
		for (int i = 70; i < 400; i += 95) {
			for (int j = 70; j < 400; j += 95) {
				g.fillRoundRect(j, i, 75, 75, 10, 10);
			}
		}

	}
}
