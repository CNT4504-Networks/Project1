package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

	private Socket client;

	public ServerThread(Socket clientSock) {
		client = clientSock;
	}

	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String inputLine;

			// notify the client that we have connected
			//System.out.println("Client connected!");
			out.println("Connection established.");
			out.println("[END]");

			while (true) {
				if (in.ready()) {
					// Process user input
					inputLine = in.readLine();
					if (inputLine != null && !inputLine.equals("[END]")) {
						//System.out.println("Client selected option: " + inputLine);
						out.println(processInput(inputLine));
						out.println("[END]");
					}

					// Close all open connections
					if (inputLine.equals("7")) {
						System.out.println("Client done talking");
						out.close();
						in.close();

						client.close();

					} // end if
				} // end if
			} // end client connection loop
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Select the command to run
	private static String processInput(String inputLine) {
		switch (inputLine) {
		case "1":
			return getOutput("date");
		case "2":
			return getOutput("uptime");
		case "3":
			return getOutput("cat /proc/meminfo");
		case "4":
			return getOutput("netstat");
		case "5":
			return getOutput("w");
		case "6":
			return getOutput("ps -A");
		case "7":
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
}
