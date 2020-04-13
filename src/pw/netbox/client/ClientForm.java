package pw.netbox.client;

import pw.netbox.common.Player;
import pw.netbox.common.commandImpl.server.EndGameCommand;
import pw.netbox.common.commandImpl.server.MoveCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static pw.netbox.common.Constans.*;

public class ClientForm {
    private static JFrame frame = new JFrame();
    private static JLabel messageLabel = new JLabel("");

    private static Square[][] board = new Square[BOARD_SIZE][BOARD_SIZE];
    private static Square currentSquare;

    public static void initWindow(int roomNumber, Player player) {
        frame.setTitle(String.valueOf(roomNumber));
        messageLabel.setBackground(Color.lightGray);
        frame.getContentPane().add(messageLabel, "South");
        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.black);
        boardPanel.setLayout(new GridLayout(15, 15, 1, 1));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                final int x = i;
                final int y = j;
                board[i][j] = new Square();
                board[i][j].addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        currentSquare = board[x][y];
                        if (player.isMyTurn() && currentSquare.getBackground().equals(Color.white)) {
                            currentSquare.setBackground(player.getColor());
                            player.setMyTurn(false);
                            if (hasWinner(player) == CONTINUE_THE_GAME) {
                                player.sendMessage(new MoveCommand(x, y));
                            } else {
                                endGame(player);
                            }
                        }
                    }
                });
                boardPanel.add(board[i][j]);
            }
        }
        frame.getContentPane().add(boardPanel, "Center");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    static class Square extends JPanel {
        JLabel label = new JLabel((Icon) null);

        Square() {
            setBackground(Color.white);
            add(label);
        }
    }

    public static Square[][] getBoard() {
        return board;
    }

    public static void setColor(Color color, int x, int y) {
        board[x][y].setBackground(color);
    }

    public static void setMessageLabelText(String text) {
        ClientForm.messageLabel.setText(text);
    }

    private static int hasWinner(Player player) {
        //horizontally
        for (int i = 0; i <= BOARD_SIZE - 5; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (!board[i][j].getBackground().equals(Color.white) &&
                        board[i + 1][j].getBackground().equals(board[i][j].getBackground()) &&
                        board[i + 2][j].getBackground().equals(board[i][j].getBackground()) &&
                        board[i + 3][j].getBackground().equals(board[i][j].getBackground()) &&
                        board[i + 4][j].getBackground().equals(board[i][j].getBackground())) {
                    return board[i][j].getBackground().equals(player.getColor()) ? WIN : LOSE;
                }
            }
        }
        //vertically
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j <= BOARD_SIZE - 5; j++) {
                if (!board[i][j].getBackground().equals(Color.white) &&
                        board[i][j + 1].getBackground().equals(board[i][j].getBackground()) &&
                        board[i][j + 2].getBackground().equals(board[i][j].getBackground()) &&
                        board[i][j + 3].getBackground().equals(board[i][j].getBackground()) &&
                        board[i][j + 4].getBackground().equals(board[i][j].getBackground())) {
                    return board[i][j].getBackground().equals(player.getColor()) ? WIN : LOSE;
                }
            }
        }
        // Check first diagonal
        for (int i = 0; i <= BOARD_SIZE - 5; i++) {
            for (int j = 0; j <= BOARD_SIZE - 5; j++) {
                if (!board[i][j].getBackground().equals(Color.white) &&
                        board[i + 1][j + 1].getBackground().equals(board[i][j].getBackground()) &&
                        board[i + 2][j + 2].getBackground().equals(board[i][j].getBackground()) &&
                        board[i + 3][j + 3].getBackground().equals(board[i][j].getBackground()) &&
                        board[i + 4][j + 4].getBackground().equals(board[i][j].getBackground())) {
                    return board[i][j].getBackground().equals(player.getColor()) ? WIN : LOSE;
                }
            }
        }
        //Is board fill
        for (int i = 0; i <= BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (!board[i][j].getBackground().equals(Color.white)) {
                    return CONTINUE_THE_GAME;
                }
            }
        }
        return DRAW;
    }

    private static void endGame(Player player) {
        switch (hasWinner(player)) {
            case DRAW:
                messageLabel.setText("DRAW");
                player.sendMessage(new EndGameCommand("DRAW"));
                break;
            case WIN:
                messageLabel.setText("WIN");
                player.sendMessage(new EndGameCommand("LOSE"));
                break;
            case LOSE:
                messageLabel.setText("LOSE");
                player.sendMessage(new EndGameCommand("WIN"));
                break;

        }
    }
}
