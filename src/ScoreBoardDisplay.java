import objectdraw.*;
import java.awt.Color;

public class ScoreBoardDisplay {
	
	private static final String TITLE = "SCORE";
	private static final int BOX_WIDTH = 135;
	private static final int BOX_HEIGHT = 80;
	private static final int TEXT_SIZE = 30;
	private static final int DEFAULT_SCORE = 0;
	
	private DrawingCanvas canvas;
	private Location location;
	private Text scoreDisplay;
	
	/**
	 * Draws the score board.
	 * 
	 * @param loc  Coordinates where the score board is drawn.
	 * @param canvas  Canvas where the score board is drawn.
	 */
	public ScoreBoardDisplay(Location loc, DrawingCanvas canvas) {
		location = loc;
		this.canvas = canvas;
		
		drawBox();
		drawTitle();
		drawScore();
	}
	
	/**
	 * Resets the score board.
	 */
	public void reset() {
		update(DEFAULT_SCORE);
	}
	
	/**
	 * Updates the score board.
	 * 
	 * @param score  The score to update to.
	 */
	public void update(int score) {
		scoreDisplay.setText(score);
		centerText(scoreDisplay);
	}
	
	private void drawBox() {
		new FilledRect(location, BOX_WIDTH, BOX_HEIGHT, canvas).setColor(Color2048.CELL_BG);
	}
	
	private void drawTitle() {
		Text title = new Text(TITLE, location, canvas);
		title.setColor(Color.WHITE);
		title.setFontSize(TEXT_SIZE);
		centerText(title);
	}
	
	private void drawScore() {
		scoreDisplay = new Text("", location.getX(), location.getY() + BOX_HEIGHT/2, canvas);
		scoreDisplay.setColor(Color.WHITE);
		scoreDisplay.setFontSize(TEXT_SIZE);
		reset();
	}
	
	private void centerText(Text text) {
		double x = location.getX() + (BOX_WIDTH - text.getWidth())/2;
		double y = text.getY();
		text.moveTo(x, y);
	}
}
