package com.thanh.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.thanh.game.Game;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH/2-45,200,100,50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2-45,300,100,50);
	
	public void render(Graphics2D g) {
		g.setFont(new Font("Clear Sans", Font.BOLD, 100));
		g.setColor(Color.decode("#776E65"));
		g.drawString("2048", Game.WIDTH / 2 - 110, 100);
		
		g.setFont(new Font("Clear Sans", Font.BOLD, 30));
		g.drawString("Play", playButton.x + 19, playButton.y +35);
		g.draw(playButton);
		g.drawString("Quit", quitButton.x + 19, quitButton.y +35);
		g.draw(quitButton);
	}
}
