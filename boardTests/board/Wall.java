package board;

public class Wall extends Square {

	public Wall(Square s) {
		super(s.getRow(),s.getColumn());
		reLink(s);
	}
	
	//Relinks Wall into the grid
	private void reLink(Square s){
		if(s.adjacentDown !=null){
			this.adjacentDown = s.adjacentDown;
			s.adjacentDown.adjacentUp = this;
		}
		if(s.adjacentLeft !=null){
			this.adjacentLeft = s.adjacentLeft;
			s.adjacentLeft.adjacentRight =this;
	
		}
		if(s.adjacentRight !=null){
			this.adjacentRight = s.adjacentRight;
			s.adjacentRight.adjacentLeft = this;
		   
		}
		
		if(s.adjacentUp !=null){	
			s.adjacentUp.adjacentDown = this;
			this.adjacentUp = s.adjacentUp;
		}
	}

}
