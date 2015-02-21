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
		int w = WALLS/numberOfPlayers;
		players = new ArrayList<Player>();
		players.add(new Player(grid[0][4],w));
		players.add(new Player(grid[8][4], w));
		if (numberOfPlayers == 4) {
			players.add(new Player(grid[4][0],w));
			players.add(new Player(grid[4][8], w));
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
	// (i,j)-(a,b) represents the "top" half of the wall
	//That is the wall is between (i,j) and (i,j+1) if its horizontal etc.
	public boolean placeWall(int i,int j, int a, int b){
		if((i==0 && a==0)|| (j==0 && b==0) || (i==8 && a==8) || (j==8 && b==8)){
			return false;
		}
		boolean check = false;
		if(Math.abs(i-a)==1){
			//The wall is horizontal
			Square s =  grid[i][j];
			check = s.breakLink(grid[i][j+1]);
			s = grid[a][b];
			check = check && s.breakLink(grid[a][b+1]);
		}
		if(Math.abs(j-b)==1){
			Square s =  grid[i][j];
			check = s.breakLink(grid[i+1][j]);
			s = grid[a][b];
			check = check && s.breakLink(grid[a+1][b]);
		}
		
		return check;
	}
	
	//Undoes the placement of a wall, will be useful when checking legal moves
	//Super slow, but it works
	//Assumes placewall has already been called just before hand
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
	//
	//s for character move is of the form: [rowLetter]-[columnRomanNumeral]. Example: A-VIII
	//s for a wall placement is of the form: [rowLetter]-[columnRomanNumeral]_[rowLetter]-[columnRomanNumeral] Example: A-V_A-VI
	//Legal move tests have not been added yet
	//paremeter p: is the player in the list of players who is making the move
	public boolean makeMove(String s, int p){
            boolean isLegal = false;
            if (s.contains("_")) {
                  int r1 = convertRowToInt(s.charAt(0));
                  int c1 =    converColToInt(s.substring(2, s.indexOf("_")));
                  int r2 = convertRowToInt(s.charAt((char)(s.indexOf("_")+1)));
                  int c2 = converColToInt(s.substring(s.lastIndexOf("-")+1));
                  isLegal = checkIfLegalWallPlace(r1, c1, r2, c2);
                  if (isLegal == false) 
                        return isLegal;
                  if (players.get(p).getWallNum() == 0)
                        return false;
                  players.get(p).useWall();
                  placeWall(r1, c1, r2, c2);

            } else if (s.contains("-")) {
                  int r = convertRowToInt(s.charAt(0));
                  int c = converColToInt(s.substring(2));
                  isLegal = checkIfLegalPlayerMove(r, c, p);
                  if (!(isLegal))
                        return isLegal;
                  players.get(p).move(getSquareAt(r, c));
            }
            //add the method call here to print the board to view board
            return isLegal;
    }
	

	//Converts the the row character to its equivalent row int (0-8)
	private int convertRowToInt(char c) {
		return c - 65;

	}

	//converts the column roman numeral to its equivalent colum int (0-8)
	private int converColToInt(String s) {
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

	 //These methods are here because the makeMove method needs them
      //Ignores if there is a wall there already since that case is handled in another method
      //Assumes a player will not be cut off from winning
      private boolean checkIfLegalWallPlace(int r1, int c1, int r2, int c2) {
            if (r1>8 || r1<0 || c1>8 || c1<0 || r2>8 || r2<0 || c2>8 || c2<0)
                  return false;
            if (checkIfPlayerIsThere(r1, c1))
                  return false;
            if (checkIfPlayerIsThere(r2, c2))
                  return false;
            if (r1 != r2 && c1 != c2)
                  return false;
            if ((r1 == 0 && r2 == 0) || (r1 == 8 && r2 == 8))
            	return false;
            if ((c1 == 0 && c2 == 0) || (c1 == 8 && c2 == 8))
            	return false;
            if (Math.abs(r1-r2)!=1 && Math.abs(c1-c2)!=1)
                  return false;
            return true;
      }

      //Returns false if move is illegal, true otherwise
      private boolean checkIfLegalPlayerMove(int r, int c, int p) {
            if (r>8 || r<0 || c>8 || c<0)
                  return false;
            if (checkIfWallIsThere(r, c))
                  return false;
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

      //Returns true if there is a wall at this location
      private boolean checkIfWallIsThere(int r, int c) {
            Square s = getSquareAt(r, c);
            if (s.adjacentUp == null && r>0) {
                  Square square = getSquareAt(r-1, c);
                  if (square.adjacentDown == null)
                        return true;
            } else if (s.adjacentDown == null && r<8) {
                   Square square = getSquareAt(r+1, c);
                   if (square.adjacentUp == null)
                         return true;
            } else if (s.adjacentLeft == null && c>0) {
                  Square square = getSquareAt(r, c-1);
                  if (square.adjacentRight == null)
                        return true;
            } else if (s.adjacentRight == null && c<8) {
                  Square square = getSquareAt(r, c+1);
                  if (square.adjacentLeft == null)
                        return true;
            }
            return false;
      }

      //Returns true if a player is in given coordinates
      private boolean checkIfPlayerIsThere(int r, int c) {
            Square s = getSquareAt(r, c);
            for (int i = 0; i < players.size(); i++) {
                   if (players.get(i).getSquare().equals(s))
                         return true;
            }
            return false;

      }


}