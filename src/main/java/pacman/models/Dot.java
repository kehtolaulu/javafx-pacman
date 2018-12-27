package pacman.models;

import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;
import pacman.interfaces.Observer;
import pacman.interfaces.Movable;
import pacman.interfaces.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Dot extends Model implements Player, Observer, Movable {
    private Circle circle;
    public static final int RADIUS = 10;
    private static final Color COLOR = Color.WHITE;

    public Dot() {
        super();
        this.circle = new Circle(RADIUS, COLOR);
    }

    public Dot(double x, double y) {
        this();
        move(x, y);
    }

    public Dot(int id) {
        this();
        setId(id);
    }


    public Circle asView() {
        return circle;
    }


    @Override
    public void moveVertically(double step) {
        circle.setCenterY(circle.getCenterY() + step);
    }

    @Override
    public void moveHorizontally(double step) {
        circle.setCenterX(circle.getCenterX() + step);
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
    public void onNext(String msg) {
        KeyCode code = KeyCode.valueOf(msg);
        Bounds bounds = root.getBoundsInLocal();

        switch (code) {
            case UP:
                if (getY() > bounds.getMinY() + 20) {
                    moveUp();
                }
                break;
            case DOWN:
                if (getY() < bounds.getMaxY() - 20) {
                    moveDown();
                }
                break;
            case RIGHT:
                if (getX() < bounds.getMaxX() - 20) {
                    moveRight();
                }
                break;
            case LEFT:
                if (getX() > bounds.getMinX() + 20) {
                    moveLeft();
                }
                break;
        }
    }
}
