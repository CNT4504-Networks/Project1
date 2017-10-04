package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

	public static void main(String[] args) {
		// Make sure we have included starting arguments
		if (args.length != 1) {
			System.err.println("No port provided. Aborting...");
			System.err.println("Usage: java projectServer <port number>");
			System.exit(1);
		}

		System.out.println("Starting server...");

		int portNumber = Integer.parseInt(args[0]);

		// Create the server
		try (ServerSocket serverSock = new ServerSocket(portNumber);) {
			// Main program loop
			while (true) {
				System.out.println("Waiting for client connection...");
				// Listen for client connection (can only accept 1 at a time)
				Socket clientSock = serverSock.accept();
				PrintWriter out = new PrintWriter(clientSock.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
				String inputLine;

				// notify the client that we have connected
				System.out.println("Client connected!");
				out.println("Connection established.");
				out.println("[END]");
				
				while (true) {
					// Process user input
					if (!(inputLine = in.readLine()).equals("[END]")) {
						System.out.println("Client selected option: " + inputLine);
						out.println(processInput(inputLine));
						out.println("[END]");
					}

					// Close all open connections
					if (inputLine.equals("7")) {
						System.out.println("Client done talking");
						out.close();
						in.close();
						clientSock.close();
						break;
					}//end if
				}//end client connection loop
			} // end main program loop
		} catch (IOException e) {
			System.out.println("Exception caught while trying to listen on port " + portNumber);
			System.out.println(e.getMessage());
		} // end try/catch
	}// end main

	// Select the command to run
	private static String processInput(String inputLine) {
		int selection = Integer.parseInt(inputLine);

		switch (selection) {
		case 1:
			return getOutput("date");
		case 2:
			return getOutput("uptime");
		case 3:
			return getOutput("cat /proc/meminfo");
		case 4:
			return getOutput("netstat");
		case 5:
			return getOutput("w");
		case 6:
			return getOutput("ps -A");
		case 7:
			return "Goodbye!";
		default:
			return "Invalid Selection!";
		}// end switch
	}// end processInput

	// Execute the command and get the output
	private static String getOutput(String command) {
		String output = null;
		String nextLine;
		Process process;
		try {
			process = Runtime.getRuntime().exec(command);

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			while ((nextLine = reader.readLine()) != null) {
				output = output + "\n" + nextLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} // end try/catch
		return output;
	}// end getInput
}// end Class MainServer
