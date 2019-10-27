import objectdraw.*;
import java.awt.event.*;
import java.awt.Color;

/**
 * Game2048.java
 * 
 * 2048 is a game in which player combines tiles to get a 2048 tile. 
 * Player loses if there are no legal moves left.
 * 
 * Suggested Window Size: 445x585 
 */
public class Game2048 extends WindowController implements KeyListener {
	
	// board and tile information
	private static final int NUM_CELLS = 4;
	private static final int TILE_OFFSET = 5; // space between tiles
	
	// object sizes
	private static final int TITLE_FONT_SIZE = 100;
	private static final int TILE_SIZE = 100;
	private static final int RESET_FONT_SIZE = 30;
	private static final int GAMEOVER_FONT_SIZE = 50;
	private static final int BOX_WIDTH = 135;
	private static final int BOX_HEIGHT = 80;
	private static final int BOARD_SIZE = (NUM_CELLS+1)*TILE_OFFSET + NUM_CELLS*TILE_SIZE;
	
	// object locations
	private static final Location TITLE_LOC = new Location(10, 10);
	private static final Location SCORE_LOC = new Location(300, 10);
	private static final Location RESET_LOC = new Location(300, 100);
	private static final Location BOARD_LOC = new Location(10, 150);
	
	// objects
	private Board board;
	private FilledRect resetButton;
	private FilledRect gameOverBG;
	private ScoreBoard scoreBoard;
	private Text gameOverText;
	private Text gameWinText;

	// remembers if the game over screen is currently shown
	private boolean activatedGameOver = false; 

	// remembers if key is currently pressed
	private boolean keyDown = false; 
	
	/**
	 * Set-up and start the game.
	 */
	public void begin() {
		drawTitle();
		drawGameOverObjects();
		drawScoreBoard();
		drawResetObjects();
		initializeGameBoard();
		setupArrowKeyListener();
	}
	
	/**
	 * Event handler, called when mouse is clicked.
	 * 
	 * Handles the reset button.
	 * 
	 * @param point    mouse coordinates
	 */
	public void onMouseClick(Location point) {
		if(resetButton.contains(point)) {
			restart();
		}
	}

	/**
	 * (required) KeyListener event handler for a key having been pressed and
	 * released.
	 * 
	 * @param e    event (key that was typed)
	 */
	public void keyTyped(KeyEvent e) {}

	/**
	 * (required) KeyListener event handler for a key having been released.
	 * 
	 * @param e    event (key that was released)
	 */
	public void keyReleased(KeyEvent e) {
		keyDown = false; // remember that the key is no longer down
	}

	/**
	 * (required) KeyListener event handler for a key having been pressed.
	 * 
	 * Handles the game state (playing or game over) and 
	 * handles arrow keys by moving the tiles in the indicated direction.
	 * 
	 * @param e    event (key that was pressed)
	 */
	public void keyPressed(KeyEvent e) {
		if(!isGameOver()) {
			if(!keyDown) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					board.moveUp();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					board.moveDown();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					board.moveLeft();
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					board.moveRight();
				}
				keyDown = true;
				if (board.istile2048()) {
					gameWin();
				}
			}
		} else if(!activatedGameOver) {
			gameOver();
		}
	}
	
	private void drawTitle() {
		Text title = new Text("2048", TITLE_LOC, canvas);
		title.setFontSize(TITLE_FONT_SIZE);
		title.setColor(Color2048.DARK_FONT);
	}
	
	private void drawGameOverObjects() {
		gameOverBG = new FilledRect(BOARD_LOC, BOARD_SIZE, BOARD_SIZE, canvas);
		gameOverBG.setColor(Color2048.GAMEOVER_BG);
		gameOverBG.hide();
		gameOverText = new Text("GAME OVER", BOARD_SIZE/2, BOARD_SIZE/2, canvas);
		gameOverText.setFontSize(GAMEOVER_FONT_SIZE);
		centerText(gameOverText, BOARD_LOC, BOARD_SIZE, BOARD_SIZE);
		gameOverText.hide();
		gameWinText = new Text("YOU WIN!", BOARD_SIZE/2, BOARD_SIZE/2, canvas);
		gameWinText.setFontSize(GAMEOVER_FONT_SIZE);
		centerText(gameWinText, BOARD_LOC, BOARD_SIZE, BOARD_SIZE);
		gameWinText.hide();
	}
	
	private void drawScoreBoard() {
		scoreBoard = new ScoreBoard(SCORE_LOC, canvas);
	}
	
	private void drawResetObjects() {
		resetButton = new FilledRect(RESET_LOC, BOX_WIDTH, BOX_HEIGHT/2, canvas);
		resetButton.setColor(Color2048.CELL_BG);
		
		Text restartText = new Text("RESET", RESET_LOC, canvas);
		restartText.setColor(Color.WHITE);
		restartText.setFontSize(RESET_FONT_SIZE);
		centerText(restartText, RESET_LOC, BOX_WIDTH, BOX_HEIGHT/2);
	}
	
	private Board drawGameBoard() {
		board = new Board(BOARD_LOC, NUM_CELLS, TILE_SIZE, TILE_OFFSET, scoreBoard, canvas);
		new FilledRect(BOARD_LOC, BOARD_SIZE, BOARD_SIZE, canvas).setColor(Color2048.BOARD_BG);
		
		for(int row = 0; row < NUM_CELLS; row++) {
			for(int col = 0; col < NUM_CELLS; col++) {
				Location cellLoc = board.posToCoord(row, col);
				new FilledRect(cellLoc.getX(), cellLoc.getY(), TILE_SIZE, TILE_SIZE, canvas).setColor(Color2048.CELL_BG);
			}
		}
		return board;
	}

	private void initializeGameBoard() {
		board = drawGameBoard();
		board.addTile();
	}
	
	private void setupArrowKeyListener() {
		requestFocus();
		addKeyListener(this);
		setFocusable(true);
		canvas.addKeyListener(this);
	}

	/**
	 * Centers the text based on its container.
	 *
	 * @param text      the text to center
	 * @param loc       the location of the container
	 * @param width     the width of the container
	 * @param height    the height of the container
	 */
	private void centerText(Text text, Location loc, int width, int height) {
		double x = loc.getX()+(width-text.getWidth())/2;
		double y = loc.getY()+(height-text.getHeight())/2;
		text.moveTo(x, y);
	}
	
	/**
	 * Resets the game.
	 */
	private void restart() {
		activatedGameOver = false;
		board.restart();
		scoreBoard.reset();
		gameOverText.hide();
		gameOverBG.hide();
		gameWinText.hide();
		board.addTile();
	}
	
	/**
	 * Determines the current state of the game.
	 * 
	 * @return true if there are no available moves,
	 *         false otherwise
	 */
	private boolean isGameOver() {
		return !board.canMove();
	}
	
	/**
	 * Handles the game when it is in the game over state. 
	 */
	private void gameOver() {
		activatedGameOver = true;
		gameOverBG.show();
		gameOverBG.sendToFront();
		gameOverText.show();
		gameOverText.sendToFront();
	}
	
	private void gameWin() {
		activatedGameOver = true;
		gameOverBG.show();
		gameOverBG.sendToFront();
		gameWinText.show();
		gameWinText.sendToFront();
	}	
}