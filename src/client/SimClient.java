package client;

import java.io.IOException;

public class SimClient {

  public static void main(String[] args) throws IOException {
    //Need to do netstat & date command
	  
    if(args.length != 1) {
      System.err.println("You must enter the number of simulations to run");
      System.err.println("Usage: java -jar simClient.jar <number of clients>");
      System.exit(1);
    }

    int numClients = Integer.parseInt(args[0]);
    Thread newThread;
    System.out.println("New Thread");
    for (int i = 0; i < numClients; i++) {  	
      newThread = new Thread(new ClientThread());
      newThread.start();   
    }    
   }
}//end Class SimClient

class ClientThread implements Runnable {
  public void run() {
    System.out.println("New Thread");
    try {
      long startTime =System.currentTimeMillis();
      MainClient client = new MainClient();
      //Change array arguments to meet test requirements then create jar file
      //example {"192.168.100.107", "9000", "7", "1"};   will just execute 1(date/time) and 7 exit
      //{"host name of server","port server is on", "args in descending order to execute"
      String arguments[] = {"192.168.100.107", "9000", "7", "4", "1"};
      client.main(arguments);
      long estimatedTime = System.currentTimeMillis() - startTime;
      System.out.println(estimatedTime + " Milliseconds to complete");
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
