package board;


import java.util.ArrayList;
import java.util.List;

/**
 * The Board Class builds the board grid, allows for placement of walls, allows for calls
 * to moves, stores a list of the players and currently can print out the board to
 * terminal for visual effects.
 * 
 * @author Tyler, Chris, Laura, James, Matt
 *
 */
public class Board {
	/*
	 * Squares can hold player's, and the endpoints of the square make up
	 * the walls.  Therefore only two things can be in a square the player
	 * peice of wall.  Note currently a row is being called 'I' and the
	 * first column is being called 'I. 
	 */

    private static final int WALLS = 20; // Number of walls allowed in the game
	private final int HEIGHT = 9; // Number of rows 	
	private final int WIDTH = 9; // Number of columns
	
	
	private Square [][] grid; // Two-dimensional array that will represent the grid
	private List<Player> players; // ArrayList to store the players in the game
	//private int current; // Keeps track of what player's turn it is

	
	/**
	 * The Board Constructor.  
	 * This when called will build the board, delegate the walls to the players and 
	 * add the players to the board.
	 * 
	 * @param numberOfPlayers: the number of players wishing to play the game
	 */
	public Board(int numberOfPlayers) {
		if(numberOfPlayers !=2 && numberOfPlayers!=4){
			//BREAK
			throw new IllegalArgumentException("Must have 2 or 4 players");
		}
		createGrid();
		int w = WALLS/numberOfPlayers;
		
		players = new ArrayList<Player>();
		players.add(new Player(grid[0][4],w,0,getWinningSquares(0)));
		players.add(new Player(grid[8][4], w,1,getWinningSquares(1)));
		if (numberOfPlayers == 4) {
			players.add(new Player(grid[4][8], w,2,getWinningSquares(2)));
			players.add(new Player(grid[4][0],w,3,getWinningSquares(3)));
		}
	}

	
	/**
	 * This method is used to build the actual grid for the board to be played
	 * on. Note: this method must be private otherwise it could be called 
	 * and the current board could be wiped out at any time.
	 */
	private void createGrid() {
		 grid = new Square[HEIGHT][WIDTH]; 
		 for (int i = 0; i < grid.length; i++) {
  			  for (int k = 0; k < grid.length; k++) {
                   grid[i][k] = new Square(i,k);
  			  }
		 }
		 Square.setNeighbours(grid, HEIGHT,WIDTH);
	}  

	

	/**
	 * Checks if a wall is in the current location, if the current location is
	 * empty a wall is then placed there.  Note that the display server will 
	 * check for legality of the wall, so we are assuming the location is legal.
	 * 
	 * (i,j)-(a,b) represents the "top" half of the wall.  In other words, the
	 * wall is between (i,j) and (i,j+1) is it is horizontal, etc.
	 * 
	 * @param i
	 * @param j
	 * @param a
	 * @param b
	 * @return true: if the wall has been placed in the desired location, false
	 * 			     otherwise.
	 */
	private boolean placeWall(int x1,int y1, int x2, int y2){
		if((x1==0 && x2==0)|| (y1==0 && y2==0) || (x1==8 && x2==8) || (y1==8 && y2==8)){
			return false;
		}
		if(Math.abs(x1-x2)*Math.abs(x1-x2)+Math.abs(y1-y2)*Math.abs(y1-y2)>1){
			return false;
		}
		boolean check = false;
		if(Math.abs(x1-x2)==1 && !(checkIfWallIsThere(x1,y1,true)) && !(checkIfWallIsThere(x2,y2,true))){
			//The wall is horizontal
			Square s1 =  grid[x1][y1];
			Square s2 = grid[x2][y2];
			check = s1.breakLink(grid[x1][y1+1]) && s2.breakLink(grid[x2][y2+1]);
			if(!check){
				unDoPlaceWall(x2,y2,true);
			}
			
		}
		else if(Math.abs(y1-y2)==1 && !(checkIfWallIsThere(x1,y1,false)) && !(checkIfWallIsThere(x2,y2,false))){
			Square s1 =  grid[x1][y1];
			Square s2 = grid[x2][y2];
			check = s1.breakLink(grid[x1+1][y1]) && s2.breakLink(grid[x2+1][y2]);
			if(!check){
				unDoPlaceWall(x2,y2,false);
			}
		}
		
		return check;
	}
	

	/**
	 * This method will remove a wall once it is placed.  
	 * 
	 * Note: We assume a place wall has already been called and this technique is also
	 * very slow.  
	 * 
	 * @param i:the row 
	 * @param j:the column
	 * @param direction: one of the two cardinal directions that the wall can be placed in
	 */
	private void unDoPlaceWall(int i,int j, boolean direction){
		if(direction){
			Square s= this.getSquareAt(i, j);
			s.reLink(this.getSquareAt(i+1, j), 1);
		}
		else{
			Square s= this.getSquareAt(i, j);
			s.reLink(this.getSquareAt(i, j+1), 2);
		}
	}
	
	
	/**
	 * Method to find the square and the given values.
	 * 
	 * @param i
	 * @param j
	 * @return the square at the given location values.
	 */
	private Square getSquareAt(int i,int j){
		return grid[i][j];
	}
	
	
	/**
	 * Method to get the players currently playing the game. Useful in helping to 
	 * ensure that the players are in the correct positions.
	 * 
	 * @return the list of players involved in the game.
	 */
	public List<Player> getPlayers(){
		return players;
	}


	/**
	 * Allows the user to make a move on the board object.
	 * 
     * @param current:the player making the move;
	 * @param s: string for character move or wall placement
	 * 			 for character move it is of the form: [columnRomanNumeral]-[rowLetter]. Example: VIII-A
	 * 			 for wall placement it is of the form: [columnRomanNumeral]-[rowLetter]_[columnRomanNumeral]-[rowLetter] Example: V-A_VI-A
	 * @return false if the move was not legal, otherwise return true. 
	 */
	public boolean makeMove(String s,int current){
        if(!players.get(current).isActive()){
            throw new IllegalArgumentException("That Player is not in the game");
        }
        boolean isLegal = true;
        if (s.contains("_")) {
        	  int c1 = converColToInt(s.substring(0, s.indexOf("-")));
              int r1 = convertRowToInt(s.charAt(s.indexOf("-")+1));
              int c2 = converColToInt(s.substring(s.indexOf("_")+1,s.lastIndexOf("-")));
              int r2 = convertRowToInt(s.charAt(s.length()-1));
              isLegal = players.get(current).getWallNum()>=1 && placeWall(r1,c1,r2,c2) && players.get(current).useWall();
        	}
          
        else if (s.contains("-")) {
              int c = converColToInt(s.substring(0, s.indexOf("-")));
              int r = convertRowToInt(s.charAt(s.length()-1));
              isLegal = checkIfLegalPlayerMove(r, c, current) &&  players.get(current).move(getSquareAt(r, c));
        }		
        return isLegal;
	}
	
	

	/**
	 * Method converts the row character to its equivalent row int (0-8)
	 * 
	 * @param c: the row character 
	 * @return the integer equivalent to the character given
	 */
	public int convertRowToInt(char c) {
		return c - 65;

	}

	
	/**
	 * Method converts the column Roman numeral to its column integer (0-8)
	 * 
	 * @param s: the column Roman numeral
	 * @return the integer equivalent to the character given
	 */
	public int converColToInt(String s) {
		if (s.equals("IV"))
			return 3;
		if (s.equals("IX"))
			return 8;
		int col = 0;
		for (int i = 0; i < s.length(); i++ ) {
        	if (s.charAt(i) =='V')
            	col += 5;
          	else if (s.charAt(i) == 'I')
            	col += 1;
    	}
        return col-1;
	}
	


    /**
     * Method to check if the player's desired move is legal
     * @param r: row number
     * @param c: column number
     * @param p: player number
     * @return true if the move is legal, false otherwise
     */
    private boolean checkIfLegalPlayerMove(int r, int c,int num) {
    	  Square goal = getSquareAt(r, c);
          if (r>8 || r<0 || c>8 || c<0)
                return false;
          if (checkIfPlayerIsThere(goal))
                return false;//Make sure we arnt trying to jump onto a character
          
          Square curr = players.get(num).getSquare();
          return recursiveJumpCheck(curr,goal,players.size()-1);
    }
    
    /**
    * Method to do the recursive check for jumps
    *
    */
    private boolean recursiveJumpCheck(Square cur,Square goal,int tally){
        if(tally<0){
            return false;
        }
    	if (cur.adjacentUp != null && cur.adjacentUp.equals(goal))
             return true;
       if (cur.adjacentDown != null && cur.adjacentDown.equals(goal))
             return true;
       if (cur.adjacentLeft != null && cur.adjacentLeft.equals(goal))
             return true;
       if (cur.adjacentRight != null && cur.adjacentRight.equals(goal))
             return true;
       boolean b = false;
       tally = tally-1;
       for(int i=0;i<4;i++){
     	  if(checkIfPlayerIsThere(cur.getNeighbour(i))){
     		  b = b ||recursiveJumpCheck(cur.getNeighbour(i),goal,tally);
     	  }
       }
       return b;
       
    }


    /**
     * Method to check if a wall is at a desired location
     * @param r: row number of desired location
     * @param c: column number of desired location
     * @return true if a wall is present, false otherwise
     */
    private boolean checkIfWallIsThere(int r, int c, boolean b) { 
    	//b is a boolean since we really can only a wall in two places relative to a square
    	//b=true means a right wall
    	//b=false means checking for a wall to the south of our sqr
          Square s = getSquareAt(r, c);
          if(b && s.adjacentRight ==null){
          return true;
          }
          if(!b && s.adjacentDown==null){
        	  return true;
          }
          return false;
    }
    
    /**
     * Method to check if a player is in a desired location
     * @param Square s: the square to be check for a player
     * @return true if a player is located here, false otherwise
     */
    private boolean checkIfPlayerIsThere(Square s){
    	for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isActive() && players.get(i).getSquare().equals(s))
                  return true;
     }
    	return false;
    }
    
   
	/**
	 * A |w| or -w- represents a wall in the grid, where as a |+| or -+- represents a legal path
	 * any blank space is a legal place for a player to reside and the number (0,1,2,3) represents the 
	 * players 0,1,2,3 respectively.
	 * 
	 * @return a String representation of Board
	 */
	public String toString(){	   
		String a ="---------------------------------------\n";
		String s ="" +a;
		List<Square> temp = new ArrayList<Square>();
		for(Player p: players){
			//makes a temp copy of players
			temp.add(p.getSquare());
			
		}
		
		for(int i=0;i<9;i++){
			String temp1 = "|w|";
			String temp2 = "|-";
			for(int j=0;j<9;j++){
				Square current = grid[i][j];
				if(temp.contains(current)){
					int n = temp.indexOf(current);
					temp1 +=n;
				}
				else{
					temp1 += " ";
				}
				if(current.getNeighbour(1)==null){
					temp1 += "|w|";
				}
				else
					temp1 += "|+|";
	
				if(current.getNeighbour(2)==null){
					temp2 += "-w-";
				}
				else
					temp2 += "-+-";
			 
				if(j<8){
				 temp2 +="-";
				}
			}
			temp1 += "\n";
			temp2 += "-|" + "\n";
			s += temp1;
			//if its not the last line, add temp2
			//if not use the template String a
			if(i!=8){
				s +=temp2;
			}
			else{
				s+=a;
			}
		}
		return s;
	}
	
	

	public static void main(String[] args) {
		Board board = new Board(2);
		System.out.println(board.toString());
	}

	/**
	 * Method to determine the winner of the game
	 * @param kickedPlayers
	 * @return the winner of the game
	 */
	public Player isWinner(int kickedPlayers){
		//Called after a move is made to see if that player won
		for(Player p: players){
		if(p.isActive() && 
			(kickedPlayers == players.size()-1 || p.isAWinner())){
			return p;
		}
		}
		return null;
	}
	
	/**
	 * 
	 * @return -1 if there is no winner, else it returns the winner's id num
	 */
	public int getWinnerNum(){
		int next = this.findNextLegalPlayer(0);
		if(next ==-1){
			return 0;
		}
		next = this.findNextLegalPlayer(next);
		if(next ==-1){//There are no more legal players after the first one we find!
			return findNextLegalPlayer(0); 
		}
		return -1;
	}
	
	/**
	 * 
	 * @param current - current player going
	 * @return the next player (if there is one)
	 */
	public int findNextLegalPlayer(int current){
		//Figured this would be useful for my PlayerInfoHub
		int size = players.size();
		for(int i=(current+1)%size;i!=current;i=(i+1)%size){
			if(players.get(i).isActive()){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 
	 * @param num the player number we want the array of squares for
	 * @return an Array of Squares to be passed into the player constructor
	 *		    will help with checking winning conditions!
	 */
	private Square[] getWinningSquares(int num){
		Square[] sqrs = new Square[9];
		if(num ==0){
			for(int i=0;i<9;i++){
				sqrs[i]= grid[8][i];
			}
			
		}
		if(num ==1){
			for(int i=0;i<9;i++){
				sqrs[i]= grid[0][i];
			}
			
		}
		if(num ==2){
			for(int i=0;i<9;i++){
				sqrs[i]= grid[i][0];
			}
			
		}
		if(num ==3){
			for(int i=0;i<9;i++){
				sqrs[i]= grid[i][8];
			}
			
		}
		return sqrs;
	}
	

}
