package sample;

import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class ModelMaker implements Observer {
    private AnchorPane anchorPane;
    private Observable observable;

    public ModelMaker(AnchorPane anchorPane, Observable observable) {
        this.anchorPane = anchorPane;
        this.observable = observable;
    }

    @Override
    public void onNext(String msg) {
        if (msg.startsWith("new")) {
            String[] args = msg.split(":");

            Random random = new Random();

            Dot player = new Dot(
                    Integer.parseInt(args[2]),
                    Integer.parseInt(args[3])
            );
            player.setId(Integer.parseInt(args[1]));

            this.anchorPane.getChildren().add(player.asView());
            this.observable.addObserver(player);
        }
    }
}
