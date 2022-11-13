package fi.utu.tech.assignment3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Client {
    // avaa takaisinkytkentäosoitteeseen
    public static void main(String[] args) {
        try (Socket s = new Socket(InetAddress.getByName(null), 12346);
            OutputStream outputStream = s.getOutputStream()) {
            if (s.isConnected()) {
                System.out.println("Yhdistetty!");
            }
            String g = "Terve, palvelin!";
            outputStream.write(g.getBytes());
            outputStream.flush();
            System.out.println("Lähetetty!");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Suljetaan!");
    }

}
