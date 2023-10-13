package main.menu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.mocks.mockmenustep;

class MenuStepTest {
	
	public MenuStep menu;
	public Window window;
	public mockmenustep mock;
	@BeforeEach
	protected
	void init() {
		window = new Window();
		menu = new MenuStep(window);
		mock=new mockmenustep(window);
		//g= new Graphics();
	}
	
	@Test
    void testButtonCount() {
        assertEquals(2, menu.getButtons().size(), "Menu should have two buttons");
    }
	
	@Test
    void testPlayButtonOnClick() {
        menu.getButtons().get(0).getClick().onClick();
        assertEquals(window.getLevelSelectorStep(), Step.currentState, "Click on PLAY button should switch to LevelSelectorStep");
    }
	
	@Test
	void testUpdate() {
		assertEquals(mock.called,0);
		mock.update();
		assertEquals(mock.called,1);
	}
	
}
