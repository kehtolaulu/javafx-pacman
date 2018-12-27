import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pacman.models.Dot;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BoundsTest {

    private static Dot dot;
    private static Bounds bounds;

    @BeforeAll
    static void init() throws IOException {
        dot = new Dot();
        AnchorPane root = new AnchorPane();
        dot.setRoot(root);
        bounds = root.getLayoutBounds();
    }

    @Test
    void dotsShouldStayInBounds() {
        for (int i = (int) bounds.getMinY() - 100; i < bounds.getMaxY(); i++) {
            dot.onNext("UP");
            assertTrue(bounds.contains(dot.getX(), dot.getY()));
        }
    }
}
