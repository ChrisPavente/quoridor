package boardTest;

import static org.junit.Assert.*;

import org.junit.Test;

import board.*;

import java.util.Arrays;

public class BoardTest {
	
	@Test
	public void testBoardConstructor() {
		Board board = new Board(2);
	}

	@Test
	public void testMakeMove() {
		//Board board = new Board(2);
		//String move = "Down";
		//Position pos = board.setStartingPosition();
		//pos = board.makeMove(move, pos);
		//assertEquals("")
	}
	
	@Test
	public void testPlaceWall(){
		Board board = new Board(2);
		board.placeWall(2,2);
		Square s = board.getSquareAt(2,2);
		assert(s instanceof Wall);
	}
}