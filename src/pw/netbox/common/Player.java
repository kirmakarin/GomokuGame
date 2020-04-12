package pw.netbox.common;

import java.io.*;
import java.net.Socket;

public class Player {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private boolean hasGame = false;

    public Player(Socket socket) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            new ReceiveMessage().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setMessage(message);
        sendMessage.start();
    }

    private class SendMessage extends Thread {
        String message;

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            try {
                out.write(message);
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
                    String message = in.readLine();
                    System.out.println(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
