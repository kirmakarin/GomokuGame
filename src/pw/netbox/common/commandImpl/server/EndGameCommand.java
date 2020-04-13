package pw.netbox.common.commandImpl.server;

import pw.netbox.common.Command;
import pw.netbox.common.Player;
import pw.netbox.common.commandImpl.client.ShowMessageCommand;
import pw.netbox.server.Game;

public class EndGameCommand extends Command {
    private String text;

    public EndGameCommand(String text) {
        this.text = text;
    }

    @Override
    public void execute(Player player) {
        Game game = player.getGame();
        if (game.getPlayer1().equals(player)) {
            Player player2 = game.getPlayer2();
            player2.sendMessage(new ShowMessageCommand(text));
        } else {
            Player player1 = game.getPlayer1();
            player1.sendMessage(new ShowMessageCommand(text));
        }

    }
}
