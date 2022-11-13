package fi.utu.tech.assignment4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/* Yhdistämisen jälkeen asiakas lähettää viestin
 * "Hello" palvelimelle ja jää odottamaan palvelimen vastausta.
 * Kun vastaus saadaan, asiakas tulostaa komentoriville
 * "Varmistus saatu" ja lähettää palvelimelle viestin "quit".
 * Asiakasohjelman suoritus voi tämän jälkeen päättyä. 
 */
public class Client {
    // avaa takaisinkytkentäosoitteeseen
    public static void main(String[] args) {
        try (Socket s = new Socket(InetAddress.getByName(null), 12346);
            PrintWriter w = new PrintWriter(s.getOutputStream(), true);
            BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()))){
            if (s.isConnected()) {
                System.out.println("Yhdistetty!");
            }
            w.println("Hello");
            while(true) {
                String t = r.readLine();
                if (t.equals("Ack")) {
                    System.out.println("Varmistus saatu!");
                    w.println("quit");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Suljetaan!");
    }

}
