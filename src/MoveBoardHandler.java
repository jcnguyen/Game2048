import java.awt.event.*;

public class MoveBoardHandler extends SingleActionPerKeyPressHandler {

	private GameBoard board;
	
	public MoveBoardHandler(GameBoard board) {
		this.board = board;
	}
	
	@Override
	public void handleKeyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (isArrowKey(keyCode)) {
			board.moveBoard(keyCode);
		}
	}
	
	private Boolean isArrowKey(int keyEvent) {
		return keyEvent == KeyEvent.VK_UP 
				|| keyEvent == KeyEvent.VK_DOWN 
				|| keyEvent == KeyEvent.VK_LEFT
				|| keyEvent == KeyEvent.VK_RIGHT;
	}
}
