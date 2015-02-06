package guiTest;

import javax.swing.JFrame;

import gui.BoardPanel;

import org.junit.Test;



public class BoardPanelTest{
	@Test
	public void constructEmptyPanel(){
		BoardPanel p = new BoardPanel();
		assert(p) != null;
	}
	
	@Test
	public void addPanelToFrame(){
		JFrame f = new JFrame();
		f.add(new BoardPanel());
		f.setVisible(true);
	}
}
