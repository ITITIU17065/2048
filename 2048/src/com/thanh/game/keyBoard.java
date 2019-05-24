package com.thanh.game;

import java.awt.event.KeyEvent;

public class keyBoard{
	public static boolean[] pressed = new boolean[256];
	public static boolean[] prev = new boolean[256];
	
	public void update() {
		for(int i=0; i<4; i++) {
			if(i==0) prev[KeyEvent.VK_LEFT] = pressed[KeyEvent.VK_LEFT];
			if(i==2) prev[KeyEvent.VK_RIGHT] = pressed[KeyEvent.VK_RIGHT];
			if(i==3) prev[KeyEvent.VK_UP] = pressed[KeyEvent.VK_UP];
			if(i==4) prev[KeyEvent.VK_DOWN] = pressed[KeyEvent.VK_DOWN];
		}
	}
	public void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		pressed[e.getKeyCode()] = false;
	}
	public boolean typed(int keyEvent) {
		return	!pressed[keyEvent] && prev[keyEvent];
	}
}