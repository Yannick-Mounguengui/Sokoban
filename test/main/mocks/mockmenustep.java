package main.mocks;

import main.gfx.Assets;
import main.menu.MenuStep;
import main.menu.Step;
import main.menu.Window;
import main.ui.Button;
import main.ui.Click;

public class mockmenustep extends MenuStep {
	
	public int called;
	
	public mockmenustep(Window window) {
		super(window);
		this.called=0;
		getButtons().add(new Button("PLAY", Window.WIDTH/2, Window.HEIGHT/2 - 50, new Click(){

			@Override
			public void onClick() {
				Step.currentState = window.getLevelSelectorStep();
			}}, Assets.font50));
		getButtons().add(new Button("EXIT", Window.WIDTH/2, Window.HEIGHT/2 + 50, new Click(){

			@Override
			public void onClick() {
				System.exit(1);
			}}, Assets.font50));
	}
	
	@Override
	public void update() {
		this.called+=1;
	}
	
}
