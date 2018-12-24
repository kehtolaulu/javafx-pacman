package server;

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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    private double x;
    private double y;

    Connection(int id, Socket socket, Server server) throws IOException {
        this.id = id;
        this.server = server;
        this.socket = socket;
        writer = new PrintWriter(socket.getOutputStream());

        println(String.valueOf(id));

        new Thread(this::run).start();
    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String s = reader.readLine();
            while (s != null) {
                if (s.startsWith(String.valueOf(this.id + ":"))) {
                    String cmd = s.substring(s.indexOf(":") + 1);

                    //1:right

                    switch (cmd) {
                        case "RIGHT":
                            this.x += 10;
                            break;
                        case "LEFT":
                            this.x -= 10;
                            break;
                        case "UP":
                            this.y -= 10;
                            break;
                        case "DOWN":
                            this.y += 10;
                            break;
                    }
                }

                server.cast(this, s);
                if (s.startsWith("state")) {
                    println(s);
                }
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

    public String getState() {
        return "state:" + this.id + ":" + getX() + ":" + getY();
    }
}
