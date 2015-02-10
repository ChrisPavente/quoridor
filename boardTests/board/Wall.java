package board;

public class Wall extends Square {

	public Wall(Square s) {
		super(s.getRow(),s.getColumn());
		this.adjacentDown = s.adjacentDown;
		this.adjacentLeft = s.adjacentLeft;
		this.adjacentRight = s.adjacentRight;
		this.adjacentUp = s.adjacentUp;
		// TODO Auto-generated constructor stub
	}

}
