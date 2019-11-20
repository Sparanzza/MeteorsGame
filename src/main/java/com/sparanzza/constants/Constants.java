package com.sparanzza.constants;

public class Constants {
	
	private Constants() {
	
	}
	
	// game board width and height
	public static final int BOARD_WIDTH = 600;
	public static final int BOARD_HEIGHT = 750;
	public static final String GAME_TITLE = "Meteora";
	public static final String GAME_OVER = "Game Over!";
	
	// spaceship's width and height
	public static final int SPACESHIP_WIDTH = 52;
	public static final int SPACESHIP_HEIGHT = 52;
	
	// meteor's width and height
	public static final int METEOR_WIDTH = 37;
	public static final int METEOR_HEIGHT = 50;
	
	// meteor related information
	public static final int METEOR_VERTICAL_TRANSLATION = 1;
	public static final double METEOR_PROBABILITY = 0.007 ;
	
	//light star related information
	public static final int STAR_WIDTH = 30;
	public static final int STAR_HEIGHT = 30;
	public static final double STAR_PROBABILITY = 0.05;
	public static final int STAR_VERTICAL_MOVEMENT = 4;
	
	// laser related information
	public static final int LASER_VERTICAL_TRANSLATION = 4;
	public static final int LASER_WIDTH = 11;
	public static final int LASER_HEIGHT = 39;
	
	//laser line parameters
	public static final int LASER_LINE_VERTICAL_MOVEMENT = 2;
	public static final int LASER_LINE_WIDTH = 600;
	public static final int LASER_LINE_HEIGHT = 11;
	
	// images for the objects
	public static final String METEOR_IMAGE_URL = "resources/images/meteor.png";
	public static final String LASER_IMAGE_URL = "resources/images/laser.png";
	public static final String BACKGROUND_IMAGE_URL = "resources/images/background.png";
	public static final String SPACESHIP_URL = "resources/images/shuttle.png";
	public static final String LIGHT_URL = "resources/images/light.png";
	public static final String LASER_LINE_URL = "resources/images/laserline.png";
	
	// we should get 240 FPS
	public static final int GAME_SPEED = 4;
	
	
}

