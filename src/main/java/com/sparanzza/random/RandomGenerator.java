package com.sparanzza.random;

import java.util.Random;

import static com.sparanzza.constants.Constants.*;

public class RandomGenerator {
	
	private Random random;
	
	public RandomGenerator() {
		this.random = new Random();
	}
	
	public boolean isLightStarGenerated() {
		return random.nextDouble() < STAR_PROBABILITY;
	}
	
	public boolean isMeteorGenerated() {
		return random.nextDouble() < METEOR_PROBABILITY;
	}
	
	public int generateRandomX() {
		return random.nextInt(BOARD_WIDTH - 2 * SPACESHIP_WIDTH) + SPACESHIP_WIDTH;
	}
}
