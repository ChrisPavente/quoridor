package boardTest;

import static org.junit.Assert.*;

import org.junit.Test;

import board.*;

import java.util.Arrays;
import java.util.List;

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
		board.placeWall(2,2,2,3);
		Square s = board.getSquareAt(2,2);
		assertEquals("s's adjacent down should be null",s.getNeighbour(2),null);
		System.out.println(s);
		boolean b = board.placeWall(2, 2,2,3);
		assertEquals("After trying to place another wall, we should have a problem",b,false);
		board.unDoPlaceWall(2, 2, 2, 3);
		Square a = board.getSquareAt(2,2);
		System.out.println(a);
		assert(a!=s);
	}
	

	@Test
	public void testPlayerCreation(){
		Board board = new Board(2);
		List<Player> p = board.getPlayers();
		assertEquals("p1 should be at square [0][4]", p.get(0).getSquare() , board.getSquareAt(0, 4));
	}
	
	@Test
	public void testToString(){
		Board board = new Board(4);
		System.out.println(board);
		board.placeWall(2,2,2,3);
		board.placeWall(1,2,1,3);
		Player p =board.getPlayers().get(0);
		p.move(board.getSquareAt(2, 5));
		System.out.println(board);
		
		
		
		
		
		
	}
}