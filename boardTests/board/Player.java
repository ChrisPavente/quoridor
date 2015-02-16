package board;

import java.util.List;

import board.Square;

public class Player{

	private Square square;
	private Square oldLoc;
	private int walls;

	//Player has a reference to where it in on the board
	public Player(Square s,int walls) {
	this.square = s;
	this.oldLoc =s;
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
	
	
	
	//this move method moves the player to a specified square
	//the square parameter is assumed to be the legal move
	//Realistically shouldn't ever need to return false
	//So returns nothing
	public void move(Square s) {
		oldLoc = square;
		square = s;
	} 
	/*
	Any move call assumes that the square is a legal place for a player to go
	Display server will check the actual validity of the move
	follows the same 0-3 directions as Square
	0=north,...,3=left
	
	Keeps moving until we are over every player in the way
	*/
	//I am unsure what the purpose of this method is
	public boolean move(int n, List<Player> people){
		Square s = square.getNeighbour(n);
		if(s instanceof Wall){
			square = oldLoc;
			return false;
		}
		square =s;
		return false;
	}

	
} 