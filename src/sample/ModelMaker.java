package sample;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class ModelMaker implements Observer {
    private AnchorPane anchorPane;
    private Observable observable;

    ModelMaker(AnchorPane anchorPane, Observable observable) {
        this.anchorPane = anchorPane;
        this.observable = observable;
    }

    @Override
    public void onNext(String msg) {
        if (!msg.startsWith("new")) {
            return;
        }

        String[] args = msg.split(":");


        Random random = new Random();

        Player player = new Player(
                random.nextInt((int) anchorPane.getWidth()),
                random.nextInt((int) anchorPane.getHeight())
        );
        player.setId(Integer.parseInt(args[1]));

        this.anchorPane.getChildren().add(player.asView());
        this.observable.addObserver(player);
    }
}
