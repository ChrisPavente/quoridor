package board;

import board.Square;

public class Player{

	private Square square;
	private int walls;

	//Player has a reference to where it in on the board
	public Player(Square s,int walls) {
	this.square = s;
	this.walls=walls;
	}	
	
	public Square getSquare(){
		return square;
	}
	
	public int getWallNum(){
		return walls;
	}
	
	public void useWall(){
		walls--;
	}
	
	
	
	
	/*
	Any move call assumes that the square is a legal place for a player to go
	Display server will check the actual validity of the move
	*/
	public void moveUp(){
		if(square.adjacentUp ==null){
			//BREAK!!
		}
		square = square.adjacentUp;
	}
	
	public void moveDown(){
		if(square.adjacentDown ==null){
			//BREAK!!
		}
		square = square.adjacentDown;
	}
	
	public void moveLeft(){
		if(square.adjacentDown ==null){
			//BREAK!!
		}
		square = square.adjacentLeft;
	}
	
	public void moveRight(){
		if(square.adjacentDown ==null){
			//BREAK!!
		}
		square = square.adjacentRight;
	}

	
} 