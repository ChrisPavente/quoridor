package gui;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardPanel extends JPanel{
	private static final int height = 400;
	private static final int width = 600;
	
	
	//Construct an empty board object with no game information associated with it.
	public BoardPanel(){
		this.setBounds(0, 0, width, height);
		//No game information known at this point
	}
	
	//Draws a board that consists of a 9x9 blank grid
	public void paintnull(Graphics g){
		g.setColor(new Color(15,60,100));
		for(int i=10;i<height-10;i+=height/9){
			for(int j=10;j<width-10;j+=width/9){
				g.fillRect(i, j, (width-10)/15, (height-10)/9);
			}
		}
	}
	
	public void paint(Graphics g){
		//just calls paintnull to paint an empty board
		paintnull(g);
	}
	
	
	
}
