package main.menu;

import java.awt.Graphics;

/**
The abstract class Step represents a step in the game's user interface, such as a menu or a game level.
It defines a window and contains abstract methods for updating and rendering the user interface.
A static variable currentState is defined to keep track of the current state of the game interface.
Concrete implementations of the Step class must implement the update() and render(Graphics g) methods.
*/
public abstract class Step{
	
	/**
	A static variable that keeps track of the current state of the game interface.
	*/
	public static Step currentState = null;
	
	/**
	The window associated with this step.
	*/
	protected Window window;
	
	/**
	Constructor for the Step class.
	@param window The window associated with this step.
	*/
	public Step(Window window){
		this.window = window;
	}
	
	/**
	Updates the user interface for this step.
	*/
	public abstract void update();
	
	/**
	Renders the user interface for this step.
	@param g The graphics object to render with.
	*/
	public abstract void render(Graphics g);
		
}

