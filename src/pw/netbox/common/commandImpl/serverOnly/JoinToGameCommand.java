package pw.netbox.common.commandImpl.serverOnly;

import pw.netbox.common.Command;
import pw.netbox.common.Player;
import pw.netbox.common.commandImpl.StartGameCommand;
import pw.netbox.server.Game;
import pw.netbox.server.Server;

import java.util.List;

public class JoinToGameCommand extends Command {
    private int roomNumber;

    public JoinToGameCommand(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public void execute(Player player) {
        List<Game> games = Player.getGames();
        Game necessaryGame = games.get(roomNumber);
        necessaryGame.setPlayer2(player);
        necessaryGame.setGameStart(true);
        necessaryGame.getPlayer1().sendMessage(new StartGameCommand(necessaryGame.getRoomNumber()));
        necessaryGame.getPlayer2().sendMessage(new StartGameCommand(necessaryGame.getRoomNumber()));
        necessaryGame.getPlayer1().setGame(necessaryGame);
        necessaryGame.getPlayer2().setGame(necessaryGame);
        if (!Server.needNewGame) {
            Server.needNewGame = true;
        }
    }
}
