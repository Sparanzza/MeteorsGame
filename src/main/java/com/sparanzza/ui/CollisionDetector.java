package com.sparanzza.ui;

import com.sparanzza.model.Sprite;

import static com.sparanzza.constants.Constants.*;

public class CollisionDetector {
	
	public boolean collisionLightStarSpaceShip(Sprite lightStar, Sprite spaceShip) {
		
		// spaceship light star collision
		if (lightStar.getX() >= (spaceShip.getX()) && lightStar.getX() <= (spaceShip.getX() + SPACESHIP_WIDTH)
				&& lightStar.getY() >= (spaceShip.getY())
				&& lightStar.getY() <= (spaceShip.getY() + SPACESHIP_HEIGHT)) {
			return true;
		}
		
		return false;
	}
	
	public boolean collisionLaserLineMeteor(Sprite laserLine, Sprite meteor) {
		
		//no need to check for x coordinate because the laser line fills the canvas horizontally
		if (laserLine.getY() >= (meteor.getY()) && laserLine.getY() <= (meteor.getY() + METEOR_HEIGHT)) {
			return true;
		}
		
		return false;
	}
	
	public boolean collisionLaserMeteor(Sprite laser, Sprite meteor) {
		
		int shotX = laser.getX();
		int shotY = laser.getY();
		int meteorX = meteor.getX();
		int meteorY = meteor.getY();
		
		if (shotX >= (meteorX) && shotX <= (meteorX + METEOR_WIDTH) && shotY >= (meteorY)
				&& shotY <= (meteorY + METEOR_HEIGHT)) {
			return true;
		}
		
		return false;
	}
	
	public boolean collisionMeteorSpaceShip(Sprite meteor, Sprite spaceShip) {
		
		int meteorX = meteor.getX();
		int meteorY = meteor.getY();
		int spaceShipX = spaceShip.getX();
		int spaceShipY = spaceShip.getY();
		
		if (meteorX >= (spaceShipX) && meteorX <= (spaceShipX + SPACESHIP_WIDTH)
				&& meteorY >= (spaceShipY) && meteorY <= (spaceShipY + SPACESHIP_HEIGHT)) {
			return true;
		}
		
		return false;
	}
}
