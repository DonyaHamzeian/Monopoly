package Network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Timer;

import javax.swing.JOptionPane;

import Controller.Controller;
import Game.Move;

public class Client {
	static PrintWriter pw = null;
	public static String name;
	public static int turn = 0;
	public static Controller c;
	public static Move m;

	public static Timer t;

	public static void main(String[] args) {
		try {
			name = JOptionPane.showInputDialog(null, "New Game",
					"Type Your Name");

		} catch (Exception e) {
			System.exit(0);
		}
		Socket connection = null;

		try {
			connection = new Socket("127.0.0.1", 8081);
			System.out.println("NEW CLIENT");
			pw = new PrintWriter(connection.getOutputStream());
			System.out.println("name" + name);
			pw.println(name);
			pw.flush();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scanner read = null;
		try {
			read = new Scanner(connection.getInputStream());
			if (read.next().equals("START")) {
				c = new Controller(name);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			pw.println("TURN?");
			pw.flush();
			String str = read.next();
			if (str.startsWith("YES@") && turn == 0) {
				synchronized (c) {
					c.setText(str);
				}
				for (int j = 0; j < 1000; j++)
					System.err.println("hi");
				m = new Move();
				turn++;
				m.analyseMove(str);
				c.repaint();
				c.contentPane.endTurn.setEnabled(false);
				c.contentPane.rollDice.setEnabled(true);
			}

			if (turn == 2) {
				c.contentPane.rollDice.setEnabled(false);
				String tmp = m.giveInfo();
				System.err.println(tmp);
				pw.println(tmp);
				pw.flush();
				turn = 0;
			}

		}

	}

}
