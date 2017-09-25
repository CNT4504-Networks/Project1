package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient {

	public static void main(String args[]) {
		//If no hostname provided in command line abort
		if(args.length != 2) {
			System.out.println("Invalid hostname! Aborting...");
			System.out.println("Usage: java project1.jar <host name> <port number>");
			System.exit(1);
		}
		
		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		
		try (
				Socket sock = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));	
		){
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String serverMessage;
			String clientMessage;
			
			while((serverMessage = in.readLine()) != null) {
				System.out.println("Server: " + serverMessage);
				
				clientMessage = stdIn.readLine();
				if (clientMessage != null) {
					System.out.println("Client: " + clientMessage);
					out.println(clientMessage);
				}
			}
		} catch (UnknownHostException e) {
			System.err.println("Unable to connect to host at: " + hostName + ":" + portNumber);
			System.exit(1);
		} catch (IOException e) {
			System.exit(1);
		}
	}
}
