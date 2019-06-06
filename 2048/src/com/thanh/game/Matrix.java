//package com.thanh.game;
//
//import java.awt.event.KeyEvent;
//import java.util.Random;
//
//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
//
//import com.thanh.linkStack.LinkStack;
//
//public class Matrix {
//	private int ROWS = Board.ROWS;
//	private int COLS = Board.COLS;
//	private boolean gameover;
//	private boolean win;
//
//	public int[][] matrix;
//	public int[][] preMatrix;
//	private LinkStack undo;
//	private LinkStack redo;
//	public int score = 0;
//
//	private boolean canMove = false;
//	private boolean canCombine = false;
//
//	public Matrix() {
//		matrix = new int[ROWS][COLS];
//		preMatrix = new int[ROWS][COLS];
//		undo = new LinkStack();
//		redo = new LinkStack();
//		start();
//	}
//
//	public void update() {
//		checkKeys();
//		for (int row = 0; row < ROWS; row++) {
//			for (int col = 0; col < COLS; col++) {
//				if (matrix[row][col] == 2048) {
//					System.out.println("Win");
//					win = true;
//				}
//			}
//		}
//	}
//
//	private void moveTiles(String dir) {
//		if (dir == "LEFT") {
//			for (int row = 0; row < ROWS; row++) {
//				int count = 0;
//				for (int col = 0; col < COLS; col++)
//					if (matrix[row][col] != 0) {
//						matrix[row][count++] = matrix[row][col];
//						if (count - 1 != col)
//							canMove = true;
//					}
//				while (count < COLS)
//					matrix[row][count++] = 0;
//			}
//		} else if (dir == "RIGHT") {
//			for (int row = 0; row < ROWS; row++) {
//				int count = COLS - 1;
//				for (int col = COLS - 1; col >= 0; col--)
//					if (matrix[row][col] != 0) {
//						matrix[row][count--] = matrix[row][col];
//						if (count + 1 != col)
//							canMove = true;
//					}
//				while (count >= 0)
//					matrix[row][count--] = 0;
//
//			}
//		} else if (dir == "UP") {
//			for (int col = 0; col < COLS; col++) {
//				int count = 0;
//				for (int row = 0; row < ROWS; row++)
//					if (matrix[row][col] != 0) {
//						matrix[count++][col] = matrix[row][col];
//						if (count - 1 != row) {
//							canMove = true;
//						}
//					}
//				while (count < ROWS)
//					matrix[count++][col] = 0;
//
//			}
//		} else if (dir == "DOWN") {
//			for (int col = 0; col < COLS; col++) {
//				int count = ROWS - 1;
//				for (int row = ROWS - 1; row >= 0; row--)
//					if (matrix[row][col] != 0) {
//						matrix[count--][col] = matrix[row][col];
//						if (count + 1 != row)
//							canMove = true;
//					}
//				while (count >= 0)
//					matrix[count--][col] = 0;
//
//			}
//		}
//	}
//
//	public void combineTiles(String dir) {
//		if (dir == "LEFT") {
//			for (int row = 0; row < ROWS; row++) {
//				for (int col = 0; col < COLS - 1; col++) {
//					int nextCol = col + 1;
//					if (matrix[row][col] == 0 || matrix[row][nextCol] == 0)
//						continue;
//					if (matrix[row][col] == matrix[row][nextCol]) {
//						matrix[row][col] = matrix[row][col] * 2;
//						matrix[row][nextCol] = 0;
//						score += matrix[row][col];
//						canCombine = true;
//					}
//				}
//			}
//		} else if (dir == "RIGHT") {
//			for (int row = 0; row < ROWS; row++) {
//				for (int col = COLS - 1; col > 0; col--) {
//					int preCol = col - 1;
//					if (matrix[row][col] == 0 || matrix[row][preCol] == 0)
//						continue;
//					if (matrix[row][col] == matrix[row][preCol]) {
//						matrix[row][col] = matrix[row][col] * 2;
//						matrix[row][preCol] = 0;
//						score += matrix[row][col];
//						canCombine = true;
//					}
//				}
//			}
//		} else if (dir == "UP") {
//			for (int col = 0; col < COLS; col++) {
//				for (int row = 0; row < ROWS - 1; row++) {
//					int nextRow = row + 1;
//					if (matrix[row][col] == 0 || matrix[nextRow][col] == 0)
//						continue;
//					if (matrix[row][col] == matrix[nextRow][col]) {
//						matrix[row][col] = matrix[row][col] * 2;
//						matrix[nextRow][col] = 0;
//						score += matrix[row][col];
//						canCombine = true;
//					}
//				}
//			}
//		} else if (dir == "DOWN") {
//			for (int col = 0; col < COLS; col++) {
//				for (int row = ROWS - 1; row > 0; row--) {
//					int preRow = row - 1;
//					if (matrix[row][col] == 0 || matrix[preRow][col] == 0)
//						continue;
//					if (matrix[row][col] == matrix[preRow][col]) {
//						matrix[row][col] = matrix[row][col] * 2;
//						matrix[preRow][col] = 0;
//						score += matrix[row][col];
//						canCombine = true;
//					}
//				}
//			}
//		}
//	}
//
//	public void printArr() {
//		for (int row = 0; row < ROWS; row++) {
//			for (int col = 0; col < COLS; col++) {
//				System.out.print(matrix[row][col] + " ");
//			}
//			System.out.println("");
//		}
//		System.out.println("---------------------");
//	}
//
//	private void doUndo() {
//		if(undo.isEmpty())
//			return;
//		redo.push(preMatrix);
//		matrix = undo.pop();
//		printArr();
//	}
//
//	private void doRedo() {
//		if (redo.isEmpty())
//			return;
//		undo.push(this.matrix);
//		this.matrix = redo.pop();
//	}
//
//	private void checkKeys() {
//		this.preMatrix = matrix;
//		if (Keyboard.typed(KeyEvent.VK_LEFT)) {
//			undo.push(this.preMatrix);
//			redo = new LinkStack();
//			moveTiles("LEFT");
//			combineTiles("LEFT");
//			moveTiles("LEFT");
//		} else if (Keyboard.typed(KeyEvent.VK_RIGHT)) {
//			this.preMatrix = matrix;
//			redo = new LinkStack();
//			moveTiles("RIGHT");
//			combineTiles("RIGHT");
//			moveTiles("RIGHT");
//		} else if (Keyboard.typed(KeyEvent.VK_UP)) {
//			this.preMatrix = matrix;
//			redo = new LinkStack();
//			moveTiles("UP");
//			combineTiles("UP");
//			moveTiles("UP");
//		} else if (Keyboard.typed(KeyEvent.VK_DOWN)) {
//			redo = new LinkStack();
//			moveTiles("DOWN");
//			combineTiles("DOWN");
//			moveTiles("DOWN");
//		} else if (Keyboard.typed(KeyEvent.VK_Z)) {
//			doUndo();
//		} else if (Keyboard.typed(KeyEvent.VK_X)) {
//			doRedo();
//		}
//		if (canCombine || canMove) {
//			spawnRandom();
//			checkGameover();
//			canCombine = false;
//			canMove = false;
//		}
//	}
//
//	private void checkGameover() {
//		for (int row = 0; row < ROWS; row++) {
//			for (int col = 0; col < COLS; col++) {
//				if (matrix[row][col] == 0)
//					return;
//			}
//		}
//		if (checkSurroundingTiless())
//			return;
//		gameover = true;
//	}
//
//	private boolean checkSurroundingTiless() {
//		for (int row = 0; row < ROWS; row++) {
//			for (int col = 0; col < COLS - 1; col++) {
//				int nextCol = col + 1;
//				if (matrix[row][col] == matrix[row][nextCol]) {
//					return true;
//				}
//			}
//		}
//		for (int col = 0; col < COLS; col++) {
//			for (int row = 0; row < ROWS - 1; row++) {
//				int nextRow = row + 1;
//				if (matrix[row][col] == matrix[nextRow][col]) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	private void start() {
//		spawnRandom();
//		spawnRandom();
//	}
//
//	private void spawnRandom() {
//		Random r = new Random();
//		boolean notValid = true;
//		while (notValid) {
//			int location = r.nextInt(ROWS * COLS);
//			int row = location % ROWS;
//			int col = location / COLS;
//			if (matrix[row][col] == 0) {
//				matrix[row][col] = r.nextInt(10) < 9 ? 2 : 4;
//				notValid = false;
//			}
//		}
//	}
//}
package com.thanh.game;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

import com.thanh.linkStack.LinkStack;

public class Matrix {
	private int ROWS = Board.ROWS;
	private int COLS = Board.COLS;
	private boolean gameover;
	private boolean win;

	public int[][] matrix;
	public int[][] preMatrix;
	private LinkStack undo;
	private LinkStack redo;
	public int score = 0;

	private boolean canMove = false;
	private boolean canCombine = false;

	public Matrix() {
		matrix = new int[ROWS][COLS];
		preMatrix = new int[ROWS][COLS];
		undo = new LinkStack();
		redo = new LinkStack();
		start();
	}

	public void update() {
		checkKeys();
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (matrix[row][col] == 2048) {
					System.out.println("Win");
					win = true;
				}
			}
		}
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
		if (Keyboard.typed(KeyEvent.VK_LEFT)) {
			undo.push(this.returnString(matrix), this.score);
			redo = new LinkStack();
			moveTiles("LEFT");
			combineTiles("LEFT");
			moveTiles("LEFT");
		} else if (Keyboard.typed(KeyEvent.VK_RIGHT)) {
			undo.push(this.returnString(matrix), this.score);
			redo = new LinkStack();
			moveTiles("RIGHT");
			combineTiles("RIGHT");
			moveTiles("RIGHT");
		} else if (Keyboard.typed(KeyEvent.VK_UP)) {
			undo.push(this.returnString(matrix), this.score);
			redo = new LinkStack();
			moveTiles("UP");
			combineTiles("UP");
			moveTiles("UP");
		} else if (Keyboard.typed(KeyEvent.VK_DOWN)) {
			undo.push(this.returnString(matrix), this.score);
			redo = new LinkStack();
			moveTiles("DOWN");
			combineTiles("DOWN");
			moveTiles("DOWN");
		} else if (Keyboard.typed(KeyEvent.VK_Z)) {
			doUndo();
		} else if (Keyboard.typed(KeyEvent.VK_X)) {
			doRedo();
		}
		if (canCombine || canMove) {
			spawnRandom();
			checkGameover();
			canCombine = false;
			canMove = false;
		}
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
	}

	private boolean checkSurroundingTiless() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS - 1; col++) {
				int nextCol = col + 1;
				if (matrix[row][col] == matrix[row][nextCol]) {
					return true;
				}
			}
		}
		for (int col = 0; col < COLS; col++) {
			for (int row = 0; row < ROWS - 1; row++) {
				int nextRow = row + 1;
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
			if (matrix[row][col] == 0) {
				matrix[row][col] = r.nextInt(10) < 9 ? 2 : 4;
				notValid = false;
			}
		}
	}

	public String returnString(int a[][]) {
		String str = "";
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (j == COLS - 1)
					str += a[i][j] + "-";
				else
					str += a[i][j] + "/";
			}
		}
		return str;
	}
	
	public String getHighScore() {
		String str = "";
		try {
			FileReader fr = new FileReader("highScore.txt");
			s
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void main(String[] args) {
		Matrix m = new Matrix();
	}
}
