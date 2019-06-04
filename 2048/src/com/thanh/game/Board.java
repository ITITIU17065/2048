package com.thanh.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Board {
	public static final int ROWS = 4;
	public static final int COLS = 4;

	private boolean gameover;
	private boolean win;
	private BufferedImage gameBoard;
	private BufferedImage finalBoard;
	private int x;
	private int y;
	private Matrix matrix;
	private Tile tile;

	private static int SPACING= 10;
	public static int BOARD_WIDTH = (COLS + 1) * SPACING + COLS * Tile.WIDTH;
	public static int BOARD_HEIGHT = (ROWS + 1) * SPACING + ROWS * Tile.HEIGHT;

	public Board(int x, int y) {
		this.x = x;
		this.y = y;
		matrix = new Matrix();
		gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);

		createBoardImage();
	}

	private void createBoardImage() {
		Graphics2D g = (Graphics2D) gameBoard.getGraphics();
		g.setColor(Color.decode("#BBADA0"));
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		g.setColor(Color.decode("#CDC1B4"));

		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				int x = SPACING + SPACING * col + Tile.WIDTH * col;
				int y = SPACING + SPACING * row + Tile.HEIGHT * row;
				g.fillRoundRect(x, y, Tile.WIDTH, Tile.HEIGHT, Tile.ARC_WIDTH, Tile.ARC_HEIGHT);
			}
		}
	}

	public void update() {
		matrix.update();
	}



	private int getTileY(int row) {

		return SPACING + row * Tile.HEIGHT + row * SPACING;
	}

	private int getTileX(int col) {
		return SPACING + col * Tile.WIDTH + col * SPACING;
	}

	public void render(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) finalBoard.getGraphics();
		g2d.drawImage(gameBoard, 0, 0, null);
		
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				tile = new Tile(matrix.matrix[row][col],getTileX(col),getTileY(row));
				tile.render(g2d);
			}
		}
		g.drawImage(finalBoard, x, y, null);
		g2d.dispose();
	}
}
