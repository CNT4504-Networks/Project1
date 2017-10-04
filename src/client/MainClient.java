package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient {

	public static void main(String args[]) {
		// If no hostname provided in command line abort
		if (args.length != 2) {
			System.out.println(args.length);
			System.out.println("Invalid hostname! Aborting...");
			System.out.println("Usage: java project1.jar <host name> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);

		//Connect to the server
		System.out.println("Connecting to server...");
		try (Socket sock = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		) {

			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String serverMessage;
			String clientMessage;

			//Main program loop
			while (true) {
				// Get server message
				while (!(serverMessage = in.readLine()).equals("[END]")) {
					System.out.println(serverMessage);
				} // end while

				displayMenu();

				clientMessage = stdIn.readLine();
				if (clientMessage != null) {
					out.println(clientMessage);
					out.println("[END]");
					
					//If client message = 7 just quit
					if(clientMessage.equals("7")) {
						System.exit(0);
					}
				}//end if
			}//end main program loop

		} catch (UnknownHostException e) {
			System.err.println("Unable to connect to host at: " + hostName + ":" + portNumber);
			System.exit(1);
		} catch (IOException e) {
			System.err.print(e.getMessage());
			System.exit(1);
		} // end try/catch

		System.exit(0);
	}// end Main

	// Display the main menu
	private static void displayMenu() {
		System.out.println("Welcome! Please enter your choice from the menu below");
		System.out.println("1.   Host current Date and Time");
		System.out.println("2.   Host uptime");
		System.out.println("3.   Host memory use");
		System.out.println("4.   Host Netstat");
		System.out.println("5.   Host current users");
		System.out.println("6.   Host running processes");
		System.out.println("7.   Quit");
	}// end displayMenu
}// end MainClient
