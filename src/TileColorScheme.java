import java.awt.Color;
import Constants.Color2048;
import Constants.TileStyle;

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
			backgroundColor = TileStyle.BACKGROUND_2;
			textColor = Color2048.DARK_FONT;
			break;
		case 4:
			backgroundColor = TileStyle.BACKGROUND_4;
			textColor = Color2048.DARK_FONT;
			break;
		case 8:
			backgroundColor = TileStyle.BACKGROUND_8;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 16:
			backgroundColor = TileStyle.BACKGROUND_16;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 32:
			backgroundColor = TileStyle.BACKGROUND_32;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 64:
			backgroundColor = TileStyle.BACKGROUND_64;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 128:
			backgroundColor = TileStyle.BACKGROUND_128;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 256:
			backgroundColor = TileStyle.BACKGROUND_256;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 512:
			backgroundColor = TileStyle.BACKGROUND_512;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 1024:
			backgroundColor = TileStyle.BACKGROUND_1024;
			textColor = Color2048.LIGHT_FONT;
			break;
		case 2048:
			backgroundColor = TileStyle.BACKGROUND_2048;
			textColor = Color2048.LIGHT_FONT;
			break;
		default:
			backgroundColor = TileStyle.BACKGROUND_DEFAULT;
			textColor = Color2048.LIGHT_FONT;
			break;
		}
	}
}
