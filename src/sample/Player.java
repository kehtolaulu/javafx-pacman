package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Player {
    private Circle circle;
    public static final int RADIUS = 10;
    public  static final int DIAMETER = 20;
    private static final Color color = Color.WHITE;

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
}
