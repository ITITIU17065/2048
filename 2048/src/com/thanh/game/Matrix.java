package com.thanh.game;

import java.util.Random;

public class Matrix {
	private boolean gameStart = true;
	public int[][] matrix = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

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
}