package _06_Networking.session00_on_class.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientChat {

	public static void main(String[] args) throws UnknownHostException, IOException {
		try (Socket socket = new Socket("192.168.1.124", 7878);
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				Scanner scanner = new Scanner(System.in);) {
			System.out.println("Client side: Client connected at port 7878");
			while (!socket.isClosed()) {
				System.out.print("Client side chat: ");
				String messageSent = scanner.nextLine();
				dos.writeUTF(messageSent);
				dos.flush();

				String messageReceived = dis.readUTF();
				System.out.println("Server side chat: " + messageReceived);
			}
		}
	}

}
