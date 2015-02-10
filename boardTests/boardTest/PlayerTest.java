package boardTest;

import static org.junit.Assert.*;

import org.junit.Test;

import board.*;

public class PlayerTest {

	@Test
	public void testMoveUp(){
		Square s = new Square(0, 0);
		s.adjacentUp = new Square(1,1);
		Player p = new Player(s,0);
		Square check = s.adjacentUp;
		p.moveUp();
		assertEquals("p.getSquare should be equal to s.adjacentUP",check, p.getSquare());
	}
	
	@Test
	public void testMoveDown(){
		Square s = new Square(0, 0);
		s.adjacentDown = new Square(1,1);
		Player p = new Player(s,0);
		Square check = s.adjacentDown;
		p.moveDown();
		assertEquals("p.getSquare should be equal to s.adjacentUP",check, p.getSquare());
	}
	
	@Test
	public void testMoveLeft(){
		Square s = new Square(0, 0);
		s.adjacentLeft = new Square(1,1);
		Player p = new Player(s,0);
		Square check = s.adjacentLeft;
		p.moveLeft();
		assertEquals("p.getSquare should be equal to s.adjacentUP",check, p.getSquare());
	}
	
	@Test
	public void testMoveRight(){
		Square s = new Square(0, 0);
		s.adjacentRight = new Square(1,1);
		Player p = new Player(s,0);
		Square check = s.adjacentRight;
		p.moveRight();
		assertEquals("p.getSquare should be equal to s.adjacentUP",check, p.getSquare());
	}
	
	@Test
	public void testWallUse(){
		Player p = new Player(new Square(0,0),1);
		assertEquals("p.getWallNum should be 1",p.getWallNum(),1);
		p.useWall();
		assertEquals("p.getWallNum should be 0",p.getWallNum(),0);
	}
	
}
