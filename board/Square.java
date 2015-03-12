package board;


/**
 * 
 * The Square class represents a square of the grid
 * 
 * @author Tyler, Chris, Laura, James, Matt
 *
 */
public class Square {

	private int row;
	private int col;
    
    //Soon to be removed
	private boolean occupied;

	protected Square adjacentUp;
	protected Square adjacentDown;
	protected Square adjacentRight;
	protected Square adjacentLeft;
	
	/**
	 * The Square Constructor.
	 * Instantiates a square on the board.  Has a flag for whether the square is occupied 
	 * and flags to all of the squares around it.
	 * 
	 * @param row: the row that the square is located on the board
	 * @param col: the column that the square is located on the board
	 */
	public Square(int row, int col) {
		this.occupied = false;
        this.row = row;
        this.col = col;
        this.adjacentUp = null;
        this.adjacentDown = null;
        this.adjacentRight = null;
        this.adjacentLeft = null;
	}
	
	
	/**
	 * Method to return the desired neighbor of the current square
	 * @param n: the direction of the neighbor you wish to return 
	 * 			 (up=0, right=1, down=2, left=3)
	 * @return the desired square
	 */
	public Square getNeighbour(int n){
		if(n<0 && n>3){
			throw new IllegalArgumentException();
		}
		if(n==0){
			return adjacentUp;
		}
		else if(n==1){
			return adjacentRight;
		}
		else if(n==2){
			return adjacentDown;
		}
		return adjacentLeft;
	}


	/**
	 * Method to pass a reference to the neighbors of a square in a grid.
	 * @param squares: square we wish to instantiate neighbors for
	 * @param h: height?
	 * @param w: width?
	 */
	public static void setNeighbours(Square[][] squares,int h,int w) {
		for(int i=0;i<h;i++){
			for(int j=0;j<w;j++){
				if(i>0){
					squares[i][j].adjacentUp = squares[i-1][j];
				}
				if(j>0){
					squares[i][j].adjacentLeft = squares[i][j-1];
				}
				if(i<h-1){
					squares[i][j].adjacentDown = squares[i+1][j];
				}
				if(j<w-1){
					squares[i][j].adjacentRight = squares[i][j+1];
				}
			}
		}
	}


	/**
	 * Method used when given a square to determine where it is in relation
	 * to the current square
	 * @param square: given square trying to find out it's direction of
	 * @return the direction the square is located
	 */
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


	/**
	 * Method to return the current row
	 * @return the row
	 */
	public int getRow() {
		return this.row; 
	}


	/**
	 * Method to return the current column
	 * @return the column
	 */
	public int getColumn() {
		return this.col;
	}

	/**
	 * Method to check if a square is occupied
	 * @return true is a player is on the square, false otherwise
	 */
	public boolean isOccupied() {
		return this.occupied;
	}

	/**
	 * Method to set a square's flag to occupied once a player
	 * moves to that square.
	 */
	public void isNowOccupied() {
		this.occupied = true;
	}

	/**
	 * Method to set a square's flag to not occupied if a player is removed.
	 */
	public void isFree() {
		this.occupied = false;
	}
	

	/**
	 * Method to determine the direction in which two squares are linked,
	 * then proceeds to break that link.
	 * @param s: the square we are trying to break the link with
	 * @return true if the link has been broken, false otherwise
	 */
	public boolean breakLink(Square s){
		if(this.adjacentDown ==s){
			this.adjacentDown =null;
			s.adjacentUp = null;
		}
		else if(this.adjacentUp ==s){
			this.adjacentUp =null;
			s.adjacentDown = null;
		}
		else if(this.adjacentLeft ==s){
			this.adjacentLeft =null;
			s.adjacentRight = null;
		}
		else if(this.adjacentRight ==s){
			this.adjacentRight = null;
			s.adjacentLeft = null;
		}
		else{
			return false;
		}
		return true;
	}
	

	/**
	 * Method to re-link two squares in the given direction.
	 * (Note: we assume that the two squares are supposed to be adjacent.)
	 * @param s: the square we wish to re-link to the current square
	 * @param n: the direction to which we wish to re-link the passed square
	 */
	public void reLink(Square s, int n){
		if(n==0){
			this.adjacentUp = s;
			s.adjacentDown = this;
		}
		else if(n==1){
			this.adjacentRight =s;
			s.adjacentLeft = this;
		}
		else if(n==2){
			this.adjacentDown =s;
			s.adjacentUp =this;
		}
		else if(n==3){
			this.adjacentLeft =s;
			s.adjacentRight = this;
		}
	}


}