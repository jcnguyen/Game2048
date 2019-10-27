public class Score {

	private int score = 0;
	
	public Score() {
		
	}
	
	protected int getScore() {
		return score;
	}
	
	protected void add(int points) {
		score += points;
	}
	
	protected void reset() {
		score = 0;
	}
}
