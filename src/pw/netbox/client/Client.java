package pw.netbox.client;

import pw.netbox.common.Player;
import pw.netbox.common.commandImpl.serverOnly.JoinToGameCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private static BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
    private static boolean hasWinner = false;
    private static String roomNumber;

    public static void main(String[] args) {
        try {
            System.out.println("Welcome to Gomoku");

            Socket clientSocket = new Socket("localhost", 4004);
            Player currentPlayer = new Player(clientSocket);

            roomNumber = inputUser.readLine();
            currentPlayer.sendMessage(new JoinToGameCommand(Integer.parseInt(roomNumber)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
