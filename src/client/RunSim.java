package client;

import java.io.IOException;

public class RunSim {

	public static long time;
	
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

		long times[] = new long[numClients];
		
		//Start and join the clients
		for (int i = 0; i < numClients; i++) {
			
			threads[i].start();
			threads[i].join();
			times[i] = time;
			System.out.println(time);
		}
		
		long avgTime = 0;
		for (long num : times) {
			avgTime+=num;
		}
		
		avgTime = avgTime / numClients;
		
		System.out.println("Average time for " + numClients + ": " + avgTime + "ms");
	}//end main
}// end Class SimClient