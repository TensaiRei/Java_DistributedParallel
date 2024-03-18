package _06_Networking.session00_on_class.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {

		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(7878);
			System.out.println("Server side: Server is running on port 7878");

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Server side: A client connected at "
						+ socket.getInetAddress().toString().replaceAll("/", "") + ";"
						+ socket.getPort());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
