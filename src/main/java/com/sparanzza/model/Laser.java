package com.sparanzza.model;

import com.sparanzza.constants.Constants;
import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;

import javax.swing.*;

public class Laser extends Sprite {
	public Laser() {
	}
	
	public Laser(int x, int y) {
		this.x = x;
		this.y = y;
		initialize();
	}
	
	private void initialize() {
		ImageIcon imageIcon = ImageFactory.createImage(Image.LASER);
		setImage(imageIcon.getImage());
		//offset to place the laser in the middle of the spaceship
		setX(x + Constants.SPACESHIP_WIDTH / 2);
		setY(y);
	}
	
	@Override
	public void move() {
		this.y -= Constants.LASER_VERTICAL_TRANSLATION;
		if (this.y < 0) {
			this.setDead(true);
		}
	}
}
