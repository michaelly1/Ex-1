
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.*;
import java.io.*;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            // sends data to server from client
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os,true, "UTF-8");

            Scanner kb = new Scanner(System.in);

            System.out.print("Client > ");
            String ctmp = " ";

            while(ctmp != null)
            {
                ctmp = kb.nextLine();

                // if client exits, close inputstream/socket to server, server still stays online so no error.
                if(ctmp.toLowerCase().equals("exit"))
                {
                    is.close();
                    System.exit(0);
                }
                else
                {
                    ps.println(ctmp);
                    System.out.print("Server > " + br.readLine() + "\n");
                    System.out.print("Client > ");
                }
            }
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}















