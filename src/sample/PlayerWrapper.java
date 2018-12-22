package sample;

public class PlayerWrapper implements Observer {
    Player player;

    public PlayerWrapper(Player player) {
        this.player = player;
    }

    @Override
    public void onNext(String msg) {
        if (msg.startsWith(String.valueOf(player.getId() + ":"))) {
            String substring = msg.substring(msg.indexOf(":"));
            player.onNext(substring);
        }
    }
}
