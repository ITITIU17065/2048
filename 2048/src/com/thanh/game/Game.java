package com.thanh.game;

public class Game  implements Runnable{
	private Board board;
	
	private boolean running = false;
	private Thread thread;
	
	private void init(){
		board = new Board();
	}
	
	private void update() {
		board.paint();
	}
	
	private void render() {
		
	}
	public void run(){
		
		init();
		
		while(running){
			update();
			
		}
		stop();
		
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
