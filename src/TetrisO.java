/**
 * Subclass of TetrisPiece. Inherit variables and methods from superclass
 * @author Xijie Guo
 *
 */
public class TetrisO extends TetrisPiece {

	/**
	 * Constructor of TetrisO. different states of TetrisO
	 */
	public TetrisO() {
		filledSquares = new boolean[][][] {
			{
				{true, true, false, false},
				{true, true, false, false},
				{false, false, false, false},
				{false, false, false, false}
			},

			{
				{true, true, false, false},
				{true, true, false, false},
				{false, false, false, false},
				{false, false, false, false}
			},

			{
				{true, true, false, false},
				{true, true, false, false},
				{false, false, false, false},
				{false, false, false, false}
			},

			{
				{true, true, false, false},
				{true, true, false, false},
				{false, false, false, false},
				{false, false, false, false}
			}

		};
	}
}
