package _06_Networking.session00_on_class.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {

		try (Socket socket = new Socket("192.168.1.124", 7878);) {
			System.out.println("Client side: Client connected at port 7878");
			while (socket.isConnected()) {
				System.out.println("Client side: Client is still connected");
				Thread.sleep(1000);
			}
		}

	}

}
