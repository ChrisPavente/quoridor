package moveServer;
//Test Server

import java.io.*;
import java.util.*;
import java.net.*;

import board.GameEngine;

import gui.QBoard;


class moveServer extends Thread {


    //Server port
    private static int defaultPort;
    //server will use this socket
    private Socket s = null;
    private static String serverName;
    private Scanner parser;
    private String[] playerList;

    private GameEngine gameEngine;

    public moveServer(Socket s) {
        this.s = s;
    }


    //port number as input
    //opens the server and multi threads the server
    public static void startServ(int serverPort) throws Exception {
        ServerSocket svr = new ServerSocket(serverPort);
        while (true) {
            System.out.println("Starting new server");
            System.out.println();
            Socket s = svr.accept();
            new moveServer(s).start();
        }

    }


    //input: client socket
    //Will run the main game loop and message passing

    public void run() {
        boolean victor = false;


        try {
            //Creates the Input/Output Streams for server/client communication
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream out = new PrintStream(s.getOutputStream());
            //Sends the servers AI identifier
            out.println("MOVE-SERVER " + serverName);
            System.out.println("Sending player name..");
            //Receives the players to signal the start of the game
            parser = new Scanner(in.readLine());
            playerList = genPList(parser);
            gameEngine = new QBoard(playerList.length, findPlayerId(serverName, playerList));


            //Game loop
            do {
                System.out.println("...Waiting for turn...");
                System.out.println();
                parser = new Scanner(in.readLine());
                String message = parser.next();
                //System.out.println(message);

                if (message.equals("GO?")) {
                    System.out.println("GO?");
                    /*Place the GO method here
					 * this method will calculate a move and send back
					 * out.println("GO" + board.makeMove());
					*/
                    
                    gameEngine.setTurn();
                    //gameEngine.setCurrentMoveToNull(); done in QBoard now so we can use an interface
                    String move = null;
                    while (gameEngine.getMove() == null){
                    	Thread.sleep(200);
                    }
                    move = gameEngine.getMove();
                    out.println("GO " + move);
                    //gameEngine.setTurn();


                }
                else if(message.equals("WENT")) {
                    String player = parser.next();
                    String moveString = parser.next();
                    //locBoard.makeMove(moveString, findPlayerId(player, playerList));
                    System.out.println(player + " WENT " + moveString);
                    System.out.println();

                    gameEngine.makeMove(moveString, findPlayerId(player, playerList));
					
					/*WENT method here
					 * INPUT: WENT <player-id> <move-string>
					 * Will be recieved each turn by every player
					 * will update the board based upon a players move
					 */

                }
                else if(message.equals("BOOT")) {
                    System.out.println("BOOT");
                    String player = parser.next();
                    if(player.equals(serverName)){
                    	//END!!!
                    }
                    gameEngine.bootPlayer(findPlayerId(player,playerList));
					/* BOOT method here
					 * INPUT BOOT <player-id>
					 * removes the booted players pawns from the game board
					 * if this instance is the booted player, break out, s.close();
					 */

                }
                else if(message.equals("VICTOR")) {
                    System.out.println("VICTOR");
                    String player = parser.next();
                    victor = true;
                    if(player.equals(serverName)){
                    	//WE WON!
                    }
					/*VICTOR method
					 * input: VICTOR <player-id>
					 * the game is over and <player> has won
					 */

                }

                else {
                    System.out.println("Unknown Case");
                    //s.close();
                    break;
                }
                //System.out.println();


            } while (!victor);

            //s.close();

        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }

    }


    public static void main(String[] args) throws Exception {

        if (args.length > 0) {
            defaultPort = Integer.parseInt(args[0]);
            serverName = args[1];
        } else
            throw new Exception("Incorrect syntax. Requires port number, then player id");

        try {
            startServ(defaultPort);
        } catch (Exception e) {
            System.err.println(e);
            System.exit(2);
        }
    }


    private int findPlayerId(String player, String[] playerList) {
        for (int i = 0; i < playerList.length; i++)
            if (playerList[i].equals(player))
                return i;
        return 0;
    }


    /* Generates the playerList array with a now variable amount of players,
     * uses a temporary array with a size of 4 to take into account a 4 player game
	 * Input: Takes the parser which contains the playerlist received from the client
    */
    private String[] genPList(Scanner parser) {
        parser.next(); // removing the "Playerlist" string

        String tmp[] = new String[4];

        int i = 0;
        while (parser.hasNext()) {
            tmp[i] = parser.next();
            i++;
        }
        String pList[] = new String[i];
        for (int j = 0; j < i; j++) {
            pList[j] = tmp[j];
        }

        return pList;

    }


}