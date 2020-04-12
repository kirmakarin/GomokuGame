package pw.netbox.server;

import pw.netbox.common.Player;

import static pw.netbox.common.Constans.BOARD_SIZE;

public class Game {
    Player player1;
    Player player2;
    char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    private void initBoard() {
        for (int i = 0; i <= BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '0';
            }
        }
    }

    private boolean hasWinner() {
        //horizontally
        for (int i = 0; i <= BOARD_SIZE - 5; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != '0' &&
                        board[i + 1][j] == board[i][j] &&
                        board[i + 2][j] == board[i][j] &&
                        board[i + 3][j] == board[i][j] &&
                        board[i + 4][j] == board[i][j]) {
                    return true;
                }
            }
        }
        //vertically
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j <= BOARD_SIZE - 5; j++) {
                if (board[i][j] != '0' &&
                        board[i][j + 1] == board[i][j] &&
                        board[i][j + 2] == board[i][j] &&
                        board[i][j + 3] == board[i][j] &&
                        board[i][j + 4] == board[i][j]) {
                    return true;
                }
            }
        }
        // Check first diagonal
        for (int i = 0; i <= BOARD_SIZE - 5; i++) {
            for (int j = 0; j <= BOARD_SIZE - 5; j++) {
                if (board[i][j] != '0' &&
                        board[i + 1][j + 1] == board[i][j] &&
                        board[i + 2][j + 2] == board[i][j] &&
                        board[i + 3][j + 3] == board[i][j] &&
                        board[i + 4][j + 4] == board[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
