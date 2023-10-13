package main.menu;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import main.gfx.Assets;
import main.input.KeyBoard;
import main.input.MouseManager;

import main.levels.Level;


/**
The main window class that handles game rendering and updates.
The game window has a canvas, thread, and input managers for keyboard and mouse.
*/
public class Window extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	/**
	 *The width and height of the game window
	 */
	public static final int WIDTH = 800, HEIGHT = 600;
	/**
	 * The canvas to render the game on
	 */
	private Canvas canvas;
	/**
	 * The thread running the game loop
	 */
	private Thread thread;
	/**
	 * Flag indicating if the game is running or not
	 */
	private boolean running = false;
	/**
	 * The buffer strategy used for rendering
	 */
	private BufferStrategy bs;
	/**
	 * The graphics object used for rendering
	 */
	private Graphics g;
	/**
	 * The target frames per second
	 */
	private final int FPS = 60;
	/**
	 * The target time in nanoseconds per frame for achieving a certain frame rate
	 */
	private double TARGETTIME = 1000000000/FPS;
	/**
	 * The amount of time passed since the last frame in nanoseconds
	 */
	private double delta = 0;
	/**
	 * The game step containing the main gameplay logic
	 */
	private GameStep gameStep;
	/**
	 * The LevelSelectorStep that manages the level selection screen
	 */
	private LevelSelectorStep levelSelectorStep;
	/**
	 * The MenuStep that manages the main menu screen
	 */
	private MenuStep menuStep;
	/**
	 * The LoadingStep that manages the loading screen
	 */
	private LoadingStep loadingStep;
	/**
	 * The KeyBoard object responsible for handling keyboard input in the game
	 */
	private KeyBoard keyBoard;
	/**
	 * The MouseManager object responsible for handling mouse input in the game
	 */
	private MouseManager mouseManager;
	
	/**
	Constructor for the Window class. Initializes the AWT toolkit, canvas, keyboard and mouse manager,
	and adds them to the window. Sets the visibility of the window to true.
	*/	
	public Window() {
		
		awtInit();
		canvas = new Canvas();
		keyBoard = new KeyBoard();
		mouseManager = new MouseManager();
		
		this.canvasInit();
		
		add(canvas);
		addMouseMotionListener(mouseManager);
		addMouseListener(mouseManager);
		
		canvas.addMouseListener(mouseManager);
		canvas.addMouseMotionListener(mouseManager);
		canvas.addKeyListener(keyBoard);
		setVisible(true);
	}
	
	/**
	Initializes the canvas with preferred, maximum, and minimum dimensions of WIDTH and HEIGHT.
	Sets the canvas as focusable.
	*/
	public void canvasInit() {
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(true);
	}
	
	
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	Initializes AWT components of the game window
	*/
	public void awtInit() {
		setTitle("Sokoban Game");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
	}
	
	/**
	Starts the game loop by creating and starting a new thread.
	*/
	public void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
	Stops the game by joining the thread, making running false
	*/
	public void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			System.out.println("interrupted game");
		}
	}
	
	/**
	Updates the current game state by calling its update method, and also updates the keyboard input if the current state is GameStep.
	*/
	public void update(){
		if(Step.currentState instanceof GameStep)
			keyBoard.update();
		
		if(Step.currentState != null)
			Step.currentState.update();
	}
	
	/**
	Draws the game screen by first getting the buffer strategy of the canvas,
	then drawing a black rectangle to clear the screen, and then rendering
	the current state of the game. It also disposes of the graphics object
	and shows the buffer strategy.
	*/
	public void draw(){
		bs = canvas.getBufferStrategy();
		
		if(bs == null)
		{
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < Window.WIDTH/Level.TILESIZE + 1; i++)
			for(int j = 0; j < Window.HEIGHT/Level.TILESIZE + 1; j++)
				g.drawImage(Assets.floor2, i*Level.TILESIZE, j*Level.TILESIZE, null);
		
		if(Step.currentState != null)
			Step.currentState.render(g);
		g.dispose();
		bs.show();
	}
	
	/**
	Continuously updates and renders the game as long as the game is running.
	Uses the delta time to update the game at a fixed frame rate and avoids inconsistent
	behavior on different hardware.
	@param lastTime the time of the last frame
	@param frames the number of frames that have been rendered since the last reset
	@param time the total time that has passed since the last reset
	*/
	public void changeFrame(long lastTime, int frames, long time) {
		long now = 0;
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/TARGETTIME;
			time += (now - lastTime);
			lastTime = now;
			
			if(delta >= 1)
			{		
				update();
				draw();
				delta --;
				frames ++;
			}
			if(time >= 1000000000)
			{
				frames = 0;
				time = 0;
			}
		}
	}
	
	/**
	Initializes the game by loading assets and creating instances of MenuStep, GameStep, LoadingStep, and LevelSelectorStep. Sets the current state to loadingStep.
	*/
	public void init()
	{
		Assets.init();
		menuStep = new MenuStep(this);
		gameStep = new GameStep(this);
		loadingStep = new LoadingStep(this);
		levelSelectorStep = new LevelSelectorStep(this);
		Step.currentState = getLoadingStep();
	}
	
	/**
	Starts the game loop and runs the game until it is stopped.
	The game loop continuously updates and draws the game while
	the game is running. It initializes the game and handles
	changes in frame rate. Once the game is stopped, it stops
	the game loop and joins the game thread.
	*/
	@Override
	public void run() {

		long lastTime = System.nanoTime();
		int frames = 0;
		long time = 0;
		
		this.init();
		
		changeFrame(lastTime, frames, time);
		
		this.stop();
	}
	
	/**
	Returns the level selector step.
	@return the level selector step.
	*/
	public Step getLevelSelectorStep() {
		return levelSelectorStep;
	}
	
	/**
	Returns the game step.
	@return the game step.
	*/
	public Step getGameStep(){
		return gameStep;
	}	
	
	/**
	Returns the menu step.
	@return the menu step.
	*/
	public Step getMenuStep(){
		return menuStep;
	}
	
	/**
	Returns the loading step.
	@return the loading step.
	*/
	public LoadingStep getLoadingStep() {
		return loadingStep;
	}
	
}
