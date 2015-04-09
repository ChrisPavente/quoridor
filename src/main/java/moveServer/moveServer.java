//Test Server
import java.io.*;
import java.util.*;
import java.net.*;
import board.Board;



class moveServer extends Thread {
    

    //Server port
    private static int defaultPort;
    //Server will use this socket
    private Socket s = null;
    private static String serverName;
    private Scanner parser;
    private Board locBoard;
    private String [] playerList;
    

    public moveServer(Socket s) {
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
    		new moveServer(s).start();
    	}

    }


    

    /* 
     * Runs the main game loop for the server,
     * handles all the network protocols.
     */
    public void run() {
    	boolean victor = false;


	try {
		//Creates the Input/Output Streams for server/client communication
	    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	    PrintStream out = new PrintStream(s.getOutputStream());
	    //Sends the servers AI identifier
	    out.println("MOVE-SERVER "+ serverName);
	    System.out.println("Sending player name..");
	    //Receives the players to signal the start of the game
	    parser = new Scanner(in.readLine());
	    playerList = genPList(parser);
	    locBoard = new Board(playerList.length);
	    	
	    
	    
	    
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
						
					/*WENT 
					 * INPUT: WENT <player-id> <move-string>
					 * Will be recieved each turn by every player
					 * will update the board based upon a players move
					 */
					break;
				case "BOOT": 
					System.out.println("BOOT");
					//parser.next();
					//Call the board's player remove
					//If it is this player being removed, s.close();
					/* BOOT method here
					 * INPUT BOOT <player-id>
					 * removes the booted players pawns from the game board
					 * if this instance is the booted player, break out, s.close();
					 */
					break;
				case "VICTOR": 
					System.out.println("VICTOR");
					
					//Match the victor's name with this server's name
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
    		defaultPort = Integer.parseInt(args[1]);
    		serverName = args[0];
    	} else
    		throw new Exception("Incorrect syntax. Requires the player ID, then port number");
    	
    	try {
    		startServ(defaultPort);
    	} catch (Exception e) {
    		System.err.println(e);
    		System.exit(2);
    	}
    }
    
    /**
     * Currently prompts the user for a input on the board
     * 
     * @return - A string input matches a move to be played.
     */
    public String go() {
    	System.out.print(serverName + ", please enter a move: ");
    	parser = new Scanner(System.in);
    	return parser.next();
    	
    }

    
    
    /**
     * Finds and matches the player ID in order to correctly update player moves.
     * 
     * @param player
     * @param playerList
     * @return If 0 it matches this instance of the server if i it is player i's turn.
     */
    private int findPlayerId(String player, String[] playerList){
        for(int i = 0; i< playerList.length; i++)
            if (playerList[i] == player)
                return i;
        return 0;
    }
    
   
    
    /**
     *  Generates the playerList array with a variable amount of players,
     *  uses a temporary array with a size of 4 to take into account a 4 player game.
     *  
     * @param parser - the string parser which contains the playlist string from the client
     * @return An array containing the players
     */
    private String[] genPList(Scanner parser) {
    	parser.next(); // removing the "Playerlist" string
	    String tmp[] = new String[4];
	    int i = 0;
	    
	    while (parser.hasNext()){
	    	tmp[i] = parser.next();
	    	i++;
	    }
	    
	    String pList [] = new String[i];
	    for (int j = 0; j < i; j++) {
	    	pList[j] = tmp[j];
	    }
	    
	    return pList;
	    
    }


}
