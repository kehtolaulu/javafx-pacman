package pacman.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pacman.models.Dot;
import pacman.sample.*;

import java.io.IOException;

public class Client extends Application {
    private final static int SCREEN_WIDTH = 800;
    private final static int SCREEN_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane root = new AnchorPane();
        primaryStage.setTitle("Dot");
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, new Color(0, 0, 0.7, 0.3));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        Connector connector = new Connector("localhost");

        Dot p = new Dot(connector.getId());
        p.setRoot(root);

        root.getChildren().add(p.asView());

        Keyboard keyboard = new Keyboard(scene);
        keyboard.addObserver(p);
        primaryStage.show();

//        keyboard.addObserver(connector);
        ProtocolWrapper wrappedKeyboard = new ProtocolWrapper(keyboard, p);
        wrappedKeyboard.addObserver(connector);

        connector.addObserver(new ProtocolUnwrapper(p));

        ModelMaker modelMaker = new ModelMaker(root, connector);
        connector.addObserver(modelMaker);

        new Thread(connector).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
