package main.menu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.mocks.mockloadingstep;

class LoadingStepTest {
	
	private Window window;
	private LoadingStep load;
	private mockloadingstep mock;
	
	@BeforeEach
	void init() {
		window=new Window();
		load=new LoadingStep(window);
		mock=new mockloadingstep(window);
	}
	
	@Test
	void testUpdate() {
		assertEquals(mock.called,0);
		mock.update();
		assertEquals(mock.called,1);
	}

}
