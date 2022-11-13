package fi.utu.tech.assignment3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Muuta palvelinohjelmaa siten, että palvelin
 * suostuu vastaamaan useammalle asiakkaalle kuin
 * yhdelle ohjelman suorituksen aikana (ts. accept-metodia
 * ei kutsuttaisi vain kerran) ja palvelin voisi
 * (ainakin teoriassa) vastata useammalle kuin yhdelle
 * asiakkaalle samanaikaisesti. Palvelin siis jatkaisi
 * uuden asiakkaan yhdistetyä muiden uusien asiakkaiden
 * yhteydenottoja
 */

public class Server {

    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(12346)) {
                while(true) {
                    System.out.println("Vastaanotetaan yhteyksiä...");
                    Socket s = ss.accept();
                    ClientHandler ch = new ClientHandler(s);
                    ch.start();
                    System.out.println("Avattu!");
                }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Suljetaan!");
    }
    
}
