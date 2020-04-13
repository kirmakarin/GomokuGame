package pw.netbox.common;

import pw.netbox.server.Game;
import pw.netbox.server.Server;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.Objects;

public class Player implements Serializable {
    private transient static List<Game> games;
    private transient Game game;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private Color color;
    private boolean hasGame = false;
    private boolean isMyTurn;

    public Player(Socket socket) {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            new ReceiveMessage(this).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Command message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setMessage(message);
        sendMessage.start();
    }

    private class SendMessage extends Thread {
        Command message;

        public void setMessage(Command message) {
            this.message = message;
        }

        @Override
        public void run() {
            try {
                out.writeObject(message);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ReceiveMessage extends Thread {
        private Player player;

        ReceiveMessage(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Command message = (Command) in.readObject();
                    message.execute(player);

                } catch (IOException | ClassNotFoundException e) {
                    Server.games.remove(player.game);
                    Server.allPlayers.remove(player);
                    this.destroy();
                }
            }
        }
    }

    public boolean isHasGame() {
        return hasGame;
    }

    public void setHasGame(boolean hasGame) {
        this.hasGame = hasGame;
    }

    public static List<Game> getGames() {
        return games;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static void setGames(List<Game> games) {
        Player.games = games;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return isHasGame() == player.isHasGame() &&
                isMyTurn() == player.isMyTurn() &&
                in.equals(player.in) &&
                out.equals(player.out) &&
                Objects.equals(getColor(), player.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), isHasGame(), isMyTurn());
    }
}
