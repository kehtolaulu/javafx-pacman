package pacman.interfaces;

import javafx.geometry.Bounds;
import javafx.scene.Node;

public interface Player extends Observer, Playable {
    void move(double x, double y);
    Node asView();
    double getX();
    double getY();

    void setBounds(Bounds boundsInLocal);
}
