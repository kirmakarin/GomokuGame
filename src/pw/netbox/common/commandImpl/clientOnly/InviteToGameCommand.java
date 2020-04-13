package pw.netbox.common.commandImpl.clientOnly;

import pw.netbox.common.commandImpl.StandardOutputCommand;
import pw.netbox.server.Game;

import java.util.List;

public class InviteToGameCommand extends StandardOutputCommand {
    List<Game> games;

    public InviteToGameCommand(List<Game> games) {
        super("");
        this.games = games;
        StringBuilder strToText = new StringBuilder();
        strToText.append("Server: U can join to next games: \n");
        for (Game game : games) {
            if (!game.isGameStart())
                strToText.append(game.getRoomNumber()).append("\n");
        }
        strToText.append("For choose type Room Number \n");
        text = strToText.toString();
    }

}
