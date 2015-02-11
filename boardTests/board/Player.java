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
	follows the same 0-3 directions as Square
	0=north,...,3=left
	*/
	public void move(int n){
		Square s = square.getNeighbour(n);
		square =s;
	}

	
} 