package sample;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

public class ModelMaker implements Observer {
    private AnchorPane anchorPane;
    private Observable observable;

    public ModelMaker(AnchorPane anchorPane, Observable observable) {
        this.anchorPane = anchorPane;
        this.observable = observable;
    }

    @Override
    public void onNext(String msg) {
        if (isNew(msg)) {
            // 1:new:100:10[:pacman]
            String[] args = msg.split(":");
            Player player;

            if (msg.endsWith("pacman")) {
                player = new Pacman(
                        Integer.parseInt(args[2]),
                        Integer.parseInt(args[3])
                );
            } else {
                player = new Dot(
                        Integer.parseInt(args[2]),
                        Integer.parseInt(args[3])
                );
            }

            player.setId(Integer.parseInt(args[0]));
            Platform.runLater(() -> anchorPane.getChildren().add(player.asView())
            );
            this.observable.addObserver(new ProtocolUnwrapper(player));
        }
    }

    private static boolean isNew(String msg) {
        String[] split = msg.split(":");
        try {
            Integer.parseInt(split[0]);
        } catch (NumberFormatException e) {
            return false;
        }

        return split.length > 1 && "new".equals(split[1]);
    }
}
