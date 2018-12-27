import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pacman.models.Dot;
import pacman.sample.ProtocolUnwrapper;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProtocolUnwrapperTest {
    private static ProtocolUnwrapper protocolUnwrapper;
    private static String s;

    @BeforeAll
    static void init() throws IOException {
        Dot dot = new Dot() {
            @Override
            public void onNext(String msg) {
                s = msg;
            }
        };
        dot.setId(1);
        protocolUnwrapper = new ProtocolUnwrapper(dot);
    }

    @Test
    void protocolUnwrapperShouldUnwrapMessages() {
        protocolUnwrapper.onNext("1:UP");
        assertEquals("UP", s);
    }
}
