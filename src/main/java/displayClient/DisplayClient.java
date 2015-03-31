package displayClient; /**
Display Client
 */

import java.io.*;
import java.net.*;
import java.util.*;

import board.Board;

//  Create display client
public class DisplayClient{
    public static void main(String[] args){
        String[] testNames = {"localhost:8888" , "localhost:8889"};
        
        try {
	        DisplayClient client = new DisplayClient(args);
            //DisplayClient client = new DisplayClient(testNames);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

//------------------------------------------------------------------------------------------

    private int numOfPlayers;
    private String[] hostNames;
    private String[] playerIDs;
    private int[] playerPorts;
    private Socket[] sockets;
    private BufferedReader[] input;
    private PrintWriter[] output;
    private boolean victor = false;
    private Board board;
    private int round;

    public DisplayClient(String[] players) throws Exception{
	

        //  Set up player and sockets
        SetUp(players);
        socketSetUp();
	    //printData();

        //  Wait for all move servers to respond and set playerIDs	
	    for(int i=0; i<numOfPlayers; i++){
	        playerIDs[i] = input[i].readLine();
            playerIDs[i] = playerIDs[i].substring(playerIDs[i].indexOf(' ') + 1);
	    }
        printData();

	    //  Send Player message to all players
        sendPlayerMessage();
        System.out.println();
        System.out.println("Sent PLAYERS message");

        //  Play round till there is a victor
        round = 0;
        while (!victor){
            round += 1;
            System.out.println();
            System.out.println("Round " + round + "----------------");
            playRound();
            //printData();
        }

    }

    //  Adds players info to lists
    public void SetUp(String[] players){
        //  Set numOfPlayers if valid
        if(players.length == 2){
            numOfPlayers = 2;
        }
        else if(players.length == 4){
            numOfPlayers = 4;
        }
        else{
            System.out.println("Invalid number of players.");
        }
	    //  Create board
	    //board = new Board(numOfPlayers);

        //  Create arrays of appropriate size
	    hostNames = new String[numOfPlayers];
        playerIDs = new String[numOfPlayers];
        playerPorts = new int[numOfPlayers];
        sockets = new Socket[numOfPlayers];
        input = new BufferedReader[numOfPlayers];
        output = new PrintWriter[numOfPlayers];

        // Split up input strings into appropriate lists
        for(int i = 0; i < players.length; i++) {
            hostNames[i] = players[i].substring(0, players[i].indexOf(":"));
            playerPorts[i] = Integer.parseInt(players[i].substring(players[i].indexOf(":")+1));

        }
    }
    //  Print info about players
    public void printData(){
        for(int i = 0; i < numOfPlayers; i++) {
            System.out.println("Player: " + Integer.toString(i + 1));
            System.out.println("HostName: " + hostNames[i]);
            System.out.println("PlayerID:  " + playerIDs[i]);
            System.out.println("PlayerPort: " + playerPorts[i]);
            System.out.println();
        }
    }
    //  Create sockets and input/outputs
    public void socketSetUp() throws Exception{
        for(int i = 0;i < numOfPlayers; i++){
            sockets[i] = new Socket(hostNames[i], playerPorts[i]);
            input[i] = new BufferedReader(new InputStreamReader(sockets[i].getInputStream()));
            output[i] = new PrintWriter(sockets[i].getOutputStream(), true);
        }
    }


    //  Sends initial PLAYERS message to signal start of game
    public void sendPlayerMessage(){
        String playersMessage = "PLAYERS ";
        for(int i = 0; i < numOfPlayers; i++){
                playersMessage += (playerIDs[i] + " ");
        }
        sendMessageToAll(playersMessage);
    }

    //  Sends given string to all players
    public void sendMessageToAll(String message){
        for(int i = 0; i < numOfPlayers; i++){
            if(playerIDs[i] != null) {
                output[i].println(message);
            }
        }
    }

    // goes through each player, asks for a move,
    public void playRound() throws Exception{
        // Cycle through each player
        for (int j = 0; j < numOfPlayers; j++){
        	//  Change To correct players turn
            int i = 0;
            if(numOfPlayers == 4)
        	    i = convertPlayerNum(j);
            else
                i = j;

        	if(playerIDs[i] != null) {
        		//  Send initial GO? message to ask for move
                System.out.println();
                System.out.println("Player turn: " + (i + 1));
                sendGoMessage(i);
                System.out.println("...Waiting for move...");

                //  Receive move from socket
        		String move = null;
        		while(move == null){
        			move = input[i].readLine();
        		}		
        		move = move.substring(move.indexOf(' ')+ 1);
                System.out.println("Received move: " + move);

                //  Check if legal and boot player if not
                System.out.println("...Checking legality...");
                if (!checkIfLegal(i, move)) {
        			sendBootMessage(i);
        			playerIDs[i] = null;
        		}else{
                    System.out.println("...Making move...");
                    // Send move to board
        			//board.makeMove(move, i);
                    System.out.println("...Sending WENT message...");
                    // Send Went message to all players
        			sendWentMessage(i, move);
        		}
                System.out.println("...Checking for victor...");
                //  Check for victor
        		if (checkIfVictor()) {
        			sendVictorMessage(i);
        		}
        	}
        }
        System.out.println("------Ending Round------");
    }

    //  Sends go? message to get the next player's move
    public void sendGoMessage(int playerOffset){
        output[playerOffset].println("GO?");

    }

    //  Sends move made to all move servers
    public void sendWentMessage(int playerOffset, String move){
        sendMessageToAll("WENT " + playerIDs[playerOffset] + " " + move);
    }

    // Checks if the move is legal and returns the value
    public boolean checkIfLegal(int playerOffset, String move){
        return true;
    }

    //  Sends BOOT message to all move servers if illegal move was made
    public void sendBootMessage(int playerOffset){
        sendMessageToAll("BOOT " + playerIDs[playerOffset]);
    }

    //  Checks if victor
    public boolean checkIfVictor(){
        return false;
    }

    //  Sends VICTOR message to all move servers
    public void sendVictorMessage(int playerOffset){
        sendMessageToAll("VICTOR " + playerIDs[playerOffset]);
    }
	
    //  Convert to correct players turn for 4 player game order
    public int convertPlayerNum(int i){
	switch(i){
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
}
