import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ConsoleController manages key handling events
 * It has an instance of TetrisGame and an instance of TetrisView
 * @author Xijie Guo
 */
public class TetrisGUIController extends JPanel implements KeyListener
{
	//fixed default drop rate
	public static final int DEFAULT_DROP_RATE = 1000; 
	
	//Drop rate of timer
	private int dropRate;
	
	//timer of tetris game
	private Timer gameTimer;
	
	//JLabel represents how many lines cleared
	private JLabel linesLabel;
	
	//JLabel represents how many tetrises cleared
	private JLabel tetrisesLabel;
	
	//Instance of TetrisGUIGame
	private TetrisGUIGame game;

	//Instance of TetrisGUIView
	private TetrisGUIView view;

	/**
	 * Constructor for controller class
	 */
	public TetrisGUIController()
	{
		super(new BorderLayout());
		
		//Initiate drop rate of timer to the default drop rate
		this.dropRate = DEFAULT_DROP_RATE;
		
		//Instantiate TetrisGame
		game = new TetrisGUIGame();

		//Instantiate TetrisView
		view = new TetrisGUIView(game.getTetrisBoard());
		
		//Call createView method to create display of tetris game
		createView();
		
		//Start timer
		setupTimer();
		refreshDisplay();
		//In order for the use of keyboard 
		setFocusable(true);
		
		//Add KeyListener
		addKeyListener(this);
	}
	
	/**
	 * Manage the timer including when to start it and when to stop it
	 */
	private void setupTimer() {
		ActionListener timerListener = new ActionListener() {
			//Perform the task inside actioinPerformed method
			public void actionPerformed(ActionEvent evt) {
				
				//If game ends, stop the timer
				if(game.checkForGameEnd()) {
					gameTimer.stop();
				}
				//Move down 
				game.attemptMove(TetrisGUIGame.DOWN);
				
				//Update the display
				refreshDisplay();
			}
		};
		
		//Create a timer
		gameTimer = new Timer(dropRate, timerListener);
		
		//Start the timer
		gameTimer.start();
		
	}
	
	/**
	 * Create the display for tetris game
	 */
	private void createView() {
		
		//Create a JLabel displaying how many lines cleared
		linesLabel = new JLabel("Lines cleared: " + 0);
		
		//Create a JLabel displaying how many tetrises cleared
		tetrisesLabel = new JLabel("Tetrises cleared: " + 0);
		
		//Create a JPanel
		JPanel panel = new JPanel();
		
		//Add those two JLabels into this panel
		panel.add(linesLabel);
		panel.add(tetrisesLabel);
		
		//Put those two labels vertically
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//Add the panel to the top of the frame
		add(panel, BorderLayout.NORTH);
		
		//Add board to the rest place of the frame
		add(view, BorderLayout.CENTER);
		
	}
	
	/**
	 * Update display of the game
	 */
	public void refreshDisplay() {
		//Update how many lines cleared
		linesLabel.setText("Lines cleared: " + game.getNumLinesCleared());
		
		//Update how many tetrises cleared
		tetrisesLabel.setText("Tetrises cleared: " + game.getNumTetrisesCleared());
		
		repaint();
	}
	
	/**
	 * Do something when a key is pressed
	 * @param e the key that is pressed
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//Right key on the keyboard
		if (key == KeyEvent.VK_RIGHT) {
	        game.attemptMove(1);
	        refreshDisplay();
	    }
		
		//Left key
		else if(key == KeyEvent.VK_LEFT) {
			game.attemptMove(2);
			refreshDisplay();
		}
		
		//Down key
		else if(key == KeyEvent.VK_DOWN) {
			game.attemptMove(3);
			refreshDisplay();
		}
		
		//x key for clockwise rotation
		else if(key == KeyEvent.VK_X) {
			game.attemptMove(4);
			refreshDisplay();
		}
		
		//z key for counterclockwise rotation
		else if(key == KeyEvent.VK_Z) {
			game.attemptMove(5);
			refreshDisplay();
		}

	}
    
	/**
	 * Move the circle when a key is released
	 * @param e the key that is released
	 */
	public void keyReleased(KeyEvent e) {
	    
	}

	/**
	 * Do something when a key is pressed and released
	 * @param e the key that is pressed and released
	 */
	public void keyTyped(KeyEvent e) {
	}

}
