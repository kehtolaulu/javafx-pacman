package pacman.models;

import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import pacman.interfaces.Movable;
import pacman.interfaces.Observer;
import pacman.interfaces.Player;

import java.io.FileInputStream;
import java.io.IOException;

public class Pacman extends Model implements Player, Movable, Observer {
    private ImageView imageView;
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;
    private int dir;

    public Pacman() {
        super();
        this.dir = 1;
        try (FileInputStream fis = new FileInputStream("img/1.png")) {
            Image img = new Image(fis);
            imageView = new ImageView(img);
            imageView.setFitHeight(WIDTH);
            imageView.setFitWidth(HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pacman(double x, double y) {
        this();
        move(x, y);
    }

    public Pacman(int id) {
        this();
        setId(id);
    }

    public ImageView asView() {
        return imageView;
    }

    public void moveVertically(double step) {
        imageView.setY(imageView.getY() + step);
    }

    @Override
    public void moveLeft() {
        if (dir == 1) {
            dir = -1;
            imageView.setScaleX(dir);
        }
        moveHorizontally(-STEP);
    }

    @Override
    public void moveRight() {
        if (dir == -1) {
            dir = 1;
            imageView.setScaleX(dir);
        }
        moveHorizontally(STEP);
    }

    @Override
    public void moveHorizontally(double step) {
        imageView.setX(imageView.getX() + step);
    }

    @Override
    public void move(double x, double y) {
        imageView.setX(x);
        imageView.setY(y);
    }


    @Override
    public double getX() {
        return imageView.getX();
    }

    @Override
    public double getY() {
        return imageView.getY();
    }

    @Override
    public void onNext(String msg) {
        super.onNext(msg);

        Platform.runLater(
                () -> {
                    FilteredList<Node> dots = root.getChildren().filtered(c -> c instanceof Circle);

                    dots.filtered(c -> c instanceof Circle).forEach(
                            c -> {
                                if (c.intersects(imageView.getLayoutBounds())) {
                                    Platform.runLater(() -> root.getChildren().remove(c));
                                }
                            }
                    );

                });
    }
}
