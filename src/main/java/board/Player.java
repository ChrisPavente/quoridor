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
	private Square[] winningSquares;
	
	private boolean inGame;
	private final static Color[] color = {Color.yellow, Color.green, Color.blue, Color.red};
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
	public Player(Square s,int walls,int num,Square[] winningSquares) {
		this.num =num;
		this.square = s;
		this.walls=walls;
		this.winningSquares = winningSquares;
		inGame = true;
		setDefaultColor();
	}	
	
	/**
	 * Method to get the square they are currently occupying.
	 * @return the square the player is on
	 */
	public Square getSquare(){
		return square;
	}
	
	/**
	 *  Method to check if the player is in a winning condition.
	 * @return true if the player has won, false otherwise
	 */
	public boolean isAWinner(){
		for(Square s: winningSquares){
			if(s.equals(square)){
				return true;
			}
		}
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
	public boolean useWall(){
		walls--;
		return true;
	}
	
	

	/**
	 * Method to move a player to a specified square.
	 * (Note: we are assuming that the move is legal because display server 
	 * will check legality)
	 * @param s the square to move the player to
	 */
	public boolean move(Square s) {
		square = s; 
		return true;
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

	/**
	 * Checks to see if the player is in the game.
	 * 
	 * @return true if the player is in the game, false otherwise
	 */
	public boolean isActive(){
		return inGame;
	}
	
	/**
	 * Remove the player who calls this method from the game
	 * PostCondition: The player has no reference to a square anymore. They are effectively out of the game
	 */
	public void removePlayer(){
		square = null;
		inGame = false;
	}
	
	/** Helpful to see where a player needs to go to get into a winning state
	 * 
	 * @return returns the array of squares the are the winning conditions for this player!
	 */
	public Square[] getWinningSquares(){
		return winningSquares;
	}

	
	
} 