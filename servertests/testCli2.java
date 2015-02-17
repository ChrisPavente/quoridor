//Testing client

import java.util.*;
import java.net.*;
import java.io.*;

public class testCli2 {


    public static void main(String [] args) {
	
	//to be edited out later
	//Client socket
	Socket s = null;
	//server address
	String address = "localhost";
	//port num
	int port = 4099;
	try {

	    s = new Socket(address, port);
	
	} catch (Exception e) {
	    System.err.println(e);
	    System.exit(1);
	}

	//Creating server in/out communications
	Scanner in = null;
	PrintStream out = null;

	try {
	    in = new Scanner(s.getInputStream());
	    out = new PrintStream(s.getOutputStream()); 
	} catch (Exception e){
	}
	
	System.out.println(in.next());

	try{
	s.close();
	} catch (Exception e){
	}

	

    }

}
