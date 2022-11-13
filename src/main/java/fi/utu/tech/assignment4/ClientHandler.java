package fi.utu.tech.assignment4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Yhdistämisen jälkeen palvelimen "asiakaspalvelijoiden"
 * tulisi noudattaa seuraavaa logiikkaa:
 *  - Kun palvelimen asikaspalvelija vastaanottaa asiakkaan
 *    lähettämän merkkijonon, tulosta se komentoriville
 *  - Mikäli viesti on merkkijono "Hello", tulee palvelimen
 *    lähettää asiakkaalle takaisin merkkijono "Ack".
 *  - Ei saa olettaa, että viesti "Hello" lähetetään vain
 *    kerran. Mikäli sama asiakas lähettää viestin "Hello"
 *    5 kertaa, palvelimen tulee lähettää myös vastaus "Ack"
 *    joka kerta takaisin.
 */

public class ClientHandler extends Thread {

    private Socket s;

    public ClientHandler(Socket s) {
        this.s = s;
    }

    public void run() {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter w = new PrintWriter(s.getOutputStream(), true)) {
                if (s.isConnected()) {
                    System.out.printf("%h Yhdistetty! %n", this);
                }
                while(true) {
                    String t = r.readLine();
					System.out.printf("%h %s%n", this, s);
					if (t.equals("Hello")) {
						w.println("Ack");
					} else if (t.equals("quit")) {
						s.close();
						break;
					}
                }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.printf("%h Suljetaan! %n", this);
    }
}
