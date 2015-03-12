package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;




public class Quoridor extends JFrame {
	
	public final static String MAIN_TITLE = "teamToo Quoridor Game";
	public static final String INFO_MESSAGE = "Click 'Player Options' for more than two players.";
	public final static String[] BUTTONS = {"Start Game", "Player Options", "Exit Game"};
	

	private JPanel panel;

	
	public Quoridor() {
		super(MAIN_TITLE);
		setName(MAIN_TITLE);

		setSize(400, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		JLabel top = new JLabel("Quoridor Game");
		top.setHorizontalAlignment(SwingConstants.CENTER);
		top.setFont(new Font("Papyrus", Font.ITALIC, 36));
		add(top, BorderLayout.PAGE_START);

		JLabel label = new JLabel(INFO_MESSAGE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 16));
		add(label, BorderLayout.AFTER_LAST_LINE);

		JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));	
        subPanel.add(Box.createRigidArea(new Dimension(0, 45)));
		initializeButtons(subPanel);
		panel.add(subPanel);


		setVisible(true);
	}
	
	private void initializeButtons(JPanel subPanel) {
		for (int i = 0; i < BUTTONS.length; i++) {
			JButton button = new JButton(BUTTONS[i]);
			//button.addActionListener(this);
			button.setAlignmentX(subPanel.CENTER_ALIGNMENT);
			button.setBackground(Color.black);
			button.setForeground(Color.white);
			subPanel.add(button);
			subPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		}
		
	}
	
	public static void main(String[] args) {
		Quoridor gameIntro = new Quoridor();
	}

}
