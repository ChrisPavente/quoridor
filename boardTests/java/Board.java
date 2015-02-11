package board;

import java.util.Arrays;

public class Board {

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
	public Board() {
		createGrid();
	}

	public void createGrid() {
		 grid = new Square[HEIGHT][WIDTH]; 
		 for (int i = 0; i < grid.length; i++) {
  			  for (int k = 0; k < grid.length; k++) {
                   grid[i][k] = new Square(i,k);
  			  }
		 }
		 
		 for (int i = 1; i < grid.length; i++) {
  			  for (int k = 0; k < grid.length; k++) {
                   grid[i][k].setNeighbour(grid[i-1][k]);
  			  }
		 }

		 for (int i = 0; i < grid.length-1; i++) {
  			  for (int k = 0; k < grid.length; k++) {
                   grid[i][k].setNeighbour(grid[i+1][k]);
  			  }
		 }

		  for (int i = 0; i < grid.length; i++) {
  			  for (int k = 1; k < grid.length; k++) {
                   grid[i][k].setNeighbour(grid[i][k-1]);
  			  }
		 }

		  for (int i = 0; i < grid.length; i++) {
  			  for (int k = 0; k < grid.length-1; k++) {
                   grid[i][k].setNeighbour(grid[i][k+1]);
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

	//Returns the square at the given row and col coordinates
	public Square getSquare(int row, int col) {
            return grid[row][col];
	}


}