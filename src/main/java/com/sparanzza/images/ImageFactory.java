package com.sparanzza.images;

import javax.swing.*;

import static com.sparanzza.constants.Constants.*;

public class ImageFactory {
	
	public static ImageIcon createImage(Image image) {
		ImageIcon imageIcon = null;
		
		switch (image) {
			case LASER:
				imageIcon = new ImageIcon(LASER_IMAGE_URL);
				break;
			case LASER_LINE:
				imageIcon = new ImageIcon(LASER_LINE_URL);
				break;
			case LIGHT:
				imageIcon = new ImageIcon(LIGHT_URL);
				break;
			case METEOR:
				imageIcon = new ImageIcon(METEOR_IMAGE_URL);
				break;
			case BACKGROUND:
				imageIcon = new ImageIcon(BACKGROUND_IMAGE_URL);
				break;
			case SPACESHIP:
				imageIcon = new ImageIcon(SPACESHIP_URL);
				break;
			default:
				return null;
		}
		return imageIcon;
	}
	
}
