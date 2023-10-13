package main.menu;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.gfx.Assets;
import main.levels.Level;
import main.mocks.mocklevelselectorstep;

class LevelSelectorStepTest {
	
	private Window window;
	private LevelSelectorStep selector;
	private mocklevelselectorstep mock;
	
	@BeforeEach
	void init() {
		window=new Window();
		window.canvasInit();
		selector=new LevelSelectorStep(window);
		mock= new mocklevelselectorstep(window);
	}
	
	@Test
    void testInit() {
        selector.init(window);
        assertNotNull(selector.getBack());
        assertEquals(Window.WIDTH / 2, selector.getBack().getX());
        assertEquals(Window.HEIGHT - 100, selector.getBack().getY());
        assertEquals(Assets.font40, selector.getBack().getFont());
    }
	
	@Test
    void testLoadFileAsString() {
        // Set up test variables
        String path = "/levels/0.txt";
        String path1 = "/levels/copy0.txt";
        String path2 = "/levels/1.txt";

        // Call the method to be tested
        String content = LevelSelectorStep.loadFileAsString(path);
        String expectedContent = LevelSelectorStep.loadFileAsString(path1);
        String expectedContent1 = LevelSelectorStep.loadFileAsString(path2);

        // Verify that the content of the file is loaded correctly
        assertEquals(expectedContent, content);
        assertNotEquals(expectedContent1, content);
    }
	
	@Test
	void testLoadLevel() {
		// Set up test variables
		String path = "/levels/0.txt";
		int[][] Maze1 = {{1,1,1,1,1},{1,0,0,0,1},{1,0,2,0,1},{1,0,0,0,1},{1,1,1,1,1}};
		int expectedPlayerRow = 4;
		int expectedPlayerCol = 4;

		// Call the method to be tested
		Level actualLevel = selector.loadLevel(path);

		// Verify that the level is correctly loaded
		assertNotEquals(Maze1, actualLevel.getMaze());
		assertEquals(expectedPlayerRow, actualLevel.getPlayer_row());
		assertEquals(expectedPlayerCol, actualLevel.getPlayer_col());
	}
	
	@Test
	void testGetLevels() {
		// Set up test variables
		int expectedNumLevels = 10;

		// Call the method to be tested
		Level[] actualLevels = selector.getLevels();

		// Verify that the correct number of levels are returned
		assertEquals(expectedNumLevels, actualLevels.length);
	}
	
	@Test
	void testUpdate() {
		assertEquals(mock.called, 0);
		mock.update();
		assertEquals(mock.called, 1);
	}
}
