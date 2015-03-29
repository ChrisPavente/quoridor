package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


/**
 * This class makes an opening menu pop up to allow the user the opportunity 
 *  for options for the players of the game Quoridor. 
 */
public class Menu extends JFrame {

	public final static String OPTIONS_WINDOW_TITLE = "Quoridor Options"; //This holds the name of the options menu.

	
	Quoridor Q;

	private JLabel PlayersLabel; //This label just says Players on it and sits above the Radio buttons controlling the number of players.
	private JRadioButton twoPlayers, fourPlayers; //These Radiobuttons are used to choose the number of Players.
	private ButtonGroup numberOfPlayers; //This makes it so that when one button is clicked, the other is unclicked.
	
	/**
	 * The constructor that is called from Quoridor to instantiate a 
	 * player options menu.
	 */
	public Menu() {
		super(OPTIONS_WINDOW_TITLE);
		initialize();
		setVisible(true);	
	}
	
	/**
	 * Method to initialize the label, and the buttons for the player options.
	 */
	private void initialize() {

		setName(OPTIONS_WINDOW_TITLE);
		setSize(500,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		//getContentPane().setBackground(Color.yellow);
		JLabel background= new JLabel();
				//new JLabel(new ImageIcon("C:\\Users\\Laura\\Desktop\\Board.png"));
		add(background);
		background.setLayout(new FlowLayout());
		
		PlayersLabel = new JLabel("Choose the number of Players:");
		PlayersLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		PlayersLabel.setSize(250, 150);
		PlayersLabel.setLocation(30, 15);
		background.add(PlayersLabel);
		
		twoPlayers = new JRadioButton("2 Players");
		JLabel or = new JLabel("OR");
		fourPlayers = new JRadioButton("4 Players");
		
		twoPlayers.setSize(100, 30);
		twoPlayers.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		or.setSize(100, 30);
		or.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		fourPlayers.setSize(100, 30);
		fourPlayers.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		twoPlayers.setLocation(50, 100);
		or.setLocation(80, 130);
		fourPlayers.setLocation(50, 160);
		
		//I still have to instantiate these buttons to do something
//		twoPlayers.addItemListener(this);
//		fourPlayers.addItemListener(this);
		
		background.add(twoPlayers);
		background.add(or);
		background.add(fourPlayers);
		
		numberOfPlayers = new ButtonGroup();
		numberOfPlayers.add(twoPlayers);
		numberOfPlayers.add(fourPlayers);
		
	}
	
	/**
	 * Creates a new instance of the Menu, used mainly for testing implementation
	 * @param args
	 */
	public static void main(String[] args) {
		new Menu();

	}
}

