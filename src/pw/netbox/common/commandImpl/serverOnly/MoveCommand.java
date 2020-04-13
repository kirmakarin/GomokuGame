package pw.netbox.common.commandImpl.serverOnly;

import pw.netbox.common.Command;
import pw.netbox.common.Player;
import pw.netbox.common.commandImpl.clientOnly.YourTurnCommand;
import pw.netbox.server.Game;

import static pw.netbox.common.Constans.FIRST_PLAYER_COLOR;
import static pw.netbox.common.Constans.SECOND_PLAYER_COLOR;

public class MoveCommand extends Command {
    private int x;
    private int y;

    public MoveCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute(Player player) {
        Game game = player.getGame();
        if (game.getPlayer1().equals(player)) {
            Player player2 = game.getPlayer2();
            player2.sendMessage(new YourTurnCommand(FIRST_PLAYER_COLOR, x, y));
        } else {
            Player player1 = game.getPlayer1();
            player1.sendMessage(new YourTurnCommand(SECOND_PLAYER_COLOR, x, y));
        }
    }
}
