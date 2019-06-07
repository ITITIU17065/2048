package com.thanh.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {

	public static int WIDTH = (Board.BOARD_WIDTH - (Board.COLS + 1) * Board.SPACING) / Board.COLS;
	public static int HEIGHT = (Board.BOARD_HEIGHT - (Board.ROWS + 1) * Board.SPACING) / Board.ROWS;
	public static int SLIDE_SPEED = 20;
	public static int ARC_WIDTH = 10;
	public static int ARC_HEIGHT = 10;

	private int fontSize = (WIDTH + HEIGHT) * 38 / 100 / 2;
	private int value;
	private BufferedImage tileImage;
	private int x;
	private int y;
	private Font font = new Font("Clear Sans", Font.BOLD, fontSize);

	public Tile(int value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;
		tileImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		drawTile();
	}

	private void drawTile() {
		Graphics2D g = (Graphics2D) tileImage.getGraphics();

		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (value == 2) {
			g.setColor(Color.decode("#EEE4DA"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "2", font);
		} else if (value == 4) {
			g.setColor(Color.decode("#EDE0C8"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "4", font);
		} else if (value == 8) {
			g.setColor(Color.decode("#F2B179"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "8", font);
		} else if (value == 16) {
			g.setColor(Color.decode("#FF9962"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "16", font);
		} else if (value == 32) {
			g.setColor(Color.decode("#FE845F"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "32", font);
		} else if (value == 64) {
			g.setColor(Color.decode("#FE6A3D"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "64", font);
		} else if (value == 128) {
			g.setColor(Color.decode("#F3CB71"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "128", font);
		} else if (value == 256) {
			g.setColor(Color.decode("#F3C75E"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "256", font);
		} else if (value == 512) {
			g.setColor(Color.decode("#F2C04F"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "512", font);
		} else if (value == 1024) {
			g.setColor(Color.decode("#F2BD3C"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "1024", font);
		} else if (value == 2048) {
			g.setColor(Color.decode("#F6BC2B"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "2048", font);
		} else if (value == 4096) {
			g.setColor(Color.decode("#FC726C"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "4096", font);
		} else if (value == 8192) {
			g.setColor(Color.decode("#FD5D5A"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "8192", font);
		} else if (value == 16384) {
			g.setColor(Color.decode("#F15137"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "16384", font);
		} else if (value == 32768) {
			g.setColor(Color.decode("#6CAED9"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "32768", font);
		} else if (value == 65536) {
			g.setColor(Color.decode("#589CE6"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			drawString(g, "65536", font);
		} else if (value == 1) {
			g.setColor(Color.GRAY);
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		} else {
			g.setColor(Color.decode("#CDC1B4"));
			g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		}

		g.dispose();
	}

	public void drawString(Graphics2D g, String text, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		int letterX = (WIDTH - metrics.stringWidth(text)) / 2;
		int letterY = ((HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
		if (text == "2" || text == "4")
			g.setColor(Color.decode("#776E65"));
		else
			g.setColor(Color.decode("#F9F6F2"));
		g.setFont(font);
		g.drawString(text, letterX, letterY);
	}

	public void render(Graphics2D g) {
		g.drawImage(tileImage, x, y, null);
	}
}
