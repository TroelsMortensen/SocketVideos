package chatexample.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(2910);
            ConnectionPool cp = new ConnectionPool();
            System.out.println("Server started..");

            while(true) {
                Socket socket = serverSocket.accept();

                ChatServerHandler csh = new ChatServerHandler(socket, cp);
                cp.addConnection(csh);

                Thread t = new Thread(csh);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
