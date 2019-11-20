package com.sparanzza.model;

import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;

import javax.swing.*;

import static com.sparanzza.constants.Constants.STAR_VERTICAL_MOVEMENT;

public class LightStar extends Sprite {
	
	public LightStar(int x, int y) {
		this.x = x;
		this.y = y;
		initialize();
	}
	
	private void initialize() {
		ImageIcon image = ImageFactory.createImage(Image.LIGHT);
		setImage(image.getImage());
	}
	
	@Override
	public void move() {
		//laser star coming from the top toward the bottom of the canvas
		this.y += STAR_VERTICAL_MOVEMENT;
	}
}
