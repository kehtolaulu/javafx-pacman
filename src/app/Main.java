package app;

import interfaces.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Pacman;
import sample.*;

import java.io.IOException;

public class Main extends Application {
    private final static int SCREEN_WIDTH = 800;
    private final static int SCREEN_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane root = new AnchorPane();
        primaryStage.setTitle("Pac man the game. The best game of 2018");
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, new Color(0, 0, 0.7, 0.3));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        Connector connector = new Connector("localhost");

        Player pacman = new Pacman(connector.getId());
        root.getChildren().add(pacman.asView());

        Keyboard keyboard = new Keyboard(scene);
        keyboard.addObserver(pacman);
        primaryStage.show();

//        keyboard.addObserver(connector);
        ProtocolWrapper wrappedKeyboard = new ProtocolWrapper(keyboard, pacman);
        wrappedKeyboard.addObserver(connector);

        connector.addObserver(new ProtocolUnwrapper(pacman));

        ModelMaker modelMaker = new ModelMaker(root, connector);
        connector.addObserver(modelMaker);

        new Thread(connector).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
