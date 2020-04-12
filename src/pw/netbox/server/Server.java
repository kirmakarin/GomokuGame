package pw.netbox.server;

import pw.netbox.common.Player;
import pw.netbox.common.commandImpl.StandardOutput;

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
                Player newPlayer = new Player(server.accept());
                allPlayers.add(newPlayer);
                System.out.println("New Player");

                for (Player player : allPlayers) {
                    if (!player.isHasGame()) {
                        player.sendMessage(new StandardOutput("u can join to him \n"));
                    }
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
