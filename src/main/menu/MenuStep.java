package main.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.gfx.Text;
import main.gfx.Assets;
import main.ui.Button;
import main.ui.Click;


/**
The MenuStep class represents the menu step of the game. It extends the Step class and implements the update and render methods.
*/
public class MenuStep extends Step {
	
	private ArrayList<Button> buttons = new ArrayList<Button>();//It contains a list of buttons that can be updated and rendered.
	
	
	/**
	Constructor for the MenuStep class.
	@param window The game window.
	*/
	public MenuStep(Window window) {
		super(window);
		getButtons().add(new Button("PLAY", Window.WIDTH/2, Window.HEIGHT/2 - 50, new Click(){

			@Override
			public void onClick() {
				Step.currentState = window.getLevelSelectorStep();
			}}, Assets.font50));
		getButtons().add(new Button("EXIT", Window.WIDTH/2, Window.HEIGHT/2 + 50, new Click(){

			@Override
			public void onClick() {
				System.exit(1);
			}}, Assets.font50));
	}
	
	/**
	The update method for the MenuStep class.
	*/
	@Override
	public void update() {
		for(int i = 0; i < getButtons().size(); i++)
			getButtons().get(i).update();
	}
	
	/**
	The render method for the MenuStep class.
	@param g The Graphics object used for rendering.
	*/
	@Override
	public void render(Graphics g) {
		g.setFont(Assets.font50);
		Text.drawString(g, "SOKOBAN", Window.WIDTH/2, Window.HEIGHT/2 - 200, true, Color.BLACK);
		for(int i = 0; i < getButtons().size(); i++)
			getButtons().get(i).render(g);
	}

	public ArrayList<Button> getButtons() {
		return buttons;
	}

}
