package boardTest;

import static org.junit.Assert.*;
import board.*;
import org.junit.Test;

import java.util.Arrays;


public class SquareTest {
	
	/*
	@Test
	public void testSquareConstructor() {
       Square square = new Square(5, 5);
       assertNotNull("Square() returned null ", square);
       assertEquals("Expected adjacentUp value mismatch ", null, square.adjacentUp);
       assertEquals("Expected adjacentDown value mismatch ", null, square.adjacentDown);
       assertEquals("Expected adjacentLeft value mismatch ", null, square.adjacentLeft);
       assertEquals("Expected adjacentRight value mismatch ", null, square.adjacentRight);
	}*/
    


	@Test
	public void testSetNeighbor() {
		int w=2;
		int h=2;
		Square[][] squares = new Square[w][h];
		for(int i=0;i<w;i++){
			for(int j=0;j<h;j++){
				squares[i][j] = new Square(i,j);
			}
		}
		Square.setNeighbours(squares, h, w);
		for(int i=0;i<w;i++){
			for(int j=0;j<h;j++){
				if(i>0){
					assertEquals("squares[i][j] with i>0 should have squares[i-1][j] as its UP neighbor",squares[i][j].getNeighbour(0),squares[i-1][j]);
				}
				if(j>0){
					assertEquals("squares[i][j] with j>0 should have squares[i][j-1] as its LEFT neighbor",squares[i][j].getNeighbour(3),squares[i][j-1]);
				}
				if(i<w-1){
					assertEquals("squares[i][j] with i<h-1 should have squares[i+1][j] as its Down neighbor",squares[i][j].getNeighbour(2),squares[i+1][j]);
				}
				if(j<h-1){
					assertEquals("squares[i][j] with j<w-1 should have squares[i][j+1] as its Right neighbor",squares[i][j].getNeighbour(1),squares[i][j+1]);
				}
			}
		}
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