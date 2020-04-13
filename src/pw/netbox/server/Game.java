package pw.netbox.server;

import pw.netbox.common.Player;

import java.io.Serializable;

import static pw.netbox.common.Constans.BOARD_SIZE;

public class Game implements Serializable {
    private int roomNumber;
    private transient Player player1;
    private transient Player player2;
    private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    private boolean isGameStart = false;

    public Game(Player player1) {
        this.player1 = player1;
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
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

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public boolean isGameStart() {
        return isGameStart;
    }

    public void setGameStart(boolean gameStart) {
        isGameStart = gameStart;
    }

    @Override
    public String toString() {
        return "Game{" +
                "roomNumber=" + roomNumber +
                ", player1=" + player1 +
                ", player2=" + player2 +
                '}';
    }
}
