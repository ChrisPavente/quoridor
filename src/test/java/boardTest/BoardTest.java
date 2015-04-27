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
		assert(board.makeMove("V-B", 0));
		assert(board.getPlayers().get(0).isActive());
		assert(board.makeMove("V-H", 1));
		assert(board.getPlayers().get(1).isActive());
		assert(board.makeMove("III-F_III-G", 2));
		assert(board.getPlayers().get(2).isActive());
		assert(board.makeMove("VII-F_VII-G", 3));
		assert(board.getPlayers().get(3).isActive());
			
	}
	
	
	@Test
	public void testMakeMove1() {
		Board board = new Board(2);
		assertTrue(board.makeMove("V-B",0));
		assertTrue(board.makeMove("V-H",1));
		assertTrue(board.makeMove("V-C",0));
		assertTrue(board.makeMove("V-G",1));
		assertTrue(board.makeMove("V-D",0));
		assertTrue(board.makeMove("V-F",1));
		assertTrue(board.makeMove("V-C_VI-C",0));
		assertTrue(board.makeMove("V-E",1));
		assertTrue(board.makeMove("V-F",0));
		assertTrue(board.makeMove("V-D",1));
		assertTrue(board.makeMove("V-G",0));
		assertFalse(board.makeMove("V-C",1));
		assertTrue(board.makeMove("V-H",0));
		assertFalse(board.makeMove("V-B",1));


	}
	

	
	@Test
	public void testRemovalFromBoard(){
		Board board = new Board(4);
		if(!board.makeMove("V-H", 0)){
			board.getPlayers().get(0).removePlayer();
		}
		assertEquals("Player 0 should not be in the game",board.getPlayers().get(0).isActive(),false);
		assertEquals("Player 0 should not have a reference to a square object",board.getPlayers().get(0).getSquare(),null);
	}
	
	@Test
	public void testPlaceWall(){
		Board board = new Board(2);
		assert(board.makeMove("III-F_III-G", 0));
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

    @Test
    public void testFindNextPlayer(){
        Board board = new Board(4);
        List<Player> p = board.getPlayers();
        assertEquals("Should be 1",board.findNextLegalPlayer(0),1);
        assertEquals("Should be 0",board.findNextLegalPlayer(3),0);
        p.get(3).removePlayer();
        p.get(1).removePlayer();
        p.get(0).removePlayer();
        assertEquals("Should be -1",board.findNextLegalPlayer(2),-1);
        //I realize now that this now has become the same as check for a winner

    }

	
}
