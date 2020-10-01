package multithreadedechoexample.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public void start() {
        System.out.println("Starting server...");
        try {
            ServerSocket serverSocket = new ServerSocket(2910);

            while(true) {
                Socket socket = serverSocket.accept(); // stuck here
                System.out.println("Client connected");

                ServerSocketHandler ssh = new ServerSocketHandler(socket);
                Thread t = new Thread(ssh);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
