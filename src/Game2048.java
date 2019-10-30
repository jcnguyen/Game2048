import objectdraw.*;
import java.awt.event.*;
import Constants.Game2048Style;
import Constants.Strings;

/**
 * 2048 is a game in which player combines tiles to get a 2048 tile. Player
 * loses if there are no legal moves left.
 * 
 * Suggested Window Size: 445x585
 */
public class Game2048 extends WindowController implements KeyListener {

	private static final int NUM_CELLS = 4;
	private static final int TILE_OFFSET = 5;
	private static final int TILE_SIZE = 100;
	private static final int BOARD_SIZE = (NUM_CELLS + 1) * TILE_OFFSET + NUM_CELLS * TILE_SIZE;

	private GameBoard board;
	private ResetButton resetButton;
	private ScoreBoard scoreBoard;

	private boolean gameOver = false;
	private boolean isKeyPressed = false;

	public void begin() {
		setupArrowKeyListener();
		drawGame();
		resetGame();
	}

	public void onMouseClick(Location point) {
		if (resetButton.isClicked(point)) {
			resetGame();
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		isKeyPressed = false;
	}

	public void keyPressed(KeyEvent e) {
		if (isKeyPressed) {
			return;
		}
		isKeyPressed = true;

		if (!board.hasLegalMove() && !gameOver) {
			gameLose();
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			board.moveUp();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			board.moveDown();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			board.moveLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			board.moveRight();
		}

		if (board.hasWinningTile()) {
			gameWin();
		}
	}

	private void setupArrowKeyListener() {
		requestFocus();
		addKeyListener(this);
		setFocusable(true);
		canvas.addKeyListener(this);
	}

	private void drawGame() {
		drawTitle();
		drawScoreBoard();
		drawResetButton();
		drawBoard();
	}

	private void drawTitle() {
		new Title(Strings.GAME_TITLE, Game2048Style.TITLE_LOC, Game2048Style.TITLE_TEXT_SIZE, Game2048Style.TITLE_COLOR,
				canvas);
	}

	private void drawScoreBoard() {
		if (scoreBoard == null) {
			scoreBoard = new ScoreBoard(Game2048Style.SCORE_LOC, canvas);
		}
	}

	private void drawResetButton() {
		resetButton = new ResetButton(Game2048Style.RESET_LOC, canvas);
	}

	private void drawBoard() {
		if (scoreBoard == null) {
			drawScoreBoard();
		}

		board = new GameBoard(Game2048Style.BOARD_LOC, BOARD_SIZE, NUM_CELLS, TILE_SIZE, TILE_OFFSET, scoreBoard,
				canvas);
	}

	private void resetGame() {
		gameOver = false;
		scoreBoard.reset();
		board.reset();
		board.addRandomTile();
	}

	private void gameLose() {
		gameOver = true;
		board.gameLose();
	}

	private void gameWin() {
		gameOver = true;
		board.gameWin();
	}
}