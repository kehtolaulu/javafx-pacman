package pacman.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }

    private final ServerSocket serverSocket;
    private final List<Connection> connections;
    private int playerCount = 0;

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
            try {
                Socket accept = serverSocket.accept();
                Connection c = new Connection(nextId(), accept, this);
                this.connect(c);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void connect(Connection c) {
        Random random = new Random();
        int x = random.nextInt(400);
        int y = random.nextInt(400);
        c.setX(x);
        c.setY(y);
        cast(c, c.getId() + ":new:" + x + ":" + y);
        connections.forEach(
                connection -> {
                    System.out.println(connection.getState());
                    c.println(connection.getState());
                }
        );
        connections.add(c);
        c.println(c.getId() + ":move:" + x + ":" + y);
    }

    void cast(Connection source, String value) {
        System.out.println(value);
        for (Connection c : connections) {
            if (c != source) c.println(value);
        }
    }

    void destroy(Connection connection) {
        connections.remove(connection);
    }
}
