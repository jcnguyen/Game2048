import objectdraw.*;

public class GameBoard {
	
	private static final int TILE_VALUE = 2;
	private static final int WIN_TILE = 2048;

	private boolean tileMoved; // remembers if a tile has been moved
	private int numCells;
	private int tileSize;
	private int tileOffset;
	private DrawingCanvas canvas;
	private Location boardLoc;
	private ScoreBoard scoreBoard;
	private RandomIntGenerator randGen; // generates random board position
	private Tile[][] board;

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
		
		board = new Tile[numCells][numCells];
		randGen = new RandomIntGenerator(0, numCells-1);
		tileMoved = false;
	}
	
	private void drawGameBoard(int size) {
		new GameBoardDrawable(size, canvas);
	}

	/**
	 * Determines if the board is full.
	 * 
	 * @return true if all the cells of the board contains a tile,
	 *         false if there is an empty cell
	 */
	private boolean isBoardFull() {
		for (int row = 0; row < numCells; row++) {
			for (int col = 0; col < numCells; col++) {
				if (board[row][col] == null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Determines if the player can make a legal move.
	 * 
	 * @return true if the player can legally move at least one tile,
	 *         false otherwise
	 */
	public boolean canMove() {
		if(!isBoardFull()) {
			return true;
		} else {
			// checks if there are adjacent tiles with the same value
			for(int row = 0; row < numCells; row++) { // row-major
				for(int col = 1; col < numCells; col++) {
					if(board[row][col].getValue() == board[row][col-1].getValue()) {
						return true;
					}
				}
			}
			for(int col = 0; col < numCells; col++) { // column-major
				for(int row = 1; row < numCells; row++) {
					if(board[row][col].getValue() == board[row-1][col].getValue()) {
						return true;
					}
				}
			}
			return false;
		}
	}
	
	public boolean istile2048() {
		// checks if there is a 2048 tile
		for(int row = 0; row < numCells; row++) { // row-major
			for(int col = 0; col < numCells; col++) {
				if(board[row][col] != null) {
					if(board[row][col].getValue() == WIN_TILE) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Randomly adds a tile to the board.
	 * 
	 * @pre the board is not full
	 */
	public void addTile() {
		// randomly choose an empty cell
		int row = randGen.nextValue();
		int col = randGen.nextValue();
		while (board[row][col] != null) {
			row = randGen.nextValue();
			col = randGen.nextValue();
		}

		// add a tile to that cell
		Location cellLoc = posToCoord(row, col);
		board[row][col] = new Tile(TILE_VALUE, cellLoc, tileSize, canvas);
	}

	/**
	 * Removes the tile at position (row, col).
	 * 
	 * @param row    the row that the tile is located
	 * @param col    the column that the tile is located
	 */
	private void removeTile(int row, int col) {
		board[row][col].removeFromCanvas();
		board[row][col] = null;
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
		// get information on tiles
		int val1 = (board[row1][col1] == null ? 0 : board[row1][col1].getValue());
		int val2 = board[row2][col2].getValue();
		if(board[row1][col1] != null) removeTile(row1, col1);
		removeTile(row2, col2);
		
		// calculate information used to construct the new tile
		int newVal = val1 + val2;
		Location cellLoc = posToCoord(row1, col1);
		
		// construct the new tile and update
		board[row1][col1] = new Tile(newVal, cellLoc, tileSize, canvas);
		scoreBoard.addToScore(newVal);
		tileMoved = true;
	}

	/**
	 * Moves tiles to the left. 
	 */
	public void moveLeft() {
		tileMoved = false;

		// move or merge every tile on the board
		for (int row = 0; row < numCells; row++) {
			boolean tileMerged = false; // prevents tile from merging twice
			for (int col = 1; col < numCells; col++) { 
				if (board[row][col] != null) { // find tile 1
					int col2 = col-1;
					while ((board[row][col2] == null) && (col2-1 > -1)) {
						col2--;
					}

					if (board[row][col2] != null) { // find tile 2
						// move or merge tile 1
						int val1 = board[row][col].getValue();
						int val2 = board[row][col2].getValue();
						if (val1 == val2 && !tileMerged) {
							tileMerged = true;
						} else {
							col2++;
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
		if(tileMoved) {
			addTile();
		}
	}

	/**
	 * Moves tiles to the right. 
	 */
	public void moveRight() {
		tileMoved = false;

		// move or merge every tile on the board
		for (int row = 0; row < numCells; row++) {
			boolean tileMerged = false; // prevents tile from merging twice
			for (int col = numCells - 2; col > -1; col--) {
				if (board[row][col] != null) { // find tile 1
					int col2 = col+1;
					while ((board[row][col2] == null) && (col2+1 < numCells)) {
						col2++;
					}

					if (board[row][col2] != null) { // find tile 2
						// move or merge tile 1
						int val1 = board[row][col].getValue();
						int val2 = board[row][col2].getValue();
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
		if(tileMoved) {
			addTile();
		}
	}

	/**
	 * Moves tiles upward. 
	 */
	public void moveUp() {
		tileMoved = false;

		// move or merge every tile on the board
		for (int col = 0; col < numCells; col++) {
			boolean tileMerged = false; // prevents tile from merging twice
			for (int row = 1; row < numCells; row++) {
				if (board[row][col] != null) { // find tile 1
					int row2 = row-1;
					while ((board[row2][col] == null) && (row2-1 > -1)) {
						row2--;
					}

					if (board[row2][col] != null) { // find tile 2
						// move or merge tile 1
						int val1 = board[row][col].getValue();
						int val2 = board[row2][col].getValue();
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
		if(tileMoved) {
			addTile();
		}
	}

	/**
	 * Moves tiles downward. 
	 */
	public void moveDown() {
		tileMoved = false;

		// move or merge every tile on the board
		for (int col = 0; col < numCells; col++) {
			boolean tileMerged = false; // prevents tile from merging twice
			for (int row = numCells - 2; row > -1; row--) {
				if (board[row][col] != null) { // find tile 1
					int row2 = row+1;
					while ((board[row2][col] == null) && (row2+1 < numCells)) {
						row2++;
					}

					if (board[row2][col] != null) { // find tile 2
						// move or merge tile 1
						int val1 = board[row][col].getValue();
						int val2 = board[row2][col].getValue();
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
		if(tileMoved) {
			addTile();
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

	/**
	 * Resets the board.
	 */
	public void restart() {
		for (int row = 0; row < numCells; row++) {
			for (int col = 0; col < numCells; col++) {
				if(board[row][col] != null) {
					board[row][col].removeFromCanvas();
					board[row][col] = null;
				}
			}
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
