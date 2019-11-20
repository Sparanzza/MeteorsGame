package com.sparanzza.ui;

import com.sparanzza.constants.Constants;
import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;

import javax.swing.*;


public class GameMainFrame extends JFrame {
	
	public GameMainFrame() {
		initializeLayout();
	}
	
	private void initializeLayout() {
		add(new GamePanel());
		setTitle(Constants.GAME_TITLE);
		setIconImage(ImageFactory.createImage(Image.METEOR).getImage());
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	
}
