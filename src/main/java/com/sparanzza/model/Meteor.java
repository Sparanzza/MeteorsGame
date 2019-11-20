package com.sparanzza.model;

import com.sparanzza.images.ImageFactory;

import javax.swing.*;

import static com.sparanzza.constants.Constants.METEOR_VERTICAL_TRANSLATION;
import static com.sparanzza.images.Image.METEOR;

public class Meteor extends Sprite {
	
	public Meteor(int x, int y) {
		this.x = x;
		this.y = y;
		initialize();
	}
	
	private void initialize() {
		ImageIcon image = ImageFactory.createImage(METEOR);
		setImage(image.getImage());
	}
	
	@Override
	public void move() {
		
		// the meteor moves from top to the bottom
		this.y += METEOR_VERTICAL_TRANSLATION;
	}
}
