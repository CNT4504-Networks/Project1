package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RunSim {

	public static List<Long> times = Collections.synchronizedList(new ArrayList<Long>());
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// Need to do netstat & date command

		if (args.length != 1) {
			System.err.println("You must enter the number of simulations to run");
			System.err.println("Usage: java -jar simClient.jar <number of clients>");
			System.exit(1);
		}

		int numClients = Integer.parseInt(args[0]);

		Thread[] threads = new Thread[numClients];

		// Create the threads
		for (int i = 0; i < numClients; i++) {
			threads[i] = new Thread(new SimClient());
			System.out.println("Creating thread: " + i);
		}
		
		//Start and join the clients
		for (int i = 0; i < numClients; i++) {	
			threads[i].start();
			
			//times[i] = time;
			//System.out.println(time);
		}
		
		for (int i=0; i<numClients; i++) {
			threads[i].join();
		}
		
		long avgTime = 0;
		
		
		synchronized (times) {
			for (long num : times) {
				avgTime+=num;
			}
		}
		
		avgTime = avgTime / numClients;
		
		System.out.println("Average time for " + numClients + ": " + avgTime + "ms");
		
		System.out.print("Times: ");
		synchronized (times) {
			for (long num : times) {
				System.out.print(num + ", ");
			}
		}
		
	}//end main
}// end Class SimClient