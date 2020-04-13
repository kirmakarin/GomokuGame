package pw.netbox.server;

import pw.netbox.common.Player;
import pw.netbox.common.commandImpl.clientOnly.InviteToGameCommand;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static ServerSocket server;

    public static boolean needNewGame = false;

    private static void startServer(List<Player> allServerPlayers, List<Game> games) {
        System.out.println("Server start");
        try {
            server = new ServerSocket(4004);
            Socket clientSocket = server.accept();
            System.out.println("1st Player");
            Player player = new Player(clientSocket);
            allServerPlayers.add(player);
            games.add(new Game(player));
            games.get(0).setRoomNumber(0);
            player.setHasGame(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<Player> allPlayers = new ArrayList<>();
        List<Game> games = new ArrayList<>();
        startServer(allPlayers, games);
        while (true) {
            try {
                Player newPlayer = new Player(server.accept());
                if (needNewGame) {
                    Game newGame = new Game(newPlayer);
                    games.add(newGame);
                    newGame.setRoomNumber(games.indexOf(newGame));
                    newPlayer.setHasGame(true);
                    needNewGame = false;
                }
                Player.setGames(games);
                allPlayers.add(newPlayer);
                System.out.println("New Player");

                for (Player player : allPlayers) {
                    if (!player.isHasGame()) {
                        player.sendMessage(new InviteToGameCommand(games));
                    }
                }
                //TODO: Add logic for create new room and don't show a full room


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
