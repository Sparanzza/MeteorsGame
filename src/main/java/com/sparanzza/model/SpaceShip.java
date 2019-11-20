package com.sparanzza.model;

import com.sparanzza.constants.Constants;
import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class SpaceShip extends Sprite {
	private int velocity = 3;
	private int dx;
	
	public SpaceShip() {
		initialize();
	}
	
	private void initialize() {
		
		ImageIcon imageIcon = ImageFactory.createImage(Image.SPACESHIP);
		setImage(imageIcon.getImage());
		int start_x = Constants.BOARD_WIDTH / 2 - Constants.SPACESHIP_WIDTH / 2;
		int start_y = Constants.BOARD_HEIGHT - 100;
		
		setX(start_x);
		setY(start_y);
	}
	
	@Override
	public void move() {
		x += dx;
		// Limit to beyond the canvas on the left and right side with padding
		if (x < 0) {
			x = 0;
		}
		if (x > Constants.BOARD_WIDTH - Constants.SPACESHIP_WIDTH) {
			x = Constants.BOARD_WIDTH - Constants.SPACESHIP_WIDTH;
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		// going to the left
		if (key == KeyEvent.VK_LEFT) {
			dx = -velocity;
		}
		// going to the right
		if (key == KeyEvent.VK_RIGHT) {
			dx = 3;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		// stop moving spaceship
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) dx = 0;
	}
}
