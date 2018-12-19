package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Player implements Observer, Playable {
    private static final int STEP = 10;
    private Circle circle;
    public static final int RADIUS = 10;
    private static final Color color = Color.WHITE;
    private int id;

    public Player() {
        this.circle = new Circle(RADIUS, color);
    }


    public Player(double x, double y) {
        this();
        circle.setCenterX(x);
        circle.setCenterY(y);
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
}
