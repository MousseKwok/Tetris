/**
 * Subclass of TetrisPiece. Inherit variables and methos from superclass
 * @author Xijie Guo
 *
 */
public class TetrisS extends TetrisPiece {
	public TetrisS() {
		filledSquares = new boolean[][][] {
			{
				{false, true, true, false},
				{true, true, false, false},
				{false, false, false, false},
				{false, false, false, false}
			},

			{
				{true, false, false, false},
				{true, true, false, false},
				{false, true, false, false},
				{false, false, false, false}
			},
			{
				{false, true, true, false},
				{true, true, false, false},
				{false, false, false, false},
				{false, false, false, false}
			},

			{
				{true, false, false, false},
				{true, true, false, false},
				{false, true, false, false},
				{false, false, false, false}
			}

		};
	}
}
