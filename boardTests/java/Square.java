package board;

import java.util.Arrays;


//Represents a square of the Grid
public class Square {

	private int row;
	private int col;
    
    //Soon to be removed
	private boolean occupied;

	public Square adjacentUp;
	public Square adjacentDown;
	public Square adjacentRight;
	public Square adjacentLeft;

	public Square(int row, int col) {
		this.occupied = false;
        this.row = row;
        this.col = col;
        this.adjacentUp = null;
        this.adjacentDown = null;
        this.adjacentRight = null;
        this.adjacentLeft = null;
	}

	public void setNeighbour(Square square) {
               String direction = this.determineWhichDirection(square);
      	       if (direction.equals("Up")) 
      	           this.adjacentUp = square;
      	       else if (direction.equals("Down")) 
     	 	       this.adjacentDown = square;
      	       else if (direction.equals("Left")) 
      	           this.adjacentLeft = square;
      	       else if (direction.equals("Right")) 
       		       this.adjacentRight = square;
	}


	private String determineWhichDirection(Square square) {
		String direction = null;
        if (this.row == square.row+1 && this.col == square.col)
            //if here then square is directly above this 
            direction = "Up";
        else if (this.row == square.row-1 && this.col == square.col)
            //if here then square is directly below this
            direction = "Down";
        else if (this.row == square.row && this.col == square.col+1)
        	//if here then square is directly to the left of this
        	direction = "Left";
        else if (this.row == square.row && this.col == square.col-1)
        	//if here then square is directly to the right of this
        	direction = "Right";
        return direction;
	}

	//Returns the row
	public int getRow() {
		return this.row; 
	}

    //Retuns the column 
	public int getColumn() {
		return this.col;
	}

	//Soon to be removed/replaced
	public boolean isOccupied() {
		return this.occupied;
	}

	public void isNowOccupied() {
		this.occupied = true;
	}

	public void isFree() {
		this.occupied = false;
	}


}