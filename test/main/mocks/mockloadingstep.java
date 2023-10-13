package main.mocks;

import main.menu.LoadingStep;
import main.menu.Window;

public class mockloadingstep extends LoadingStep {
	
	public int called;
	
	public mockloadingstep(Window window) {
		super(window);
		called=0;
	}
	
	@Override
	public void update() {
		called+=1;
	}

}
