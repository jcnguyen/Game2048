import objectdraw.*;

public class GameOverBoard {

	private static final int TEXT_SIZE = 50;
	private static final String WIN_TEXT = "YOU WIN!";
	private static final String GAME_OVER_TEXT = "GAME OVER";
	
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
		text.setText(GAME_OVER_TEXT);
		centerText(text);
	}
	
	public void activateWinningBoard() {
		showElements();
		text.setText(WIN_TEXT);
		centerText(text);
	}
	
	private void drawBoard() {
		board = new FilledRect(location, size, size, canvas);
		board.setColor(Color2048.GAMEOVER_BG);
	}

	private void drawText() {
		text = new Text(GAME_OVER_TEXT, size/2, size/2, canvas);
		text.setFontSize(TEXT_SIZE);
		centerText(text);
	}
	
	private void showElements() {
		board.show();
		board.sendToFront();
		text.show();
		text.sendToFront();
	}

	private void centerText(Text text) {
		double x = location.getX() + (size - text.getWidth())/2;
		double y = location.getY() + (size - text.getHeight())/2;
		text.moveTo(x, y);
	}
}