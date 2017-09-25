package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("No port provided. Aborting...");
			System.err.println("Usage: java projectServer <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);
		
		try (
			ServerSocket serverSock = new ServerSocket(portNumber);
			Socket clientSock = serverSock.accept();
			PrintWriter out = new PrintWriter(clientSock.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
		) {
			String inputLine, outputLine;
			
			
		} catch (IOException e) {
			System.out.println("Exception caught while trying to listen on port " + portNumber);
			System.out.println(e.getMessage());
		}
		
	}

}
