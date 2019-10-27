import objectdraw.*;
import java.awt.Color;

public class ScoreBoard {
	
	private static final int BOX_WIDTH = 135;
	private static final int BOX_HEIGHT = 80;
	private static final int FONT_SIZE = 30;
	private static final String TITLE = "SCORE";
	
	private int score = 0;
	private Location boardLocation;
	private Text scoreDisplay;
	private DrawingCanvas canvas;
	
	
	/**
	 * Constructs the score board.
	 * 
	 * @param loc  Coordinates where the score board is drawn.
	 * @param canvas  Canvas where the score board is drawn
	 */
	public ScoreBoard(Location loc, DrawingCanvas canvas) {
		boardLocation = loc;
		this.canvas = canvas;
		
		drawScoreBox();
		drawTitle();
		drawScoreDisplay(loc, canvas);	
	}

	private void drawScoreBox() {
		FilledRect board = new FilledRect(boardLocation, BOX_WIDTH, BOX_HEIGHT, canvas);
		board.setColor(Color2048.CELL_BG);
	}
	
	private void drawTitle() {
		Text title = new Text(TITLE, boardLocation, canvas);
		title.setColor(Color.WHITE);
		title.setFontSize(FONT_SIZE);
		centerText(title);
	}
	
	private void drawScoreDisplay(Location loc, DrawingCanvas canvas) {
		scoreDisplay = new Text(score, loc.getX(), loc.getY() + BOX_HEIGHT/2, canvas);
		scoreDisplay.setColor(Color.WHITE);
		scoreDisplay.setFontSize(FONT_SIZE);
		centerText(scoreDisplay);
	}
	
	/**
	 * Updates the score.
	 * 
	 * @param numPoints  the number of points to add to the current score.
	 */
	public void addToScore(int numPoints) {
		score += numPoints;
		redrawScore();
	}
	
	/**
	 * Resets the scoreboard.
	 */
	public void reset() {
		score = 0;
		redrawScore();
	}

	private void redrawScore() {
		scoreDisplay.setText(score);
		centerText(scoreDisplay);
	}
	
	private void centerText(Text text) {
		double x = boardLocation.getX()+(BOX_WIDTH-text.getWidth())/2;
		double y = text.getY();
		text.moveTo(x, y);
	}
}
