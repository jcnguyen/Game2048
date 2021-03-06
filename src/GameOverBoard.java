import objectdraw.*;
import Constants.GameOverBoardStyle;
import Constants.Strings;

public class GameOverBoard {

	private DrawingCanvas canvas;
	private Location location;
	private int size;

	private FilledRect board;
	private Text text;

	public GameOverBoard(Location location, int size, DrawingCanvas canvas) {
		this.location = location;
		this.size = size;
		this.canvas = canvas;

		drawBoard();
		drawText();
	}

	public void hide() {
		text.hide();
		board.hide();
	}

	public void activateLosingBoard() {
		showElements();
		text.setText(Strings.LOSE_TEXT);
		centerText(text);
	}

	public void activateWinningBoard() {
		showElements();
		text.setText(Strings.WIN_TEXT);
		centerText(text);
	}

	private void drawBoard() {
		board = new FilledRect(location, size, size, canvas);
		board.setColor(GameOverBoardStyle.BACKGROUND_COLOR);
	}

	private void drawText() {
		text = new Text(Strings.LOSE_TEXT, calculateTextLocation(), canvas);
		text.setFontSize(GameOverBoardStyle.TEXT_SIZE);
		centerText(text);
	}

	private void showElements() {
		board.show();
		board.sendToFront();
		text.show();
		text.sendToFront();
	}

	private Location calculateTextLocation() {
		return new Location(size / 2, size / 2);
	}

	private void centerText(Text text) {
		double x = location.getX() + (size - text.getWidth()) / 2;
		double y = location.getY() + (size - text.getHeight()) / 2;
		text.moveTo(x, y);
	}
}
