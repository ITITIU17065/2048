package com.thanh.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.thanh.GUI.End;
import com.thanh.GUI.Menu;

public class Game extends JPanel implements KeyListener, Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 560;
	public static final Font main = new Font("Clear Sans", Font.BOLD, 30);
	private Thread game;
	private boolean running = false;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private Board board;
	private Menu menu;
	private End end;

	public static enum STATE {
		MENU, GAME, END
	};

	public static STATE state = STATE.MENU;

	public Game() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		addMouseListener(new MouseInput());
		board = new Board(WIDTH / 2 - Board.BOARD_WIDTH / 2, HEIGHT - Board.BOARD_HEIGHT - 20);
		menu = new Menu();
		end = new End();
	}

	private void update() {
		if (state == STATE.GAME) {
			board.update();
			Keyboard.update();
		}
	}

	private void render() {
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.decode("#FAF8EF"));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		if (state == STATE.GAME)
			board.render(g);
		else if (state == STATE.MENU) {
			menu.render(g);
		} else if (state == STATE.END) {
			end.render(g);
		}
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
		while (running) {
			update();
			render();
		}
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (state == STATE.GAME)
			Keyboard.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (state == STATE.GAME)
			Keyboard.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
