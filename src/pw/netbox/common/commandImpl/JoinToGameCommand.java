package pw.netbox.common.commandImpl;

import pw.netbox.common.Command;
import pw.netbox.common.Player;
import pw.netbox.server.Game;

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
        //forTest
        System.out.println(games.get(roomNumber));
    }
}
