
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.io.*;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
            try (ServerSocket serverSocket = new ServerSocket(22222)) {
                while (true) {
                    Thread test = new Thread(new EServerMulti(serverSocket.accept()).mt);
                    test.start();
                    Thread.sleep(1000);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
    }
   public static class EServerMulti  {
        protected Socket socket;

        public EServerMulti(Socket s)
        {
            socket = s;
        }

        Runnable mt = () -> {
            try {
                    String address = socket.getInetAddress().getHostAddress();
                    System.out.printf("Client connected: %s%n", address);

                    InputStream is = socket.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                    BufferedReader br = new BufferedReader(isr);

                    OutputStream os = socket.getOutputStream();
                    PrintStream out = new PrintStream(os, true, "UTF-8");
                    // out.printf("Hi %s, thanks for connecting!%n", address);

                    String stmp = " ";

                    while ((stmp = br.readLine()) != null) {
                        out.println(stmp);
                    }

                    System.out.printf("Client disconnected: %s%n", address);

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
