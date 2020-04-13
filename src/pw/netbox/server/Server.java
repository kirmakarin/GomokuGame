package pw.netbox.server;

import pw.netbox.common.Player;
import pw.netbox.common.commandImpl.client.InviteToGameCommand;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static ServerSocket server;
    public static List<Game> games = new ArrayList<>();
    public static boolean needNewGame = true;
    private static int gameCount = 0;

    public static List<Player> allPlayers = new ArrayList<>();

    private static Socket clientSocket;

    public static void main(String[] args) throws IOException {
        System.out.println("Server is working");
        server = new ServerSocket(4004);
        while (true) {
            try {
                clientSocket = server.accept();
                Player newPlayer = new Player(clientSocket);
                if (needNewGame) {
                    createNewGame();
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

    public static void createNewGame() {
        Game newGame = new Game();
        newGame.setRoomNumber(gameCount);
        gameCount++;
        games.add(newGame);
        needNewGame = false;
    }
}
