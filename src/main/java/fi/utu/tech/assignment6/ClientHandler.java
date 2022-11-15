package fi.utu.tech.assignment6;

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
    Socket s;
    Hub h;

    public ClientHandler(Socket s, Hub h) {
        this.h = h;
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
                        h.turnOnLight(Integer.parseInt(cmd[2]));
                    } else if (cmd[0].equals("LIGHT") && cmd[1].equals("OFF")) {
                        h.turnOffLight(Integer.parseInt(cmd[2]));
                    } else if (cmd[0].equals("LIGHT") && cmd[1].equals("QUERY")) {
                        String query = "";
						for (var l : h.getLights()) {
							query = query.concat(Integer.toString(l.getId())+
									":"+
									(l.isPowerOn() ? "ON" : "OFF")+
									";");
						}
						w.println(query);
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.printf("%h Suljetaan! %n", this);
    }
}
