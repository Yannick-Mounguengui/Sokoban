package main.levels;

import java.awt.Graphics;
import java.awt.Image;
import main.gfx.Assets;
import main.input.KeyBoard;
import main.menu.Window;
import main.menu.LevelSelectorStep;
import main.menu.Step;
import main.ui.Button;
import main.ui.Click;

/**
The Level class represents a single level in the game. It contains the maze layout, player position,

textures, and other level-related data.
*/

public class Level {
	
	/**
	The size of each tile in pixels.
	 */	
	public static final int TILESIZE = 48;
	
	private int[][] maze; // The 2D array representing the maze layout

	private int[][] copy; // A copy of the maze array, used for resetting the level

	private int player_row; // The row of the player's current position in the maze
	private int player_col; // The column of the player's current position in the maze

	private Image texture; // The texture used to display the maze

	private int xOffset, yOffset; // The offset of the maze from the top left corner of the screen

	private long time, lastTime; // Used for timing animations and movements

	public final static int DELAY = 150; // The delay between movements in milliseconds

	private Button restart, back; // The restart and back buttons for the level

	private boolean solved; // Whether the level has been solved or not

	private int plaStartRow, plaStartCol; // The starting position of the player

	private LevelSelectorStep levelSelectorState; // The level selector state that this level belongs to

	/**
	The ID of the level. This is a static field that is incremented each time a new level is created.
	*/
	public static int ID = 0;
	private int id; // The unique ID of the level
	
	/**
	Constructs a new Level object with the specified maze, player position, and level selector state.
	@param maze the maze for the level
	@param player_row the row index of the player's starting position
	@param player_col the column index of the player's starting position
	@param levelSelectorState the level selector state for returning to the level selector screen
	*/
	public Level(int[][] maze, int player_row, int player_col, LevelSelectorStep levelSelectorState) {
		this.levelSelectorState = levelSelectorState;
		this.maze = maze;
		ID ++;
		id = ID;
		copy = new int[maze.length][maze[0].length];
		initlevel(maze, player_row, player_col, levelSelectorState);
	}
	
	public int[][] getMaze() {
		return maze;
	}

	public int getPlayer_row() {
		return player_row;
	}

	public int getPlayer_col() {
		return player_col;
	}

	public Image getTexture() {
		return texture;
	}

	public int getxOffset() {
		return xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}
	
	public void setPlayer_row(int player_row) {
		this.player_row = player_row;
	}

	public void setPlayer_col(int player_col) {
		this.player_col = player_col;
	}

	/**
	Initializes the level with the specified maze, player position, and level selector state. This method
	copies the maze array and sets up the buttons and variables for the level.
	@param maze the maze for the level
	@param player_row the row index of the player's starting position
	@param player_col the column index of the player's starting position
	@param levelSelectorState the level selector state for returning to the level selector screen
	*/
	public void initlevel(int[][] maze, int player_row, int player_col, LevelSelectorStep levelSelectorState) {
		for(int row = 0; row < maze.length; row++){
			for(int col = 0; col < maze[row].length; col ++)
				copy[row][col] = maze[row][col];
		plaStartRow = player_row;
		plaStartCol = player_col;
		this.player_row = player_row;
		this.player_col = player_col;
		if(ID == 1)
			solved = true;
		else
			solved = false;
		xOffset = (Window.WIDTH - maze[0].length*TILESIZE)/2;
		yOffset = (Window.HEIGHT - maze.length*TILESIZE)/2;
		texture = Assets.PlayerFront;
		restart = new Button("RESTART", 100, Window.HEIGHT/2, new Click(){

			@Override
			public void onClick() {
				reset();
				
			}},
				Assets.font40);
		back = new Button("BACK", Window.WIDTH - 100, Window.HEIGHT/2, new Click(){

			@Override
			public void onClick() {
				Step.currentState = levelSelectorState;
				
			}},
				Assets.font40);
		
		time = 0;
		lastTime = System.currentTimeMillis();
		}
	}
	
	/**
	Resets the level by setting the maze to its initial state and the player position to its starting position.
	*/
	public void reset(){
		for(int row = 0; row < maze.length; row++)
			for(int col = 0; col < maze[row].length; col ++)
				maze[row][col] = copy[row][col];
		
		player_row = plaStartRow;
		player_col = plaStartCol;
		texture = Assets.PlayerFront;
	}
	
	/**
	Updates the level by handling keyboard inputs, updating buttons, and checking for completion.
	Keyboard inputs are used to move the player around the maze.
	The buttons are updated to check if they are clicked.
	It checks if the maze has been completed by looping through every tile and looking for the end point.
	If the end point is found, the level is marked as solved and the current state is set to the Level Selector state.
	*/
	public void update(){
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();		
		keyboardmove();		
		restart.update();
		back.update();		
		for(int row = 0; row < maze.length; row++)
			for(int col = 0; col < maze[row].length; col ++)
				if(maze[row][col] == 2)
					return;
		
		levelSelectorState.getLevels()[id].setSolved(true);
		Step.currentState = levelSelectorState;
		
	}
	
	/**
	Moves the player in response to keyboard input.
	Checks for delays between moves to prevent rapid movement.
	Updates the texture to reflect the player's movement direction.
	*/
	public void keyboardmove() {
		if(KeyBoard.UP && time > DELAY){
			move(-1, 0);
			texture = Assets.playerBack;
		}
		if(KeyBoard.LEFT && time > DELAY){
			move(0, -1);
			texture = Assets.playerLeft;
		}
		if(KeyBoard.DOWN && time > DELAY){
			move(1, 0);
			texture = Assets.PlayerFront;
		}
		if(KeyBoard.RIGHT && time > DELAY){
			move(0, 1);
			texture = Assets.playerRight;
		}
	}
	
	/**
	Moves the player by the given amount of rows and columns, updates the current time to 0
	@param row the amount of rows to move the player by
	@param col the amount of columns to move the player by
	*/
	public void move(int row, int col){
		moveplayer(row, col);
		time = 0;
	}
	
	/**
	Moves the player in the specified direction by modifying the maze array.
	@param row the number of rows to move (negative for up, positive for down)
	@param col the number of columns to move (negative for left, positive for right)
	*/
	public void moveplayer(int row, int col) {
		if(maze[player_row + row][player_col + col] != 1){
			if(maze[player_row + row][player_col + col] == 2 || maze[player_row + row][player_col + col] == 4){
				if(maze[player_row + row*2][player_col + col*2] == 1 ||
						maze[player_row + row*2][player_col + col*2] == 2 ||
						maze[player_row + row*2][player_col + col*2] == 4)
					return;
				if(maze[player_row + row][player_col + col] == 4){
					maze[player_row + row][player_col + col] = 3;			
					if(maze[player_row + row*2][player_col + col*2] == 3)
						maze[player_row + row*2][player_col + col*2] = 4;
					else
						maze[player_row + row*2][player_col + col*2] = 2;
				}else{
					maze[player_row + row][player_col + col] = 0;
					if(maze[player_row + row*2][player_col + col*2] == 3)
						maze[player_row + row*2][player_col + col*2] = 4;
					else
						maze[player_row + row*2][player_col + col*2] = 2;
					
				}				
			}
			player_row += row;
			player_col += col;
		}
	}
	
	/**
	Renders the current state of the maze game
	@param g the graphics context to use for rendering
	*/
	public void render(Graphics g){		
		restart.render(g);
		back.render(g);
		
		drawlevel(g);
		
		g.drawImage(texture, xOffset + player_col*TILESIZE, yOffset + player_row*TILESIZE, null);	
	}
	
	/**
	Draws the maze level on the screen, including walls, floor, boxes, and spots.
	@param g the Graphics object used for drawing
	*/
	public void drawlevel(Graphics g) {
		for(int row = 0; row < maze.length; row++){
			for(int col = 0; col < maze[row].length; col ++){
				g.drawImage(Assets.floor, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 1)
					g.drawImage(Assets.wall, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 2)
					g.drawImage(Assets.boxOff, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 3)
					g.drawImage(Assets.spot, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 4)
					g.drawImage(Assets.boxOn, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
			}
		}
	}
	
	/**
	Returns whether or not the level is solved.
	@return true if the level is solved, false otherwise.
	*/
	public boolean isSolved(){return solved;}
	/**
	Sets whether the level is solved or not.
	@param bool true if the level is solved, false otherwise.
	*/
	public void setSolved(boolean bool){solved = bool;}
}
