/**
 * Subclass of TetrisPiece. Inherit variables and methods from superclass
 * @author Xijie Guo
 *
 */
public class TetrisL extends TetrisPiece {

	/**
	 * Constructor for TetrisL. Different states of TetrisL
	 */
	public TetrisL() {

		filledSquares = new boolean[][][] {
			{
				{true, true, true, false},
				{true, false, false, false},
				{false, false, false, false},
				{false, false, false, false}

			},
			{
				{true, true, false, false},
				{false, true, false, false},
				{false, true, false, false},
				{false, false, false, false}
			},
			{
				{false, false, true, false},
				{true, true, true, false},
				{false, false, false, false},
				{false, false, false, false}
			},
			{
				{true, false, false, false},
				{true, false, false, false},
				{true, true, false, false},
				{false, false, false, false}
			}
		};
	}

}

