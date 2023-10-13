package main.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Font;
import java.awt.Rectangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.input.MouseManager;

public class ButtonTest {
	
	private Button button;
	
	@BeforeEach
	public void setUp() {
		button = new Button("Test Button", 50, 50, () -> System.out.println("Button clicked"), new Font("Arial", Font.PLAIN, 12));
	}
	
	@Test
	public void testButtonInitialization() {
		assertEquals("Test Button", button.text);
		assertEquals(50, button.x);
		assertEquals(50, button.y);
		assertEquals(new Font("Arial", Font.PLAIN, 12), button.font);
		assertFalse(button.hovering);
	}
	
	@Test
	public void testButtonUpdate() {
		button.bounds = new Rectangle(40, 40, 40, 20);
		MouseManager.x = 45;
		MouseManager.y = 45;
		MouseManager.left = true;
		button.update();
		assertTrue(button.hovering);
	}
}
