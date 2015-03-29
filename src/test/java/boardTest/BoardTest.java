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
		//currently goes through one round of play
		Board board = new Board(4);
		assert(board.makeMove("B-V", 0));
		assert(board.getPlayers().get(0).isActive());
		assert(board.makeMove("H-V", 1));
		assert(board.getPlayers().get(1).isActive());
		assert(board.makeMove("F-III_G-III", 2));
		assert(board.getPlayers().get(2).isActive());
		assert(board.makeMove("F-VII_G-VII", 3));
		assert(board.getPlayers().get(3).isActive());
		
		//String move = "Down";
		
	}
	
	@Test
	public void testPlaceWall(){
		Board board = new Board(2);
		assert(board.makeMove("F-III_G-III", 0));
		assertEquals("Player 0 should have one less wall",board.getPlayers().get(0).getWallNum(),9);
		//board.placeWall(2,2,2,3);
		//Square s = board.getSquareAt(2,2);
		//assertEquals("s's adjacent down should be null",s.getNeighbour(2),null);
		//System.out.println(s);
		//boolean b = board.placeWall(2, 2,2,3);
		//assertEquals("After trying to place another wall, we should have a problem",b,false);
		//board.unDoPlaceWall(2, 2, 2, 3);
		//Square a = board.getSquareAt(2,2);
		//System.out.println(a);
		//assert(a!=s);
	}
	

	@Test
	public void testPlayerCreation(){
		Board board = new Board(2);
		List<Player> p = board.getPlayers();
		//assertEquals("p1 should be at square [0][4]", p.get(0).getSquare() , board.getSquareAt(0, 4));
	}
	
	@Test
	public void testToString(){
		Board board = new Board(4);
		System.out.println(board);
		//board.makeMove(", current)
		Player p =board.getPlayers().get(0);
		//p.move(board.getSquareAt(2, 5));
		System.out.println(board);
		
		
		
		
		
		
	}
}