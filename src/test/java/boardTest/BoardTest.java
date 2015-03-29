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
		
		
	}
	
	@Test
	public void testRemovalFromBoard(){
		Board board = new Board(4);
		if(!board.makeMove("H-V", 0)){
			board.getPlayers().get(0).removePlayer();
		}
		assertEquals("Player 0 should not be in the game",board.getPlayers().get(0).isActive(),false);
		assertEquals("Player 0 should not have a reference to a square object",board.getPlayers().get(0).getSquare(),null);
	}
	
	@Test
	public void testPlaceWall(){
		Board board = new Board(2);
		assert(board.makeMove("F-III_G-III", 0));
		assertEquals("Player 0 should have one less wall",board.getPlayers().get(0).getWallNum(),9);
		
	}
	

	@Test
	public void testPlayerCreation(){
		Board board = new Board(2);
		List<Player> p = board.getPlayers();
		assertEquals("The player list should be of size 2",2,p.size());
		assertEquals("Player 0 should have 10 walls!",10,p.get(0).getWallNum());

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