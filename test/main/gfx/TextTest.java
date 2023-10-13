package main.gfx;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TextTest {
	
	private static BufferedImage image;
	private static Graphics graphics;
	
	@BeforeAll
	static void setUp() {
		image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		graphics = image.getGraphics();
	}
	
	@Test
	void testDrawString() {
		Text.drawString(graphics, "Hello, world!", 50, 50, true, Color.BLACK);
		assertEquals(new Color(image.getRGB(50, 50)), Color.BLACK);
		assertEquals(new Color(image.getRGB(70, 50)), Color.BLACK);
		assertEquals(new Color(image.getRGB(30, 50)), Color.BLACK);
		Font font = graphics.getFont();
		FontMetrics fm = graphics.getFontMetrics(font);
		int expectedX = 50 - fm.stringWidth("Hello, world!") / 2;
		int expectedY = 50 - fm.getHeight() / 2 + fm.getAscent();
		assertEquals(new Color(image.getRGB(expectedX, expectedY)), Color.BLACK);
	}
}

