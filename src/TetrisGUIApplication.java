import javax.swing.JFrame;

/**
 * Main application for starting up the Tetris GUI 
 * @author Xijie Guo
 *
 */
public class TetrisGUIApplication {
	/**
	 * Main method
	 * @param args a String type array
	 */
	public static void main(String[] args) {
		//Create a new JFrame to hold the Tetris board
		JFrame tetrisFrame = new JFrame();
		
		//Set the size of the frame
		tetrisFrame.setSize(400, 800);
		
		//Add new TetrisController to the frame
		tetrisFrame.add(new TetrisGUIController());
		
		//Sets the operation that will happen by default when the user initiates a "close" on this frame
		tetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Make the frame visible
		tetrisFrame.setVisible(true);
	}
}
