package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Keyboard;
import sample.Pacman;
import sample.Player;

public class Client extends Application {
    private final static int SCREEN_WIDTH = 800;
    private final static int SCREEN_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Dot");
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, new Color(0, 0, 0.7, 0.3));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        Player p = new Player(300, 100);
        Pacman pacman = new Pacman();
        root.getChildren().add(p.asView());
        root.getChildren().add(pacman.asView());

        Keyboard keyboard = new Keyboard(scene);
        keyboard.addObserver(p);
        scene.setOnKeyPressed(event -> keyboard.notifyObservers(event.getCode().toString()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
