package board;
import java.util.Arrays;

import board.*;

public class Board {
   /*
   Walls are labeled by endpoints
   Player position could take a up "square"
   Only two possible things can be in a square, player piece or wall
   I do not think a row being called I and the first column called I will be a problem

   */

    //number of walls
    private static final int WALLS = 20;
    //number of rows 
	private final int HEIGHT = 9;
	//number of columns
	private final int WIDTH = 9;
	//
	//private static final int 
	
	//Two-dimensional array that will represent the grid
	//Maybe make this of type square and make a new square object that will be able to have contents
	private Square [][] grid;
    
    //Constructor
    //builds the board itself
	//Added param numberOfPlayers as Board needs to know how many player objects to make
	public Board(int numberOfPlayers) {
		createGrid();
	}

	public void createGrid() {
		 grid = new Square[HEIGHT][WIDTH]; 
		 for (int i = 0; i < grid.length; i++) {
  			  for (int k = 0; k < grid.length; k++) {
                   grid[i][k] = new Square(i,k);
  			  }
		 }
	}  

	//will be fixed later to implement players 
	public Position setStartingPosition() {
		Position start = new Position(grid[0][4]);
		grid[0][4].isNowOccupied();
		return start;
	}

	//Only makes a single move down for now
	public Position makeMove(String move, Position pos) {
        if (move.equals("Down")) {
        	grid[pos.getPosition().getRow()][pos.getPosition().getColumn()].isFree();
        	pos.moveDown();
        	grid[pos.getPosition().getRow()][pos.getPosition().getColumn()].isNowOccupied();
        }
        return pos;
	}

	//Returns the location of the square sent
	//
	public String getLocation(Square square) {
            return null;
	}
	
	//Assumes that the place the wall is to be placed is already legal
	//The display server will check the legality
	public void placeWall(int i,int j){
		grid[i][j] = new Wall(grid[i][j]);
	}

}