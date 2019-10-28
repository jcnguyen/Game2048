import objectdraw.*;
import java.awt.Color;

public class Title extends Text {

	/**
	 * Constructs a title.
	 * 
	 * @param text  The title text.
	 * @param location  The coordinates where the title is created.
	 * @param size  The size of the title.
	 * @param color  The color of the title.
	 * @param canvas  The canvas where the title is created.
	 */
	public Title(String text, Location location, int size, Color color, DrawingCanvas canvas) {
		super(text, location, canvas);
		super.setFontSize(size);
		super.setColor(color);	
	}
}
