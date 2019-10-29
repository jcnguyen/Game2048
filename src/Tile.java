import objectdraw.*;
import java.awt.Color;

import Constants.Color2048;
import Constants.TileStyle;

public class Tile {

	private DrawingCanvas canvas;
	private int value;
	private FilledRect tile;
	private Text valueDisplay;
	private Location location;

	public Tile(int value, Location location, DrawingCanvas canvas) {
		this.value = value;
		this.location = location;
		this.canvas = canvas;

		drawTile();
	}

	public int getValue() {
		return value;
	}

	public void removeFromCanvas() {
		tile.removeFromCanvas();
		valueDisplay.removeFromCanvas();
	}

	private void drawTile() {
		Color[] colorScheme = selectColorScheme(value);
		drawBox(colorScheme[0]);
		drawValue(colorScheme[1]);
	}

	private void drawBox(Color color) {
		tile = new FilledRect(location, TileStyle.SIZE, TileStyle.SIZE, canvas);
		tile.setColor(color);
	}

	private void drawValue(Color color) {
		valueDisplay = new Text(value, location, canvas);
		valueDisplay.setColor(color);
		valueDisplay.setBold(true);
		valueDisplay.setFontSize(TileStyle.TEXT_SIZE);
		centerText(valueDisplay);
	}

	private void centerText(Text text) {
		double x = location.getX() + (TileStyle.SIZE - text.getWidth()) / 2;
		double y = location.getY() + (TileStyle.SIZE - text.getHeight()) / 2;
		text.moveTo(x, y);
	}

	private Color[] selectColorScheme(int val) {
		Color[] colors = new Color[2];
		switch (val) {
		case 2:
			colors[0] = Color2048.TILE2;
			colors[1] = Color2048.DARK_FONT;
			break;
		case 4:
			colors[0] = Color2048.TILE4;
			colors[1] = Color2048.DARK_FONT;
			break;
		case 8:
			colors[0] = Color2048.TILE8;
			colors[1] = Color2048.LIGHT_FONT;
			break;
		case 16:
			colors[0] = Color2048.TILE16;
			colors[1] = Color2048.LIGHT_FONT;
			break;
		case 32:
			colors[0] = Color2048.TILE32;
			colors[1] = Color2048.LIGHT_FONT;
			break;
		case 64:
			colors[0] = Color2048.TILE64;
			colors[1] = Color2048.LIGHT_FONT;
			break;
		case 128:
			colors[0] = Color2048.TILE128;
			colors[1] = Color2048.LIGHT_FONT;
			break;
		case 256:
			colors[0] = Color2048.TILE256;
			colors[1] = Color2048.LIGHT_FONT;
			break;
		case 512:
			colors[0] = Color2048.TILE512;
			colors[1] = Color2048.LIGHT_FONT;
			break;
		case 1024:
			colors[0] = Color2048.TILE1024;
			colors[1] = Color2048.LIGHT_FONT;
			break;
		case 2048:
			colors[0] = Color2048.TILE2048;
			colors[1] = Color2048.LIGHT_FONT;
			break;
		default:
			colors[0] = Color2048.TILELARGE;
			colors[1] = Color2048.LIGHT_FONT;
			break;
		}
		return colors;
	}
}
