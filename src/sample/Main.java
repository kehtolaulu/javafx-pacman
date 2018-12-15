package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private final static int SCREEN_WIDTH = 800;
    private final static int SCREEN_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, new Color(0, 0, 1, 1));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        Player p = new Player(10, 10);

        root.getChildren().add(p.asView());

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
