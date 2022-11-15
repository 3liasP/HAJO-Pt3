package fi.utu.tech.assignment5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(12346)) {
                while(true) {
                    System.out.println("Vastaanotetaan yhteyksiä...");
                    Socket s = ss.accept();
                    ClientHandler ch = new ClientHandler(s);
                    ch.start();
                    System.out.println("Avattu!");
                }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Suljetaan!");
    }
    
}
