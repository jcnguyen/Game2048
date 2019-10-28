import objectdraw.*;

public class ScoreBoard {
	
	private ScoreBoardDrawer scoreBoardDisplay;
	private int score = 0;
	
	/**
	 * Constructs the score board.
	 * 
	 * @param loc  Coordinates where the score board is drawn.
	 * @param canvas  Canvas where the score board is drawn.
	 */
	public ScoreBoard(Location loc, DrawingCanvas canvas) {
		scoreBoardDisplay = new ScoreBoardDrawer(loc, canvas);
		reset();
	}
	
	/**
	 * Resets the score board.
	 */
	public void reset() {
		score = 0;
		scoreBoardDisplay.reset();
	}
	
	/**
	 * Updates the score.
	 * 
	 * @param points  The number of points to add to the current score.
	 */
	public void addToScore(int points) {
		score += points;
		scoreBoardDisplay.update(score);
	}
}
