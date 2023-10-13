package main;

import main.menu.Window;

/**
The Main class is the entry point of the game. It creates a new instance of the {@link Window} class and starts the game loop by calling its {@link Window#start()} method.
*/
public class Main {
	
	/**
	The main method creates a new instance of the {@link Window} class and starts the game loop by calling its {@link Window#start()} method.
	@param args The command line arguments passed to the program.
	*/
	public static void main(String[] args) {
		Window window= new Window();
		window.start();
	}
}
