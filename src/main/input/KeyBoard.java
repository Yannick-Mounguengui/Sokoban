package main.input;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
A class for managing keyboard input by implementing the KeyListener interface.
*/
public class KeyBoard implements KeyListener{
	/**
	An array of booleans representing the current state of all keyboard keys. Each index corresponds to a specific key,
	where the index is given by the virtual key code of that key. For example, if keys[KeyEvent.VK_UP] is true, it means
	that the up arrow key is currently being pressed down.
	*/
	boolean[] keys;	
	/**

	A boolean flag indicating if the up,lefet,right,down arrow key is currently pressed or not.
	*/
	public static boolean UP, LEFT, RIGHT, DOWN;
	
	/**
	Constructs a new KeyBoard object and initializes the key array.
	*/
	public KeyBoard() {
		keys = new boolean[256];
		UP = false;
		DOWN = false;
		RIGHT = false;
		LEFT = false;
	}
	
	/**
	Updates the state of the arrow keys based on the current state of the key array.
	*/
	public void update(){
		UP = keys[KeyEvent.VK_UP];
		LEFT = keys[KeyEvent.VK_LEFT];
		RIGHT = keys[KeyEvent.VK_RIGHT];
		DOWN = keys[KeyEvent.VK_DOWN];
	}
	
	/**
	Called when a key is pressed down.
	Sets the state of the corresponding key to true.
	@param e The KeyEvent object containing information about the key that was pressed.
	*/
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	/**
	Called when a key is released.
	Sets the state of the corresponding key to false.
	@param e The KeyEvent object containing information about the key that was released.
	*/
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	/**
	Called when a key is typed (pressed and released).
	This method is not used in this implementation.
	@param arg0 The KeyEvent object containing information about the key that was typed.
	*/
	@Override
	public void keyTyped(KeyEvent arg0) {		
	}
}
