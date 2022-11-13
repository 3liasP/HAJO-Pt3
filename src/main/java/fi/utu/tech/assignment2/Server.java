package fi.utu.tech.assignment2;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
* Toteuta toiminnallisuus, jossa asiakkaan yhdistettyä
* palvelimeen, asiakas lähettää merkkijonon "Hello"
* (tai muun vastaavan) palvelimelle ja palvelin tulostaa
* vastaanotetun viestin sisällön terminaaliin. Toteuta
* toiminnallisuus käyttäen suoraan Socket-olioilta
* saatavia Input- ja OutputStream-oliota ilman, että 
* käytät tässä kohtaa vielä muita tietovirtaluokkia apuna.
* 
*/

public class Server {

    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(12346);
                Socket s = ss.accept();
                InputStream inputStream = s.getInputStream()) {
            if(s.isConnected()) {
                System.out.println("Yhdistetty!");
            }
            String greeting = new String(inputStream.readAllBytes());
            System.out.println(greeting);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Suljetaan!");
    }
    
}
