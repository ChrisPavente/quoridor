package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import board.Board;


public class Quoridor extends JFrame implements ActionListener {
	
	public final static String MAIN_TITLE = "teamToo Quoridor Game"; //Title given to our team game
	//Info message is to inform the player that our default game is set to 2 players
	public static final String INFO_MESSAGE = "Click 'Player Options' for more than two players.";
	//Names on the given buttons
	public final static String[] BUTTONS = {"Start Game", "Player Options", "Exit Game"};

	private JPanel panel;//Panel which holds the labels on the GUI
	private List<JButton> buttons; //Saves a list of buttons on the page to use for enabling.

	/**
	 * Instantiates the Main front title page with buttons that are linked to
	 * the board GUI, a menu to allow the user to switch from 2 to 4 players
	 * and a button to exit the game completely.
	 */
	public Quoridor() {
		super(MAIN_TITLE);
		setName(MAIN_TITLE);

		setSize(400, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.yellow);
		
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		// Instantiates the title on the main panel
		JLabel top = new JLabel("Quoridor Game");
		top.setHorizontalAlignment(SwingConstants.CENTER);
		top.setFont(new Font("Papyrus", Font.ITALIC, 36));
		add(top, BorderLayout.PAGE_START);

		/*
		 *  Instantiates the info message for the default at the
		 *  bottom of the page on the main panel
		 */	
		JLabel label = new JLabel(INFO_MESSAGE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 16));
		add(label, BorderLayout.AFTER_LAST_LINE);

		/* 
		 * Creates a new subpanel which is place on the main panel
		 * to store the buttons.
		 */
		JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));	
        subPanel.add(Box.createRigidArea(new Dimension(0, 45)));
        buttons = new ArrayList<JButton>();
		initializeButtons(subPanel);
		panel.add(subPanel);


		setVisible(true);
	}
	
	/**
	 * Sets up the buttons on the subpanel and sets the format of the buttons
	 * @param subPanel: the panel which is placed on the main panel to store the buttons
	 */
	private void initializeButtons(JPanel subPanel) {
		for (int i = 0; i < BUTTONS.length; i++) {
			JButton button = new JButton(BUTTONS[i]);
			button.addActionListener(this);
			button.setAlignmentX(subPanel.CENTER_ALIGNMENT);
			button.setBackground(Color.black);
			button.setForeground(Color.white);
			buttons.add(button); //Adds the buttons we are adding to the subpanel to the list to enable.
			subPanel.add(button);
			subPanel.add(Box.createRigidArea(new Dimension(10, 10)));
			
		}
		
	}
	

	/**
	 * Method to create the link between all the buttons and their desired actions.
	 * Pressing 'Start Game' creates a new board instance, pressing 'Player Options'
	 * creates a new menu options and pressing 'Exit Game' exits the instance.
	 */
	@Override
	public void actionPerformed(ActionEvent action) {
		if (buttons.get(0) == action.getSource()) {
			QBoard newGame = new QBoard(2);
		}
		
		if (buttons.get(1) == action.getSource()) {
			Menu playerOptions = new Menu();
		}
		
		if (buttons.get(2) == action.getSource()) {
			System.exit(0);
		}
		return;
	}

	/**
	 * Creates a new instance of the Main page used for testing.
	 * @param args
	 */
	public static void main(String[] args) {
		Quoridor gameIntro = new Quoridor();
	}

}
