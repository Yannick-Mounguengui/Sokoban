package main.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
The MouseManager class is responsible for managing the state of the mouse input.
It extends MouseAdapter and overrides some of its methods to handle mouse events.
*/
public class MouseManager extends MouseAdapter {
	
	/**
	The x,y position of the mouse cursor.
	*/
	public static int x, y;
	/**
	A boolean indicating whether the left mouse button is currently pressed.
	*/
	public static boolean left;
	
	/**
	Initializes a new instance of the MouseManager class with default values for its properties.
	*/
	public MouseManager(){
		x = 0;
		y = 0;
	}
	
	/**
	Called when a mouse button is pressed.
	Sets the left property to true if the left mouse button is pressed.
	@param e The MouseEvent object containing information about the event.
	*/
	@Override
	public void mousePressed(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1)
			left = true;
	}
	
	/**
	Called when a mouse button is released.
	Sets the left property to false if the left mouse button is released.
	@param e The MouseEvent object containing information about the event.
	*/
	@Override
	public void mouseReleased(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1)
			left = false;
	}
	
	/**
	Called when the mouse is dragged.
	Sets the x and y properties to the current position of the mouse cursor.
	@param e The MouseEvent object containing information about the event.
	*/
	@Override
	public void mouseDragged(MouseEvent e){
		x = e.getX();
		y = e.getY();
	}
	
	/**
	Called when the mouse is moved.
	Sets the x and y properties to the current position of the mouse cursor.
	@param e The MouseEvent object containing information about the event.
	*/
	@Override
	public void mouseMoved(MouseEvent e){
		x = e.getX();
		y = e.getY();
	}
}
