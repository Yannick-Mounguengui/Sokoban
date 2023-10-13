package main.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.gfx.Text;
import main.input.MouseManager;

/**
A button class that can be clicked on.
*/
public class Button {
	
	public String text; // The text on the button
	public int x, y; // The x and y coordinates of the button
	private FontMetrics fm; // The font metrics object used to measure the button text
	public Rectangle bounds; // The bounding box of the button
	public boolean hovering; // A flag that indicates whether the mouse is hovering over the button
	private Click click; // The click handler for the button
	public Font font; // The font used for the button text
	
	/**
	Creates a new button with the specified text, position, click handler, and font.
	@param text the text to display on the button
	@param x the x coordinate of the button
	@param y the y coordinate of the button
	@param click the click handler to be called when the button is clicked
	@param font the font used for the button text
	*/
	public Button(String text, int x, int y, Click click, Font font){
		this.text = text;
		this.x = x;
		this.y = y;
		this.click = click;
		hovering = false;
		this.font = font;
	}
	
	public Font getFont() {
		return font;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Click getClick() {
		return click;
	}

	/**
	Updates the button's state based on the mouse position and button state.
	*/
	public void update(){		
		if(bounds != null && bounds.contains(MouseManager.x, MouseManager.y)){
			hovering = true;
			if(MouseManager.left)
				click.onClick();
		}else
			hovering = false;
	}
	
	/**
	Renders the button to the specified graphics context.
	@param g the graphics context to render the button to
	*/
	public void render(Graphics g){
		g.setFont(font);
		fm = g.getFontMetrics();
		if(hovering)
			Text.drawString(g, text, x, y, true, Color.DARK_GRAY);
		else
			Text.drawString(g, text, x, y, true, Color.WHITE);
		bounds = new Rectangle(x - fm.stringWidth(text)/2, y - fm.getHeight()/2, fm.stringWidth(text), fm.getHeight());
	}
}
