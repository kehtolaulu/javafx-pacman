package pacman.interfaces;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractObservable implements Observable {
    private List<Observer> observers = new LinkedList<>();

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
