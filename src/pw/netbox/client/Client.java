package pw.netbox.client;

import pw.netbox.common.Player;

import java.io.*;
import java.net.Socket;

public class Client {
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
//        ClientForm.initWindow();
        try {
            System.out.println("Welcome to Gomoku");
            Player  currentPlayer = new Player(new Socket("localhost",4004));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
