package sample;

import javafx.scene.Scene;

import java.util.LinkedList;
import java.util.List;

public class Keyboard implements Observable {
    private List<Observer> observers = new LinkedList<>();

    public Keyboard(Scene scene) {
        scene.setOnKeyPressed(event -> this.notifyObservers(event.getCode().toString()));
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
        observers.forEach(o -> o.onNext(msg));
    }
}
