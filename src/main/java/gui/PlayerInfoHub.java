package gui;

import java.awt.Color;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import board.Board;

public class PlayerInfoHub extends JPanel {
	private Board gameBoard;
	//private int[] currentWalls;
	private int currentPlayer;
	private JLabel[] playerWallInfo;
	private JButton[] currentPlayerField;
	private int playerNum;
	
	
	public PlayerInfoHub(Board b){
		gameBoard = new Board(b.getPlayers().size());
		runOnce();
	}
	
	//Sets up the UserInfoFrame
	private void runOnce(){
		this.setSize(600, 450);
		currentPlayer =0; //always starts at zero
		playerNum = gameBoard.getPlayers().size();
		//currentWalls = new int[playerNum];
		playerWallInfo = new JLabel[playerNum];
		currentPlayerField = new JButton[playerNum];
		for(int i=0;i<playerNum;i++){
			playerWallInfo[i] = new JLabel();
			labelUpdater(i);
		
			currentPlayerField[i] = new JButton();
			currentPlayerField[i].setSize(50, 50);
			currentPlayerField[i].setBackground(Color.black);
			this.add(currentPlayerField[i]);
			this.add(playerWallInfo[i]);
			
		}
		currentPlayerField[currentPlayer].setBackground(gameBoard.getPlayers().get(0).getColor());
		
		
		
	}

	
	//Updates the moveHub after a move has been made
	//Param: boolean move - true if the move was a wall move
	//						false otherwise
	//		 int nextP - the next player going
	public void updateHub(boolean move){
		int nextP = gameBoard.findNextLegalPlayer(currentPlayer);
		currentPlayerField[currentPlayer].setBackground(Color.black);
		currentPlayerField[nextP].setBackground(gameBoard.getPlayers().get(nextP).getColor());
		if(move){
			labelUpdater(currentPlayer);
		}
		currentPlayer = nextP;
	}
	
	//Passes in the number of the player who's panel is being updated
	private void labelUpdater(int p){
		JLabel l = playerWallInfo[p];
		gameBoard.getPlayers().get(p).useWall();
		String text = "Player " + (p+1) + "\n Walls: " + (gameBoard.getPlayers().get(p).getWallNum()+1);
		l.setText(text);
	}
	
	
	
}
