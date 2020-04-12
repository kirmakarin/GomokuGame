package pw.netbox.common;

import java.io.Serializable;

public abstract class Command implements Serializable {
    Player player;

    public abstract void execute(Player player);
}
