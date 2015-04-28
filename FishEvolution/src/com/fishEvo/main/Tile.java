package com.fishEvo.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile extends GameObject{
	
	public Tile(float x, float y, BufferedImage tileImg){
		super(x*32,y*32,tileImg);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
	}

}
