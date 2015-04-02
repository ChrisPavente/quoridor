//Test Server
import java.io.*;
import java.util.*;
import java.net.*;
import board.Board;



class moveServer extends Thread {
    

    //Server port
    private static int defaultPort;
    //server will use this socket
    private Socket s = null;
    private static String serverName;
    private Scanner parser;
    private Board locBoard;
    

    public testServ(Socket s) {
    	this.s = s;
    }


    //port number as input
    //opens the server and multi threads the server
    public static void startServ(int serverPort) throws Exception {
    	ServerSocket svr = new ServerSocket(serverPort);
    	while(true) {
    		System.out.println("Starting new server");
            System.out.println();
            Socket s = svr.accept();
    		new testServ(s).start();
    	}

    }


    //input: client socket
    //Will run the main game loop and message passing

    public void run() {
    	boolean victor = false;


	
	try {
		
	    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	    PrintStream out = new PrintStream(s.getOutputStream());
	    
	    //Sends the servers AI identifier
	    out.println("MOVE-SERVER "+ serverName);
	    System.out.println("Sending name");
	    //Receives the players to signal the start of the game
	    parser = new Scanner(in.readLine());
	    String playerList[] = new String[2];
	    parser.next(); // removing the "Playerlist" string
	    for (int i = 0; i < 2; i++) {
	    	playerList[i] = parser.next();
	    		
	    }
	    locBoard = new Board(playerList.length);
	    	
	    
	    
	    //System.out.println("Retrieved playerlist" + playerList);
	    
	    //Game loop
		do {
            System.out.println("...Waiting for turn...");
            System.out.println();
            parser = new Scanner(in.readLine());
			String message = parser.next();
			//System.out.println(message);
			switch (message) {
				case "GO?": 
					System.out.println("GO?");
					/*Place the GO method here
					 * this method will calculate a move and send back
					 * out.println("GO" + board.makeMove());
					*/
					String move = go();
					out.println("GO " + move);
					
					break;
				case "WENT":
                    String player = parser.next();
                    String moveString = parser.next();
                    //locBoard.makeMove(moveString, findPlayerId(player, playerList));
                    System.out.println("WENT " + player + " " + moveString);
						
					
					
					/*WENT method here
					 * INPUT: WENT <player-id> <move-string>
					 * Will be recieved each turn by every player
					 * will update the board based upon a players move
					 */
					break;
				case "BOOT": 
					System.out.println("BOOT");
					/* BOOT method here
					 * INPUT BOOT <player-id>
					 * removes the booted players pawns from the game board
					 * if this instance is the booted player, break out, s.close();
					 */
					break;
				case "VICTOR": 
					System.out.println("VICTOR");
					victor = true;
					/*VICTOR method
					 * input: VICTOR <player-id>
					 * the game is over and <player> has won
					 */
					break;
					
				default:
					System.out.println("Unknown Case");
					//s.close();
					break;
			}
            System.out.println();

			
		} while (!victor);
		
		//s.close();

	}	catch (Exception e) {
	    System.err.println(e);
	    System.exit(1);
	}

    }

    
    
    public static void main (String [] args) throws Exception {
    	
    	if(args.length > 0) {
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

    public String go() {
    	System.out.print("Enter a move: ");
    	parser = new Scanner(System.in);
    	return parser.next();
    	
    }


    private int findPlayerId(String player, String[] playerList){
        for(int i = 0; i< playerList.length; i++)
            if (playerList[i] == player)
                return i;
        return 0;
    }


}