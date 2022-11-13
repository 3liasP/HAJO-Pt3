package fi.utu.tech.assignment1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
* Kirjoita Server-luokkaan TCP-soketteihin pohjautuva
* palvelinohjelma, joka odottaa asiakkaan yhdistämistä.
* 
* Aseta palvelin kuuntelemaan yhteydenottopyyntöjä
* vapaavalintaisessa TCP-portissa.
*/

public class Server {

    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(12346);
                Socket s = ss.accept();){
            if(s.isConnected()) {
                System.out.println("Yhdistetty!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Suljetaan!");
    }
    
}
