import objectdraw.*;
import java.awt.event.*;

/**
 * Game2048.java
 * 
 * 2048 is a game in which player combines tiles to get a 2048 tile. 
 * Player loses if there are no legal moves left.
 * 
 * Suggested Window Size: 445x585 
 */
public class Game2048 extends WindowController implements KeyListener {
	
	private static final String GAME_TITLE = "2048";
	
	// board and tile information
	private static final int NUM_CELLS = 4;
	private static final int TILE_OFFSET = 5; // space between tiles
	
	// object sizes
	private static final int TITLE_FONT_SIZE = 100;
	private static final int TILE_SIZE = 100;
	private static final int BOARD_SIZE = (NUM_CELLS+1)*TILE_OFFSET + NUM_CELLS*TILE_SIZE;
	
	// object locations
	private static final Location TITLE_LOC = new Location(10, 10);
	private static final Location SCORE_LOC = new Location(300, 10);
	private static final Location RESET_LOC = new Location(300, 100);
	private static final Location BOARD_LOC = new Location(10, 150);
	
	// objects
	private Board board;
	private GameOverBoard gameOverBoard;
	private ResetButton resetButton;
	private ScoreBoard scoreBoard;

	// remembers if the game over screen is currently shown
	private boolean activatedGameOver = false; 

	// remembers if key is currently pressed
	private boolean keyDown = false; 
	
	/**
	 * Set-up and start the game.
	 */
	public void begin() {
		drawTitle();
		drawAndHideGameOverBoard();
		drawScoreBoard();
		drawResetButton();
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
		if (resetButton.isClicked(point)) {
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
		new Title(GAME_TITLE, TITLE_LOC, TITLE_FONT_SIZE, Color2048.DARK_FONT, canvas);
	}
	
	private void drawAndHideGameOverBoard() {
		gameOverBoard = new GameOverBoard(BOARD_LOC, BOARD_SIZE, canvas);
		gameOverBoard.hide();
	}
	
	private void drawScoreBoard() {
		scoreBoard = new ScoreBoard(SCORE_LOC, canvas);
	}
	
	private void drawResetButton() {
		resetButton = new ResetButton(RESET_LOC, canvas);
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
	 * Resets the game.
	 */
	private void restart() {
		activatedGameOver = false;
		board.restart();
		scoreBoard.reset();
		gameOverBoard.hide();
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
		gameOverBoard.activateLosingBoard();
	}
	
	private void gameWin() {
		activatedGameOver = true;
		gameOverBoard.activateWinningBoard();
	}	
}