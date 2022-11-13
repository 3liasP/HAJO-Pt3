package fi.utu.tech.assignment3;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/*
 * Toteuta/siirrä asiakaspalvelutoiminnallisuus tähän luokkaan ja muokkaa
 * Server-luokkaa siten, että se ei enää yksinään palvelele asiakkaita, vaan
 * delegoi asiakaspalvelun ClientHandler-luokan säikeille.
 */

public class ClientHandler extends Thread {

    private Socket s;

    public ClientHandler(Socket s) {
        this.s = s;
    }

    public void run() {
        try (InputStream inputStream = s.getInputStream()) {
            if (s.isConnected()) {
                System.out.printf("%h Yhdistetty! %n", this);
            }
            String g = new String(inputStream.readAllBytes());
            System.out.printf("%h %s%n", this, g);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.printf("%h Suljetaan! %n", this);
    }

    
}
