package main.menu;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.gfx.Assets;


class WindowTest {
	
	private Window window;
	
	@BeforeEach
	void init(){
		window=new Window();
	}

	@Test
    void testCanvasInit() {
        Canvas canvas = window.getCanvas();
        assertNotNull(canvas);
        Dimension expectedSize = new Dimension(Window.WIDTH, Window.HEIGHT);
        assertEquals(expectedSize, canvas.getPreferredSize());
        assertEquals(expectedSize, canvas.getMaximumSize());
        assertEquals(expectedSize, canvas.getMinimumSize());
        assertTrue(canvas.isFocusable());
    }
	
	@Test
    void testAwtInit() {
        assertEquals("Sokoban Game", window.getTitle());
        assertEquals(Window.WIDTH, window.getWidth());
        assertEquals(Window.HEIGHT, window.getHeight());
        assertEquals(JFrame.EXIT_ON_CLOSE, window.getDefaultCloseOperation());
        assertNotNull(window.getLayout());
        assertFalse(window.isResizable());
    }
	
	@Test
    void testInit() {
        // Call init method
        window.init();
        
        // Verify that the assets are initialized
        assertNotNull(Assets.floor2);
        
        // Verify that the menu, game, loading, and level selector steps are created
        assertNotNull(window.getMenuStep());
        assertNotNull(window.getGameStep());
        assertNotNull(window.getLoadingStep());
        assertNotNull(window.getLevelSelectorStep());
        
        // Verify that the current state is set to the loading step
        assertTrue(Step.currentState instanceof LoadingStep);
    }

}
