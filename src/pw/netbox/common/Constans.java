package pw.netbox.common;

import java.awt.*;

public interface Constans {
    int BOARD_SIZE = 15;

    Color FIRST_PLAYER_COLOR = Color.CYAN;
    Color SECOND_PLAYER_COLOR = Color.ORANGE;

    int DRAW = 0;
    int WIN = 1;
    int LOSE = -1;
    int CONTINUE_THE_GAME = 2;
}
