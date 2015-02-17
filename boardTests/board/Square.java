package board;



//Represents a square of the Grid
public class Square {

	private int row;
	private int col;
    
    //Soon to be removed
	private boolean occupied;

	protected Square adjacentUp;
	protected Square adjacentDown;
	protected Square adjacentRight;
	protected Square adjacentLeft;

	public Square(int row, int col) {
		this.occupied = false;
        this.row = row;
        this.col = col;
        this.adjacentUp = null;
        this.adjacentDown = null;
        this.adjacentRight = null;
        this.adjacentLeft = null;
	}
	
	
	//parameter: int n is the direction (0-4)
	//Up =0 , Right =1, Down=2, Left=3
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

	//Should just pass a reference to the grid
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

	//Dont know if this will be useful
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
	
	//Figures out in what direction the two squares are linked (if they even are) and breaks the link between them
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
	
	//Given a square and a direction relinks the square in that direction
	//Assumes the square given is supposed to be adjacent to this
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