package com.thanh.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 630;
	public static final Font main = new Font("Clear Sans", Font.BOLD, 30);
	private Thread game;
	private boolean running = false;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private Board board;

	public Game() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		board = new Board(WIDTH/2 - Board.BOARD_WIDTH/2, HEIGHT/2 - Board.BOARD_HEIGHT/2);
	}

	private void update() {
		board.update();
		Keyboard.update();
	}

	private void render() {
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.decode("#FAF8EF"));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		board.render(g);
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
		System.exit(0);
	}

	@Override
	public void run() {
		while(running) {
			update();
			render();
		}
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Keyboard.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Keyboard.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
