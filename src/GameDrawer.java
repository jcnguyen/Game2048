import Constants.Game2048Style;
import Constants.Strings;
import objectdraw.DrawingCanvas;

public class GameDrawer implements IGameDrawer {

	// todo move to constants
	private static final int NUM_CELLS = 4;
	private static final int TILE_OFFSET = 5;
	private static final int TILE_SIZE = 100;
	private static final int BOARD_SIZE = (NUM_CELLS + 1) * TILE_OFFSET + NUM_CELLS * TILE_SIZE;
	
	private DrawingCanvas canvas;
	private GameBoard board;
	private ResetButton resetButton;
	private ScoreBoard scoreBoard;
	
	public GameDrawer(DrawingCanvas canvas) {
		this.canvas = canvas;
	}
	
	@Override
	public void drawGame() {
		drawTitle();
		drawScoreBoard();
		drawResetButton();
		drawBoard();
	}
	
	// todo add to interface, return interface
	public ScoreBoard getScoreBoard() {
		return scoreBoard;
	}
	
	// todo add to interface, return interface
	public ResetButton getResetButton() {
		return resetButton;
	}
	
	// todo add to interface, return interface
	public GameBoard getGameBoard() {
		return board;
	}

	private void drawTitle() {
		new Title(Strings.GAME_TITLE, 
				Game2048Style.TITLE_LOC, 
				Game2048Style.TITLE_TEXT_SIZE, 
				Game2048Style.TITLE_COLOR,
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

		board = new GameBoard(Game2048Style.BOARD_LOC, 
				BOARD_SIZE, 
				NUM_CELLS, 
				TILE_SIZE, 
				TILE_OFFSET, 
				scoreBoard,
				canvas);
	}

}
