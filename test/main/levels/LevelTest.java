package main.levels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.gfx.Assets;
import main.input.KeyBoard;
import main.menu.LevelSelectorStep;
import main.menu.Window;
import main.mocks.mocklevel;

class LevelTest {
	
	public Level level;
	public mocklevel mock;
	public Window window;
	public LevelSelectorStep levelSelectorState;
	private int[][] maze;
	private int player_row ;
	private int player_col ;
	
	
	@BeforeEach
	protected
	void init() {
		this.maze = new int[] []{
				{1, 1, 1, 1, 1},
				{1, 0, 0, 0, 1},
				{1, 0, 1, 0, 1},
				{1, 0, 0, 0, 1},
				{1, 1, 1, 1, 1}
			};
		this.player_row = 1;
		this.player_col = 1;
		levelSelectorState = new LevelSelectorStep(window);
		level = new Level(maze, player_row, player_col, levelSelectorState);
		mock= new mocklevel(maze,player_row,player_col,levelSelectorState);
		
	}
	
	@Test
	void testReset() {
	    // reset the level
	    level.reset();
	    assertArrayEquals(maze, level.getMaze());
	    assertEquals(player_row, level.getPlayer_row());
	    assertEquals(player_col, level.getPlayer_col());
	    maze[2][2] = 0;
	    level.setPlayer_col(2);
	    level.setPlayer_row(2);
	    // save the modified maze and player position
	    int[][] modifiedMaze = level.getMaze();
	    int modifiedPlayerRow = level.getPlayer_row();
	    int modifiedPlayerCol = level.getPlayer_col();
	    assertEquals(modifiedMaze, level.getMaze());
	    assertEquals(modifiedPlayerRow, level.getPlayer_row());
	    assertEquals(modifiedPlayerCol, level.getPlayer_col());
	}
	
	@Test
    void testInitlevel() {
        // Set up
        int[][] expectedCopy = new int[][] {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };

        level.initlevel(maze, player_row, player_col, levelSelectorState);

        assertArrayEquals(expectedCopy,maze);
        assertEquals(player_row, level.getPlayer_row());
        assertEquals(player_col, level.getPlayer_col());
        assertFalse(level.isSolved());
        assertEquals((Window.WIDTH - maze[0].length*Level.TILESIZE)/2, level.getxOffset());
        assertEquals((Window.HEIGHT - maze.length*Level.TILESIZE)/2, level.getyOffset());
        assertEquals(Assets.PlayerFront, level.getTexture());
    }
	
	@Test
    public void testUpdate() {
        assertEquals(mock.updated,0);
        mock.update();
        assertEquals(mock.updated,1);
        
    }
	
	 @Test
	    void testKeyboardMove() {
	        level.keyboardmove();
	        // Move up
	        KeyBoard.UP = true;
	        @SuppressWarnings("unused")
			long time = System.currentTimeMillis() + Level.DELAY;
	        level.keyboardmove();
	        assertTrue(KeyBoard.UP); 
	        assertFalse(level.getPlayer_row() < player_row); 
	        assertEquals(Assets.playerBack, level.getTexture());
	        // Move left
	        KeyBoard.LEFT = true;
	        time = System.currentTimeMillis() + Level.DELAY;
	        level.keyboardmove();
	        assertTrue(KeyBoard.LEFT);
	        assertFalse(level.getPlayer_col() < player_col);
	        assertEquals(Assets.playerLeft, level.getTexture());
	        // Move down
	        KeyBoard.DOWN = true;
	        time = System.currentTimeMillis() + Level.DELAY;
	        level.keyboardmove();
	        assertTrue(KeyBoard.DOWN);
	        assertFalse(level.getPlayer_row() > player_row);
	        assertEquals(Assets.PlayerFront, level.getTexture());
	 }
	 
}