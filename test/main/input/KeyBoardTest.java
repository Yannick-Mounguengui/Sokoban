package main.input;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.Test;

class KeyBoardTest {
	
	@Test
	void testUpdate() {
		KeyBoard keyboard = new KeyBoard();
		keyboard.keys[KeyEvent.VK_UP] = true;
		keyboard.update();
		assertTrue(KeyBoard.UP);
		assertFalse(KeyBoard.DOWN);
		assertFalse(KeyBoard.LEFT);
		assertFalse(KeyBoard.RIGHT);
		
		keyboard.keys[KeyEvent.VK_DOWN] = true;
		keyboard.keys[KeyEvent.VK_LEFT] = true;
		keyboard.keys[KeyEvent.VK_RIGHT] = true;
		keyboard.update();
		assertTrue(KeyBoard.UP);
		assertTrue(KeyBoard.DOWN);
		assertTrue(KeyBoard.LEFT);
		assertTrue(KeyBoard.RIGHT);
		
		keyboard.keys[KeyEvent.VK_UP] = false;
		keyboard.keys[KeyEvent.VK_DOWN] = false;
		keyboard.keys[KeyEvent.VK_LEFT] = false;
		keyboard.keys[KeyEvent.VK_RIGHT] = false;
		keyboard.update();
		assertFalse(KeyBoard.UP);
		assertFalse(KeyBoard.DOWN);
		assertFalse(KeyBoard.LEFT);
		assertFalse(KeyBoard.RIGHT);
	}
	

}

