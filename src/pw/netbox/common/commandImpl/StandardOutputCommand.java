package pw.netbox.common.commandImpl;

import pw.netbox.common.Command;
import pw.netbox.common.Player;

public class StandardOutputCommand extends Command {
    protected String text;

    public StandardOutputCommand(String text) {
        this.text = text;
    }

    @Override
    public void execute(Player player) {
        System.out.println(text);
    }
}
