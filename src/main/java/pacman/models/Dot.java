package pacman.models;

import pacman.interfaces.Observer;
import pacman.interfaces.Playable;
import pacman.interfaces.Player;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Dot implements Player, Observer, Playable {
    private static final int STEP = 10;
    private Circle circle;
    public static final int RADIUS = 10;
    private static final Color COLOR = Color.WHITE;
    private int id;
    private Bounds bounds;

    public Dot() {
        this.circle = new Circle(RADIUS, COLOR);
    }

    public Dot(double x, double y) {
        this();
        move(x, y);
    }

    public Dot(int id) {
        this();
        this.id = id;
        move(0, 0);
    }

    public Circle asView() {
        return circle;
    }

    @Override
    public void onNext(String msg) {
        KeyCode code = KeyCode.valueOf(msg);
        switch (code) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }
    }

    @Override
    public void moveUp() {

        moveVertically(-STEP);
    }

    @Override
    public void moveDown() {
        moveVertically(STEP);
    }

    private void moveVertically(int step) {
        circle.setCenterY(circle.getCenterY() + step);
    }

    @Override
    public void moveLeft() {
        moveHorizontally(-STEP);
    }

    @Override
    public void moveRight() {
        moveHorizontally(STEP);
    }

    private void moveHorizontally(int step) {
        circle.setCenterX(circle.getCenterX() + step);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void move(double x, double y) {
        circle.setCenterX(x);
        circle.setCenterY(y);
    }

    @Override
    public double getX() {
        return circle.getCenterX();
    }

    @Override
    public double getY() {
        return circle.getCenterY();
    }

    @Override
    public void setBounds(Bounds boundsInLocal) {
        this.bounds = boundsInLocal;
    }
}
