/**
Display Client
 */

import java.io.*;
import java.net.Socket;

import board.Board;

//  Create display client
public class DisplayClient{
    public static void main(String[] args){
        //String[] testNames = {"Dog:1234", "Cat:2345", "Walrus:4321", "Monkey:9876"};
        
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

    public DisplayClient(String[] players) throws Exception{
	

        //  Set up player and sockets
        SetUp(players);
        socketSetUp();
	

        //  Wait for all move servers to respond and set playerIDs	
	for(int i=0; i<numOfPlayers; i++){
	    playerIDs[i] = input[i].readLine();
	    playerIDs[i] = playerIDs[i].substring(playerIDs[i].indexOf(' '));
	}

	//  Send Player message to all players
        sendPlayerMessage();

        //  Play round till there is a victor
        while (!victor){
            playRound();
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
	board = new Board(numOfPlayers);

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
            System.out.println(playerIDs[i] + playerPorts[i]);
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
        for (int i = 0; i < numOfPlayers; i++){
	    //  Change To correct players turn
	    i = convertPlayerNum(i);

            if(playerIDs[i] != null) {
                //  Send initial GO? message to ask for move
                sendGoMessage(i);

                //  Receive move from socket
		String move = null;
		while(move == null){
                    move = input[i].readLine();
		}	
		move = move.substring(move.indexOf(' '));

                //  Check if legal and boot player if not
                if (!checkIfLegal(i, move)) {
                    sendBootMessage(i);
                    playerIDs[i] = null;
                }else{

		    // Send move to board
		    board.makeMove(move, i);

                    // Send Went message to all players
                    sendWentMessage(i, move);
		}

                //  Check for victor
                if (checkIfVictor()) {
                    sendVictorMessage(i);
                }
            }
        }
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
	
    //  Convert player number to use with board class
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
