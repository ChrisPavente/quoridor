package gui;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


/**
 * This class makes an opening menu pop up to allow the user the opportunity for options for the game
 *  Quoridor. 
 */
public class Menu extends JFrame {

	public final static String OPTIONS_WINDOW_TITLE = "Quoridor Options"; //This holds the name of the options menu.

	
	/**
	 * This points to the Quoridor object that called the options menu.  Pretty much everything in 
	 * the options menu class makes changes to variables in this quoridor object.
	 */
	Quoridor Q;

	private JLabel PlayersLabel; //This label just says Players on it and sits above the Radio buttons controlling the number of players.
	private JRadioButton twoPlayers, fourPlayers; //These Radiobuttons are used to choose the number of Players.
	private ButtonGroup numberOfPlayers; //This makes it so that when one button is clicked, the other is unclicked.
	
	/**
	 * The constructor that will be called by Quoridor.
	 */
	public Menu() {
		super(OPTIONS_WINDOW_TITLE);
		initialize();
		setVisible(true);	
	}
	/**
	 * Initializes everything.
	 */
	private void initialize() {

		setName(OPTIONS_WINDOW_TITLE);
		setSize(500,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		PlayersLabel = new JLabel("Number of Players");
		PlayersLabel.setSize(140, 30);
		PlayersLabel.setLocation(30, 70);
		add(PlayersLabel);
		
		twoPlayers = new JRadioButton("2 Players");
		fourPlayers = new JRadioButton("4 Players");
		
		twoPlayers.setSize(100, 30);
		fourPlayers.setSize(100, 30);
		twoPlayers.setLocation(30, 100);
		fourPlayers.setLocation(30, 130);
		
//		twoPlayers.addItemListener(this);
//		fourPlayers.addItemListener(this);
		
		add(twoPlayers);
		add(fourPlayers);
		
		numberOfPlayers = new ButtonGroup();
		numberOfPlayers.add(twoPlayers);
		numberOfPlayers.add(fourPlayers);
		
	}
	
	public static void main(String[] args) {
		new Menu();

	}
}

