package com.thanh.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (mx >= Game.WIDTH / 2 - 45 && mx <= Game.WIDTH / 2 - 45 + 100) {
			// Play Button
			if (my >= 200 && my <= 250)
				Game.state = Game.STATE.GAME;
			// Quit Button
			else if (my >= 300 && my <= 350)
				System.exit(1);

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
