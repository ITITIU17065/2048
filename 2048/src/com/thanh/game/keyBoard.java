package com.thanh.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;	

public class keyBoard implements KeyListener {
	Matrix mat;
	
	  //scoreBoard board;
	
	 
	
	    keyBoard(Matrix myMatrix, scoreBoard score) {
	
	        mat = myMatrix;

	        board = score;


	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_UP) {
			if (!mat.game_Over) {
				
				                 board.Update();
				
				                    mat.slide_Up();
				
				                    if(mat.game_Won){
				
				                        doGameOver();
				
				                    }
				
				                } else {
				
				                    doGameOver();
				
				                }

		}
		if else(c == KeyEvent.VK_UP) {

		}
		if else(c == KeyEvent.VK_UP) {

		}
		if else(c == KeyEvent.VK_UP) {

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
