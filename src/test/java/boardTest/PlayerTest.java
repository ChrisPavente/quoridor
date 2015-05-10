package boardTest;

import static org.junit.Assert.*;

import org.junit.Test;

import board.*;

public class PlayerTest {

	@Test
	public void testMove(){
		int i;
	}
	
	@Test
	public void testWallUse(){
		Player p = new Player(new Square(0,0),1, 0,null, null);
		assertEquals("p.getWallNum should be 1",p.getWallNum(),1);
		p.useWall();
		assertEquals("p.getWallNum should be 0",p.getWallNum(),0);
	}
	
}
