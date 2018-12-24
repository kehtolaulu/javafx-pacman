package sample;

import interfaces.Observable;
import interfaces.Observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Connector implements Observable, Observer, Runnable {
    private final PrintWriter writer;
    private final BufferedReader reader;
    private final Socket socket;
    private final List<Observer> observers;

    private final int id;

    public Connector(String host) throws IOException {
        socket = new Socket(host, 4567);
        writer = new PrintWriter(socket.getOutputStream());
        observers = Collections.synchronizedList(new LinkedList<>());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        id = Integer.parseInt(reader.readLine());
    }

    @Override
    public void run() {
        try {
            String s = reader.readLine();
            while (s != null) {
                notifyObservers(s);
                s = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addObserver(Observer o) {
        synchronized (observers) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String msg) {
        Observer[] observersArray = new Observer[observers.size()];
        observersArray = observers.toArray(observersArray);
        for (Observer o : observersArray) {
            o.onNext(msg);
        }
//            observers.forEach(o -> o.onNext(msg));
    }

    @Override
    public void onNext(String msg) {
        writer.println(msg);
        writer.flush();
    }

    public int getId() {
        return id;
    }
}
