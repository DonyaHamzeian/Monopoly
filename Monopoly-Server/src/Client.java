import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) {
		String name =JOptionPane.showInputDialog(null, "New Game", "Type Your Name");
		try {
			Socket connection = new Socket("127.0.0.1", 8081);
			System.out.println("NEW CLIENT");
			PrintWriter pw = new PrintWriter(connection.getOutputStream());
			pw.write(name);
			pw.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
