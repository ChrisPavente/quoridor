import static org.junit.Assert.*;
import board.*;
import org.junit.Test;

public class PositionTest {

   @Test
   public void testPositionConstructor() {
   	  Square square = new Square(5, 5);
   	  Position pos = new Position(square);
   	  assertNotNull("Position() returned null ", pos);
   }

   @Test
   public void testMoveUp() {
   	  Square square = new Square(5, 5);	
      Square squareUp = new Square(4, 5);
      square.setNeighbour(squareUp);
      Position pos = new Position(square);
      pos.moveUp();
      assertEquals("square didnt move to the correct square ", pos.getPosition(), squareUp);
   }

   @Test
   public void testMoveDown() {
      Square square = new Square(5, 5);	
      Square squareDown = new Square(6, 5);
      square.setNeighbour(squareDown);
      Position pos = new Position(square);
      pos.moveDown();
      assertEquals("square didnt move to the correct square ", pos.getPosition(), squareDown);
   }

   @Test
   public void testMoveLeft() {
      Square square = new Square(5, 5);	
      Square squareLeft = new Square(5, 4);
      square.setNeighbour(squareLeft);
      Position pos = new Position(square);
      pos.moveLeft();
      assertEquals("square didnt move to the correct square ", pos.getPosition(), squareLeft);
   }

   @Test
   public void testMoveRight() {
      Square square = new Square(5, 5);	
      Square squareRight = new Square(5, 6);
      square.setNeighbour(squareRight);
      Position pos = new Position(square);
      pos.moveRight();
      assertEquals("square didnt move to the correct square ", pos.getPosition(), squareRight);
   }

}