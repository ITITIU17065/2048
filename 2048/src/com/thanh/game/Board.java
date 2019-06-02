package com.thanh.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Board {
	public static final int ROWS = 4;
	public static final int COLS = 4;

	private final int startingTiles = 2;
	private Tile[][] board;
	private Tile[][] preboard;
	private boolean gameover;
	private boolean win;
	private BufferedImage gameBoard;
	private BufferedImage finalBoard;
	private int x;
	private int y;

	private static int SPACING = 10;
	public static int BOARD_WIDTH = (COLS + 1) * SPACING + COLS * Tile.WIDTH;
	public static int BOARD_HEIGHT = (ROWS + 1) * SPACING + ROWS * Tile.HEIGHT;

	private boolean hasStarted;

	public Board(int x, int y) {
		this.x = x;
		this.y = y;
		board = new Tile[ROWS][COLS];
		preboard = new Tile[ROWS][COLS];
		gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);

		createBoardImage();
		start();
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
		checkKeys();
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				Tile current = board[row][col];
				if (current == null)
					continue;
				current.update();
			}
		}
	}

	private void moveTiles(String dir) {
		if (dir == "LEFT") {
			for (int row = 0; row < ROWS; row++) {
				int count = 0;
				for (int col = 0; col < COLS; col++)
					if (board[row][col] != null)
						board[row][count++] = board[row][col];
				while (count < 4)
					board[row][count++] = null;

			}
		} else if (dir == "RIGHT") {
			for (int row = 0; row < ROWS; row++) {
				int count = COLS - 1;
				for (int col = COLS - 1; col >= 0; col--)
					if (board[row][col] != null)
						board[row][count--] = board[row][col];
				while (count >= 0)
					board[row][count--] = null;

			}
		} else if (dir == "UP") {
			for (int col = 0; col < COLS; col++) {
				int count = 0;
				for (int row = 0; row < ROWS; row++)
					if (board[row][col] != null)
						board[count++][col] = board[row][col];
				while (count < 4)
					board[count++][col] = null;

			}
		} else if (dir == "DOWN") {
			for (int col = 0; col < COLS; col++) {
				int count = ROWS - 1;
				for (int row = ROWS - 1; row >= 0; row--)
					if (board[row][col] != null)
						board[count--][col] = board[row][col];
				while (count >= 0)
					board[count--][col] = null;

			}
		}
	}

	public void combineTiles(String dir) {
		if (dir == "LEFT") {
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLS - 1; col++) {
					int nextCol = col + 1;
					if (board[row][col] == null || board[row][nextCol] == null)
						continue;
					if (board[row][col].getValue() == board[row][nextCol].getValue()) {
						board[row][col].setValue(board[row][col].getValue() * 2);
						board[row][nextCol] = null;
					}
				}
			}
		}
		if (dir == "RIGHT") {
			for (int row = 0; row < ROWS; row++) {
				for (int col = COLS - 1; col > 0; col--) {
					int preCol = col - 1;
					if (board[row][col] == null || board[row][preCol] == null)
						continue;
					if (board[row][col].getValue() == board[row][preCol].getValue()) {
						board[row][col].setValue(board[row][col].getValue() * 2);
						board[row][preCol] = null;
					}
				}
			}
		}
		if (dir == "UP") {
			for (int col = 0; col < COLS; col++) {
				for (int row = 0; row < ROWS - 1; row++) {
					int nextRow = row + 1;
					if (board[row][col] == null || board[nextRow][col] == null)
						continue;
					if (board[row][col].getValue() == board[nextRow][col].getValue()) {
						board[row][col].setValue(board[row][col].getValue() * 2);
						board[nextRow][col] = null;
					}
				}
			}
		}
		if (dir == "DOWN") {
			for (int col = 0; col < COLS; col++) {
				for (int row = ROWS - 1; row > 0; row--) {
					int preRow = row - 1;
					if (board[row][col] == null || board[preRow][col] == null)
						continue;
					if (board[row][col].getValue() == board[preRow][col].getValue()) {
						board[row][col].setValue(board[row][col].getValue() * 2);
						board[preRow][col] = null;
					}
				}
			}
		}
	}

	public void printArr() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (board[row][col] == null)
					System.out.print("0 ");
				else
					System.out.print(board[row][col].getValue() + " ");
			}
			System.out.println("");
		}
		System.out.println("---------------------");
	}

	private void checkKeys() {
//		preboard = board;
//		if (preboard != board)
//			spawnRandom();
		if (Keyboard.typed(KeyEvent.VK_LEFT)) {
			moveTiles("LEFT");
			combineTiles("LEFT");
			moveTiles("LEFT");
			if (!hasStarted)
				hasStarted = true;
		}
		if (Keyboard.typed(KeyEvent.VK_RIGHT)) {
			moveTiles("RIGHT");
			combineTiles("RIGHT");
			moveTiles("RIGHT");
			if (!hasStarted)
				hasStarted = true;
		}
		if (Keyboard.typed(KeyEvent.VK_UP)) {
			moveTiles("UP");
			combineTiles("UP");
			moveTiles("UP");
			if (!hasStarted)
				hasStarted = true;
		}
		if (Keyboard.typed(KeyEvent.VK_DOWN)) {
			moveTiles("DOWN");
			combineTiles("DOWN");
			moveTiles("DOWN");
			if (!hasStarted)
				hasStarted = true;
		}
	}

	private void start() {
		for (int i = 0; i < startingTiles; i++) {
			spawnRandom();
		}
	}

	private void spawnRandom() {
		Random r = new Random();
		boolean notValid = true;
		int value;
		while (notValid) {
			int location = r.nextInt(ROWS * COLS);
			int row = location % ROWS;
			int col = location / COLS;
			Tile current = board[row][col];
			if (current == null) {
				value = r.nextInt(10) < 9 ? 2 : 4;
				Tile tile = new Tile(value, getTileX(col), getTileY(row));
				board[row][col] = tile;
				notValid = false;
			}
		}
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
				Tile current = board[row][col];
				if (current == null)
					continue;
				current.render(g2d);
			}
		}
		g.drawImage(finalBoard, x, y, null);
		g2d.dispose();
	}
}
