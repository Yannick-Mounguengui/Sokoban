package main.menu;

import java.awt.Color;
import java.awt.Graphics;

import main.gfx.*;

/**
The LoadingStep class represents a step in the game where the game is loading. This class extends the abstract class Step.
*/
public class LoadingStep extends Step {
	
	/*
	 * The text displayed on the loading screen
	 */
	private String text = "";
	
	/**
	Constructs a LoadingStep object with the specified Window object.
	@param window the Window object for this LoadingStep
	*/
	public LoadingStep(Window window) {
		super(window);
	}
	
	/**
	Updates the LoadingStep.
	This method updates the current state of the LoadingStep.
	*/
	@Override
	public void update() {
		Step.currentState =  window.getMenuStep();
	}
	
	/**
	Renders the LoadingStep.
	This method renders the current state of the LoadingStep on the screen.
	@param g the Graphics object used for rendering
	*/
	@Override
	public void render(Graphics g) {
		g.setFont(Assets.font40);
		Text.drawString(g, text, Window.WIDTH/2, Window.HEIGHT/2, true, Color.WHITE);
		
	}

}
