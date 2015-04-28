package com.fishEvo.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;



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
	public static BufferedImage background, missingTileImg;
	
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
		
		new Window(WIDTH, HEIGHT, "fishEvo #rekt", this);

		menu = new Menu();
		pause = new Pause();
		
		this.addKeyListener(new KeyInput(handler));	
		this.addMouseListener(new MouseInput(menu));
		
		try {
            missingTileImg = ImageIO.read(new File("missingTile.png"));
            background = ImageIO.read(new File("tank.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("missing file");
        }
		
		int h = 50;
		for(int i = 0; i < h; i++){
			for(int j = 0; j < h; j++){
				handler.addObject(new Tile(i,j,missingTileImg));
			}
		}
		background = new BufferedImage(h*32, h*32, BufferedImage.TYPE_INT_ARGB);
		Graphics gTemp = background.getGraphics(); 
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			gTemp.drawImage(missingTileImg, tempObject.getX(), tempObject.getY(), null);
		}

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
				System.out.println("FPS: " + frames);
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
		handler.tick();
		
	}
	
	public void render(){
		this.requestFocus();
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g.create();

		g.setColor(Color.black);
		g.fillRect(0, 0, 500, 500);
		g.setClip(new Rectangle(100,100,500,500));
		handler.render(g);
		g.setClip(null);

		g.dispose();
		g2d.dispose();
		bs.show();
		
	
	}

	
	public static void main(String args[]){
		new Game();
	}
	
}
