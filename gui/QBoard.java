package gui;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Board;

public class QBoard extends JFrame {

	public final static String BOARD_TITLE = "Quoridor Board";
	public final static Color SQUARE_DEFAULT_COLOR = Color.black;
	public final static int boardlth = 9; //The number of squares in a board object
	
	public JButton[][] squares = new JButton[boardlth][boardlth]; //The tile buttons
	public JButton[][] vertWalls = new JButton[boardlth-1][boardlth]; //The vertical wall buttons
	public JButton[][] horWalls = new JButton[boardlth][boardlth-1]; //The horizontal wall buttons

	private JPanel buttonCanvas; // Canvas that holds all the buttons of the game
	
	/**
	 * Quoridor Board GUI Constructor.
	 * Creates a 9x9 grid of buttons to play the game. Also instantiates
	 * buttons for the vertical and horizontal walls.
	 * @param b: The board object that gets passed to the board created.
	 */
	public QBoard(Board b) {
		super();
		initialize();
	}
	
	/**
	 * Instantiates the whole board complete with buttons.
	 */
	private void initialize() {
		setName(BOARD_TITLE);
		setTitle(BOARD_TITLE);
		setSize(512, 369);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		buttonCanvas = new JPanel();
		buttonCanvas.setLayout(null);
		
		initializeButtons();
		buttonCanvas.setSize(356, 356);

		add(buttonCanvas);

		setVisible(true);
	}
	
	/**
	 * Instantiates all buttons on the board game grid.
	 */
	private void initializeButtons(){
		int fromTop = 5; // Offsets the grid from the edge of the board
		boolean walls = false;
		for(int i = 0; i < 17; i++){
			int fromLeft = 6;
			for (int j = 0; j < 17; j++) {
				if(!walls){
					if(j%2 == 0){
						JButton button = buttonHelper();
						Insets insets = buttonCanvas.getInsets();
						button.setBounds(fromLeft + insets.left, fromTop + insets.top, 20, 20);
						fromLeft += 26;
						squares[j/2][i/2]=button;
					} else {
						JButton button = buttonHelper();
						Insets insets = buttonCanvas.getInsets();
						button.setBounds(fromLeft + insets.left, fromTop + insets.top, 5, 20);
						fromLeft += 11;
						vertWalls[j/2][i/2]=button;
					}
				}
				if (walls){
					JButton button = buttonHelper();
					Insets insets = buttonCanvas.getInsets();
					button.setBounds(fromLeft + insets.left, fromTop + insets.top, 20, 5);
					fromLeft += 37;
					j++;
					horWalls[j/2][i/2]=button;
				}
			}
			if(!walls){
				fromTop += 26;
			}else{
				fromTop += 11;
			}
			walls = !walls;
		}
	}
	
	public JButton buttonHelper(){
		JButton button = new JButton("");
		//button.addActionListener(this);
		button.setBackground(SQUARE_DEFAULT_COLOR);
		buttonCanvas.add(button);
		return button;
	}
	
	
	public static void main(String[] args) {
		Board board = new Board(2);
		new QBoard(board);
	}
}
