package board;

import java.util.List;

import board.Square;

public class Player{
	
	private int num; //Knows which number player it is, MAY helpful for shortest path
	private Square square;
	private int walls;
	private String oldShortestPath;//Keeps a representation of the shortest path
									//We hold on to this, because in some cases the shortest path hasn't changed so we can use our old path

	//Player has a reference to where it in on the board
	public Player(Square s,int walls,int num) {
	this.num = num;
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
	
	
	
	//this move method moves the player to a specified square
	//the square parameter is assumed to be the legal move
	//Realistically shouldn't ever need to return false
	//So returns nothing
	public void move(Square s) {
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
			//square = oldLoc;
			return false;
		}
		square =s;
		return false;
	}
	


	
} 