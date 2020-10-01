package chatexample.server;

import chatexample.shared.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatServerHandler implements Runnable {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ConnectionPool pool;
    private String userName;

    public ChatServerHandler(Socket socket, ConnectionPool pool) {
        this.socket = socket;
        this.pool = pool;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            userName = (String) in.readObject();

            while(true) {
                Message message = (Message) in.readObject();

                String body = message.getMessageBody();
                System.out.println(message);
                if(body.equalsIgnoreCase("exit")) {
                    pool.removeConnection(this);
                    socket.close();
                    break;
                }
                pool.broadcast(message);
            }
        } catch(Exception e) {
           e.printStackTrace();
        }
    }

    public void sendMessageToClient(Message msg) {
        try {
            out.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClientName() {
        return userName;
    }
}
