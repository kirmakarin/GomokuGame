package pw.netbox.common.commandImpl.client;

import pw.netbox.common.Command;
import pw.netbox.common.Player;

import java.awt.*;

public class SetInitialParamsCommand extends Command {
    private Color color;
    private boolean isMyTurn;

    public SetInitialParamsCommand(Color color, boolean isMyTurn) {
        this.color = color;
        this.isMyTurn = isMyTurn;
    }

    @Override
    public void execute(Player player) {
        player.setColor(color);
        player.setMyTurn(isMyTurn);
    }
}
