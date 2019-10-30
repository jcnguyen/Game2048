import objectdraw.*;
import Constants.ResetButtonStyle;

public class ResetButton {

	private static final String TITLE = "RESET";

	private DrawingCanvas canvas;
	private Location location;

	private FilledRect button;

	public ResetButton(Location loc, DrawingCanvas canvas) {
		location = loc;
		this.canvas = canvas;

		drawButton();
		drawTitle();
	}

	public Boolean isClicked(Location coordinates) {
		return button.contains(coordinates);
	}

	private void drawButton() {
		button = new FilledRect(location, ResetButtonStyle.WIDTH, ResetButtonStyle.HEIGHT, canvas);
		button.setColor(ResetButtonStyle.BACKGROUND_COLOR);
	}

	private void drawTitle() {
		Title title = new Title(TITLE, location, ResetButtonStyle.TEXT_SIZE, ResetButtonStyle.TEXT_COLOR, canvas);
		centerText(title);
	}

	private void centerText(Text text) {
		double x = location.getX() + (ResetButtonStyle.WIDTH - text.getWidth()) / 2;
		double y = location.getY() + (ResetButtonStyle.HEIGHT - text.getHeight()) / 2;
		text.moveTo(x, y);
	}
}
