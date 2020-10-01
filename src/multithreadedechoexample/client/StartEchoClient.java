package multithreadedechoexample.client;

public class StartEchoClient {

    public static void main(String[] args) {
        EchoClient ec = new EchoClient();
        ec.runClient();
    }
}
