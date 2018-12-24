package sample;

import interfaces.AbstractObservable;
import interfaces.Observable;
import interfaces.Observer;
import interfaces.Player;

public class ProtocolWrapper extends AbstractObservable implements Observer, Observable {
    private Player p;

    public ProtocolWrapper(Observable keyboard, Player p) {
        keyboard.addObserver(this);
        this.p = p;
    }

    @Override
    public void onNext(String msg) {
        notifyObservers(String.format("%d:%s", p.getId(), msg));
    }
}
