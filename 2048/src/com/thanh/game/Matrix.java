package com.thanh.game;

import java.util.Random;

public class Matrix {
	private boolean gameStart = true;
	public int[][] matrix = new int[][] { { 16, 0, 4, 0 }, { 64, 128, 0, 0 }, { 0, 0, 0, 128 }, { 0, 0, 2048, 0 } };

	public Matrix() {
		spawnRandom();
	}

	private void spawnRandom() {
		if (gameStart) {
			addNumber();
			addNumber();
			gameStart = false;
		} else
			addNumber();

	}

	private void addNumber() {
		int[] option = new int[16];
		int lenght = 0;
		int spot;
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (matrix[j][i] == 0)
					option[lenght++] = j * 10 + i;
			}
		}
		if (lenght > 0) {
			spot = option[r.nextInt(lenght)];
			matrix[spot / 10][spot % 10] = r.nextInt(10) > 0 ? 2 : 4;
		}
	}

	public void slide_Left() {
		for (int i = 0; i < 4; i++) {
			int count = 0;
			for (int j = 0; j < 4; j++)
				if (matrix[i][j] != 0)
					matrix[i][count++] = matrix[i][j];
			while (count < 4)
				matrix[i][count++] = 0;
		}
	}
	public void slide_Right() {
		for (int i = 0; i < 4; i++) {
			int count = 3;
			for (int j = 3; j >=0 ; j--)
				if (matrix[i][j] != 0)
					matrix[i][count--] = matrix[i][j];
			while (count >=0)
				matrix[i][count--] = 0;
		}
	}
	public void slide_Up() {
		for (int i = 0; i < 4; i++) {
			int count = 0;
			for (int j = 0; j < 4; j++)
				if (matrix[j][i] != 0)
					matrix[count++][i] = matrix[j][i];
			while (count < 4)
				matrix[count++][i] = 0;
		}
	}
	public void slide_Down() {
		for (int i = 0; i < 4; i++) {
			int count = 3;
			for (int j = 3; j >=0 ; j--)
				if (matrix[j][i] != 0)
					matrix[count--][i] = matrix[j][i];
			while (count >=0)
				matrix[count--][i] = 0;
		}
	}
}
