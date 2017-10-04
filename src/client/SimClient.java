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
		try {
			MainClient client = new MainClient();
			String arguments[] = {"192.168.100.107", "8080", "7", "4", "1"};
			client.main(arguments);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		
	}
}//end Class SimClient
