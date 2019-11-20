package com.sparanzza.model;


import java.awt.*;

public abstract class Sprite {
	protected int x;
	protected int y;
	private Image image;
	private boolean dead;
	
	public Sprite() {
		this.dead = false;
	}
	
	public abstract void move();
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}
