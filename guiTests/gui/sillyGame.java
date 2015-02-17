package gui;

import java.awt.Point;
import java.util.*;

import javax.swing.JFrame;

public class sillyGame {

	/**
	 * @param args
	 */
	//Makes a Frame object so I can test what is being drawn with swing
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameFrame a = new GameFrame();
	
		a.add(new BoardPanel());
		a.setVisible(true);
		List<List<Point>> p = new ArrayList<List<Point>>();
		for(int i=0;i<9;i++){
		p.add(new ArrayList<Point>()); //row 1
			for(int j=0;j<9;j++){
				
			}
		}
	}

}
