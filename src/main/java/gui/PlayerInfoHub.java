package gui;

import java.awt.Color;

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
		gameBoard =b;
		runOnce();
	}
	
	//Sets up the UserInfoFrame
	private void runOnce(){
		this.setSize(500, 500);
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
			placeComponents(i);
			currentPlayerField[i].setBackground(Color.black);
			this.add(currentPlayerField[i]);
			this.add(playerWallInfo[i]);
			//currentPlayerField[currentPlayer].setBackground(Color.black);
			
		}
		currentPlayerField[currentPlayer].setBackground(gameBoard.getPlayers().get(0).getColor());
		
		
		
	}
	
	//Silly way to place the different components on the frame
	private void placeComponents(int p){
		JLabel label = playerWallInfo[p];
		JButton button = currentPlayerField[p];
		if(p==0){
			label.setBounds(40, 40, 5, 10);
			button.setBounds(50, 50, 0, 350);
		}
		else if(p==1){
			label.setBounds(40, 40,5, 200);
			button.setBounds(50, 50,50, 350);
		}
		else if(p==2){
			label.setBounds(40, 40, 250, 10);
			button.setBounds(50, 50,100, 350);
		}
		else{
			label.setBounds(40, 40, 250, 200);
			button.setBounds(50, 50,150, 350);
		}
		
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
		String text = "Player " + p + "\n Walls: " + gameBoard.getPlayers().get(p).getWallNum();
		l.setText(text);
	}
	
	
	
}
