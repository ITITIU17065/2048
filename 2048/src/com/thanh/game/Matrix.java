package com.thanh.game;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import com.thanh.linkStack.LinkStack;

public class Matrix {
	private int ROWS = Board.ROWS;
	private int COLS = Board.COLS;
	public static boolean gameover;
	public static boolean win;

	public int[][] matrix;
	private LinkStack undo;
	private LinkStack redo;
	private double winScore;
	public int score = 0;
	private int preScore;
	public float highScore;
	public int bomb = 0;
	private int addRock = 0;
	private boolean isRock = false;

	private boolean canMove = false;
	private boolean canCombine = false;

	public Matrix() {
		matrix = new int[ROWS][COLS];
		undo = new LinkStack();
		redo = new LinkStack();
		makeWinScore();
		highScore = Float.parseFloat(getHighScore());
		start();
	}

	private void makeWinScore() {
		if (ROWS > COLS)
			winScore = Math.pow(2, ROWS * 2 + (7 - ROWS));
		else
			winScore = Math.pow(2, COLS * 2 + (7 - COLS));
	}

	public void update() {
		checkKeys();
		if (score > highScore)
			highScore = score;
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (matrix[row][col] == winScore) {
					System.out.println("Win");
					win = true;
				}
			}
		}
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (matrix[row][col] == 1) {
					isRock = true;
				} else
					isRock = true;
			}
		}
		if(win || gameover)
			Game.state = Game.STATE.END;
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
				while (count < COLS)
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
				while (count < ROWS)
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
					if (matrix[row][col] == 1 || matrix[row][nextCol] == 1)
						continue;
					if (matrix[row][col] == matrix[row][nextCol]) {
						matrix[row][col] = matrix[row][col] * 2;
						matrix[row][nextCol] = 0;
						score += matrix[row][col];
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
					if (matrix[row][col] == 1 || matrix[row][preCol] == 1)
						continue;
					if (matrix[row][col] == matrix[row][preCol]) {
						matrix[row][col] = matrix[row][col] * 2;
						matrix[row][preCol] = 0;
						score += matrix[row][col];
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
					if (matrix[row][col] == 1 || matrix[nextRow][col] == 1)
						continue;
					if (matrix[row][col] == matrix[nextRow][col]) {
						matrix[row][col] = matrix[row][col] * 2;
						matrix[nextRow][col] = 0;
						score += matrix[row][col];
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
					if (matrix[row][col] == 1 || matrix[preRow][col] == 1)
						continue;
					if (matrix[row][col] == matrix[preRow][col]) {
						matrix[row][col] = matrix[row][col] * 2;
						matrix[preRow][col] = 0;
						score += matrix[row][col];
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

	private void doUndo() {
		if (undo.isEmpty())
			return;
		String temp = undo.pop();
		redo.push(returnString(matrix), this.score);
		String[] list = temp.split("-");

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				matrix[i][j] = Integer.parseInt(list[i].split("/")[j]);
			}
		}
		this.score = Integer.parseInt(temp.split("-")[temp.split("-").length - 1]);
	}

	private void doRedo() {
		if (redo.isEmpty())
			return;
		String temp = redo.pop();
		undo.push(returnString(matrix), this.score);
		String[] list = temp.split("-");

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				matrix[i][j] = Integer.parseInt(list[i].split("/")[j]);
			}
		}
		this.score = Integer.parseInt(temp.split("-")[temp.split("-").length - 1]);
	}

	private void checkKeys() {
		preScore = score;
		if (Keyboard.typed(KeyEvent.VK_LEFT)) {
			undo.push(this.returnString(matrix), this.score);
			redo = new LinkStack();
			moveTiles("LEFT");
			combineTiles("LEFT");
			moveTiles("LEFT");
			if (preScore == score) {
				addRock++;
			} else if (score - preScore == 32)
				bomb++;
			else
				addRock = 0;
		} else if (Keyboard.typed(KeyEvent.VK_RIGHT)) {
			undo.push(this.returnString(matrix), this.score);
			redo = new LinkStack();
			moveTiles("RIGHT");
			combineTiles("RIGHT");
			moveTiles("RIGHT");
			if (preScore == score) {
				addRock++;
			} else if (score - preScore == 32)
				bomb++;
			else
				addRock = 0;
		} else if (Keyboard.typed(KeyEvent.VK_UP)) {
			undo.push(this.returnString(matrix), this.score);
			redo = new LinkStack();
			moveTiles("UP");
			combineTiles("UP");
			moveTiles("UP");
			if (preScore == score) {
				addRock++;
			} else if (score - preScore == 32)
				bomb++;
			else
				addRock = 0;
		} else if (Keyboard.typed(KeyEvent.VK_DOWN)) {
			undo.push(this.returnString(matrix), this.score);
			redo = new LinkStack();
			moveTiles("DOWN");
			combineTiles("DOWN");
			moveTiles("DOWN");
			if (preScore == score) {
				addRock++;
			} else if (score - preScore == 32)
				bomb++;
			else
				addRock = 0;
		} else if (Keyboard.typed(KeyEvent.VK_Z)) {
			doUndo();
		} else if (Keyboard.typed(KeyEvent.VK_X)) {
			doRedo();
		} else if (Keyboard.typed(KeyEvent.VK_SPACE)) {
			if (bomb > 0 && isRock) {
				undo.push(this.returnString(matrix), this.score);
				redo = new LinkStack();
				destroyRock();
				bomb--;
			}
		}
		if (canCombine || canMove) {
			spawnRandom();
			checkGameover();
			canCombine = false;
			canMove = false;
		}
	}

	private void destroyRock() {
		int location = chooseRock();
		matrix[location / 10][location % 10] = 0;
	}

	private void checkGameover() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (matrix[row][col] == 0)
					return;
			}
		}
		if (checkSurroundingTiless())
			return;
		gameover = true;
		setHighScore();
	}

	private boolean checkSurroundingTiless() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS - 1; col++) {
				int nextCol = col + 1;
				if (matrix[row][col] == 1)
					continue;
				if (matrix[row][col] == matrix[row][nextCol]) {
					return true;
				}
			}
		}
		for (int col = 0; col < COLS; col++) {
			for (int row = 0; row < ROWS - 1; row++) {
				int nextRow = row + 1;
				if (matrix[row][col] == 1)
					continue;
				if (matrix[row][col] == matrix[nextRow][col]) {
					return true;
				}
			}
		}
		return false;
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
			if (matrix[row][col] == 0 && addRock < 2) {
				matrix[row][col] = r.nextInt(10) < 9 ? 2 : 4;
				notValid = false;
			} else if (matrix[row][col] == 0 && addRock >= 2) {
				matrix[row][col] = 1;
				notValid = false;
				addRock = 0;
				isRock = true;
			}
		}
	}

	private int chooseRock() {
		Random r = new Random();
		int[] rockLocation = new int[ROWS * COLS];
		int count = 0;
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (matrix[row][col] == 1) {
					rockLocation[count++] = row * 10 + col;
				}
			}
		}
		int location = rockLocation[r.nextInt(count)];
		return location;
	}

	public String returnString(int a[][]) {
		String str = "";
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (col == COLS - 1)
					str += a[row][col] + "-";
				else
					str += a[row][col] + "/";
			}
		}
		return str;
	}

	private String getHighScore() {
		String str = "";
		try {
			File file = new File("highScore.txt");
			FileReader fr = new FileReader(file);
			char[] a = new char[50];
			fr.read(a);
			for (char c : a)
				str += c;
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	private void setHighScore() {
		String str = Float.toString(highScore);
		try {
			File file = new File("highScore.txt");
			FileWriter fw = new FileWriter(file, false);
			fw.write(str);
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
