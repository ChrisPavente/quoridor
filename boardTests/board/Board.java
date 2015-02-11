package board;

import java.util.ArrayList;
import java.util.List;


public class Board {
   /*
   Walls are labeled by endpoints
   Player position could take a up "square"
   Only two possible things can be in a square, player piece or wall
   I do not think a row being called I and the first column called I will be a problem

   */

    //number of walls
    private static final int WALLS = 20;
    //number of rows 
	private final int HEIGHT = 9;
	//number of columns
	private final int WIDTH = 9;
	//
	//private static final int 
	
	//Two-dimensional array that will represent the grid
	//Maybe make this of type square and make a new square object that will be able to have contents
	private Square [][] grid;
	private List<Player> players;
    
    //Constructor
    //builds the board itself
	//Added param numberOfPlayers as Board needs to know how many player objects to make
	//now constructs a board object, which has a reference to the list of players
	public Board(int numberOfPlayers) {
		if(numberOfPlayers !=2 && numberOfPlayers!=4){
			//BREAK
			throw new IllegalArgumentException("Must have 2 or 4 players");
		}
		createGrid();
		int w = WALLS/4; //the number of walls each player will be getting
		players = new ArrayList<Player>();
		players.add(new Player(grid[0][4],w));
		players.add(new Player(grid[8][4],w));
		
		if(numberOfPlayers==4){
			players.add(new Player(grid[4][0],w));
			players.add(new Player(grid[4][8],w));
		}
		
	}

	//Must be private or this can be called at any time wiping out the current board
	private void createGrid() {
		 grid = new Square[HEIGHT][WIDTH]; 
		 for (int i = 0; i < grid.length; i++) {
  			  for (int k = 0; k < grid.length; k++) {
                   grid[i][k] = new Square(i,k);
  			  }
		 }
		 Square.setNeighbours(grid, HEIGHT,WIDTH);
	}  

	

	//Returns the location of the square sent
	//
	public String getLocation(Square square) {
            return null;
	}
	
	//Assumes that the place the wall is to be placed is already legal
	//The display server will check the legality
	//Checks now if there is a wall already there, if not places a wall
	public boolean placeWall(int i,int j){
		if(i==0 || j==0 || i==8 || j==8){
			return false;
		}
		for(Player p:players){
			if(p.getSquare() ==grid[i][j]){
				return false;
			}
		}
			if(!(grid[i][j] instanceof Wall)){
				grid[i][j] = new Wall(grid[i][j]);
				return true;
			}
		return false;
	}
	
	//Undoes the placement of a wall, will be useful when checking legal moves
	//Super slow, but it works
	public void unDoPlaceWall(int i,int j){
		//grid[i][j] = new Square(i,j);
		Square.setNeighbours(grid, HEIGHT,WIDTH);
	}
	
	
	public Square getSquareAt(int i,int j){
		return grid[i][j];
	}
	
	//Returns the list of players (so we can test that they are in the right spots of the grid
	public List<Player> getPlayers(){
		return players;
	}

	//Put code for making a move here
	//It should return false if the move was not legal
	//We will encode the shortest path algorithm later
	public boolean makeMove(String s){
		return false;
	}
		

}