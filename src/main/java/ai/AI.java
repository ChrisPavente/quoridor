
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
public class AI  {
    
    /**
     * Gets the next move algorithm (weighted by distance to end)
     * 
     * @param b input board
     * @return move to apply
     */
    public void getMove(Board b) {
    	GameEngine gameEngine = null;
    	Player current = null;
        Square sq = getShortestPath(b, current);
        if (sq.parent != null) {
            while (sq.parent.parent != null) {
                sq = sq.parent;
            }
        }
        
//        String move = sq.getRow() + " - " + sq.getColumn();
//        System.out.println(sq.getRow() + sq.getColumn());
//        gameEngine.makeMove(move, 1);
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
    
    
    

    
}
