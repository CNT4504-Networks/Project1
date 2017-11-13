package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
	static String test;

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
				// Listen for client connection (can only accept 1 at a time)
				Socket clientSock = serverSock.accept();
				System.out.println("Accepting new client!");
				Thread thread = new Thread(new ServerThread(clientSock));
				thread.start();
			} // end main program loop
		} catch (IOException e) {
			System.out.println("Exception caught while trying to listen on port " + portNumber);
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}// end main
}// end Class MainServer
