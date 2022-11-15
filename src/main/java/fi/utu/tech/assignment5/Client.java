package fi.utu.tech.assignment5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

/* Asiakkaan rooli on toimia lähinnä testausapuna.
 * Laita asiakas lähettämään eri komentoja (vaikkapa kovakoodaamalla
 * lähetettäviä merkkijonoja) ja testaa siten palvelimen toiminta.
 */
public class Client {
    // avaa takaisinkytkentäosoitteeseen
    public static void main(String[] args) {
        try (Socket s = new Socket(InetAddress.getByName(null), 12346);
            PrintWriter w = new PrintWriter(s.getOutputStream(), true)){
            Random rand = new Random();
            if (s.isConnected()) {
                System.out.println("Yhdistetty!");
            }
            while (true) {
                String cmd = "LIGHT;";
                switch (rand.nextInt(3)) {
                case 0: // ON
                    cmd = cmd.concat("ON;" + Integer.toString(rand.nextInt(5)));
                    break;
                case 1: // OFF
                    cmd = cmd.concat("OFF;" + Integer.toString(rand.nextInt(5)));
                    break;
                case 2: // QUERY
                    cmd = cmd.concat("QUERY");
                }
                w.println(cmd);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // tyhjä
                }
            }  
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Suljetaan!");
    }

}
