import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pacman.server.Connection;
import pacman.server.Server;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ServerTest {
    private static Server server;
    private static Connection connection1;
    private static  Connection connection2;
    private static Socket socket;
    private static boolean idk = true;

    @BeforeAll
    static void setUpObstacles() throws IOException {
        server = new Server();
        socket = new Socket("localhost", 4567);
        connection1 = new Connection (1, socket, server) {
            @Override
            public void println(String string) {
                idk = false;
            }
        };
        connection2 = new Connection(2, socket, server);
    }

    @Test
    void serverShouldNotifyAboutNewConnection() throws IOException {
        server.connect(connection2);
        assertFalse(idk);
    }
}
