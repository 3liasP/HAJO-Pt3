package fi.utu.tech.assignment2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/* 
* Toteuta toiminnallisuus, jossa asiakkaan yhdistettyä
* palvelimeen, asiakas lähettää merkkijonon "Hello"
* (tai muun vastaavan) palvelimelle ja palvelin tulostaa
* vastaanotetun viestin sisällön terminaaliin. Toteuta
* toiminnallisuus käyttäen suoraan Socket-olioilta
* saatavia Input- ja OutputStream-oliota ilman, että 
* käytät tässä kohtaa vielä muita tietovirtaluokkia apuna.
*/

public class Client {
    // avaa takaisinkytkentäosoitteeseen
    public static void main(String[] args) {
        try (Socket s = new Socket(InetAddress.getByName(null), 12346);
            OutputStream outputStream = s.getOutputStream()) {
            if (s.isConnected()) {
                System.out.println("Yhdistetty!");
            }
            String greeting = "Terve, palvelin!";
            outputStream.write(greeting.getBytes());
            outputStream.flush();
            System.out.println("Lähetetty!");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Suljetaan!");
    }

}
