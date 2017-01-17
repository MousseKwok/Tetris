/**
 * Subclass of TetrisPiece. Inherit variables and methods from superclass
 * @author Xijie Guo
 *
 */
public class TetrisZ extends TetrisPiece {

	/**
	 * Constructor of TetrisZ. different states of TetrisZ
	 */
	public TetrisZ() {
		filledSquares = new boolean[][][] {
			{
				{true, true, false, false},
				{false, true, true, false},
				{false, false, false, false},
				{false, false, false, false}
			},

			{
				{false, true, false, false},
				{true, true, false, false},
				{true, false, false, false},
				{false, false, false, false}
			},

			{
				{true, true, false, false},
				{false, true, true, false},
				{false, false, false, false},
				{false, false, false, false}
			},

			{
				{false, true, false, false},
				{true, true, false, false},
				{true, false, false, false},
				{false, false, false, false}
			}

		};
	}
}
