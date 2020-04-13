package pw.netbox.server;

import pw.netbox.common.Player;
import pw.netbox.common.commandImpl.StandardOutputCommand;
import pw.netbox.common.commandImpl.clientOnly.InviteToGameCommand;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static ServerSocket server;

    public static List<Game> games = new ArrayList<>();
    public static boolean needNewGame = false;
    private static Socket clientSocket;
    public static List<Player> allPlayers = new ArrayList<>();

    private static void startServer() {
        System.out.println("Server start");
        try {
            server = new ServerSocket(4004);
            clientSocket = server.accept();
            System.out.println("1st Player");
            Player player = new Player(clientSocket);
            allPlayers.add(player);
            player.sendMessage(new StandardOutputCommand("I have game, room number is 0 \n"));
            games.add(new Game(player));
            games.get(0).setRoomNumber(0);
            player.setHasGame(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        startServer();
        while (true) {
            try {
                clientSocket = server.accept();
                Player newPlayer = new Player(clientSocket);
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


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
