package pw.netbox.common.commandImpl.clientOnly;

import pw.netbox.client.ClientForm;
import pw.netbox.common.Command;
import pw.netbox.common.Player;

import java.awt.*;

public class YourTurnCommand extends Command {
    private Color color;
    private int x;
    private int y;

    public YourTurnCommand(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute(Player player) {
        player.setMyTurn(true);
        ClientForm.setColor(color, x, y);


    }
}
