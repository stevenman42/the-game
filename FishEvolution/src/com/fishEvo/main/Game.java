package com.fishEvo.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;



public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 8926385898770160133L;
	
	public static final int WIDTH = 1280, HEIGHT = WIDTH / 16 * 9;

	
	private Thread thread;
	private Menu menu;
	private Handler handler;
	private Pause pause;
	private ID id;
	private HUD hud;
	private Spawn spawn;
	
	private Random r = new Random();
	private boolean running = false;
	
	public static enum STATE{
		MENU,
		GAME,
		PAUSE
	};
	
	public static STATE State = STATE.MENU;

	
	public Game(){
		handler = new Handler();
		menu = new Menu();
		pause = new Pause();
		
		this.addKeyListener(new KeyInput(handler));	
		this.addMouseListener(new MouseInput());

		new Window(WIDTH, HEIGHT, "fishEvo #rekt", this);
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) /ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
			//	System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();		
	}
	
	//start the game (through starting the thread)
	public synchronized void start(){
		thread  = new Thread(this);
		thread.start();
		running = true;
	}
	
	//if it fails give a reason why
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void tick(){
		if(State == STATE.GAME){
			handler.tick();
		}	
		else{}
		
	}
	
	public void render(){
		this.requestFocus();
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//make background
		
		if(State == STATE.GAME){
			g.setColor(Color.white);
		}
		if(State == STATE.MENU){
			
		/*
		    STEVEN WHY DOES THIS NOT WORK!@#?!@?#!?@#!@?#?!@#?@!?#!@?#?!@?#!?#!@?#!?@#?!@?#!@?#!@?#!@?#!?@#?!@#?!@#?!@#?!@?#?!@#?
			
			ImageIcon i = new ImageIcon("test.png");
            Image image = i.getImage();
            g.drawImage(image, 200, 200, null);
            
            STEVEN WHY DOES THIS NOT WORK!??!@#?!@#?!@?@?#?!@#?!@#?!@?#!@?#!@?#!@?#!@?#?!@#?#?!@?#!?@#?!@#?!@#?!@#?@?!#?!@?#?!@#
        */
			//g.setColor(Color.black);

		}
		g.fillRect(0, 0, WIDTH, HEIGHT);

		
		//render the environment
		if(State == STATE.GAME){
			//render the foreground
			handler.render(g);
		}
		if(State == STATE.MENU){
			menu.render(g);
		}

		//update the image
		g.dispose();
		bs.show();
		
	
	}
	
	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else 
			return var;
	}
	
	public static void main(String args[]){
		new Game();
	}
	
	

}
