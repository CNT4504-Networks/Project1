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

		try (ServerSocket serverSock = new ServerSocket(portNumber);
				Socket clientSock = serverSock.accept();
				PrintWriter out = new PrintWriter(clientSock.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
		) {
			String inputLine;
			
			//notify the client that we have connected
			out.println("Connection established.");
			
			//Main program loop
			while(true) {
				inputLine = in.readLine();
				out.println(processInput(inputLine));
			}
		} catch (IOException e) {
			System.out.println("Exception caught while trying to listen on port " + portNumber);
			System.out.println(e.getMessage());
		}

	}

	private static String processInput(String inputLine) {
		int selection = Integer.parseInt(inputLine);
		String outputLine = null;

		Process process;
		BufferedReader reader;

		try {
			switch (selection) {
			case 1:
				process = Runtime.getRuntime().exec("date");
				reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				outputLine = reader.readLine();
				break;
			case 2:
				process = Runtime.getRuntime().exec("uptime");
				reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				outputLine = reader.readLine();
				break;
			case 3:
				process = Runtime.getRuntime().exec("cat /proc/meminfo");
				reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				outputLine = reader.readLine();
				break;
			case 4:
				process = Runtime.getRuntime().exec("netstat");
				reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				outputLine = reader.readLine();
				break;
			case 5:
				process = Runtime.getRuntime().exec("w");
				reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				outputLine = reader.readLine();
				break;
			case 6:
				process = Runtime.getRuntime().exec("ps -A");
				reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				outputLine = reader.readLine();
				break;
			default:
				outputLine = "Invalid selection!";
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return outputLine;
	}

}
