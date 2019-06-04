package com.thanh.game;

import java.awt.event.KeyEvent;
import java.util.Random;

public class Matrix {
	public static final int ROWS = 4;
	public static final int COLS = 4;

	public int[][] matrix;
	private boolean canMove = false;
	private boolean canCombine = false;

	public Matrix() {
		matrix = new int[ROWS][COLS];
		start();
	}

	public void update() {
		checkKeys();
	}

	private void moveTiles(String dir) {
		if (dir == "LEFT") {
			for (int row = 0; row < ROWS; row++) {
				int count = 0;
				for (int col = 0; col < COLS; col++)
					if (matrix[row][col] != 0) {
						matrix[row][count++] = matrix[row][col];
						if (count - 1 != col)
							canMove = true;
					}
				while (count < 4)
					matrix[row][count++] = 0;
			}
		} else if (dir == "RIGHT") {
			for (int row = 0; row < ROWS; row++) {
				int count = COLS - 1;
				for (int col = COLS - 1; col >= 0; col--)
					if (matrix[row][col] != 0) {
						matrix[row][count--] = matrix[row][col];
						if (count + 1 != col)
							canMove = true;
					}
				while (count >= 0)
					matrix[row][count--] = 0;

			}
		} else if (dir == "UP") {
			for (int col = 0; col < COLS; col++) {
				int count = 0;
				for (int row = 0; row < ROWS; row++)
					if (matrix[row][col] != 0) {
						matrix[count++][col] = matrix[row][col];
						if (count - 1 != row) {
							canMove = true;
						}
					}
				while (count < 4)
					matrix[count++][col] = 0;

			}
		} else if (dir == "DOWN") {
			for (int col = 0; col < COLS; col++) {
				int count = ROWS - 1;
				for (int row = ROWS - 1; row >= 0; row--)
					if (matrix[row][col] != 0) {
						matrix[count--][col] = matrix[row][col];
						if (count + 1 != row)
							canMove = true;
					}
				while (count >= 0)
					matrix[count--][col] = 0;

			}
		}
	}

	public void combineTiles(String dir) {
		if (dir == "LEFT") {
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLS - 1; col++) {
					int nextCol = col + 1;
					if (matrix[row][col] == 0 || matrix[row][nextCol] == 0)
						continue;
					if (matrix[row][col] == matrix[row][nextCol]) {
						matrix[row][col] = matrix[row][col] * 2;
						matrix[row][nextCol] = 0;
						canCombine = true;
					}
				}
			}
		} else if (dir == "RIGHT") {
			for (int row = 0; row < ROWS; row++) {
				for (int col = COLS - 1; col > 0; col--) {
					int preCol = col - 1;
					if (matrix[row][col] == 0 || matrix[row][preCol] == 0)
						continue;
					if (matrix[row][col] == matrix[row][preCol]) {
						matrix[row][col] = matrix[row][col] * 2;
						matrix[row][preCol] = 0;
						canCombine = true;
					}
				}
			}
		} else if (dir == "UP") {
			for (int col = 0; col < COLS; col++) {
				for (int row = 0; row < ROWS - 1; row++) {
					int nextRow = row + 1;
					if (matrix[row][col] == 0 || matrix[nextRow][col] == 0)
						continue;
					if (matrix[row][col] == matrix[nextRow][col]) {
						matrix[row][col] = matrix[row][col] * 2;
						matrix[nextRow][col] = 0;
						canCombine = true;
					}
				}
			}
		} else if (dir == "DOWN") {
			for (int col = 0; col < COLS; col++) {
				for (int row = ROWS - 1; row > 0; row--) {
					int preRow = row - 1;
					if (matrix[row][col] == 0 || matrix[preRow][col] == 0)
						continue;
					if (matrix[row][col] == matrix[preRow][col]) {
						matrix[row][col] = matrix[row][col] * 2;
						matrix[preRow][col] = 0;
						canCombine = true;
					}
				}
			}
		}
	}

	public void printArr() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				System.out.print(matrix[row][col] + " ");
			}
			System.out.println("");
		}
		System.out.println("---------------------");
	}

	private void checkKeys() {
		if (Keyboard.typed(KeyEvent.VK_LEFT)) {
			moveTiles("LEFT");
			combineTiles("LEFT");
			moveTiles("LEFT");
		} else if (Keyboard.typed(KeyEvent.VK_RIGHT)) {
			moveTiles("RIGHT");
			combineTiles("RIGHT");
			moveTiles("RIGHT");
		} else if (Keyboard.typed(KeyEvent.VK_UP)) {
			moveTiles("UP");
			combineTiles("UP");
			moveTiles("UP");
		} else if (Keyboard.typed(KeyEvent.VK_DOWN)) {
			moveTiles("DOWN");
			combineTiles("DOWN");
			moveTiles("DOWN");
		} else if (Keyboard.typed(KeyEvent.VK_Z)) {
			System.out.println("1");
		} else if (Keyboard.typed(KeyEvent.VK_X)) {
			System.out.println("2");
		}
		if (canCombine || canMove) {
			spawnRandom();
			canCombine = false;
			canMove = false;
		}
	}

	private void start() {
		spawnRandom();
		spawnRandom();
	}

	private void spawnRandom() {
		Random r = new Random();
		boolean notValid = true;
		while (notValid) {
			int location = r.nextInt(ROWS * COLS);
			int row = location % ROWS;
			int col = location / COLS;
			if (matrix[row][col] == 0) {
				matrix[row][col] = r.nextInt(10) < 9 ? 2 : 4;
				notValid = false;
			}
		}
	}
}
