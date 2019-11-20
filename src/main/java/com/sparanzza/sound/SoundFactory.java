package com.sparanzza.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundFactory {
	
	private Clip clip;
	
	public SoundFactory(){
	
	}
	
	public void laser(){
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/sounds/laser.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void explosion(){
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/sounds/explosion.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
