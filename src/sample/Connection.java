package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {
    private final Server server;
    private final Socket socket;
    private final PrintWriter writer;
    private final int id;

    Connection(int id, Socket socket, Server server) throws IOException {
        this.id = id;

        this.server = server;
        this.socket = socket;
        writer = new PrintWriter(socket.getOutputStream());
        new Thread(this::run).start();
    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String s = reader.readLine();
            while (s != null) {
                server.cast(this, s);
                s = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.destroy(this);
    }

    int getId() {
        return id;
    }

    public void println(String string) {
        writer.println(string);
        writer.flush();
    }
}
