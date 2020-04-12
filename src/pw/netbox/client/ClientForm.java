package pw.netbox.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static pw.netbox.common.Constans.BOARD_SIZE;

public class ClientForm {
    private static JFrame frame = new JFrame("Gomoky");
    private static JLabel messageLabel = new JLabel("");

    private static Square[][] board = new Square[BOARD_SIZE][BOARD_SIZE];
    private static Square currentSquare;

    public static void initWindow() {
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
//                        out.println("MOVE (" + x + ',' + y + ")");
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
}
