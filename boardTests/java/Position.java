package board;

//Represents a moveable piece on the Board
public class Position {
	private Square square;

	//Creates a position at the row and col on the Board
	public Position(Square square) {
		this.square = square;
	}
	
	public Square getPosition() {
		return this.square;
	}

	public void moveUp() {
        square = square.adjacentUp;
	}

	public void moveDown() {
       square = square.adjacentDown;
	}

	public void moveLeft() {
		square = square.adjacentLeft;
	}

	public void moveRight() {
        square = square.adjacentRight;
	}
}