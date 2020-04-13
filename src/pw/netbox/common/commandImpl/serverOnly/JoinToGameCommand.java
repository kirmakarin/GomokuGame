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
        necessaryGame.setPlayer2(player);

        Player player1 = necessaryGame.getPlayer1();
        Player player2 = necessaryGame.getPlayer2();

        necessaryGame.setGameStart(true);
        //set Room Number
        player1.sendMessage(new StartGameCommand(necessaryGame.getRoomNumber()));
        player2.sendMessage(new StartGameCommand(necessaryGame.getRoomNumber()));
        //Set game into game
        player1.setGame(necessaryGame);
        player2.setGame(necessaryGame);
        //Set init param
        player1.sendMessage(new SetInitialParamsCommand(FIRST_PLAYER_COLOR, true));
        player2.sendMessage(new SetInitialParamsCommand(SECOND_PLAYER_COLOR, false));

        if (!Server.needNewGame) {
            Server.needNewGame = true;
        }
    }
}
