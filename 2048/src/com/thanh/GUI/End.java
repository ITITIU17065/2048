package com.thanh.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.thanh.game.Game;
import com.thanh.game.Matrix;

public class End {
	public Rectangle restartButton = new Rectangle(Game.WIDTH / 2 - 70, 200, 140, 50);

	public void render(Graphics2D g) {
		g.setFont(new Font("Clear Sans", Font.BOLD, 100));
		g.setColor(Color.decode("#776E65"));
		if (Matrix.win)
			g.drawString("Win", Game.WIDTH / 2 - 90, Game.HEIGHT / 2);
		else if (Matrix.gameover)
			g.drawString("Lose", Game.WIDTH / 2 - 110, Game.HEIGHT / 2);
	}
}
