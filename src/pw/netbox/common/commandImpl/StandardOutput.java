package pw.netbox.common.commandImpl;

import pw.netbox.common.Command;

import java.io.Serializable;

public class StandardOutput extends Command{
    private String text;

    public StandardOutput(String text) {
        this.text = text;
    }

    @Override
    public void execute() {
        System.out.println(text);
    }
}
