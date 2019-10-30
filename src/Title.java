import objectdraw.*;
import java.awt.Color;

public class Title extends Text {

	public Title(String text, Location location, int size, Color color, DrawingCanvas canvas) {
		super(text, location, canvas);
		super.setFontSize(size);
		super.setColor(color);	
	}
}
