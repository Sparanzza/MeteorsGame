package com.sparanzza.callbacks;

import com.sparanzza.ui.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameEventListener extends KeyAdapter {
	
	private GamePanel board;
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		this.board.keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		this.board.keyReleased(e);
	}
	
	public GameEventListener(GamePanel board) {
		this.board = board;
	}
	
}
