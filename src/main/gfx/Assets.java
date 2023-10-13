package main.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.levels.Level;

/**

The Assets class is responsible for loading and storing all of the game's assets.

This includes images and fonts used in the game.
*/
public class Assets {
	
	/**
	Image objects for player sprites facing different directions.
	*/
	public static Image playerLeft, playerBack, playerRight, PlayerFront;
	
	/**
	Image objects for floor tiles, wall tiles, box tiles, and goal tiles.
	*/
	public static Image floor, floor2, wall, boxOn, boxOff, spot, outline, outline2;
	
	/**
	Font objects for different text sizes in the game.
	*/
	public static Font font50;
	/**
	Font objects for different text sizes in the game.
	*/
	public static Font font40;
	/**
	Font objects for different text sizes in the game.
	*/
	public static Font font42;
	
	/**
	Constructor for the Assets class.
	*/
	public Assets() {
		
	}
	/**
	Loads an image from a specified file path.
	@param path the file path of the image to load
	@return the loaded image as a BufferedImage object
	*/
	public static BufferedImage loadImage(String path)
	{
		try {
			
			return ImageIO.read(Assets.class.getResource(path));
		} catch (IOException e) {
			System.out.println("image can't be load");
		}
		return null;
	}
	
	/**
	Loads a font from a specified file path and size.
	@param path the file path of the font to load
	@param size the size of the font to load
	@return the loaded font as a Font object
	*/
	public static Font loadFont(String path, int size){
		try {
			return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			System.out.println("image can't be load");
		}
		return null;
	}
	
	/**
	Initializes all of the game's assets by loading images and fonts.
	*/
	public static void init() {
		playerLeft = loadImage("/player/left.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		playerBack = loadImage("/player/back.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		PlayerFront = loadImage("/player/front.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		playerRight = loadImage("/player/right.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		
		floor = loadImage("/blocks/ground.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		floor2 = loadImage("/blocks/ground2.jpg").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		wall = loadImage("/blocks/redBrick.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		boxOn = loadImage("/blocks/boxOn.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		boxOff = loadImage("/blocks/boxOff.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		spot = loadImage("/blocks/spot.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		outline = loadImage("/blocks/outline.png").getScaledInstance(64, 64, BufferedImage.SCALE_DEFAULT);
		outline2 = loadImage("/blocks/outline2.png").getScaledInstance(64, 64, BufferedImage.SCALE_DEFAULT);
		
		font50 = loadFont("res/fonts/code.ttf", 50);
		font40 = loadFont("res/fonts/code.ttf", 40);
		font42 = loadFont("res/fonts/code.ttf", 42);
	}
	
	
}
