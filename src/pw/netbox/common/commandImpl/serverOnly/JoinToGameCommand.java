package pw.netbox.common.commandImpl.serverOnly;

import pw.netbox.common.Command;
import pw.netbox.common.Player;
import pw.netbox.common.commandImpl.clientOnly.SetInitialParamsCommand;
import pw.netbox.common.commandImpl.clientOnly.StartGameCommand;
import pw.netbox.server.Game;
import pw.netbox.server.Server;

import java.util.List;

import static pw.netbox.common.Constans.FIRST_PLAYER_COLOR;
import static pw.netbox.common.Constans.SECOND_PLAYER_COLOR;

public class JoinToGameCommand extends Command {
    private int roomNumber;

    public JoinToGameCommand(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public void execute(Player player) {
        List<Game> games = Player.getGames();
        Game necessaryGame = games.get(roomNumber);
        if (necessaryGame.getPlayer1() == null) {
            necessaryGame.setPlayer1(player);
            player.setGame(necessaryGame);

        } else {
            necessaryGame.setPlayer2(player);
            player.setGame(necessaryGame);

            necessaryGame.getPlayer1().sendMessage(new StartGameCommand(necessaryGame.getRoomNumber()));
            necessaryGame.getPlayer2().sendMessage(new StartGameCommand(necessaryGame.getRoomNumber()));

            necessaryGame.getPlayer1().sendMessage(new SetInitialParamsCommand(FIRST_PLAYER_COLOR, true));
            necessaryGame.getPlayer2().sendMessage(new SetInitialParamsCommand(SECOND_PLAYER_COLOR, false));
            necessaryGame.setGameStart(true);

            Server.needNewGame = true;
        }
    }
}
