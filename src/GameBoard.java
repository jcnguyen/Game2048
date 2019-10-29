import Constants.Color2048;
import objectdraw.*;

public class GameBoard {
	
	private static final int TILE_VALUE = 2;
	

	private boolean hasATileMoved; // remembers if a tile has been moved
	private int numCells;
	private int tileSize;
	private int tileOffset;
	private DrawingCanvas canvas;
	private Location boardLoc;
	private ScoreBoard scoreBoard;
	private Field field;

	/**
	 * Constructs the board.
	 * 
	 * @param boardLoc      the coordinates of the board
	 * @param size  The size of the board.
	 * @param numCells      the number of rows and columns in the board
	 * @param tileSize      the width and height of each tile
	 * @param tileOffset    the space between each tile
	 * @param scoreBoard    the current score
	 * @param canvas        where the board is drawn
	 */
	public GameBoard(Location boardLoc, int size, int numCells, int tileSize, int tileOffset, ScoreBoard scoreBoard, DrawingCanvas canvas) {
		this.numCells = numCells;
		this.boardLoc = boardLoc;
		this.tileSize = tileSize;
		this.tileOffset = tileOffset;
		this.scoreBoard = scoreBoard;
		this.canvas = canvas;
		
		drawGameBoard(size);
		
		field = new Field(numCells);
		hasATileMoved = false;
	}
	
	private void drawGameBoard(int size) {
		new GameBoardDrawable(size, canvas);
	}

	/**
	 * Checks if the player can make a legal move.
	 * 
	 * @return True if the player can legally move at least one tile;
	 *         false otherwise.
	 */
	public boolean canMove() {
		return field.hasEmptyCell() || field.hasAdjacentTilesWithSameValue();
	}
	
	/**
	 * Checks if there's a winning tile.
	 *  
	 * @return True if there is; false otherwise.
	 */
	public boolean hasWinningTile() {
		return field.hasWinningTile();
	}

	/**
	 * Randomly adds a tile to the board.
	 * 
	 * @pre the board is not full
	 */
	public void addRandomTile() {
		// todo check that board isn't full;
		
		field.addRandomTile();
	}
	
	/**
	 * Resets the board.
	 */
	public void reset() {
		field.reset();
	}

	/**
	 * Moves the tile at cell2 (row2, col2) to cell1 (row1, col1).
	 * If cell1 contains a tile, then this method merges the tiles.
	 * 
	 * @pre the cell at (row2, col2) is not null (i.e. it has a tile on it)
	 * @param row1    the row position of cell1
	 * @param col1    the col position of cell1
	 * @param row2    the row position of cell2
	 * @param col2    the col position of cell2
	 */
	private void moveMergeTile(int row1, int col1, int row2, int col2) {
		int valOfTile1 = field.emptyAt(row1, col1) ? 0 : field.getTileValue(row1, col1);
		int valOfTile2 = field.getTileValue(row2, col2);
		if (!field.emptyAt(row1, col1)) {
			field.removeTile(row1, col1);
		}
		field.removeTile(row2, col2);
		
		int newVal = valOfTile1 + valOfTile2;
		field.addTile(row1, col1, newVal);
		scoreBoard.addToScore(newVal);
		hasATileMoved = true;
	}
	

	/**
	 * Moves tiles to the left. 
	 */
	public void moveLeft() {
		hasATileMoved = false;

		// move or merge every tile on the board
		for (int row = 0; row < numCells; row++) {
			boolean tileMerged = false; // prevents tile from merging twice
			
			for (int col = 1; col < numCells; col++) { 
				if (!field.emptyAt(row, col)) { // find tile 1
					int col2 = col-1;
					while ((field.emptyAt(row, col2)) && (col2-1 > -1)) {
						col2--;
					}

					if (!field.emptyAt(row, col2)) { // find tile 2
						// move or merge tile 1
						int val1 = field.getTileValue(row, col);
						int val2 = field.getTileValue(row, col2);
						if (val1 == val2 && !tileMerged) {
							tileMerged = true;
						} else {
							col2++;
							tileMerged = false;
						}
					} 

					// move or merge here
					if (col2 != col) { 
						moveMergeTile(row, col2, row, col); 
					}
				}
			}
		}

		if (hasATileMoved) {
			addRandomTile();
		}
	}

	/**
	 * Moves tiles to the right. 
	 */
	public void moveRight() {
		hasATileMoved = false;

		// move or merge every tile on the board
		for (int row = 0; row < numCells; row++) {
			boolean tileMerged = false; // prevents tile from merging twice
			for (int col = numCells - 2; col > -1; col--) {
				if (!field.emptyAt(row, col)) { // find tile 1
					int col2 = col+1;
					while ((field.emptyAt(row, col2)) && (col2+1 < numCells)) {
						col2++;
					}

					if (!field.emptyAt(row, col2)) { // find tile 2
						// move or merge tile 1
						int val1 = field.getTileValue(row, col);
						int val2 = field.getTileValue(row, col2);
						if (val1 == val2 && !tileMerged) {
							tileMerged = true;
						} else {
							col2--;
							tileMerged = false;
						}
					} 

					// move or merge here
					if(col2 != col) { 
						moveMergeTile(row, col2, row, col); 
					}
				}
			}
		}

		// add tile if a move/merge occurred
		if (hasATileMoved) {
			addRandomTile();
		}
	}

	/**
	 * Moves tiles upward. 
	 */
	public void moveUp() {
		hasATileMoved = false;

		// move or merge every tile on the board
		for (int col = 0; col < numCells; col++) {
			boolean tileMerged = false; // prevents tile from merging twice
			for (int row = 1; row < numCells; row++) {
				if (!field.emptyAt(row, col)) { // find tile 1
					int row2 = row-1;
					while ((field.emptyAt(row2, col)) && (row2-1 > -1)) {
						row2--;
					}

					if (!field.emptyAt(row2, col)) { // find tile 2
						// move or merge tile 1
						int val1 = field.getTileValue(row, col);
						int val2 = field.getTileValue(row2, col);
						if (val1 == val2 && !tileMerged) {
							tileMerged = true;
						} else {
							row2++;
							tileMerged = false;
						}
					} 

					// move or merge here
					if(row2 != row) { 
						moveMergeTile(row2, col, row, col); 
					}
				}
			}
		}

		// add tile if a move/merge occurred
		if(hasATileMoved) {
			addRandomTile();
		}
	}

	/**
	 * Moves tiles downward. 
	 */
	public void moveDown() {
		hasATileMoved = false;

		// move or merge every tile on the board
		for (int col = 0; col < numCells; col++) {
			boolean tileMerged = false; // prevents tile from merging twice
			for (int row = numCells - 2; row > -1; row--) {
				if (!field.emptyAt(row, col)) { // find tile 1
					int row2 = row+1;
					while ((field.emptyAt(row2, col)) && (row2+1 < numCells)) {
						row2++;
					}

					if (!field.emptyAt(row2, col)) { // find tile 2
						// move or merge tile 1
						int val1 = field.getTileValue(row, col);
						int val2 = field.getTileValue(row2, col);
						if (val1 == val2 && !tileMerged) {
							tileMerged = true;
						} else {
							row2--;
							tileMerged = false;
						}
					} 

					// move or merge here
					if(row2 != row) { 
						moveMergeTile(row2, col, row, col); 
					}
				}
			}
		}

		// add tile if a move/merge occurred
		if(hasATileMoved) {
			addRandomTile();
		}
	}

	/**
	 * Converts a (row, col) position into a coordinate.
	 * 
	 * @param pos    the position (row, column) of a cell on the board
	 * @return the corresponding coordinates of the position
	 */
	private Location posToCoord(int row, int col) {
		double x = boardLoc.getX() + (col+1)*tileOffset + col*tileSize;
		double y = boardLoc.getY() + (row+1)*tileOffset + row*tileSize;
		return new Location(x, y);
	}
	
	class Field {
		
		private static final int WIN_TILE = 2048;
		
		private Tile[][] field;
		private int numCells;
		private RandomIntGenerator randomPositionGenerator; 
		
		protected Field(int numCells) {
			this.numCells = numCells;
			
			field = new Tile[numCells][numCells];
			randomPositionGenerator = new RandomIntGenerator(0, numCells - 1);
		}
		
		protected boolean hasEmptyCell() {
			for (int row = 0; row < numCells; row++) {
				for (int col = 0; col < numCells; col++) {
					if (field[row][col] == null) {
						return true;
					}
				}
			}
			return false;
		}
		
		protected boolean hasAdjacentTilesWithSameValue() {
			for (int row = 0; row < numCells; row++) {
				for (int col = 1; col < numCells; col++) {
					if (field[row][col].getValue() == field[row][col-1].getValue()) {
						return true;
					}
				}
			}
			
			for (int col = 0; col < numCells; col++) { 
				for (int row = 1; row < numCells; row++) {
					if (field[row][col].getValue() == field[row-1][col].getValue()) {
						return true;
					}
				}
			}
			
			return false;
		}
		
		protected boolean hasWinningTile() {
			for (int row = 0; row < numCells; row++) {
				for (int col = 0; col < numCells; col++) {
					if (field[row][col] != null && field[row][col].getValue() == WIN_TILE) {
						return true;
					}
				}
			}
			return false;
		}
	
		protected void addTile(int row, int col, int tileValue) {
			field[row][col] = new Tile(tileValue, posToCoord(row, col), tileSize, canvas);
		}
		
		protected void addRandomTile() {
			int[] position = randomlyChooseEmptyCell();
			field[position[0]][position[1]] = new Tile(TILE_VALUE, posToCoord(position[0], position[1]), tileSize, canvas);
		}
		
		protected void removeTile(int row, int col) {
			field[row][col].removeFromCanvas();
			field[row][col] = null;
		}
		
		protected void reset() {
			for (int row = 0; row < numCells; row++) {
				for (int col = 0; col < numCells; col++) {
					if (field[row][col] != null) {
						removeTile(row, col);
					}
				}
			}
		}
		
		protected Tile getTile(int row, int col) {
			return field[row][col];
		}
		
		protected int getTileValue(int row, int col) {
			return getTile(row, col).getValue();
		}
		
		protected Boolean emptyAt(int row, int col) {
			return field[row][col] == null;
		}
		
		private int[] randomlyChooseEmptyCell() {
			int row = randomPositionGenerator.nextValue();
			int col = randomPositionGenerator.nextValue();
			while (field[row][col] != null) {
				row = randomPositionGenerator.nextValue();
				col = randomPositionGenerator.nextValue();
			}
			
			int[] ret = {row, col};
			return ret;
		}
	}

	class GameBoardDrawable {
		
		private DrawingCanvas canvas;
		private int size;
		
		protected GameBoardDrawable(int size, DrawingCanvas canvas) {
			this.size = size;
			this.canvas = canvas;
			
			drawEmptyBoard();
		}
		
		private void drawEmptyBoard() {
			drawBoardOutline();
			drawCells();
		}

		private void drawBoardOutline() {
			new FilledRect(boardLoc, size, size, canvas).setColor(Color2048.BOARD_BG);
		}
		
		private void drawCells() {
			for(int row = 0; row < numCells; row++) {
				for(int col = 0; col < numCells; col++) {
					Location cellLoc = posToCoord(row, col);
					new FilledRect(cellLoc.getX(), cellLoc.getY(), tileSize, tileSize, canvas).setColor(Color2048.CELL_BG);
				}
			}
		}

	}
}
