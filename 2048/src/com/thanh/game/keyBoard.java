package com.thanh.game;

import java.awt.event.KeyEvent;

public class Keyboard{
	public static boolean[] pressed = new boolean[256];
	public static boolean[] prev = new boolean[256];
	
	public static void update() {
		for(int i=0; i<7; i++) {
			if(i==0) prev[KeyEvent.VK_LEFT] = pressed[KeyEvent.VK_LEFT];
			if(i==1) prev[KeyEvent.VK_RIGHT] = pressed[KeyEvent.VK_RIGHT];
			if(i==2) prev[KeyEvent.VK_UP] = pressed[KeyEvent.VK_UP];
			if(i==3) prev[KeyEvent.VK_DOWN] = pressed[KeyEvent.VK_DOWN];
			if(i==4) prev[KeyEvent.VK_Z] = pressed[KeyEvent.VK_Z];
			if(i==5) prev[KeyEvent.VK_X] = pressed[KeyEvent.VK_X];
			if(i==6) prev[KeyEvent.VK_SPACE] = pressed[KeyEvent.VK_SPACE];
		}
	}
	public static void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()] = true;
	}

	public static void keyReleased(KeyEvent e) {
		pressed[e.getKeyCode()] = false;
	}
	public static boolean typed(int keyEvent) {
		return	!pressed[keyEvent] && prev[keyEvent];
	}
}