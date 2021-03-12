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

public class Utility extends Space {
	private int mortgageValue, price, activeRent;
	Player owner = null;
	private boolean isMortgaged = false;

	Utility(int x, int y, int w, int h, String name, int price,
			int mortgageValue) {
		this.name = name;
		this.price = price;
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.mortgageValue = mortgageValue;
		this.type = 3;
	}

	@Override
	public void drawInFrame() {
		@SuppressWarnings("unused")
		final ShowUtilityInfo frame = new ShowUtilityInfo(this);

	}

	@Override
	public void function() {
		if (this.owner == null) {
			drawInFrame();
			return;
		}
		if (this.owner.getName().equals(MyPanel.p.getName()))
			return;
		if (!this.owner.getName().equals(MyPanel.p.getName())) {
			MyPanel.bank.getRent(3, MyPanel.p, num);
		}

		Controller.repaint();
	}

	public void updateOwner(Player owner) {
		this.owner = owner;
	}

	public int getMortgageValue() {
		return mortgageValue;
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
				JOptionPane
						.showMessageDialog(null, "Owner:   " + owner.getName()
								+ "\nactiveRent:   " + activeRent
								+ "\nPlayer on this space:   " + players,
								this.name, JOptionPane.CLOSED_OPTION);

			else
				JOptionPane.showMessageDialog(null,
						"Owner:   " + owner.getName() + "\nactiveRent:   "
								+ activeRent, this.name,
						JOptionPane.CLOSED_OPTION);

		} else {
			if (this.playersOnSpace.size() != 0)
				JOptionPane.showMessageDialog(null, "activeRent:   "
						+ activeRent + "\nPlayer on this space:   " + players,
						this.name, JOptionPane.CLOSED_OPTION);
			else
				JOptionPane.showMessageDialog(null, "activeRent:   "
						+ activeRent, this.name, JOptionPane.CLOSED_OPTION);

		}
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
		Client.m.spaceBought(this.num);
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

class ShowUtilityInfo extends JFrame {
	Utility u;
	String url;

	public ShowUtilityInfo(Utility utility) {
		this.u = utility;
		if (u.name.equals("SOLAR ENERGY"))
			url = "src/images/image/solar_energy_vector-512.png";

		if (u.name.equals("WIND ENERGY")) {
			url = "src/images/image/wind-turbine-256.png";
		}
		JButton buy = new JButton("BUY");
		buy.setBounds(13, 360, 110, 20);
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyPanel.bank.buySpace(MyPanel.p, u.num);
				// if(!MyPanel.bank.buySpace(MyPanel.p, u.num))
				// JOptionPane.showMessageDialog(null,"YOU DON'T HAVE ENOUGH MONEY, SELL HOUSE, SELL HOTEL, OR MORTGAGE"
				// ,
				// "NO MONEY", JOptionPane.INFORMATION_MESSAGE);
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
		this.setTitle("UTILITY");
		this.setSize(260, 440);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
	}

	public JPanel panel = new JPanel();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {

		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawRect(13, 23, 232, 60);
		g.drawRect(8, 18, 242, 392);
		g.setColor(Color.black);
		try {
			BufferedImage image = ImageIO.read(new File(url));
			Image scaledImage = image.getScaledInstance(232, 60,
					Image.SCALE_SMOOTH);
			g.drawImage(scaledImage, 8, 21, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FontMetrics fm = g.getFontMetrics();
		int height = fm.getHeight();
		g.setFont(new Font(g.getFont().getName(), Font.BOLD, 13));
		g.setColor(Color.BLACK);
		int a = (44 + height) / 2 + height + 20;
		g.drawString(u.name + "  " + u.getPrice() + "k", 216 / 2 - 32, a += 33);
		g.drawString("If one Utility is owned, rent is ", 16, a += 33);
		g.drawString("4 times amount shown on dice.* ", 16, a += 23);
		g.drawString("If both Utilitis are owned, rent is ", 16, a += 33);
		g.drawString("10 times amount shown on dice.* ", 16, a += 23);
		g.drawString("MORTGAGE VALUE  " + u.getMortgageValue() + "k", 16,
				a += 33);
		g.drawString("*Multiplied by 10,000 ", 16, a += 33);

	}

}