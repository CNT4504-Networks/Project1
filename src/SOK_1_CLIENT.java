import java.io.*;
import java.net.*;

public class SOK_1_CLIENT
{
//---------------------------------------------------------
	public static void main(String[] args) throws Exception
	{
		SOK_1_CLIENT CLIENT = new SOK_1_CLIENT();
		CLIENT.run();
	}//end main
//----------------------------------------------------------
	public void run() throws Exception
	{
		//change localhost to server ip if running in real server/client relationship
		Socket SOCK = new Socket("localhost",444);
		PrintStream PS = new PrintStream(SOCK.getOutputStream());
		PS.println("Hello to SERVER From CLIENT");
		
		InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
		BufferedReader BR = new BufferedReader(IR);
		
		String MESSAGE = BR.readLine();
		System.out.println(MESSAGE);
	}//end run method

}//end class
