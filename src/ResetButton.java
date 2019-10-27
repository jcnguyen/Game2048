import objectdraw.*;
import java.awt.Color;

public class ResetButton {
	
	private static final String TITLE = "RESET";
	private static final int BOX_WIDTH = 135;
	private static final int BOX_HEIGHT = 80;
	private static final int TEXT_SIZE = 30;
	
	private DrawingCanvas canvas;
	private Location location;
	private FilledRect button;
	
	/**
	 * Constructs the reset button.
	 * 
	 * @param loc  Coordinates where the button is drawn.
	 * @param canvas  Canvas where the button is drawn.
	 */
	public ResetButton(Location loc, DrawingCanvas canvas) {
		location = loc;
		this.canvas = canvas;
		
		drawButton();
		drawTitle();
	}
	
	/**
	 * Checks if the button is clicked.
	 * 
	 * @param coordinates  The coordinates of the click.
	 * @return True if the button is clicked; false otherwise.
	 */
	public Boolean isClicked(Location coordinates) {
		return button.contains(coordinates);
	}

	private void drawButton() {
		button = new FilledRect(location, BOX_WIDTH, BOX_HEIGHT/2, canvas);
		button.setColor(Color2048.CELL_BG);
	}

	private void drawTitle() {
		Text title = new Text(TITLE, location, canvas);
		title.setColor(Color.WHITE);
		title.setFontSize(TEXT_SIZE);
		centerText(title);
	}
	
	private void centerText(Text text) {
		double x = location.getX() + (BOX_WIDTH - text.getWidth())/2;
		double y = location.getY() + (BOX_HEIGHT/2 - text.getHeight())/2;
		text.moveTo(x, y);
	}
}
