package com.fishEvo.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
/*
	private Handler handler;
	private Menu menu;
	private Pause pause;
	
	public MouseInput(Handler handler, Menu menu, Pause pause){
		this.handler = handler;
		this.menu = menu;
		this.pause = pause;
	}
*/
	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		int mx = arg0.getX();
		int my = arg0.getY();
		
		//public Rectangle playButton = new Rectangle(Game.WIDTH/2-50, Game.HEIGHT/2 - 30, 100, 50);

		if(Game.State == Game.STATE.MENU)
			if(mouseOver(mx, my, Game.WIDTH/2-50, Game.HEIGHT/2 - 30, 100, 50))
				Game.State = Game.STATE.GAME;
	
	}

	
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	public boolean mouseOver(int mx, int my, int x, int y, int w, int h){
		if(mx > x && mx < x+w){
			if(my > y && mx < y+h){
				return true;
			}
			return false;
		}
		return false;
	}

}
