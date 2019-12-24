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
	
	private KeyListener keyListener;
	private IResetGame resetBoardHandler;

	public void begin() {
		setupArrowKeyListener();
		drawGame();
		
		keyListener = new MoveBoardHandler(board);
		resetBoardHandler = new ResetGameHandler(resetButton, scoreBoard, board);
		
		resetBoardHandler.resetGame();
	}
	
	private void setupArrowKeyListener() {
		requestFocus();
		addKeyListener(this);
		setFocusable(true);
		canvas.addKeyListener(this);
	}

	public void onMouseClick(Location point) {
		resetBoardHandler.resetGameOnClick(point);
	}

	public void keyTyped(KeyEvent e) {
		keyListener.keyTyped(e);
	}

	public void keyReleased(KeyEvent e) {
		keyListener.keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {
		keyListener.keyPressed(e);
	}
	/////////////////////////////////////////////////////////////////////

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
}