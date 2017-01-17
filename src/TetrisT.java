/**
 * Subclass of TetrisPiece. Inherit variables and methods from superclass
 * @author Xijie Guo
 *
 */
public class TetrisT extends TetrisPiece{
	
	/**
	 * Constructor of TetrisT
	 */
	public TetrisT() {
		filledSquares = new boolean[][][] {
			{
				{true, true, true, false},
	    		{false, true, false, false},
	    		{false, false, false, false},
	    		{false, false, false, false}
			},
			
			{
				{false, true, false, false},
	    		{true, true, false, false},
	    		{false, true, false, false},
	    		{false, false, false, false}
			},
			
			{
				{false, true, false, false},
	    		{true, true, true, false},
	    		{false, false, false, false},
	    		{false, false, false, false}
			},
			
			{
				{true, false, false, false},
	    		{true, true, false, false},
	    		{true, false, false, false},
	    		{false, false, false, false}
			}
				
		};
	}
}
