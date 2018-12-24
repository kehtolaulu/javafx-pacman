package sample;

import javafx.scene.Node;

public interface Player extends Observer, Playable {
    void move(double x, double y);
    Node asView();
    double getX();
    double getY();
}
