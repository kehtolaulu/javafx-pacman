package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }


    private final ServerSocket serverSocket;
    private final List<Connection> connections;
    private int playerCount = 1;

    public Server() {
        try {
            serverSocket = new ServerSocket(4567);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        connections = new LinkedList<>();
    }

    private int nextId() {
        playerCount += 1;
        return playerCount;
    }

    private void run() {
        while (true) {
            try (Socket accept = serverSocket.accept()) {
                Connection c = new Connection(nextId(), accept, this);
                this.connect(c);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private void connect(Connection c) {
        cast(c, "new:" + c.getId() + ":" + "100:100");
        connections.add(c);
        c.println("move:" + c.getId() + ":" + "100:100");
    }

    void cast(Connection source, String value) {
        for (Connection c : connections) {
            if (c != source) c.println(value);
        }
    }

    void destroy(Connection connection) {
        connections.remove(connection);
    }
}
