package fi.utu.tech.assignment6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(12346)){
        	Hub h = new Hub();
			while (true) {
				System.out.println("Vastaanotetaan yhteyksiä...");
				Socket socket = ss.accept();
				ClientHandler clientHandler = new ClientHandler(socket, h);
				clientHandler.start();
				System.out.println("Avattu.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Suljetaan.");
    }

    
}
