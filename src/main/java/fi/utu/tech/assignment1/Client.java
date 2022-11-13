package fi.utu.tech.assignment1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/*
 * Toteuta Client-luokkaan asiakasohjelma,
 * joka pystyy yhdistämään Server-luokan palvelimeen.
 * Voit yhdistäessä käyttää takaisinkytkentäosoitetta
 * ("loopback", 127.0.0.1).
 */

public class Client {
    // avaa takaisinkytkentäosoitteeseen
    public static void main(String[] args) {
        try (Socket s = new Socket(InetAddress.getByName(null), 12346)){
            if (s.isConnected()) {
                System.out.println("Yhdistetty!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Suljetaan!");
    }

}
