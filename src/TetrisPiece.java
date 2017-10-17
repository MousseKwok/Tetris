import java.awt.Color;

/**
 * TetrisPiece constructs tetris pieces Superclass
 * @author Xijie Guo
 *
 */
public class TetrisPiece {
	//piece defined by 3D boolean array[number of roatations][num of rows][num of cols]
	protected boolean[][][] filledSquares = new boolean [4][4][4];
	
	//First set piece rotation to 0
	public int pieceRotation = 0;
	
	/**
	 * Constructor for TetrisPiece
	 */
	public TetrisPiece() {
	}
	
	/**
	 * Rotate clockwise
	 */
	public void rotateCW() {
		//For first three rotations, rotate clockwise 90 degrees each time
		if(pieceRotation < 3) {
			
			//Update piece rotation
			pieceRotation = pieceRotation + 1;
		}
		
		//For the last rotation, rotate it back to the state of first rotation
		else {
			pieceRotation = pieceRotation - 3;
		}
	}
   
	/**
	 * Rotate counterclockwise
	 */
	public void rotateCCW() {
		//For first three rotations, rotate counterclockwise 90 degrees each time
		if(pieceRotation > 0) {
			
			//Update piece rotation
			pieceRotation = pieceRotation - 1;
		}
		
		//For last one, rotate it back to the state of first rotation
		else {
			pieceRotation = pieceRotation + 3;
		}
	}
	
	/**
	 * Get piece rotation
	 * @return piece rotation
	 */
	public int getPieceRotation() {
		return pieceRotation;
	}
	
	/**
	 * Determine if one block in the piece grid is filled
	 * @param rot piece rotation
	 * @param row row of piece grid
	 * @param col column of piece grid
	 * @return true if it is filled
	 */
	public boolean isFilled(int rot, int row, int col) {
		if(filledSquares[rot][row][col]) {
			return true;
	        }
		return false;
	}
	
}
