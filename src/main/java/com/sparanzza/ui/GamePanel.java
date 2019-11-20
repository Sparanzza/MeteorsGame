package com.sparanzza.ui;

import com.sparanzza.callbacks.GameEventListener;
import com.sparanzza.constants.Constants;
import com.sparanzza.constants.GameVariables;
import com.sparanzza.images.ImageFactory;
import com.sparanzza.model.*;
import com.sparanzza.random.RandomGenerator;
import com.sparanzza.sound.SoundFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static com.sparanzza.images.Image.BACKGROUND;


public class GamePanel extends JPanel {
	
	private SoundFactory soundFactory;
	private Timer timer;
	private SpaceShip spaceShip;
	private List<Laser> lasers;
	private LaserLine laserLine;
	private LightStar lightStar;
	private Image backgroundImage;
	private List<Meteor> meteors;
	private RandomGenerator randomGenerator;
	private CollisionDetector collisionDetector;
	
	public GamePanel() {
		initializeVariables();
		initializeLayout();
		startAnimation();
	}
	
	private void startAnimation() {
		this.timer = new Timer(Constants.GAME_SPEED, new GameLoop(this));
		timer.start();
	}
	
	private void initializeVariables() {
		addKeyListener(new GameEventListener(this));
		this.soundFactory = new SoundFactory();
		this.randomGenerator = new RandomGenerator();
		this.meteors = new ArrayList<Meteor>();
		this.spaceShip = new SpaceShip();
		this.lasers = new ArrayList<Laser>();
		this.backgroundImage = ImageFactory.createImage(BACKGROUND).getImage();
		this.collisionDetector = new CollisionDetector();
	}
	
	private void initializeLayout() {
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT));
		setFocusable(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		handleCanvas(g);
	}
	
	public void drawBackground(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, null);
	}
	
	public void drawSpaceShip(Graphics g) {
		g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), null);
	}
	
	public void drawLaser(Graphics g) {
		for (Laser laser : this.lasers)
			if (!laser.isDead())
				g.drawImage(laser.getImage(), laser.getX(), laser.getY(), null);
		
	}
	
	public void drawMeteors(Graphics g) {
		for (Meteor meteor : this.meteors) {
			g.drawImage(meteor.getImage(), meteor.getX(), meteor.getY(), null);
		}
	}
	
	private void gameOver(Graphics g) {
		
		g.drawImage(backgroundImage, 0, 0, null);
		
		Font font = new Font("Helvetica", Font.BOLD, 50);
		FontMetrics fontMetrics = this.getFontMetrics(font);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(Constants.GAME_OVER, (Constants.BOARD_WIDTH - fontMetrics.stringWidth(Constants.GAME_OVER)) / 2,
				Constants.BOARD_HEIGHT / 2 - 100);
		
		g.setColor(Color.YELLOW);
		g.drawString("Score: " + GameVariables.SCORE, (Constants.BOARD_WIDTH - fontMetrics.stringWidth(Constants.GAME_OVER)) / 2, Constants.BOARD_HEIGHT - 300);
	}
	
	// printing the value of the actual score + the number of remaining shields
	private void drawScoreAndShield(Graphics g) {
		
		if (!GameVariables.IN_GAME)
			return;
		
		Font font = new Font("Helvetica", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.GRAY);
		g.drawString("Score: " + GameVariables.SCORE, Constants.BOARD_WIDTH - 150, 50);
		g.drawString("Shields: " + GameVariables.SHIELDS, 50, 50);
	}
	
	private void drawLaserLight(Graphics g) {
		if (lightStar != null && !lightStar.isDead()) {
			g.drawImage(lightStar.getImage(), lightStar.getX(), lightStar.getY(), null);
		}
	}
	
	private void laserLineAnimation(Graphics g) {
		
		if (laserLine != null) {
			g.drawImage(laserLine.getImage(), laserLine.getX(), laserLine.getY(), null);
		}
	}
	
	private void handleCanvas(Graphics g) {
		
		if (GameVariables.IN_GAME) {
			drawBackground(g);
			drawSpaceShip(g);
			drawLaser(g);
			drawMeteors(g);
			drawScoreAndShield(g);
			drawLaserLight(g);
			laserLineAnimation(g);
		} else {
			if (timer.isRunning()) {
				timer.stop();
			}
			
			gameOver(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void update() {
		
		if (spaceShip.isDead()) {
			GameVariables.IN_GAME = false;
			return;
		}
		
		this.spaceShip.move();
		
		// handling light star
		if (lightStar == null && randomGenerator.isLightStarGenerated()) {
			lightStar = new LightStar(randomGenerator.generateRandomX(), 0 - Constants.STAR_HEIGHT);
		}
		
		if (lightStar != null) {
			
			if (collisionDetector.collisionLightStarSpaceShip(lightStar, spaceShip)) {
				lightStar = null;
				laserLine = new LaserLine();
			} else {
				// when the star dies
				if (lightStar.getY() > Constants.BOARD_HEIGHT + Constants.STAR_HEIGHT) {
					lightStar = null;
				} else {
					lightStar.move();
				}
			}
		}
		
		if (laserLine != null) {
			
			List<Meteor> meteorsToDie = new ArrayList<>();
			
			for (Meteor meteor : this.meteors) {
				
				// the x dimension of the laser line
				if (collisionDetector.collisionLaserLineMeteor(laserLine, meteor)) {
					GameVariables.SCORE += 20;
					soundFactory.explosion();
					meteorsToDie.add(meteor);
				}
			}
			
			this.meteors.removeAll(meteorsToDie);
			
			laserLine.move();
			
			if (laserLine.getY() < 0 - Constants.LASER_LINE_HEIGHT) {
				laserLine = null;
			}
		}
		
		//  meteors
		if (randomGenerator.isMeteorGenerated()) {
			int meteorX = randomGenerator.generateRandomX();
			int meteorY = 0 - Constants.METEOR_HEIGHT;
			Meteor meteor = new Meteor(meteorX, meteorY);
			this.meteors.add(meteor);
		}
		
		for (Meteor meteor : this.meteors) {
			if (meteor.getY() > Constants.BOARD_HEIGHT) {
				spaceShip.setDead(true);
			} else {
				meteor.move();
			}
		}
		
		Meteor destroyedMeteor = null;
		Laser destroyedLaser = null;
		
		// lasers meteors collision
		for (Laser laser : this.lasers) {
			if (!laser.isDead()) {
				
				// enemy-laser collisions
				for (Meteor meteor : this.meteors) {
					
					// collision related constraints
					if (collisionDetector.collisionLaserMeteor(laser, meteor)) {
						
						soundFactory.explosion();
						destroyedMeteor = meteor;
						destroyedLaser = laser;
						GameVariables.SCORE += 20;
					}
				}
				
				this.meteors.remove(destroyedMeteor);
			}
		}
		
		this.lasers.remove(destroyedLaser);
		
		// moving the laser
		for (Laser laser : this.lasers) {
			if (laser.getY() < 0) {
				destroyedLaser = laser;
			} else {
				laser.move();
			}
		}
		
		this.lasers.remove(destroyedLaser);
		
		destroyedMeteor = null;
		for (Meteor meteor : this.meteors) {
			
			if (collisionDetector.collisionMeteorSpaceShip(meteor, spaceShip)) {
				soundFactory.explosion();
				destroyedMeteor = meteor;
				GameVariables.SHIELDS--;
				if (GameVariables.SHIELDS < 0)
					spaceShip.setDead(true);
			}
		}
		
		this.meteors.remove(destroyedMeteor);
	}
	
	// game loop
	public void doOneLoop() {
		update();
		repaint();
	}
	
	public void keyReleased(KeyEvent e) {
		this.spaceShip.keyReleased(e);
	}
	
	public void keyPressed(KeyEvent e) {
		this.spaceShip.keyPressed(e);
		
		int x = spaceShip.getX();
		int y = spaceShip.getY();
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_SPACE) {
			
			if (GameVariables.IN_GAME) {
				soundFactory.laser();
				lasers.add(new Laser(x, y));
			}
		}
	}
}