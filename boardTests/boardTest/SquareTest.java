import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;


public class SquareTest {

	@Test
	public void testSquareConstructor() {
       Square square = new Square(5, 5);
       assertNotNull("Square() returned null ", square);
       assertEquals("Expected adjacentUp value mismatch ", null, square.adjacentUp);
       assertEquals("Expected adjacentDown value mismatch ", null, square.adjacentDown);
       assertEquals("Expected adjacentLeft value mismatch ", null, square.adjacentLeft);
       assertEquals("Expected adjacentRight value mismatch ", null, square.adjacentRight);
	}
    


	@Test
	public void testSetNeighbor() {
	Square square = new Square(5, 5);	
      Square squareUp = new Square(4, 5);
      Square squareDown = new Square(6, 5);
      Square squareLeft = new Square(5, 4);
      Square squareRight = new Square(5, 6);
      Square square2 = new Square(5, 5);
      Square square3 = new Square(4, 4);
      Square square4 = new Square(6, 6);
      Square square5 = new Square(3, 7); 
      square.setNeighbour(squareUp);
      square.setNeighbour(squareDown);
      square.setNeighbour(squareLeft);
      square.setNeighbour(squareRight);
      assertNotNull("square.adjacentUp.equals(null) ", square.adjacentUp);
      assertNotNull("square.adjacentDown.equals(null) ", square.adjacentDown);
      assertNotNull("square.adjacentLeft.equals(null) ", square.adjacentLeft);
      assertNotNull("square.adjacentRight.equals(null) ", square.adjacentRight);
      assertEquals("!(square.adjacentUp.equals(squareUp)) ", square.adjacentUp, squareUp);
      assertEquals("!(square.adjacentUp.equals(squareDown)) ", square.adjacentDown, squareDown);
      assertEquals("!(square.adjacentUp.equals(squareLeft)) ", square.adjacentLeft, squareLeft);
      assertEquals("!(square.adjacentUp.equals(squareDown)) ", square.adjacentDown, squareDown);
      assertNotEquals("square.adjacentUp.equals(square2) ", square.adjacentUp, square2);
      assertNotEquals("square.adjacentUp.equals(square3) ", square.adjacentUp, square3);
      assertNotEquals("square.adjacentUp.equals(square4) ", square.adjacentUp, square4);
      assertNotEquals("square.adjacentUp.equals(square5) ", square.adjacentUp, square5);
      assertNotEquals("square.adjacentDown.equals(square2) ", square.adjacentDown, square2);
      assertNotEquals("square.adjacentDown.equals(square3) ", square.adjacentDown, square3);
      assertNotEquals("square.adjacentDown.equals(square4) ", square.adjacentDown, square4);
      assertNotEquals("square.adjacentDown.equals(square5) ", square.adjacentDown, square5);
      assertNotEquals("square.adjacentLeft.equals(square2) ", square.adjacentLeft, square2);
      assertNotEquals("square.adjacentLeft.equals(square3) ", square.adjacentLeft, square3);
      assertNotEquals("square.adjacentLeft.equals(square4) ", square.adjacentLeft, square4);
      assertNotEquals("square.adjacentLeft.equals(square5) ", square.adjacentLeft, square5);
      assertNotEquals("square.adjacentRight.equals(square2) ", square.adjacentRight, square2);
      assertNotEquals("square.adjacentRight.equals(square3) ", square.adjacentRight, square3);
      assertNotEquals("square.adjacentRight.equals(square4) ", square.adjacentRight, square4);
      assertNotEquals("square.adjacentright.equals(square5) ", square.adjacentRight, square5);
	}

	@Test
	public void testGetRow() {
      Square square = new Square(5, 5);
      Square square2 = new Square(1, 1);
      Square square3 = new Square(0, 0);
      Square square4 = new Square(8, 8);
      Square square5 = new Square(9, 9); 
      assertEquals("square.row != 5 ", 5, square.getRow());
      assertEquals("square2.row != 1 ", 1, square2.getRow());
      assertEquals("square3.row != 0 ", 0, square3.getRow());
      assertEquals("square4.row != 8 ", 8, square4.getRow());
      assertEquals("square5.row != 9 ", 9, square5.getRow());	
	}

	@Test
	public void testGetColumn() {
      Square square = new Square(5, 5);
      Square square2 = new Square(1, 1);
      Square square3 = new Square(0, 0);
      Square square4 = new Square(8, 8);
      Square square5 = new Square(9, 9); 
      assertEquals("square.col != 5 ", 5, square.getColumn());
      assertEquals("square2.col != 1 ", 1, square2.getColumn());
      assertEquals("square3.col != 0 ", 0, square3.getColumn());
      assertEquals("square4.col != 8 ", 8, square4.getColumn());
      assertEquals("square5.col != 9 ", 9, square5.getColumn());
	}

}