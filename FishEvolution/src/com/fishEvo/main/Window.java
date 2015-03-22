package com.fishEvo.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = -4290613327296369588L;

	public Window(int w, int h, String title, Game game){
		JFrame frame = new JFrame(title); // makes window
		
		//set window size
		frame.setPreferredSize(new Dimension(w,h));
		frame.setMaximumSize(new Dimension(w,h));
		frame.setMinimumSize(new Dimension(w,h));
		//
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes Exit button work
		frame.setResizable(false); //makes game window not resizable (fixes strecthes)
		frame.setLocationRelativeTo(null); //not needed but window would start in topleft otherwise
		frame.add(game); //use the game
		frame.setVisible(true); // have to see it
		game.start(); //start game from method
	}

}
