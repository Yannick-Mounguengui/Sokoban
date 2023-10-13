package main.menu;

import java.awt.Graphics;
import main.levels.*;

/**
The GameStep class represents the step of the game where the user plays a level.
It extends the Step abstract class and implements the update() and render() methods.
*/
public class GameStep extends Step {
	
	/*
	 * The level currently being played in the game step
	 */
	private Level level;
	

	/**
	Constructs a GameStep object with the given Window.
	@param window the Window object where the GameStep will be displayed.
	*/
	public GameStep(Window window) {
		super(window);
	}

	/**
	Updates the level currently being played.
	*/
	@Override
	public void update() {
		level.update();
	}
	
	/**
	Renders the level currently being played.
	@param g the Graphics object used to render the level.
	*/
	@Override
	public void render(Graphics g) {
		level.render(g);
	}
	
	public Level getLevel() {
		return level;
	}
	
	/**
	Sets the level to be played.
	@param level the Level object to be played.
	*/
	public void setLevel(Level level){
		this.level = level;
	}
}
