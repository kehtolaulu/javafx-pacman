import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pacman.sample.Connector;
import pacman.sample.ModelMaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ModelMakerTest extends Application {

    private AnchorPane root;
    private ModelMaker modelMaker;

//    @BeforeAll
//    static void setUpObstacles() throws IOException {
//        root = new AnchorPane();
//        Connector connector = new Connector("localhost");
//        modelMaker = new ModelMaker(root, connector);
//
//    }

    @Test
    void childrenShouldIncrease() {
        int size = root.getChildren().size();
        modelMaker.onNext("1:new:100:10");
        assertEquals(root.getChildren().size(), size + 1);
    }

    @Test
    void pacmanShouldBeTheFirst() {
        modelMaker.onNext("1:new:100:10");
        assertTrue(root.getChildren().get(0) instanceof ImageView);
    }

    @BeforeAll
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new AnchorPane();
        Connector connector = new Connector("localhost");
        modelMaker = new ModelMaker(root, connector);
    }

    @BeforeAll
    public static void main(String[] args) {
        launch(args);
    }
}
