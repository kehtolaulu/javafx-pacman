package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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

        Pacman pacman = new Pacman();
        root.getChildren().add(pacman.asView());

        Keyboard keyboard = new Keyboard(scene);
        keyboard.addObserver(pacman);
        primaryStage.show();

        Connector connector = new Connector("localhost");
        keyboard.addObserver(connector);
        connector.addObserver(pacman);

        ModelMaker modelMaker = new ModelMaker(root, connector);
        connector.addObserver(modelMaker);

        connector.run();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
