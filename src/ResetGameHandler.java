import objectdraw.Location;

public class ResetGameHandler implements IResetGame {
	
	private ResetButton resetButton;
	private IScoreBoard scoreBoard;
	private GameBoard board;
	
	public ResetGameHandler(ResetButton resetButton, IScoreBoard scoreBoard, GameBoard board) {
		this.resetButton = resetButton;
		this.scoreBoard = scoreBoard;
		this.board = board;
	}
	
	public void resetGame() {
		scoreBoard.reset();
		board.reset();
		board.addRandomTile();
	}

	public void resetGameOnClick(Location point) {
		if (resetButton.isClicked(point)) {
			resetGame();
		}
	}
}
