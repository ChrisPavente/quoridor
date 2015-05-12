package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Board;
import board.GameEngine;
import board.Player;
import board.Square;

public class QBoard extends JFrame implements ActionListener, GameEngine {

	public final static String BOARD_TITLE = "Quoridor Board"; //The title on our game board
	public final static Color SQUARE_DEFAULT_COLOR = Color.black; //The default color of the buttons on the game board
	public final static int boardlth = 9; //The number of squares in a board object
	
	public JButton[][] squares = new JButton[boardlth][boardlth]; //Stores the square buttons for the players
	public JButton[][] vertWalls = new JButton[boardlth-1][boardlth]; //Stores the vertical wall buttons
	public JButton[][] horWalls = new JButton[boardlth][boardlth-1]; //Stores the horizontal wall buttons

	private JPanel buttonCanvas; // Canvas that holds all the buttons of the game
	private JPanel infoPanel; // Sub-panel for the info hub
	
	private Board board;
	private Stack<String> moveStack = new Stack<String>();

    private String currentMove;
    private int playerID;
    private PlayerInfoHub hub;
	private boolean isTurn;
	/**
	 * Quoridor Board GUI Constructor.
	 * Creates a 9x9 grid of buttons to play the game. Also instantiates
	 * buttons for the vertical and horizontal walls.
	 * 
	 */
    public QBoard(int num) {
        super();
        playerID = -1;//Just watching the game
        board = new Board(num);
        initialize();
        for(Player p:board.getPlayers()){
            Square s = p.getSquare();
            this.setColorOfSpace(s.getRow(),s.getColumn(), p.getColor());
        }
       

    }
	public QBoard(int num, int ID) {
		super();
        playerID = ID;
		board = new Board(num);
		initialize();
		for(Player p:board.getPlayers()){
			Square s = p.getSquare();
			this.setColorOfSpace(s.getRow(),s.getColumn(), p.getColor());
		}

	}
	
	/**
	 * Instantiates the whole board complete with clickable buttons.
	 */
	private void initialize() {
		setName(BOARD_TITLE);
		setTitle("Player " + (playerID+1));
		setSize(550, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		buttonCanvas = new JPanel();
		infoPanel = new JPanel();
		buttonCanvas.setLayout(null);
		infoPanel.setLayout(new BorderLayout());
		
		initializeButtons();
		buttonCanvas.setSize(356, 356);
		infoPanel.setPreferredSize(new Dimension(150, 356));
		
		hub = new PlayerInfoHub(board);
		infoPanel.add(hub);
		
		add(buttonCanvas);
		add (infoPanel, BorderLayout.EAST);
		
//	    this.add(hub);
//	    hub.setBounds(700,10,500,500);
		setVisible(true);
	}
	
	/**
	 * Instantiates all buttons on the board game grid. In the order of squares, column walls
	 * and row wall clickable buttons.
	 */
	private void initializeButtons(){
		int fromTop = 5; // Offsets the grid from the edge of the board
		boolean walls = false;
		for(int i = 0; i < 17; i++){
			int fromLeft = 6;
			for (int j = 0; j < 17; j++) {
				if(!walls){
					if(j%2 == 0){ //button squares
						JButton button = buttonHelper(i, j);
						Insets insets = buttonCanvas.getInsets();
						button.setBounds(fromLeft + insets.left, fromTop + insets.top, 20, 20);
						fromLeft += 26;
						button.addActionListener(this);
						squares[j/2][i/2]=button;
					} else { //button wall column
						JButton button = buttonHelper(i, j);
						Insets insets = buttonCanvas.getInsets();
						button.setBounds(fromLeft + insets.left, fromTop + insets.top, 5, 20);
						fromLeft += 11;
						button.setVisible(false);
						vertWalls[j/2][i/2]=button;
						
					}
				}
				if (walls){ //button wall row
					JButton button = buttonHelper(i, j);
					Insets insets = buttonCanvas.getInsets();
					button.setBounds(fromLeft + insets.left, fromTop + insets.top, 20, 5);
					fromLeft += 37;
					j++;
					horWalls[j/2][i/2]=button;
					button.setVisible(false);
				}
			}
			if(!walls){
				fromTop += 26;
			}else{
				fromTop += 11;
			}
			walls = !walls;
		}
	}
	
	/**
	 * Helps reduce the amount of redundant code when initiating the buttons
	 * @param i: used in the for loop for the square row number
	 * @param j: used in the for loop for the square column number
	 * @return the JButton created
	 */
	public JButton buttonHelper(int i, int j){
		JButton button = new JButton("");
		button.setName(getColumnRomanNumeral(j/2 + 1)+ "-" + getRowLetter(i/2+1));
		
		button.setBackground(SQUARE_DEFAULT_COLOR);
		buttonCanvas.add(button);
		return button;
	}
	
	/**
	 * Method to convert the given row integer to the corresponding letter
	 * @param i: the integer row number
	 * @return the corresponding string row letter
	 */
	public String getRowLetter(int i){
		if(i == 1)
			return "A";
		if(i == 2)
			return "B";
		if(i == 3)
			return "C";
		if(i == 4)
			return "D";
		if(i == 5)
			return "E";
		if(i == 6)
			return "F";
		if(i == 7)
			return "G";
		if(i == 8)
			return "H";
		if(i == 9)
			return "I";
		return "ERROR";
	}
	
	/**
	 * Method to convert the given column integer to the corresponding roman
	 * numeral.
	 * @param j: the integer column number
	 * @return the corresponding string roman numeral
	 */
	public String getColumnRomanNumeral(int j){
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
	//These fields are currently here as our network isn't hooked up so something needs to keep track of the game

	
	
	public void makeMove(String move, int current){

		String a;
		String b;
		if(move.contains(",")){
			a = move.substring(1, move.indexOf(","));
			b = move.substring(move.indexOf(",")+1,move.length()-1);	
		}
		else{
			a = move;
			b = move;
		}

		if(a.equals(b)){
			hub.updateHub(false);
			//movement of character
			Player temp =board.getPlayers().get(current);
			setColorOfSpace(temp.getSquare().getRow(),temp.getSquare().getColumn(),Color.black);
			if(board.makeMove(a,current)){
				setColorOfSpace(temp.getSquare().getRow(),temp.getSquare().getColumn(),temp.getColor());
			}
			else{
				//make it so we can remove player to be added
				setColorOfSpace(temp.getSquare().getRow(),temp.getSquare().getColumn(),temp.getColor());
				temp.removePlayer();

			}

		}
		else{
			
			//must be trying to place a wall
			//Square temp = board.getPlayers().get(current).getSquare();
			String s = "("+a+","+b+")";
			//if(board.makeMove(s,current)){
			
			 int c1 = board.converColToInt(s.substring(1, s.indexOf("-")));
             int r1 = board.convertRowToInt(s.charAt(s.indexOf("-")+1));
             int c2 = board.converColToInt(s.substring(s.indexOf(",")+1,s.lastIndexOf("-")));
             int r2 = board.convertRowToInt(s.charAt(s.length()-2));
            hub.updateHub(true);
		
			if(c1==c2){
             vertWalls[c1][r1].setVisible(true);
		     vertWalls[c2][r2].setVisible(true);
			}
			else{
				horWalls[c1][r1].setVisible(true);
			    horWalls[c2][r2].setVisible(true);
			}
			
			}
			current = board.findNextLegalPlayer(current);
            this.setTitle(("Player " + (current+1) + "'s turn"));
		}

	public int changeCurrentPlayer(int current){
        switch(current){
            case 0:
                return 0;
            case 1:
                return 3;
            case 2:
                return 1;
            case 3:
                return 2;
            default:
                return 4;
        }
    }


	public void actionPerformed(ActionEvent action){
		//System.out.println(current);
        if(isTurn) {
            String move = ((JButton) action.getSource()).getName();
            if (moveStack.isEmpty()) {
                moveStack.push(move);
            } else {
                //Send Move to be called here:Currently calls makeMove as the game is Local
                //makeMove(moveStack.pop(),move);
                String firstMove = moveStack.pop();
                if (firstMove.equals(move)) {
                    currentMove = move;
             
                } else {
                    currentMove = "("+firstMove + ',' + move+ ")";
                }
            }
        }
       // System.out.println(currentMove);
		//System.out.println(board.makeMove(move, board.getCurrent()));
		
		//For testing purposes
		//Need to figure out a way to get the current player to implement
		//Tyler's makeMove method and then need to implement in the Board class
		//how to change the color of the square to represent the actual move
		//board.makeMove(move, );
	}

    public String getMove(){
    	if(currentMove !=null){
    		isTurn = false;
    	}
        return currentMove;
    }

    /**
     * PostCondition: currentMove is null
     * 				  and isTurn = !isTurn
     * 
     */
    public void setTurn(){//Flips the switch for the player to be able to go
    	isTurn = true;
    	currentMove=null;
    }

	/**
	 * Changes the color of a chosen space to a the players color.
	 * 
	 * @param y: the y coordinate of the given space
	 * @param x: the x coordinate of the given space
	 * @param c: the new color of the space, held in a color object
	 * 			 and indicated by new RGB values
	 */
	public void setColorOfSpace(int y, int x, Color c) {
		squares[x][y].setBackground(c);
	}
	
	@Override
	public void bootPlayer(int n) {
		Player temp = board.getPlayers().get(n);
		setColorOfSpace(temp.getSquare().getRow(),temp.getSquare().getColumn(),Color.black);
		//System.out.println("CANT PLACE WALL");
		temp.removePlayer();
		
		// TODO Auto-generated method stub
		//Should handle booting the player n
	}
	
	public void declareWinner(int n){
		//Do something
		System.out.println("Player "+n + " is the winner!!");
		this.dispose();
	}
	
	
}