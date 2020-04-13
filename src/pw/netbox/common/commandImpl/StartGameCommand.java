package pw.netbox.common.commandImpl;

import pw.netbox.client.ClientForm;
import pw.netbox.common.Command;
import pw.netbox.common.Player;

public class StartGameCommand extends Command {
    private int roomNumber;

    public StartGameCommand(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public void execute(Player player) {
        ClientForm.initWindow(roomNumber, player);
    }
}
