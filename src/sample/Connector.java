package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Connector implements Observable, Observer {
    private final PrintWriter writer;
    private final Socket socket;
    private final List<Observer> observers;

    public Connector(String host) throws IOException {
        socket = new Socket(host, 4567);
        writer = new PrintWriter(socket.getOutputStream());
        observers = new LinkedList<>();
    }

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = br.readLine();
            while (s != null) {
                notifyObservers(s);
                s = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
        for (Observer o : observers) {
            o.onNext(msg);
        }
    }

    @Override
    public void onNext(String msg) {
        writer.println(msg);
        writer.flush();
    }
}
