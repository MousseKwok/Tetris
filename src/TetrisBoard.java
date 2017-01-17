import java.awt.Color;

/**
 * TetrisBoard represents the whole board
 * It checks rules for the game and manage movements of tetris piece
 * @author Xijie Guo
 *
 */
public class TetrisBoard {

	//Board size with 18 rows and 10 columns
	public static final int NUM_ROWS = 18;
	public static final int NUM_COLS = 10;

	//Number of rows and columns of board
	private int numRows;
	private int numCols;

	//A boolean 2D array which represents board
	private boolean [][] blockMatrix;

	//Instance of TetrisPiece
	private TetrisPiece currentPiece;

	//Stores the current grid position of current piece 
	//An int array with length 2 one stores the x coordinates value the other one for y coordinates value
	private int[] currentPieceGridPosition = new int[2];

	//Number of full lines
	private int numLines;


	/**
	 * Constructor for TetrisBoard
	 */
	public TetrisBoard() {
		//Initialize numRows and numCols
		this.numRows = NUM_ROWS;
		this.numCols = NUM_COLS;

		//create a board
		blockMatrix = new boolean[numRows][numCols];

		//Initialize the board
		initBoard();

		//Start game with a new piece
		addNewPiece();
	}

	/**
	 * Initialize the board. First set every blocks in the board to be false
	 */
	private void initBoard() {
		for(int i = 0; i  < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				blockMatrix[i][j] = false;
			}
		}
	}

	/**
	 * Initialize the current grid position of the current piece at (0, 3)
	 */
	private void initCurrentGP() {                                                                                                                                                                              
		currentPieceGridPosition[0] = 0;
		currentPieceGridPosition[1] = 3;
	}

	/**
	 * Reflect the newly landed piece's filled squares and add a new piece after one piece has landed if game isn't over 
	 */
	public void landPiece() {
		//Get rotation of current piece
		int rot = currentPiece.getPieceRotation();

		//Loop through piece grid to mark on board the filled squares of grid piece
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(currentPiece.isFilled(rot, i, j)) {
					blockMatrix[currentPieceGridPosition[0] + i][currentPieceGridPosition[1] + j] = true;
				}
			}
		}

		//Check if game is over. If it's not over, then add a new piece
		if(!gameOver()) {
			addNewPiece();
		}
	}



	/**
	 * Checking if moving left is valid
	 * @return true if it is a valid move and false otherwise
	 */
	public boolean moveLeft() {
		//If it is valid, move one block to the left
		if(validMove(currentPiece, currentPiece.getPieceRotation(), currentPieceGridPosition[0], currentPieceGridPosition[1] - 1)) {
			currentPieceGridPosition[1] -= 1;

			//Set the moveLeft to be true
			return true;
		}

		//If not valid, set it to false
		return false;
	}

	/**
	 * Checking if moving right is valid
	 * @return true if it is a valid move and false otherwise
	 */
	public boolean moveRight() {
		//If it is valid, move one block to the right
		if(validMove(currentPiece, currentPiece.getPieceRotation(), currentPieceGridPosition[0], currentPieceGridPosition[1] + 1)) {
			currentPieceGridPosition[1] += 1;

			//Set moveRight to be true
			return true;
		}

		//If not valid, set it to false
		return false;
	}

	/**
	 * Checking if moving down is valid
	 * @return true if it is a valid move and false otherwise
	 */
	public boolean moveDown() {

		//If it is valid, move one block down
		if(!gameOver() && validMove(currentPiece, currentPiece.getPieceRotation(), currentPieceGridPosition[0] + 1, currentPieceGridPosition[1])) {
			currentPieceGridPosition[0] += 1;
			//Set the moveDown to be true
			return true;
		}

		//If not valid, remove line and update the number of lines cleared, also set it to be false
		else {
			numberOfFormedLines();
			return false;
		}

	}

	/**
	 * Checking if rotating clockwise is valid
	 * @return true if it's valid and false otherwise
	 */
	public boolean rotateCW() {
		//First rotate the current piece clockwise
		currentPiece.rotateCW();

		//Check if it's a valid move
		if(validMove(currentPiece, currentPiece.getPieceRotation(), currentPieceGridPosition[0], currentPieceGridPosition[1])) {

			//Return true if it is
			return true;
		}
		else {
			//If not valid, undo the move and return false
			currentPiece.rotateCCW();
			return false;
		}
	}

	/**
	 * Checking if rotating counterclockwise is valid
	 * @return true if valid and false otherwise
	 */
	public boolean rotateCCW() {
		//First rotate current piece counterclockwise
		currentPiece.rotateCCW();

		//Check if the move is valid
		if(validMove(currentPiece, currentPiece.getPieceRotation(), currentPieceGridPosition[0], currentPieceGridPosition[1])) {

			//Return true if it is 
			return true;
		}

		//If not valid, undo the move and return false
		else {
			currentPiece.rotateCW();
			return false;
		}

	}

	/**
	 * Checking if placing piece at grid position would cause a collision
	 * @param piece the current piece
	 * @param rot the rotation of the piece
	 * @param gridRow x coordinate of current piece grid position
	 * @param gridCol y coordinate 
	 * @return true if a collision would happen and false otherwise
	 */
	private boolean detectCollision(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		//Loop through piece grid
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				//If the block is filled
				if(piece.isFilled(rot, i, j)) {
					//Check for right, left, bottom blocks
					if(blockMatrix[gridRow + i][gridCol + j] || blockMatrix[gridRow][gridCol + j]) {
						//If there is a block on the right, left, or bottom of the current bottom, return true
						return true;
					}
				}
			}
		}
		//If there's no collision, return false
		return false;
	}

	/**
	 * Checking if placing piece at grid position would cause out of bounds problem
	 * @param piece the current piece
	 * @param rot the rotation of the piece
	 * @param gridRow x coordinate of current piece grid position
	 * @param gridCol y coordinate of current piece grid position
	 * @return true if out of bounds would happen and false otherwise
	 */
	private boolean detectOutOfBounds(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		//Loop through piece grid
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				//If the block is filled
				if(piece.isFilled(rot, i, j)) {
					//Check right bound and left bound and bottom bottom
					if(gridCol + j < 0 || gridCol + j > numCols - 1 || gridRow + i > numRows - 1) {
						//Return true if it tetris piece touches the bound
						return true;
					}
				}
			}
		}
		//Return false otherwise
		return false;
	}

	/**
	 * Check if game is over
	 * @return true if it is over
	 */
	public boolean gameOver() {
		//Check the top row of the board 
		for(int col = 0; col < numCols; col++) {
			//If there is a block in any position of the first row, return true
			if(blockMatrix[0][col]) {
				return true;
			}
		}
		//Return false if there is no block
		return false;
	}
	/**
	 * Check if the move is valid
	 * @param piece the current piece
	 * @param rot the rotation of piece
	 * @param gridRow  x coordinate of current piece grid position
	 * @param gridCol  y coordinate of current piece grid position
	 * @return true if the move is a valid one
	 */
	private boolean validMove(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		//If we didn't detect bounds or detect collision then it's a valid move
		if(!detectOutOfBounds(piece, rot, gridRow, gridCol) && !detectCollision(piece, rot, gridRow, gridCol)) {
			return true;
		}
		//Otherwise it's not a valid move and return false
		return false;
	}

	/**
	 * Get current piece
	 * @return current piece
	 */
	public TetrisPiece getCurrentPiece() {
		return currentPiece;
	}

	/**
	 * Get the current piece grid position
	 * @return the grid position of the current piece grid
	 */
	public int[] getCurrentPieceGridPosition() {
		return currentPieceGridPosition;
	}
	/**
	 * Get the block matrix
	 * @return the block matrix
	 */
	public boolean[][] getBlockMatrix() {
		return blockMatrix;
	}

	/**
	 * Check if there is a block in the row and column
	 * @param row row of the board
	 * @param col column of the board
	 * @return true if there is a block 
	 */
	public boolean hasBlock(int row, int col) {
		//Loop through piece grid
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {

				//Remember the current position of the block of piece grid on the board
				int rowPosition = currentPieceGridPosition[0] + i;
				int colPosition = currentPieceGridPosition[1] + j;

				//Check if the current piece is filled
				if(rowPosition == row && colPosition == col && currentPiece.isFilled(currentPiece.getPieceRotation(), i, j)) {
					return true;
				}
			}
		}
		return blockMatrix[row][col];
	}

	/**
	 * Update the number of full lines and remove it if there is any
	 * @return the number of full lines
	 */
	public int numberOfFormedLines() {
		//Loop through rows of the board
		for(int row = 0; row < numRows; row++) {

			//If the row we are checking is full
			if(fullLine(row)) {

				//Update the number of full lines
				numLines++;

				//Remove full lines
				removeLine(row);
			}
		}
		//Return the number of full lines
		return numLines;
	}

	/**
	 * Check if there is a full line
	 * @param row row of the board
	 * @return true if there's full line and false otherwise
	 */
	private boolean fullLine(int row) {
		//Loop through columns of the board
		for(int i = 0; i < numCols; i++) {

			//If the block we are checking is not occupied return false
			if(!blockMatrix[row][i]) {
				return false;
			}
		}
		//If all blocks in the a row are occupied return true
		return true;
	}

	/**
	 * Remove full lines
	 * @param row board row
	 */
	private void removeLine(int row) {
		//Loop through the current row
		for(int i = row; i > 0; i--) {

			//for each column
			for(int j = 0; j < numCols; j++) {
				//Remove the full row, move the upper level of row down 
				blockMatrix[i][j] = blockMatrix[i - 1][j];
			}
		}

	}

	/**
	 * Get number of full lines
	 * @return the number of full lines
	 */
	public int getNumRows() {
		return numLines;
	}



	/**
	 * Randomize and add new piece
	 */
	public void addNewPiece() {
		//Randomly generate 7 numbers from 0 to 6
		int randomPiece = (int)(Math.random()*7);

		//If the number is 0, create a I shape
		if(randomPiece == 0) {
			currentPiece = new TetrisI();
		}

		//If the number is 1, create a J shape
		else if(randomPiece == 1) {
			currentPiece = new TetrisJ();
		}

		//If the number is 2, create a L shape
		else if(randomPiece == 2) {
			currentPiece = new TetrisL();
		}

		//If the number is 3, create a O shape
		else if(randomPiece == 3) {
			currentPiece = new TetrisO();
		}

		//If the number is 4, create a S shape
		else if(randomPiece == 4) {
			currentPiece = new TetrisS();
		}

		//If the number is 5, create a T shape
		else if(randomPiece == 5) {
			currentPiece = new TetrisT();
		}

		//If the number is 6, create a Z shape
		else if(randomPiece == 6) {
			currentPiece = new TetrisZ();
		}

		//Initialize the current grid position of the piece
		initCurrentGP();
	}
}

