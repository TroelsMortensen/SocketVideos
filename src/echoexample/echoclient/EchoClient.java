package echoexample.echoclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    public void runClient() {
        // connect to server
        try {
            Socket socket = new Socket("localhost", 2910); // 127.0.0.1
            System.out.println("Connected");
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

            // send message to server
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please type message >");

            String s = scanner.nextLine();
            outToServer.writeObject(s);

            // read result from server

            String result = (String) inFromServer.readObject();
            System.out.println("From server: " + result);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
