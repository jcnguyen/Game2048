import objectdraw.*;
import java.awt.Color;

/**
 * ScoreBoard.java
 *
 * Keeps track of the score.
 */
public class ScoreBoard {
	
	private static final int FONT_SIZE = 30;
	
	private int score;
	private int width;
	private FilledRect board;
	private Text scoreDisplay;
	private Text titleDisplay;
	
	/**
	 * Constructs the scoreboard with an initial value of 0.
	 * 
	 * @param loc       the coordinates of the scoreboard
	 * @param w         the width of the box containing the scoreboard
	 * @param h         the height of the box containing the scoreboard
	 * @param canvas    where the scoreboard is drawn
	 */
	public ScoreBoard(Location loc, int w, int h, DrawingCanvas canvas) {
		width = w;
		score = 0;
		
		// construct the scoreboard
		board = new FilledRect(loc, width, h, canvas);
		board.setColor(Color2048.CELL_BG);
		
		// construct the title
		titleDisplay = new Text("SCORE", loc, canvas);
		titleDisplay.setColor(Color.WHITE);
		titleDisplay.setFontSize(FONT_SIZE);
		centerText(titleDisplay);
		
		// construct the score display
		scoreDisplay = new Text(score, loc.getX(), loc.getY() + h/2, canvas);
		scoreDisplay.setColor(Color.WHITE);
		scoreDisplay.setFontSize(FONT_SIZE);
		centerText(scoreDisplay);	
	}
	
	/**
	 * Updates the score.
	 * 
	 * @param numPoints    the number of points to add to the current score.
	 */
	public void updateScore(int numPoints) {
		score += numPoints;
		scoreDisplay.setText(score);
		centerText(scoreDisplay);
	}
	
	/**
	 * Resets the scoreboard.
	 */
	public void restart() {
		score = 0;
		scoreDisplay.setText(score);
		centerText(scoreDisplay);
	}
	
	/**
	 * Centers the text of the scoreboard.
	 * 
	 * @param text    the text to center
	 */
	private void centerText(Text text) {
		double x = board.getX()+(width-text.getWidth())/2;
		double y = text.getY();
		text.moveTo(x, y);
	}

}
