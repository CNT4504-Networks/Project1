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
    Thread newThread;

    for (int i = 0; i < numClients; i++) {
    System.out.println("New Thread");
      newThread = new Thread(new ClientThread());
      newThread.start();
    }
  }
}//end Class SimClient

class ClientThread implements Runnable {
  public void run() {
    System.out.println("New Thread");
    try {
      MainClient client = new MainClient();
      String arguments[] = {"192.168.100.107", "9000", "7", "4", "1"};
      client.main(arguments);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
