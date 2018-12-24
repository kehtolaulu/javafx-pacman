package sample;

import interfaces.Observer;
import interfaces.Player;

public class ProtocolUnwrapper implements Observer {
    private Player player;

    public ProtocolUnwrapper(Player player) {
        this.player = player;
    }

    @Override
    public void onNext(String msg) {
        if (msg.startsWith(String.valueOf(player.getId() + ":"))) {
            String cmd = msg.substring(msg.indexOf(":") + 1);

            if (cmd.startsWith("move:")) {
                String[] args = cmd.substring(cmd.indexOf(":") + 1).split(":");
                player.move(
                        Integer.parseInt(args[0]),
                        Integer.parseInt(args[1])
                );
            } else {
                player.onNext(cmd);
            }
        }
    }
}
