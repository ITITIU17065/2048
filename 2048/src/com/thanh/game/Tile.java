package com.thanh.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Tile {
	private int value;
	private int x;
	private int y;
	private final Font f=new Font("Clear Sans",Font.BOLD,30);

	public Tile(int value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;
	}

	public void drawTile(Graphics g) {
		if (value == 2) {
			g.setColor(Color.decode("#EEE4DA"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "2", f);
		} else if (value == 4) {
			g.setColor(Color.decode("#EDE0C8"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "4", f);
		} else if (value == 8) {
			g.setColor(Color.decode("#F2B179"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "8", f);
		} else if (value == 16) {
			g.setColor(Color.decode("#FF9962"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "16", f);
		} else if (value == 32) {
			g.setColor(Color.decode("#FE845F"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "32", f);
		} else if (value == 64) {
			g.setColor(Color.decode("#FE6A3D"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "64", f);
		} else if (value == 128) {
			g.setColor(Color.decode("#F3CB71"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "128", f);
		} else if (value == 256) {
			g.setColor(Color.decode("#F3C75E"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "256", f);
		} else if (value == 512) {
			g.setColor(Color.decode("#F2C04F"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "512", f);
		} else if (value == 1024) {
			g.setColor(Color.decode("#F2BD3C"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "1024", f);
		} else if (value == 2048) {
			g.setColor(Color.decode("#F6BC2B"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "2048", f);
		} else if (value == 4096) {
			g.setColor(Color.decode("#FC726C"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "4096", f);
		} else if (value == 8192) {
			g.setColor(Color.decode("#FD5D5A"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "8192", f);
		} else if (value == 16384) {
			g.setColor(Color.decode("#F15137"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "16384", f);
		} else if (value == 32768) {
			g.setColor(Color.decode("#6CAED9"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "32768", f);
		} else if (value == 65536) {
			g.setColor(Color.decode("#589CE6"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "65536", f);
		} else if (value == 131072) {
			g.setColor(Color.decode("#0073C2"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
			drawString(g, "131072", f);
		} else {
			g.setColor(Color.decode("#CDC1B4"));
			g.fillRoundRect(70 + 95 * x, 70 + 95 * y, 75, 75, 10, 10);
		}	
	}
	public void drawString(Graphics g, String text, Font font) {
	    FontMetrics metrics = g.getFontMetrics(font);
	    int letterX = 70+95*x + (75 - metrics.stringWidth(text)) / 2;
	    int letterY = 70+95*y + ((75 - metrics.getHeight()) / 2) + metrics.getAscent();
	    if(text == "2" || text == "4")
	    	g.setColor(Color.decode("#776E65"));
	    else
	    	g.setColor(Color.decode("#F9F6F2"));
	    g.setFont(font);
	    g.drawString(text, letterX, letterY);
	}

}
