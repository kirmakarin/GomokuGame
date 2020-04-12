package pw.netbox.common;

import java.io.*;
import java.net.Socket;

public class Player {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean hasGame = false;

    public Player(Socket socket) {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            new ReceiveMessage().start();
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
        @Override
        public void run() {
            while (true) {
                try {
                    Command message = (Command) in.readObject();
                    message.execute();

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isHasGame() {
        return hasGame;
    }
}
