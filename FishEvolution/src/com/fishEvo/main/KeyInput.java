package com.fishEvo.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[6];
	
	
	public KeyInput(Handler handler){
		this.handler = handler;
		
		//set keydown to false
		for(int i = 0; i < 6; i++)
			keyDown[i] = false;
		//keyDown[0] = keyDown[1] = keyDown[2] = keyDown[3] = keyDown[4] = false;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		//this for loop appears A LOT, it cycles through every object in the game and returns the 
		//object ID (which we compare to the list we made in ID.java
	//	for(int i = 0; i < handler.object.size(); i++){
		//	GameObject tempObject = handler.object.get(i);
		if(Game.State == Game.STATE.MENU){	
			if(key == KeyEvent.VK_SPACE){
				Game.State = Game.STATE.GAME;
				keyDown[0] = true;
			}	
		}else if(Game.State == Game.STATE.GAME){
			if(key == KeyEvent.VK_ESCAPE){
				Game.State = Game.STATE.MENU;
				keyDown[5] = true;
			}
		}
		if(key == KeyEvent.VK_END){
			System.exit(1);
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
//		for(int i = 0; i < handler.object.size(); i++){
			//GameObject tempObject = handler.object.get(i);
	
			//if(tempObject.getId() == ID.Player){
				//key events for player one
				
				if(key == KeyEvent.VK_UP) keyDown[0] = false;
				if(key == KeyEvent.VK_DOWN) keyDown[1] = false;
				if(key == KeyEvent.VK_LEFT) keyDown[2] = false;
				if(key == KeyEvent.VK_RIGHT) keyDown[3] = false;
				if(key == KeyEvent.VK_ENTER) keyDown[4] = false;
				
				if(!keyDown[0] && !keyDown[1]){
					//Code to do
				}
				
		}
			
		
		
	

}
