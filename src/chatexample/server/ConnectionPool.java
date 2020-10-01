package chatexample.server;

import chatexample.shared.Message;

import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private List<ChatServerHandler> conns = new ArrayList<>();

    public void addConnection(ChatServerHandler csh) {
        conns.add(csh);
    }

    public void broadcast(Message msg) {
        for (ChatServerHandler conn : conns) {
            if(!conn.getClientName().equals(msg.getUser())) {
                conn.sendMessageToClient(msg);
            }
        }
    }

    public void removeConnection(ChatServerHandler csh) {
        conns.remove(csh);
    }
}
