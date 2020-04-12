package pw.netbox.common;

import pw.netbox.server.Game;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Player implements Serializable {
    private transient static List<Game> games;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private boolean hasGame = false;

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
                    e.printStackTrace();
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

    public static void setGames(List<Game> games) {
        Player.games = games;
    }
}
