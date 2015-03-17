package board;

import java.awt.Color;
import java.util.List;

import board.Square;

/**
 * The Player class instantiates a player object to be used in the game.
 * 
 * @author Tyler, Chris, Laura, James, Matt
 *
 */
public class Player{
	
	private int num; //Knows which number player it is, MAY helpful for shortest path
	private Square square;
	private int walls;
	private String oldShortestPath;//Keeps a representation of the shortest path
									//We hold on to this, because in some cases the shortest path hasn't changed so we can use our old path

	public final static Color[] color = {Color.yellow, Color.green, Color.blue, Color.red};
	private Color col;

	/**
	 * The Player Constructor.
	 * This when called constructs the player object, which has a which has a reference to its location,
	 * and the number of walls it has.
	 * 
	 * @param s: the square the player is located on
	 * @param walls: the number of walls the player has remaining
	 * @param num: the players reference number
	 */
	public Player(Square s,int walls,int num) {
		this.num =num;
		this.square = s;
		this.walls=walls;
		setDefaultColor();
	}	
	
	/**
	 * Method to get the square they are currently occupying.
	 * @return the square the player is on
	 */
	public Square getSquare(){
		return square;
	}
	
	public boolean isAWinner(){
		//checks if the player is currently in a winning condition
		if(num==0 && square.getRow()==8){
			return true;
		}
		if(num==1 && square.getRow()==0){
			return true;
		}
		//We dont have more players yet so it would be silly to add more win conditions yet
		return false;
	}
	
	/**
	 * Method to return the number of walls the player has left.
	 * @return the number of walls the player currently has left to use
	 */
	public int getWallNum(){
		return walls;
	}
	
	/**
	 * Method to decrement the number of walls the player has.  Used after
	 * the player uses one of their walls.
	 */
	public void useWall(){
		walls--;
	}
	
	

	/**
	 * Method to move a player to a specified square.
	 * (Note: we are assuming that the move is legal because display server 
	 * will check legality)
	 * @param s the square to move the player to
	 */
	public void move(Square s) {
		square = s;
	} 
	
	/**
	 * Method to get the player's number in the game
	 * @return the players number
	 */
	public int getNum() {
		return num;
	}

	
	/**
	 * Sets the Player's Color to the default Color based on their PlayerID.
	 */
	private void setDefaultColor() {
		if (num > -1 && num < 4) {
			col = color[num];
			col = color[num];
		} else {
			col = Color.white;
		}

	}
	
	/**
	 * Returns the Player's Color.
	 * 
	 * @return Returns the Player's Color.
	 */
	public Color getColor() {
		return col;
	}


	
} 