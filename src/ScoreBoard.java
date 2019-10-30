import objectdraw.*;
import Constants.ScoreBoardStyle;
import Constants.Strings;

public class ScoreBoard {

	private ScoreBoardDrawer scoreBoardDisplay;
	private int score = 0;

	public ScoreBoard(Location location, DrawingCanvas canvas) {
		scoreBoardDisplay = new ScoreBoardDrawer(location, canvas);
		reset();
	}

	public void reset() {
		score = 0;
		scoreBoardDisplay.reset();
	}

	public void addToScore(int points) {
		score += points;
		scoreBoardDisplay.update(score);
	}

	class ScoreBoardDrawer {

		private static final int DEFAULT_SCORE = 0;

		private DrawingCanvas canvas;
		private Location location;

		private Text scoreDisplay;

		protected ScoreBoardDrawer(Location location, DrawingCanvas canvas) {
			this.location = location;
			this.canvas = canvas;

			drawBox();
			drawTitle();
			drawScore();
		}

		protected void reset() {
			update(DEFAULT_SCORE);
		}

		protected void update(int score) {
			scoreDisplay.setText(score);
			centerText(scoreDisplay);
		}

		private void drawBox() {
			new FilledRect(location, ScoreBoardStyle.WIDTH, ScoreBoardStyle.HEIGHT, canvas)
					.setColor(ScoreBoardStyle.BACKGROUND_COLOR);
		}

		private void drawTitle() {
			Title title = new Title(Strings.SCORE_TITLE, location, ScoreBoardStyle.TEXT_SIZE,
					ScoreBoardStyle.TEXT_COLOR, canvas);
			centerText(title);
		}

		private void drawScore() {
			scoreDisplay = new Text(DEFAULT_SCORE, calculateScoreLocation(), canvas);
			scoreDisplay.setColor(ScoreBoardStyle.TEXT_COLOR);
			scoreDisplay.setFontSize(ScoreBoardStyle.TEXT_SIZE);
			centerText(scoreDisplay);
		}

		private Location calculateScoreLocation() {
			double y = location.getY() + ScoreBoardStyle.HEIGHT / 2;
			return new Location(location.getX(), y);
		}

		private void centerText(Text text) {
			double x = location.getX() + (ScoreBoardStyle.WIDTH - text.getWidth()) / 2;
			double y = text.getY();
			text.moveTo(x, y);
		}
	}
}
