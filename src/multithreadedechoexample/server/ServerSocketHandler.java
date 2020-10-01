package multithreadedechoexample.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable {

    private final Socket socket;

    public ServerSocketHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inFromClient = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                String read = (String) inFromClient.readObject(); // stuck here
                if(read.equalsIgnoreCase("exit")){
                    socket.close();
                    break;
                }

                System.out.println("Received from client: " + read);
                String result = read.toUpperCase();

                outToClient.writeObject(result);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
