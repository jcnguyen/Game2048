import objectdraw.*;
import java.awt.Color;

public class Tile {
	
	private static final int FONT_SIZE = 40;
	
	private DrawingCanvas canvas;
	private double size;
	private int value;
	private FilledRect tile;
	private Text valueDisplay;
	private Location location;
	
	/**
	 * Constructs a tile.
	 * 
	 * @param value  The value of the tile.
	 * @param loction  The location of the tile.
	 * @param size  The size of the tile.
	 * @param canvas  The canvas where the tile is drawn.
	 */	
	public Tile(int value, Location location, double size, DrawingCanvas canvas) {
		this.value = value;
		this.size = size;
		this.location = location;
		this.canvas = canvas;

		drawTile();
	}

	private void drawTile() {
		drawBox();
		drawValue();
	}

	private void drawBox() {
		tile = new FilledRect(location, size, size, canvas);
		tile.setColor(Color2048.TILE2);
	}
	
	private void drawValue() {
		valueDisplay = new Text(value, location, canvas);
		valueDisplay.setColor(Color2048.DARK_FONT);
		valueDisplay.setBold(true);
		valueDisplay.setFontSize(FONT_SIZE);
		centerText(valueDisplay);
	}
	
	/**
	 * Gets the tile value.
	 * 
	 * @return The value of the tile.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Sets the color of the tile.
	 * 
	 * @param colTile  The color to set the tile.
	 * @param colText  The color to set the value.
	 */
	public void setColor(Color colTile, Color colText) {
		tile.setColor(colTile);
		valueDisplay.setColor(colText);
	}
	
	/**
	 * Permanently removes the tile from the canvas.
	 */
	public void removeFromCanvas() {
		tile.removeFromCanvas();
		valueDisplay.removeFromCanvas();
	}
	
	private void centerText(Text text) {
		double x = tile.getX() + (size - text.getWidth())/2;
		double y = tile.getY() + (size - text.getHeight())/2;
		text.moveTo(x, y);
	}
}
