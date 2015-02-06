package gui;

import javax.swing.JFrame;

public class sillyGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameFrame j = new GameFrame();
	
		j.add(new BoardPanel());
		j.setVisible(true);
	}

}
