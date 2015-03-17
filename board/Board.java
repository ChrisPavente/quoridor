package board;

import gui.QBoard;

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
	private int current; // Keeps track of what player's turn it is
    
	private QBoard graphic;
	
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
		players.add(new Player(grid[0][4],w,0));
		players.add(new Player(grid[8][4], w,1));
		if (numberOfPlayers == 4) {
			players.add(new Player(grid[4][0],w,2));
			players.add(new Player(grid[4][8], w,3));
		}
		newGraphic();
		current=0;
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
	 * Returns the location of the square that is passed.
	 * 
	 * @param square: the square we are trying to get the location for.
	 * @return: Supposed to return the location of the square, however currently returns null.
	 */
	public String getLocation(Square square) {
		// FIXME
		return null;
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
	public boolean placeWall(int i,int j, int a, int b){
		if((i==0 && a==0)|| (j==0 && b==0) || (i==8 && a==8) || (j==8 && b==8)){
			return false;
		}
		boolean check = false;
		if(Math.abs(i-a)==1){
			//The wall is horizontal
			Square s =  grid[i][j];
			check = s.breakLink(grid[i][j+1]);
			if(!check){
				return check;
			}
			s = grid[a][b];
			check = check && s.breakLink(grid[a][b+1]);
		}
		if(Math.abs(j-b)==1){
			Square s =  grid[i][j];
			check = s.breakLink(grid[i+1][j]);
			if(!check){
				return check;
			}
			s = grid[a][b];
			check = check && s.breakLink(grid[a+1][b]);
		}
		
		return check;
	}
	

	/**
	 * This method will remove a wall once it is placed.  
	 * 
	 * Note: We assume a place wall has already been called and this technique is also
	 * very slow.  
	 * 
	 * @param i
	 * @param j
	 * @param a
	 * @param b
	 */
	public void unDoPlaceWall(int i,int j, int a, int b){
		if(Math.abs(i-a)==1){
			//The wall is horizontal
			Square s =  grid[i][j];
			s.reLink(grid[i][j+1], 2);
			s = grid[a][b];
			s.reLink(grid[a][b+1],2);
		}
		if(Math.abs(j-b)==1){
			Square s =  grid[i][j];
			s.reLink(grid[i+1][j],1);
			s = grid[a][b];
			s.reLink(grid[a+1][b],1);
		}
	}
	
	
	/**
	 * Method to find the square and the given values.
	 * 
	 * @param i
	 * @param j
	 * @return the square at the given location values.
	 */
	public Square getSquareAt(int i,int j){
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
	 * @param s: string for character move or wall placement
	 * 			 for character move it is of the form: [rowLetter]-[columnRomanNumeral]. Example: A-VIII
	 * 			 for wall placement it is of the form: [rowLetter]-[columnRomanNumeral]_[rowLetter]-[columnRomanNumeral] Example: A-V_A-VI
	 * @return false if the move was not legal, otherwise return true.
	 */
	public boolean makeMove(String s){
        boolean isLegal = false;
        if (s.contains("_")) {
              int r1 = convertRowToInt(s.charAt(0));
              int c1 = converColToInt(s.substring(2, s.indexOf("_")));
              int r2 = convertRowToInt(s.charAt((char)(s.indexOf("_")+1)));
              int c2 = converColToInt(s.substring(s.lastIndexOf("-")+1));
              if (players.get(current).getWallNum()-1 == 0)
                  return false;
              isLegal = placeWall(r1, c1, r2, c2);
              if (isLegal == false) 
                    return isLegal;
 
              players.get(current).useWall();
        }  
        else if (s.contains("-")) {
              int r = convertRowToInt(s.charAt(0));
              int c = converColToInt(s.substring(2));
              isLegal = checkIfLegalPlayerMove(r, c, current);
              if (!(isLegal))
                 return isLegal;
              players.get(current).move(getSquareAt(r, c));
        }
        //add the method call here to print the board to view board
        if(isLegal){
        	current = (current+1)%players.size();
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
	 * Method to check if the placement of the wall is legal.
	 * (Note: Assume a player will not be cut off from winning.)
	 * @param r1
	 * @param c1
	 * @param r2
	 * @param c2
	 * @return true if the wall placement is legal, false otherwise
	 */
    private boolean checkIfLegalWallPlace(int r1, int c1, int r2, int c2) {
          if (r1>8 || r1<0 || c1>8 || c1<0 || r2>8 || r2<0 || c2>8 || c2<0)
                return false;
          //if (checkIfPlayerIsThere(r1, c1))
            //    return false;
          //if (checkIfPlayerIsThere(r2, c2))
            //    return false;
          if (r1 != r2 && c1 != c2)
                return false;
          if ((r1 == 8 && r2 == 8))
          	return false;
          if ((c1 == 8 && c2 == 8))
          	return false;
          if (Math.abs(r1-r2)!=1 && Math.abs(c1-c2)!=1)
                return false;
         
          return true;
    }


    /**
     * Method to check if the player's desired move is legal
     * @param r: row number
     * @param c: column number
     * @param p: player number
     * @return true if the move is legal, false otherwise
     */
    private boolean checkIfLegalPlayerMove(int r, int c, int p) {
          if (r>8 || r<0 || c>8 || c<0)
                return false;
        //  if (checkIfWallIsThere(r, c))
                //return false;// Tyler this makes no sense, you check later if the square is adjacent, that is all you need
          if (checkIfPlayerIsThere(r, c))
                return false;
          Square s = getSquareAt(r, c);
          //int row = players.get(p).getSquare().getRow();
          //int col = players.get(p).getSquare().getColumn();
          //Square pS = getSquareAt(row, col);
          Square pS = players.get(p).getSquare();
          if (pS.adjacentUp != null && pS.adjacentUp.equals(s))
                return true;
          if (pS.adjacentDown != null && pS.adjacentDown.equals(s))
                return true;
          if (pS.adjacentLeft != null && pS.adjacentLeft.equals(s))
                return true;
          if (pS.adjacentRight != null && pS.adjacentRight.equals(s))
                return true;
          return false;

    }


    /**
     * Method to check if a wall is at a desired location
     * @param r: row number of desired location
     * @param c: column number of desired location
     * @return true if a wall is present, false otherwise
     */
    private boolean checkIfWallIsThere(int r, int c, boolean b) { 
    	//b is a boolean since we really can only a wall in two places relative to a wall
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
     * @param r: row number of desired location
     * @param c: column number of desired location
     * @return true if a player is located here, false otherwise
     */
    private boolean checkIfPlayerIsThere(int r, int c) {
          Square s = getSquareAt(r, c);
          for (int i = 0; i < players.size(); i++) {
                 if (players.get(i).getSquare().equals(s))
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
	
	public void newGraphic(){
		graphic = new QBoard(this);
		for (int i = 0; i < players.size(); i++){
			graphic.setColorOfSpace(players.get(i).getSquare().getRow(), players.get(i).getSquare().getColumn(),  players.get(i).getColor());
		}
	}

	public static void main(String[] args) {
		Board board = new Board(2);
		System.out.println(board.toString());
	}


	public int getCurrent() {
		return current;
	}
	public Player isWinner(){
		//Called after a move is made to see if that player won
		for(Player p: players){
		if(p.isAWinner()){
			return p;
		}
		}
		return null;
	}

}