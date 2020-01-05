import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Enforces one action per key press.
 */
public abstract class SingleActionPerKeyPressHandler implements KeyListener {

	private boolean isKeyPressed = false; 

	@Override
	public void keyPressed(KeyEvent e) {
		if (isKeyPressed) {
			return;
		}
		isKeyPressed = true;

		handleKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		isKeyPressed = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public void handleKeyPressed(KeyEvent e) {
	}
}

