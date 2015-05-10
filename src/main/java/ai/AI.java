
package ai;


import java.util.List;
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
	private List<Square> path;
    
	
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
    	//path = current.genShortPath(b, board.getPlayers());
    	//Square sq = path.get(0);
    	//System.out.println("column: " + sq.getColumn() + " row: " + sq.getRow());
        //Square sq = getShortestPath(b, current);
    	Square sq = getRandomValidSquare(b, current);
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
		if(j == 0)
			return "I";
		if(j == 1)
			return "II";
		if(j == 2)
			return "III";
		if(j == 3)
			return "IV";
		if(j == 4)
			return "V";
		if(j == 5)
			return "VI";
		if(j == 6)
			return "VII";
		if(j == 7)
			return "VIII";
		if(j == 8)
			return "IX";
		return "ERROR";
	}
	
	public Square getRandomValidSquare(Board board, Player pl){
		Random rand = new Random();
		List<Square> posMove = board.nextPossibleValidMove(pl.getSquare());
		
		int randNum = rand.nextInt(Integer.MAX_VALUE);
		int randSquare = randNum % posMove.size();
		
//		while(true){
//			for(Square s: pl.getVisitedSquares()){				
//				if(posMove.get(randSquare) != s){
//					pl.getVisitedSquares().add(s);
					return posMove.get(randSquare);
//				}
//			}
//			randSquare = randNum % posMove.size();
//		}

	}
    
//    /**
//     * Shortest path algorithm from a position to the end of the board.
//     * 
//     * @param board input board
//     * @param pl player id
//     * @return child node of the generated tree of positions.
//     */
//    public Square getShortestPath(Board board, Player pl) {
//        Queue<Square> q = new LinkedList<Square>();
//        LinkedList<Square> visited = new LinkedList<Square>();
//        Square current = pl.getSquare();
//        List<Square> neighbours = board.nextPossibleValidMove(current);
//        
//        boolean finished = false;
//        
//        q.add(current);
//        visited.add(current);
//        
//        while (!q.isEmpty() && !finished) {
//            current = q.remove();
//            
//            for (Square p : neighbours) {
//                if (!visited.contains(p)) {
//                    p.parent = current;
//                    visited.add(p);
//                    q.add(p);
//                    if (pl.isAWinner()) {
//                        current = p;
//                        finished = true;
//                        break;
//                    }
//                }
//            }
//        }
//        
//        return current;
//    }

	@Override
	public void setTurn() {
		this.isTurn = true;
		
	}

	@Override
	public String getMove() {
		isTurn = false;
		String s = getMove(board);
		System.out.println(s);
		return s;
	}

	@Override
	public void makeMove(String s, int n) {
		board.makeMove(s, n);
		
	}

	@Override
	public void bootPlayer(int n) {	
		board.getPlayers().get(n).removePlayer();
	}
    
    
    

    
}
