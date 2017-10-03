/**
 * TetrisGame stores current state of the game. It's updated by controller
 * @author Xijie Guo
 *
 */
public class TetrisGUIGame {

	//Number that represents right move
	public static final int RIGHT = 1;

	//Number that represents left move
	public static final int LEFT = 2;

	//Number that represents move down
	public static final int DOWN = 3;

	//Number that represents clockwise rotation
	public static final int CW = 4;

	//Number that represents counterclockwise rotation
	public static final int CCW = 5;
	
        public boolean isEndRound;

	//Instance of TetrisBoard
	private TetrisBoard tetrisBoard;

	/**
	 * Constructor for TetrisGame
	 */
	public TetrisGUIGame() {
		tetrisBoard = new TetrisBoard();
	}

	/**
	 * Try to move the current piece with RIGHT, LEFT, DOWN, CW, CCW
	 * @param moveType for 5 kinds of move, right, left, down, rotate CW, rotate CCW
	 */
	public void attemptMove(int moveType) {
		//Move right
		if(moveType == RIGHT) {
			tetrisBoard.moveRight();
		}

		//Move left
		else if(moveType == LEFT) {
			tetrisBoard.moveLeft();
		}

		//Move down
		else if(moveType == DOWN) {
			if(!tetrisBoard.moveDown()) {
				endRound();
			}
		}

		//Rotate clockwise
		else if(moveType == CW) {
			tetrisBoard.rotateCW();
		}

		//rotate counterclockwise
		else if(moveType == CCW) {
			tetrisBoard.rotateCCW();
		}
	}

	/**
	 * Performed when a piece cannot move down anymore
	 * Ends the round by checking if the game should be over
	 */
	public void endRound() {
		//If game is not over, update landed pieces information, add new pieces, and update number of lines
		if(!tetrisBoard.gameOver()) {
			tetrisBoard.landPiece();
			tetrisBoard.numberOfFormedLines();
		}

		//If game is over, set isEndRound to true
		else if(tetrisBoard.gameOver()){
			isEndRound = true;
		}
	}

	/**
	 * Get the number of lines cleared
	 * @return the number of lines cleared
	 */
	public int getNumLinesCleared() {
		return tetrisBoard.numberOfFormedLines();
	}

	/**
	 * Get the number of tetrises cleared
	 * @return the number of tetrises cleared
	 */
	public int getNumTetrisesCleared() {
		return tetrisBoard.getNumRows() / 4;
	}

	/**
	 * Get the tetris board
	 * @return tetris board
	 */
	public TetrisBoard getTetrisBoard() {
		return tetrisBoard;
	}

	/**
	 * Check for game end in order to use this to determine if the timer needs to stop in controller class
	 * @return true if game ends
	 */
	public boolean checkForGameEnd() {
		if(isEndRound) {
			return true;
		}
		return false;
	}
}
