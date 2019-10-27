import objectdraw.*;
import java.awt.Color;

public class ScoreBoard {
	
	private static final int BOX_WIDTH = 135;
	private static final int BOX_HEIGHT = 80;
	private static final int FONT_SIZE = 30;
	private static final String TITLE = "SCORE";
	
	private DrawingCanvas canvas;
	private Location boardLocation;
	private Text scoreDisplay;
	
	private Score score;
	
	/**
	 * Constructs the score board.
	 * 
	 * @param loc  Coordinates where the score board is drawn.
	 * @param canvas  Canvas where the score board is drawn.
	 */
	public ScoreBoard(Location loc, DrawingCanvas canvas) {
		boardLocation = loc;
		this.canvas = canvas;
		
		drawScoreBoard();
		initializeScore();
	}
	
	/**
	 * Updates the score.
	 * 
	 * @param numPoints  The number of points to add to the current score.
	 */
	public void addToScore(int numPoints) {
		score.add(numPoints);
		redrawScore();
	}
	
	/**
	 * Resets the score board.
	 */
	public void reset() {
		score.reset();
		redrawScore();
	}
	
	private void drawScoreBoard() {
		drawBox();
		drawTitle();
		drawScore();	
	}
	
	private void drawBox() {
		new FilledRect(boardLocation, BOX_WIDTH, BOX_HEIGHT, canvas).setColor(Color2048.CELL_BG);
	}
	
	private void drawTitle() {
		Text title = new Text(TITLE, boardLocation, canvas);
		title.setColor(Color.WHITE);
		title.setFontSize(FONT_SIZE);
		centerText(title);
	}
	
	private void drawScore() {
		scoreDisplay = new Text("", boardLocation.getX(), boardLocation.getY() + BOX_HEIGHT/2, canvas);
		scoreDisplay.setColor(Color.WHITE);
		scoreDisplay.setFontSize(FONT_SIZE);
	}
	
	private void initializeScore() {
		score = new Score();
		redrawScore();
		centerText(scoreDisplay);
	}

	private void redrawScore() {
		scoreDisplay.setText(score.getScore());
		centerText(scoreDisplay);
	}
	
	private void centerText(Text text) {
		double x = boardLocation.getX()+(BOX_WIDTH-text.getWidth())/2;
		double y = text.getY();
		text.moveTo(x, y);
	}
}
