package com.fishEvo.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class GameObject {
	
	protected float x,y;
	protected BufferedImage tileImg;
	
	public GameObject(float x, float y, BufferedImage tileImg){
		this.x = x;
		this.y = y;
		this.tileImg = tileImg;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	
	public int getX(){
		return (int)x;
	}
	public int getY(){
		return (int)y;
	}
	
}
