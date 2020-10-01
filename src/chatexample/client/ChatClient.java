package chatexample.client;

import chatexample.shared.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private ObjectInputStream in;

    public void startClient() {
        try {
            Socket socket = new Socket("localhost", 2910);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            Thread thread = new Thread(this::listenToServer);
            thread.setDaemon(true);
            thread.start();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert user name>");

            String userName = scanner.nextLine();
            out.writeObject(userName);

            while(true) {
                System.out.println("Input > ");
                String msg = scanner.nextLine();
                Message m = new Message(msg, userName);
                out.writeObject(m);

                if(msg.equalsIgnoreCase("exit")){
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenToServer() {
        try {
            while (true) {
                Message response = (Message) in.readObject();
                System.out.println(response);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
