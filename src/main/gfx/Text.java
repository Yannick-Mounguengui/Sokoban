package main.gfx;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
A utility class for drawing text on a graphics context.
*/
public class Text {
	
	/**
	Constructor for the Text class.
	*/
	public Text() {
		
	}
	
	/**
	Draws a string of text on the specified graphics context.
	@param g the graphics context to draw on
	@param text the text to draw
	@param posX the x-coordinate of the text's starting position
	@param posY the y-coordinate of the text's starting position
	@param center whether to center the text horizontally and vertically around the starting position
	@param c the color to use for the text
	*/
	public static void drawString(Graphics g, String text, int posX, int posY, boolean center, Color c)
	{
		g.setColor(c);
		int x = posX;
		int y = posY;
		FontMetrics fm = g.getFontMetrics();
		if(center)
		{			
			x = x - fm.stringWidth(text)/2;
			y = (y - fm.getHeight()/2) + fm.getAscent();
		}		
		g.drawString(text, x, y);
	}
}
