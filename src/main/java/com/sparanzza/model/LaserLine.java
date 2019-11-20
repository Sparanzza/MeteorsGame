package com.sparanzza.model;


import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;

import javax.swing.*;

import static com.sparanzza.constants.Constants.BOARD_HEIGHT;
import static com.sparanzza.constants.Constants.LASER_LINE_VERTICAL_MOVEMENT;

public class LaserLine extends Sprite {
	
	public LaserLine() {
		initialize();
	}
	
	private void initialize() {
		
		ImageIcon image = ImageFactory.createImage(Image.LASER_LINE);
		setImage(image.getImage());
		
		setX(0);
		setY(BOARD_HEIGHT-20);
	}
	
	@Override
	public void move() {
		
		//from bottom to the top
		this.y -= LASER_LINE_VERTICAL_MOVEMENT;
		
	}
	
}
