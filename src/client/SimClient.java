package client;

public class SimClient {

	public static void main(String[] args) {
		//Need to do netstat & date command
		if(args.length != 1) {
			System.err.println("You must enter the number of simulations to run");
			System.err.println("Usage: java -jar simClient.jar <number of clients>");
			System.exit(1);
		}
		int numClients = Integer.parseInt(args[0]);
		System.out.println(Thread.currentThread().getName());
	    for(int i=0; i<numClients; i++){
	      new Thread("" + i){
	        public void run(){
	          System.out.println("Thread: " + getName() + " running");
	          
	        }
	      }
	      .start();
	      }
	    
//		int numClients = Integer.parseInt(args[0]);
//		try {
//			MainClient client = new MainClient();
//			Thread clientThread = new Thread();
//			for (int i=0;i<numClients;i++){
//			clientThread.start();
//			System.out.println ("Thread " +Thread.currentThread().getId() +" is running");
//			
//			}
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		runClients();
//		
	}
	private static void runClients(){
		MainClient client = new MainClient();
		String arguments[] = {"192.168.100.107", "9000", "7", "4", "1"};
		MainClient.main(arguments);	
	}
}//end Class SimClient
