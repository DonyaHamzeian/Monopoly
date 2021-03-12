 package Network;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	static String[] players;
	static int count = 0;
	static int k = 1;
	static String[] info;
	static boolean[] isBankrupt;

	public static void main(String[] args) {
		k = 3;
		players = new String[k];
		isBankrupt = new boolean[k];
		Socket connection = null;
		Thread[] t = new Thread[k];
		info = new String[k];
		try {
			ServerSocket myServer = new ServerSocket(8081);
			System.out.println("server created");
			int i = 0;
			while (i < k) {
				connection = myServer.accept();
				Scanner read = new Scanner(connection.getInputStream());
				players[i] = read.next();
				t[i] = new Thread(new ClientHandler(i, connection, players[i]));

				i++;
			}

			System.out.println("done!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < k; i++)
			info[i] = "INFO*";

		for (int i = 0; i < k; i++)
			t[i].start();

	}

}

class ClientHandler implements Runnable {
	Socket connection;
	String player;
	PrintWriter pw;
	Scanner read;
	int i;
	boolean[] giveInfo;

	public ClientHandler(int i, Socket connection, String player) {
		this.connection = connection;
		this.player = player;
		this.i = i;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		try {
			pw = new PrintWriter(connection.getOutputStream());
			read = new Scanner(connection.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.println("START");
		pw.flush();
		int d = 0;
		while (true) {

			String str = read.next();
			if (str.equals("TURN?")) {
				if (Server.count == i) {
					String s = "YES@";

					for (int j = 0; j < Server.info.length; j++)
						if (i != j)
							s +="%"+ Server.players[j] + "*" + Server.info[j];
					if (d == 0) {
						System.err.println("s" + s);
						d++;
					}
					pw.println(s);
					pw.flush();
				} else {
					pw.println("NO");
					pw.flush();
				}
			}
			if (str.startsWith("GIVE")) {
				Server.info[i] = str;
				add();
			}
			if (str.startsWith("BANKRUPT")) {
				Server.isBankrupt[i] = true;
				Thread.currentThread().stop();
				
			}

		}
	}

	public void add() {
		synchronized (this) {

			do {
				Server.count++;
				Server.count %= Server.k;
			} while (Server.isBankrupt[Server.count]);
		}
	}
}
