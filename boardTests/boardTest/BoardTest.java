import static org.junit.Assert.*;

import org.junit.Test;

import board.*;

import java.util.Arrays;

public class BoardTest {
	
	@Test
	public void testBoardConstructor() {
		Board board = new Board();
	}

	@Test
	public void testMakeMove() {
		Board board = new Board();
		//String move = "Down";
		//Position pos = board.setStartingPosition();
		//pos = board.makeMove(move, pos);
		//assertEquals("")
	}
	
	@Test
	public void testPlaceWall(){
		Board board = new Board();
		board.placeWall(1,1);
	}
}