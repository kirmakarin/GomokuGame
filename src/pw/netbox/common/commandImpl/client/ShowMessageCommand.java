package pw.netbox.common.commandImpl.client;

import pw.netbox.client.ClientForm;
import pw.netbox.common.Command;
import pw.netbox.common.Player;

public class ShowMessageCommand extends Command {
    private String text;

    public ShowMessageCommand(String text) {
        this.text = text;
    }

    @Override
    public void execute(Player player) {
        ClientForm.setMessageLabelText(text);
    }
}
