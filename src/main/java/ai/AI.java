
package ai;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import board.Board;
import board.GameEngine;
import board.Player;
import board.Square;


/**
 * Artificial Intelligence Player
 *
 */
public class AI implements GameEngine {
	private Board board;
	private Boolean isTurn;
	private int playerID;
	private Player current;
    
	
	public AI(int size, int ID){
		playerID = ID;
		board = new Board(size);
		current = board.getPlayers().get(ID);
	}
	
    /**
     * Gets the next move algorithm (weighted by distance to end)
     * 
     * @param b input board
     * @return move to apply
     */
    public String getMove(Board b) {
        Square sq = getShortestPath(b, current);
        if (sq.parent != null) {
            while (sq.parent.parent != null) {
                sq = sq.parent;
            }
        }
        System.out.println(sq.getRow());
        return getColumnRomanNumeral(sq.getColumn()) + "-" + getRowLetter(sq.getRow());
    }
    
	/**
	 * Method to convert the given row integer to the corresponding letter
	 * @param i: the integer row number
	 * @return the corresponding string row letter
	 */
	public static String getRowLetter(int i){
		if(i == 0)
			return "A";
		if(i == 1)
			return "B";
		if(i == 2)
			return "C";
		if(i == 3)
			return "D";
		if(i == 4)
			return "E";
		if(i == 5)
			return "F";
		if(i == 6)
			return "G";
		if(i == 7)
			return "H";
		if(i == 8)
			return "I";
		return "ERROR";
	}
	
	/**
	 * Method to convert the given column integer to the corresponding roman
	 * numeral.
	 * @param j: the integer column number
	 * @return the corresponding string roman numeral
	 */
	public static String getColumnRomanNumeral(int j){
		if(j == 1)
			return "I";
		if(j == 2)
			return "II";
		if(j == 3)
			return "III";
		if(j == 4)
			return "IV";
		if(j == 5)
			return "V";
		if(j == 6)
			return "VI";
		if(j == 7)
			return "VII";
		if(j == 8)
			return "VIII";
		if(j == 9)
			return "IX";
		return "ERROR";
	}
    
    /**
     * Shortest path algorithm from a position to the end of the board.
     * 
     * @param board input board
     * @param pl player id
     * @return child node of the generated tree of positions.
     */
    public Square getShortestPath(Board board, Player pl) {
        Queue<Square> q = new LinkedList<Square>();
        LinkedList<Square> visited = new LinkedList<Square>();
        Square current = pl.getSquare();
        List<Square> neighbours = board.nextPossibleValidMove(current);
        
        boolean finished = false;
        
        q.add(current);
        visited.add(current);
        
        while (!q.isEmpty() && !finished) {
            current = q.remove();
            
            for (Square p : neighbours) {
                if (!visited.contains(p)) {
                    p.parent = current;
                    visited.add(p);
                    q.add(p);
                    if (pl.isAWinner()) {
                        current = p;
                        finished = true;
                        break;
                    }
                }
            }
        }
        
        return current;
    }

	@Override
	public void setTurn() {
		this.isTurn = true;
		
	}

	@Override
	public String getMove() {
		isTurn = false;
		return getMove(board);
	}

	@Override
	public void makeMove(String s, int n) {
		board.makeMove(s, n);
		
	}

	@Override
	public void bootPlayer(int n) {
		for(Player pl: board.getPlayers()){
			if(pl.getNum() == n)
				pl.removePlayer();
		}
			
		
	}
    
    
    

    
}
