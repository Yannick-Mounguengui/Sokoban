package main.menu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.mocks.mocklevel;


class GameStepTest {
	
	private GameStep gamestep;
	public mocklevel mocklevel;
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
	mocklevel= new mocklevel(maze,player_row,player_col,levelSelectorState);
	gamestep=new GameStep(window);
	
	}
	
	@Test
	void testSetLevel() {
	    gamestep.setLevel(mocklevel);	    
	    // Verify that the level was set correctly
	    assertEquals(mocklevel, gamestep.getLevel());
	}
	
	@Test
	void testUpdate() {
	    gamestep.setLevel(mocklevel);	    
	    assertEquals(mocklevel.updated,0);
	    // Call the update() method
	    gamestep.update();	    
	   assertEquals(mocklevel.updated,1);
	}
}
