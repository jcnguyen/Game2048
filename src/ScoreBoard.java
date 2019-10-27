import objectdraw.*;

public class ScoreBoard extends ScoreBoardDisplay {
	
	private int score = 0;
	
	/**
	 * Constructs the score board.
	 * 
	 * @param loc  Coordinates where the score board is drawn.
	 * @param canvas  Canvas where the score board is drawn.
	 */
	public ScoreBoard(Location loc, DrawingCanvas canvas) {
		super(loc, canvas);
		reset();
	}
	
	/**
	 * Resets the score board.
	 */
	public void reset() {
		score = 0;
		update(score);
	}
	
	/**
	 * Updates the score.
	 * 
	 * @param points  The number of points to add to the current score.
	 */
	public void addToScore(int points) {
		score += points;
		update(score);
	}
}
