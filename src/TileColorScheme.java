import java.awt.Color;
import Constants.Color2048;

public class TileColorScheme {

	private Color backgroundColor;
	private Color textColor;

	public TileColorScheme(int val) {
		selectColorScheme(val);
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Color getTextColor() {
		return textColor;
	}

	private void selectColorScheme(int val) {
		switch (val) {
		case 2:
			backgroundColor = Color2048.TILE2;
			textColor = Color2048.DARK_FONT;
			break;
		case 4:
			backgroundColor = Color2048.TILE4;
			textColor = Color2048.DARK_FONT;
			break;
		case 8:
			backgroundColor = Color2048.TILE8;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 16:
			backgroundColor = Color2048.TILE16;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 32:
			backgroundColor = Color2048.TILE32;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 64:
			backgroundColor = Color2048.TILE64;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 128:
			backgroundColor = Color2048.TILE128;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 256:
			backgroundColor = Color2048.TILE256;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 512:
			backgroundColor = Color2048.TILE512;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 1024:
			backgroundColor = Color2048.TILE1024;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 2048:
			backgroundColor = Color2048.TILE2048;
			textColor = Color2048.LIGHT_FONT;
			break;
		default:
			backgroundColor = Color2048.TILELARGE;
			textColor = Color2048.LIGHT_FONT;
			break;
		}
	}

}
