package main.mocks;

import main.menu.LevelSelectorStep;
import main.menu.Window;

public class mocklevelselectorstep extends LevelSelectorStep{
	
	public int called;
	public mocklevelselectorstep(Window window) {
		super(window);
		this.called=0;
	}
	
	@Override
	public void update(){
		this.called+=1;
	}
	
}
