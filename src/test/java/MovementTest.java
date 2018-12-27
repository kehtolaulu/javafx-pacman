import javafx.scene.layout.AnchorPane;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pacman.models.Dot;
import pacman.server.Connection;

import java.io.IOException;
import java.net.Socket;

class MovementTest {
    private static Dot dot;

    @BeforeAll
    static void setUpObstacles() throws IOException {
        AnchorPane root = new AnchorPane();
        dot = new Dot();
        root.getChildren().add(dot.asView());
        dot.setRoot(root);
        dot.move(300, 300);
    }

    @Test
    void dotShouldMove() {
        double oldX = dot.getX();
        System.out.println(oldX);
        dot.onNext("RIGHT");
        System.out.println(dot.getX());
        dot.onNext("RIGHT");
        System.out.println(dot.getX());
        Assert.assertTrue(oldX < dot.getX());
    }

}
