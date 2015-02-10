package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
		//Will eventually implement an instance of our BoardPanel
		public GameFrame(){
			this.setSize(900,600);
			this.setMaximumSize(new Dimension(900,600));
		}
}
