package com.thanh.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final Font main = new Font("Clear Sans", Font.BOLD, 30);
	private Thread game;
	private boolean running = false;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	public Game() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
	}

	private void update() {

	}

	private void render() {
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.decode("#FAF8EF"));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.decode("#BBADA0"));
		g.fillRoundRect(50, 50, 400, 400, 10, 10);
		// Draw the board
		g.dispose();

		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		game = new Thread(this);
		game.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			game.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
