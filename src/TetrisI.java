/**
 * Subclass of TetrisPiece. Inherit variables and methods from superclass
 * @author Xijie Guo
 *
 */
public class TetrisI extends TetrisPiece{

	/**
	 * Constructor of TetrisI. different states of TetrisI
	 */
	public TetrisI() {
		filledSquares = new boolean[][][] {
			{
				{true, true, true, true},
				{false, false, false, false},
				{false, false, false, false},
				{false, false, false, false}
			},

			{
				{true, false, false, false},
				{true, false, false, false},
				{true, false, false, false},
				{true, false, false, false}
			},

			{
				{true, true, true, true},
				{false, false, false, false},
				{false, false, false, false},
				{false, false, false, false}
			},

			{
				{true, false, false, false},
				{true, false, false, false},
				{true, false, false, false},
				{true, false, false, false}
			}
		};
	}

}

