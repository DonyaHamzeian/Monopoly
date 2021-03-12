import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) {
		int k = 2;
		String[] players = new String[k];
		Socket connection = null;
		try {
			ServerSocket myServer = new ServerSocket(8081);
			System.out.println("server created");
			int i = 0;
			while (i < k) {
				connection = myServer.accept();
				Scanner read = new Scanner(connection.getInputStream());
				System.out.println(read.next());
				System.out.println("i"+ i);
				i++;
				// players[i] = read.next();
			}
			System.out.println("done");

		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread[] t = new Thread[k];
		for (int i = 0; i < k; i++) {
			t[i] = new Thread(new ClientHandler(connection, players[i]));
			t[i].start();
		}
	}
}

class ClientHandler implements Runnable {
	Socket connection;
	String player;
	PrintWriter pw;
	Scanner read;

	public ClientHandler(Socket connection, String player) {
		this.connection = connection;
		this.player = player;

	}

	@Override
	public void run() {
		System.out.println("run");
		try {
			pw = new PrintWriter(connection.getOutputStream());
			read = new Scanner(connection.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.write("START");
		pw.flush();
	}
}
