package main.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import main.gfx.Assets;
import main.gfx.Text;
import main.input.MouseManager;
import main.levels.Level;
import main.ui.Button;
import main.ui.Click;

/**
LevelSelectorStep is a class representing a step in the game's main menu, allowing the player to select a level to play.
It extends the abstract Step class.
*/
public class LevelSelectorStep extends Step {
	
	/*
	 * The size of each tile in pixels.
	 */
	private final int DOUBLETILESIZE = 64;
	/*
	 * An array of Level objects representing each level in the game
	 */
	private Level[] levels = new Level[10];
	/*
	 * The x offset used to center the level selector on the screen
	 */
	private final int xOffset = (Window.WIDTH - DOUBLETILESIZE*6)/2;
	/*
	 * The y offset used to center the level selector on the screen
	 */
	private final int yOffset = (Window.HEIGHT - DOUBLETILESIZE*5)/2;
	/*
	 * //The "back" button used to return to the main menu
	 */
	private Button back;
	
	/**
	Constructor for the LevelSelectorStep class.
	@param window the main game window
	*/
	public LevelSelectorStep(Window window) {
		super(window);	
		init(window);		
	}
	
	/**
	Initializes the levels array and the back button.
	@param window the main game window
	*/
	public void init(Window window) {
		for(int i = 0; i < levels.length; i++)
			levels[i] = loadLevel("/levels/"+i+".txt");
			
		back = new Button("BACK", Window.WIDTH/2, Window.HEIGHT - 100, new Click(){

			@Override
			public void onClick() {
				Step.currentState = window.getMenuStep();
			}
			
		}, Assets.font40);
	}
	
	public int getDOUBLETILESIZE() {
		return DOUBLETILESIZE;
	}

	public int getxOffset() {
		return xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public Button getBack() {
		return back;
	}
	
	/**
	Updates the back button.
	*/
	@Override
	public void update(){
		back.update();
	}
	
	/**
	Renders the level selector step, including the back button and the levels to select from.
	@param g the Graphics object used to render
	*/
	@Override
	public void render(Graphics g){
		back.render(g);
		int counter = 1;
		g.setFont(Assets.font40);
		drawlevel(g, counter);
		
	}
	
	/**
	Draws a single level button on the screen.
	@param g The Graphics object that is used to render the button.
	@param counter The number of the level that the button represents.
	*/
	public void drawlevel(Graphics g, int counter) {
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 5; j++){
				Rectangle bounds = new Rectangle(xOffset + j*DOUBLETILESIZE,
						yOffset + i*DOUBLETILESIZE, DOUBLETILESIZE, DOUBLETILESIZE);
				if(bounds.contains(MouseManager.x, MouseManager.y)){
					if(MouseManager.left && levels[counter-1].isSolved()){
						((GameStep)window.getGameStep()).setLevel(levels[counter-1]);
						Step.currentState = window.getGameStep();
					}
					g.drawImage(Assets.outline2, bounds.x, bounds.y, null);
					if(levels[counter-1].isSolved())
						Text.drawString(g, counter+"", xOffset + DOUBLETILESIZE/2 + j*DOUBLETILESIZE,
							yOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE, true, Color.RED);
					else
						Text.drawString(g,"?", xOffset + DOUBLETILESIZE/2 + j*DOUBLETILESIZE,
								yOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE, true, Color.RED);
				}else{
					g.drawImage(Assets.outline, bounds.x, bounds.y, null);
					if(levels[counter-1].isSolved())
						Text.drawString(g, counter+"", xOffset + DOUBLETILESIZE/2 + j*DOUBLETILESIZE,
							yOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE, true, Color.GREEN);
					else
						Text.drawString(g,"?", xOffset + DOUBLETILESIZE/2 + j*DOUBLETILESIZE,
								yOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE, true, Color.BLUE);
				}
				counter ++;
			}
		}
	}
	
	/**
	Loads a level from a file.
	@param path the path of the file containing the level data
	@return a Level object created from the data in the file
	*/
	public Level loadLevel(String path){
		
		String file = loadFileAsString(path);
		String[] numbers = file.split("\\s+");
		int cols = parseInt(numbers[0]);
		int rows = parseInt(numbers[1]);
		int player_col = parseInt(numbers[2]);
		int player_row = parseInt(numbers[3]);
		int[][] maze = new int[rows][cols];
		for(int row = 0; row < rows; row++)
			for(int col = 0; col < cols; col++)
				maze[row][col] = parseInt(numbers[(col + (row*cols)) + 4]);
		
		return new Level(maze, player_row, player_col, this);
	}
	
	/**
	 * This method returns an array of Level objects. The levels are loaded from text files and stored in the levels array.
	 * @return an array of Level objects.
	 */
	public Level[] getLevels(){return levels;}
	
	
	/**
	Loads a file as a string from the specified path.
	@param path The path to the file.
	@return A string containing the contents of the file.
	*/
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();
		try{
		InputStream in = LevelSelectorStep.class.getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		String line;
		while((line = br.readLine()) != null){
			builder.append(line+ "\n");
		}
		br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	/**
	Converts a string representation of an integer into an integer.
	@param numero the string representation of the integer to be parsed.
	@return the integer represented by the input string, or 0 if the string is not a valid integer.
	*/
	public static int parseInt(String numero){
		try{
			return Integer.parseInt(numero);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
}
