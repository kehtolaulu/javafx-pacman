package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.io.FileInputStream;
import java.io.IOException;

public class Pacman implements Player, Playable, Observer {
    private static final int STEP = 10;
    private ImageView imageView;
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;
    private int dir;
    private int id;

    public Pacman() {
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
        this.id = id;
        move(0, 0);
    }

    public ImageView asView() {
        return imageView;
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

    private void moveHorizontally(int step) {
        imageView.setX(imageView.getX() + step);
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
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
        }
    }

    public int getId() { return id; }

    @Override
    public void setId(int id) {
        this.id = id;
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
}
