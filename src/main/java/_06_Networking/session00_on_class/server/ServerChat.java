package _06_Networking.session00_on_class.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerChat {

	public static void main(String[] args) {

		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(7878);
			System.out.println("Server side: Server is running on port 7878");

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Server side: A client connected at "
						+ socket.getInetAddress().toString().replaceAll("/", "") + ":"
						+ socket.getPort());

				ServerChat server = new ServerChat();
				Handler handler = server.new Handler(socket);
				Thread thread = new Thread(handler);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private class Handler implements Runnable {

		private Socket socket;

		public Handler(Socket socket) {
			super();
			this.socket = socket;
		}

		@Override
		public void run() {
			try (DataInputStream dis = new DataInputStream(socket.getInputStream());
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					Scanner scanner = new Scanner(System.in);) {
				while (!socket.isClosed()) {
					String messageReceived = dis.readUTF();
					System.out.println("Client side chat: " + messageReceived);

					System.out.print("Server side chat: ");
					String messageSent = scanner.nextLine();
					dos.writeUTF(messageSent);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
