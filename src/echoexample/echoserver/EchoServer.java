package echoexample.echoserver;

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

                ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream());

                String read = (String) inFromClient.readObject(); // stuck here
                System.out.println("Received from client: " + read);
                String result = read.toUpperCase();

                outToClient.writeObject(result);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
