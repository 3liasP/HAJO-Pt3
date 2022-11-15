package fi.utu.tech.assignment5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Muuta palvelimen asiakaspalvelijoiden koodia siten, että ne tunnistavat viestit,
 * jotka noudattavat muotoa "LIGHT;KOMENTO;[ID]<rivinvaihto>", jossa hakasulkeet ID
 * ympärillä tarkoittavat, että ID on vapaaehtoinen komennosta riippuen (viestissä ei
 * siis hakasulkeita tarvitse lähettää). Komentovaihtoehdot ovat ON, OFF ja QUERY.
 * Vastaanottaessa rivinvaihdon, palvelin tietää, että viesti on vastaanotettu ja
 * voidaan parsia. Viestit voisivat näyttää esimerkiksi seuraavilta:
 * 
 * LIGHT;ON;3
 * LIGHT;OFF;4
 * LIGHT;QUERY
 * 
 * Tässä kohtaa tehtävää riittää, että tunnistaessaan komennon, palvelin tulostaa
 * konsoliin esimerkiksi "Kytketään lamppu 5 POIS", "Kyselykomento vastaanotettu" jne.
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
					String[] cmd = t.split(";", 3);
                    if (cmd[0].equals("LIGHT") && cmd[1].equals("ON")) {
                       System.out.printf("%h Kytketään lamppu %s PÄÄLLE%n", this, cmd[2]);
                    } else if (cmd[0].equals("LIGHT") && cmd[1].equals("OFF")) {
                        System.out.printf("%h Kytketään lamppu %s POIS%n", this, cmd[2]);
                    } else if (cmd[0].equals("LIGHT") && cmd[1].equals("QUERY")) {
                        System.out.printf("%h Kyselykomento vastaanotettu%n", this); 
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.printf("%h Suljetaan! %n", this);
    }
}
