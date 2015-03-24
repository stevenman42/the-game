package com.fishEvo.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	private Menu menu;
	
	public MouseInput(Menu menu){
		this.menu = menu;
	}
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.State == Game.STATE.MENU){
			if(mouseOverRectangle(mx, my, menu.playButton))
			{
				Game.State = Game.STATE.GAME;
			}
			if(mouseOverRectangle(mx, my, menu.quitButton)){
				System.exit(1);
			}
		}else if(Game.State == Game.STATE.GAME)
		{
			
		}else if(Game.State == Game.STATE.PAUSE)
		{
			
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	
	public boolean mouseOver(int mx, int my, int x, int y, int w, int h){
		if(mx > x && mx < x+w){
			if(my > y && my < y+h){
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean mouseOverRectangle(int mx, int my, Rectangle rect){
		if(mx > rect.x && mx < rect.x + rect.width){
			if(my > rect.y && my < rect.y + rect.height){
				return true;
			}
			return false;
		}
		return false;
	}

}
