package main.mocks;

import main.levels.Level;
import main.menu.LevelSelectorStep;

public class mocklevel extends Level {
	
	@SuppressWarnings("unused")
	public int updated;
	
	public mocklevel(int[][] maze, int player_row, int player_col, LevelSelectorStep levelSelectorState) {
		super(maze, player_row, player_col, levelSelectorState);
		this.updated=0;
	}
	
	@Override
	public void update(){
		this.updated+=1;
		
	}
}
