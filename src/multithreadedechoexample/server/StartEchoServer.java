package multithreadedechoexample.server;

public class StartEchoServer {

    public static void main(String[] args) {
        EchoServer es = new EchoServer();
        es.start();
    }
}
