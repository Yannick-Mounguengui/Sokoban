package main.gfx;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.levels.Level;

class AssetsTest {

	@BeforeAll
	static void init() {
		Assets.init();
	}

	@Test
	void testPlayerImages() {
		Image left = Assets.playerLeft;
		Image back = Assets.playerBack;
		Image right = Assets.playerRight;
		Image front = Assets.PlayerFront;

		assertNotNull(left);
		assertNotNull(back);
		assertNotNull(right);
		assertNotNull(front);

		assertEquals(left.getWidth(null), Level.TILESIZE);
		assertEquals(left.getHeight(null), Level.TILESIZE);
		assertEquals(back.getWidth(null), Level.TILESIZE);
		assertEquals(back.getHeight(null), Level.TILESIZE);
		assertEquals(right.getWidth(null), Level.TILESIZE);
		assertEquals(right.getHeight(null), Level.TILESIZE);
		assertEquals(front.getWidth(null), Level.TILESIZE);
		assertEquals(front.getHeight(null), Level.TILESIZE);
	}

	@Test
	void testFloorImages() {
		Image floor = Assets.floor;
		Image floor2 = Assets.floor2;

		assertNotNull(floor);
		assertNotNull(floor2);

		assertEquals(floor.getWidth(null), Level.TILESIZE);
		assertEquals(floor.getHeight(null), Level.TILESIZE);
		assertEquals(floor2.getWidth(null), Level.TILESIZE);
		assertEquals(floor2.getHeight(null), Level.TILESIZE);
	}

	@Test
	void testWallImage() {
		Image wall = Assets.wall;

		assertNotNull(wall);

		assertEquals(wall.getWidth(null), Level.TILESIZE);
		assertEquals(wall.getHeight(null), Level.TILESIZE);
	}

	@Test
	void testBoxImages() {
		Image boxOn = Assets.boxOn;
		Image boxOff = Assets.boxOff;

		assertNotNull(boxOn);
		assertNotNull(boxOff);

		assertEquals(boxOn.getWidth(null), Level.TILESIZE);
		assertEquals(boxOn.getHeight(null), Level.TILESIZE);
		assertEquals(boxOff.getWidth(null), Level.TILESIZE);
		assertEquals(boxOff.getHeight(null), Level.TILESIZE);
	}

	@Test
	void testSpotImage() {
		Image spot = Assets.spot;

		assertNotNull(spot);

		assertEquals(spot.getWidth(null), Level.TILESIZE);
		assertEquals(spot.getHeight(null), Level.TILESIZE);
	}

	@Test
	void testOutlineImages() {
		Image outline = Assets.outline;
		Image outline2 = Assets.outline2;

		assertNotNull(outline);
		assertNotNull(outline2);

		assertEquals(outline.getWidth(null), 64);
		assertEquals(outline.getHeight(null), 64);
		assertEquals(outline2.getWidth(null), 64);
		assertEquals(outline2.getHeight(null), 64);
	}
	

	@Test
	void testLoadImage() {
		BufferedImage image = Assets.loadImage("/player/left.png");
		BufferedImage image2 = Assets.loadImage("/player/right.png");
		assertNotNull(image);
		assertNotEquals(image.getWidth(), Level.TILESIZE);
		assertNotEquals(image.getHeight(), Level.TILESIZE);
		assertNotSame(image,image2);
	}

}
