package pacman.interfaces;

import javafx.scene.Node;

public interface Player extends Observer, Movable {
    Node asView();
    double getX();
    double getY();
    int getId();
    void setId(int id);
}
