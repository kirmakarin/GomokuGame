package pw.netbox.server;

import pw.netbox.common.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static ServerSocket server;

    private static boolean working = true;

    private static void startServer(List<Player> allServerPlayers) {
        System.out.println("Server start");
        try {
            server = new ServerSocket(4004);
            Socket clientSocket = server.accept();
            System.out.println("1st Player");
            allServerPlayers.add(new Player(clientSocket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Player> allPlayers = new ArrayList<>();
        List<Game> games = new ArrayList<>();
        startServer(allPlayers);
        while (working) {
            try {
                allPlayers.add(new Player(server.accept()));
                System.out.println("New Player");
                //Уведомить всех играков без пары, что появился новый игрок
                for (Player player : allPlayers) {
                    player.sendMessage("New player , if u want to play with him \n");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            if (allPlayers.isEmpty()) {
                working = false;
            }
        }
    }
}
