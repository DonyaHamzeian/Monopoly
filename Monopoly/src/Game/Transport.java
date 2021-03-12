package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.Controller;
import Network.Client;
import Player.Player;
import View.MyPanel;

public class Transport extends Space {
	private int mortgageValue, price;
	public int[] rents = new int[4];
	Player owner = null;
	private boolean isMortgaged = false;

	Transport(int x, int y, int w, int h, String name, int num, int price,
			int mortgageValue, int... rents) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.num = num;
		this.price = price;
		this.mortgageValue = mortgageValue;
		for (int i = 0; i < rents.length; i++)
			this.rents[i] = rents[i];
		this.type = 2;
	}

	@Override
	public void drawInFrame() {
		@SuppressWarnings("unused")
		final ShowTransportInfo frame = new ShowTransportInfo(this);

	}

	@Override
	public void function() {
		if (this.owner == null) {
			drawInFrame();
			return;
		}
		if (owner.getName().equals(MyPanel.p.getName()))
			return;
		else {
			Bank b = new Bank();
			b.getRent(2, MyPanel.p, num);
		}
		Controller.repaint();

	}

	public int getMortgageValue() {
		return mortgageValue;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;

		Client.m.spaceBought(this.num);
	}
	public void updateOwner(Player owner){
		this.owner= owner;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public void showMessage() {
		String players = "";
		if (this.playersOnSpace.size() != 0)
			for (int i = 0; i < this.playersOnSpace.size(); i++)
				players += "\n" + this.playersOnSpace.get(i).getName();
		if (owner != null) {
			if (this.playersOnSpace.size() != 0)
				JOptionPane.showMessageDialog(null,
						"Owner:   " + owner.getName() + "\nactiveRent:   "
								+ MyPanel.bank.getActiveRent(num)
								+ "\nPlayer on this space:   " + players,
						this.name, JOptionPane.CLOSED_OPTION);
			else
				JOptionPane.showMessageDialog(null,
						"Owner:   " + owner.getName() + "\nactiveRent:   "
								+ MyPanel.bank.getActiveRent(num), this.name,
						JOptionPane.CLOSED_OPTION);

		} else {
			if (this.playersOnSpace.size() != 0)
				JOptionPane.showMessageDialog(null, "activeRent:   "
						+ MyPanel.bank.getActiveRent(num)
						+ "\nPlayer on this space:   " + players, this.name,
						JOptionPane.CLOSED_OPTION);
			else
				JOptionPane.showMessageDialog(null, "activeRent:   "
						+ MyPanel.bank.getActiveRent(num), this.name,
						JOptionPane.CLOSED_OPTION);

		}

	}

	public boolean isMortgaged() {
		return isMortgaged;
	}

	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
		for (int i = 0; i < Client.m.variedSpace.size(); i++)
			if (Client.m.variedSpace.get(i).space == this.num) {
				Client.m.variedSpace.get(i).setMortgaged(isMortgaged);
				return;
			}
		Client.m.addvariedSpace(this.num);
		Client.m.variedSpace.get(Client.m.variedSpace.size() - 1).setMortgaged(
				isMortgaged);
	}

}

class ShowTransportInfo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Transport t;
	String url = null;

	public ShowTransportInfo(Transport transport) {
		this.t = transport;
		if (t.name.equals("MONOPOLY AIR")) {
			url = "src/images/image/images.png";
		}
		if (t.name.equals("MONOPOLY SPACE")) {
			url = "src/images/image/Objects-Rocket-icon.png";
		}
		if (t.name.equals("MONOPOLY RAIL")) {
			url = "src/images/image/danland_favicon.gif";

		}
		if (t.name.equals("MONOPOLY CRUISE")) {
			url = "src/images/image/landscape6.png";

		}
		JButton buy = new JButton("BUY");
		buy.setBounds(13, 360, 110, 20);
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyPanel.bank.buySpace(MyPanel.p, t.num);
				dispose();
			}
		});

		JButton auction = new JButton("AUCTION");
		auction.setBounds(133, 360, 110, 20);
		auction.setBackground(new Color(180, 214, 175));

		auction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});

		this.add(auction);
		this.add(buy);
		this.setTitle("TRANSPORT");
		this.setSize(260, 430);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
	}

	public JPanel panel = new JPanel() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g) {

			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.black);
			g.drawRect(13, 13, 232, 50);
			g.drawRect(8, 8, 242, 382);
			g.setColor(Color.black);
			try {
				BufferedImage image = ImageIO.read(new File(url));
				Image scaledImage = image.getScaledInstance(232, 50,
						Image.SCALE_SMOOTH);
				g.drawImage(scaledImage, 8, 11, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			FontMetrics fm = g.getFontMetrics();
			int height = fm.getHeight();
			g.setFont(new Font(g.getFont().getName(), Font.BOLD, 13));
			g.setColor(Color.BLACK);
			int a = (44 + height) / 2 + height;
			g.drawString(t.name + "  " + t.getPrice() + "k", 216 / 2 - 32,
					a += 33);
			g.drawString("RENT   " + t.rents[0] + "k", 16, a += 33);
			g.drawString("If 2 transports are owned    " + t.rents[1] + "k",
					16, a += 33);
			g.drawString("If 3 transports are owned    " + t.rents[2] + "k",
					16, a += 33);
			g.drawString("If 4 transports are owned    " + t.rents[3] + "k",
					16, a += 33);
			g.drawString(
					"Mortgage Value                  " + t.getMortgageValue()
							+ "k", 16, a += 33);
		}
	};

}
