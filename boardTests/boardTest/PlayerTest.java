package boardTest;

import static org.junit.Assert.*;

import org.junit.Test;

import board.*;

public class PlayerTest {

	@Test
	public void testMoveUp(){
		Square s = new Square(0, 0);
		s.adjacentUp = new Square(1,1);
		Player p = new Player(s);
		Square check = s.adjacentUp;
		p.moveUp();
		assertEquals("p.getSquare should be equal to s.adjacentUP",check, p.getSquare());
	}
	
	@Test
	public void testMoveDown(){
		Square s = new Square(0, 0);
		s.adjacentDown = new Square(1,1);
		Player p = new Player(s);
		Square check = s.adjacentDown;
		p.moveDown();
		assertEquals("p.getSquare should be equal to s.adjacentUP",check, p.getSquare());
	}
	
	@Test
	public void testMoveLeft(){
		Square s = new Square(0, 0);
		s.adjacentLeft = new Square(1,1);
		Player p = new Player(s);
		Square check = s.adjacentLeft;
		p.moveLeft();
		assertEquals("p.getSquare should be equal to s.adjacentUP",check, p.getSquare());
	}
	
	@Test
	public void testMoveRight(){
		Square s = new Square(0, 0);
		s.adjacentRight = new Square(1,1);
		Player p = new Player(s);
		Square check = s.adjacentRight;
		p.moveRight();
		assertEquals("p.getSquare should be equal to s.adjacentUP",check, p.getSquare());
	}
	
}
