package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimClient implements Runnable {
	@Override
	public void run() {
		// Connect to the server and wait
		int port = 9800;
		String ip = "192.168.100.107";

		Socket sock;
		try {
			sock = new Socket(ip, port);
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			String serverMessage;
			
			long startTime = System.currentTimeMillis();

			// Dump the server hello
			while (!(serverMessage = in.readLine()).equals("[END]")) {	}
			
			
			out.println("4");
			out.println("[END]");
			while (!(serverMessage = in.readLine()).equals("[END]")) {	
				//System.out.println(serverMessage);
			}
			long stopTime = System.currentTimeMillis();
			
			out.println("7");
			RunSim.times.add(stopTime - startTime);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
