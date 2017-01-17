import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Graphics;

/**
 * TetrisView gets board and display it
 * @author Xijie Guo
 *
 */
public class TetrisGUIView extends JComponent{

	//Instance of TetrisBoard
	private TetrisBoard board;

	/**
	 * Constructor for TetrisView
	 * @param board TetrisBoard
	 */
	public TetrisGUIView(TetrisBoard board) {
		this.board = board;
		//If true the component paints every pixel within its bounds
		this.setOpaque(true);

	}
	
	/**
	 * Paint board outline and blocks
	 * @param g for the Graphics object
	 */
	public void paint(Graphics g) {
		paintBoardOutline(g, computeBlockSize());
		paintBlock(g, computeBlockSize());
	}
	
	/**
	 * Compute the size of each block
	 * @return size of each block
	 */
	private int computeBlockSize() {
		return getWidth() / 10;
	}
	
	/**
	 * Paint board outline
	 * @param g for Graphics object
	 * @param blockSize for size of each block
	 */
	private void paintBoardOutline(Graphics g, int blockSize) {
		//Get the size of the block
		blockSize = computeBlockSize();
		
		//Draw the board outline 
		g.drawRect(0, 0, blockSize * 10, blockSize * 18);
	}
	
	/**
	 * Paint the blocks
	 * @param g for Graphics object
	 * @param blockSize for size of each block
	 */
	public void paintBlock(Graphics g, int blockSize) {
		//Get the board
		boolean[][] gridArray = board.getBlockMatrix();
		
		//Get the size of block
		blockSize = computeBlockSize();
		
		//Loop through the whole board
		for(int i = 0; i < gridArray.length; i++) {
			for(int j = 0; j < gridArray[i].length; j++) {
				
				//If the block is occupied
				if(board.hasBlock(i, j)) {
					
					//Set the color of the block and draw the block
					g.setColor(Color.magenta);
					g.fillRect(j * blockSize, i * blockSize, blockSize, blockSize);
					
					//Add the outline of block
					g.setColor(Color.black);
					g.drawRect(j * blockSize, i * blockSize, blockSize, blockSize);
					
				}
			}
		}
	}
}
