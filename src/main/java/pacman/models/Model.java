package pacman.models;

import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import pacman.interfaces.Observer;
import pacman.interfaces.Player;

public abstract class Model implements Player, Observer {
    public static final int STEP = 10;
    private int id;
    protected AnchorPane root;

    @Override
    public void onNext(String msg) {
        KeyCode code = KeyCode.valueOf(msg);
        Bounds bounds = root.getBoundsInLocal();

        switch (code) {
            case UP:
                if (getY() > bounds.getMinY()) {
                    moveUp();
                }
                break;
            case DOWN:
                if (getY() < bounds.getMaxY() - 80) {
                    moveDown();
                }
                break;
            case RIGHT:
                if (getX() < bounds.getMaxX() - 80) {
                    moveRight();
                }
                break;
            case LEFT:
                if (getX() > bounds.getMinX()) {
                    moveLeft();
                }
                break;
        }

    }

    Model() {

    }

    public Model(int id) {
        this();
        this.id = id;
        move(0, 0);
    }

    public abstract void moveVertically(double step);

    public abstract void moveHorizontally(double step);

    @Override
    public void moveUp() {
        moveVertically(-STEP);
    }

    @Override
    public void moveDown() {
        moveVertically(STEP);
    }

    @Override
    public void moveLeft() {
        moveHorizontally(-STEP);
    }

    @Override
    public void moveRight() {
        moveHorizontally(STEP);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public AnchorPane getRoot() {
        return root;
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }
}
