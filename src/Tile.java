import objectdraw.*;
import Constants.TileStyle;

public class Tile {

	private int value;
	private Location location;
	private DrawingCanvas canvas;

	private FilledRect box;
	private Text text;
	private TileColorScheme colorScheme;

	public Tile(int value, Location location, DrawingCanvas canvas) {
		this.value = value;
		this.location = location;
		this.canvas = canvas;

		colorScheme = new TileColorScheme(value);
		drawTile();
	}

	public int getValue() {
		return value;
	}

	public void removeFromCanvas() {
		box.removeFromCanvas();
		text.removeFromCanvas();
	}

	private void drawTile() {
		drawBox();
		drawText();
	}

	private void drawBox() {
		box = new FilledRect(location, TileStyle.SIZE, TileStyle.SIZE, canvas);
		box.setColor(colorScheme.getBackgroundColor());
	}

	private void drawText() {
		text = new Text(value, location, canvas);
		text.setColor(colorScheme.getTextColor());
		text.setBold(true);
		text.setFontSize(TileStyle.TEXT_SIZE);
		centerText(text);
	}

	private void centerText(Text text) {
		double x = location.getX() + (TileStyle.SIZE - text.getWidth()) / 2;
		double y = location.getY() + (TileStyle.SIZE - text.getHeight()) / 2;
		text.moveTo(x, y);
	}
}
