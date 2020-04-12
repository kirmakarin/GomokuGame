package pw.netbox.client;

import pw.netbox.common.Player;
import pw.netbox.common.commandImpl.StandardOutput;

import java.io.*;
import java.net.Socket;

public class Client {
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

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
