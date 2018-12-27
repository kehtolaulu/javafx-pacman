import javafx.scene.layout.AnchorPane;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pacman.models.Dot;

import java.io.IOException;

class MovementTest {
    private static Dot dot;
    private static AnchorPane root;

    @BeforeAll
    static void setUpObstacles() throws IOException {
        root = new AnchorPane();
        dot = new Dot();
        root.getChildren().add(dot.asView());
        dot.setRoot(root);
        dot.move(300, 300);
    }

    @Test
    void dotShouldMove() {
        double oldX = dot.getX();
        dot.onNext("LEFT");
        Assert.assertTrue(oldX > dot.getX());
    }

}
