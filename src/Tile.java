import objectdraw.*;
import java.awt.Color;

/**
 * Tile.java
 *
 * Describes a tile of the game 2048.
 */
public class Tile {
	
	private static final int FONT_SIZE = 40;
	
	private double size;
	private int value;
	private FilledRect tile;
	private Text valueDisplay;
	
	/**
	 * Constructs a tile.
	 * 
	 * @param value         the value of the tile
	 * @param x             the x location of the tile
	 * @param y             the y location of the tile
	 * @param size          the width and height of the tile
	 * @param canvas        where the tile is drawn
	 */
	public Tile(int value, double x, double y, double size, DrawingCanvas canvas) {
		this.value = value;
		this.size = size;

		// construct the tile
		tile = new FilledRect(x, y, size, size, canvas);
		tile.setColor(Color2048.TILE2);
		
		// construct the text representing the tile value
		valueDisplay = new Text(value, x, y, canvas);
		valueDisplay.setColor(Color2048.DARK_FONT);
		valueDisplay.setBold(true);;
		valueDisplay.setFontSize(FONT_SIZE);
		centerText(valueDisplay);
	}
	
	/**
	 * Gets the tile value.
	 * 
	 * @return the value of the tile
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Sets the color of the tile.
	 * 
	 * @param colTile    the color to set the tile
	 * @param colText    the color to set the value
	 */
	public void setColor(Color colTile, Color colText) {
		tile.setColor(colTile);
		valueDisplay.setColor(colText);
	}
	
	/**
	 * Permanently removes the tile from the canvas it is currently on.
	 */
	public void removeFromCanvas() {
		tile.removeFromCanvas();
		valueDisplay.removeFromCanvas();
	}
	
	/**
	 * Centers the tile value.
	 *
	 * @param text    the text to center
	 */
	private void centerText(Text text) {
		double x = tile.getX() + (size-text.getWidth())/2;
		double y = tile.getY() + (size-text.getHeight())/2;
		text.moveTo(x, y);
	}

}
