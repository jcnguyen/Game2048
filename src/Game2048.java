import objectdraw.*;
import java.awt.event.*;
import Constants.Game2048Style;
import Constants.Strings;

/**
 * 2048 is a game in which player combines tiles to get a 2048 tile. Player
 * loses if there are no legal moves left.
 * 
 * Suggested Window Size: 445x585
 */
public class Game2048 extends WindowController implements KeyListener {
	
	private KeyListener keyListener;
	private IResetGame resetBoardHandler;
	private GameDrawer gameDrawer;

	public void begin() {
		setupArrowKeyListener();
		
		gameDrawer = new GameDrawer(canvas);
		gameDrawer.drawGame();
		
		// todo interface
		keyListener = new MoveBoardHandler(gameDrawer.getGameBoard());
		resetBoardHandler = new ResetGameHandler(gameDrawer.getResetButton(), gameDrawer.getScoreBoard(), gameDrawer.getGameBoard());
		
		resetBoardHandler.resetGame();
	}
	
	private void setupArrowKeyListener() {
		requestFocus();
		addKeyListener(this);
		setFocusable(true);
		canvas.addKeyListener(this);
	}

	public void onMouseClick(Location point) {
		resetBoardHandler.resetGameOnClick(point);
	}

	public void keyTyped(KeyEvent e) {
		keyListener.keyTyped(e);
	}

	public void keyReleased(KeyEvent e) {
		keyListener.keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {
		keyListener.keyPressed(e);
	}
}