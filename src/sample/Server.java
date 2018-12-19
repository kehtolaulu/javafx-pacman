package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server implements Observable {
    List<Observer> observers;

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String msg) {
        observers.forEach(o -> o.onNext(msg));
    }

    private final ServerSocket serverSocket;
    private final List<Connection> connections;
    private int playerCount = 1;

    private Server() {
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
        cast(c, "new:" + c.getId());
        connections.add(c);
    }

    void cast(Connection source, String value) {
        for (Connection c : connections) {
            if (c == source) continue;
            c.println(value);
        }
    }

    void destroy(Connection connection) {
        connections.remove(connection);
    }
}
