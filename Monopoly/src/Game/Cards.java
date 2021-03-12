package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Network.Client;
import Player.Player;
import View.MyPanel;

public class Cards {
	public ArrayList<Card> communityChestCards = new ArrayList<Card>();
	public ArrayList<Card> chanceCards = new ArrayList<Card>();
	int currentCommunityChest = 0;
	int currentChance = 0;

	public Cards() {
		communityChestCards.add(new AdvanceTo(1,
				"ADVANCE TO GO. \n (COLLECT 2M)", 0));
		communityChestCards
				.add(new CollectCard(
						1,
						"HOST AN EXCLUSIVE\nKARAOKE CLUB NIGHT IN\nTOKYO, JAPAN. COLLECT \n 100k IN TICKET SALES.",
						100, false));
		communityChestCards.add(new PayCard(1,
				"PAY 1M TO HOST DUBLIN'S \n SAINT PATRICK'S DAY FESTIVAL.",
				1000, false));
		communityChestCards
				.add(new CollectCard(
						1,
						"COLLECT 1M FOR HIRING OUT \n YOUR MOUNTAIN IN THE ALPS \n FOR AN EXCLUSIVE SKI TRIP",
						1000, false));
		communityChestCards.add(new PayCard(1,
				"SPEND 500K AT MUNICH'S \n CHRISTMAS MARKET", 500, false));
		communityChestCards.add(new CollectCard(1,
				"YOU GET A TAX REBATE. \n COLLECT 500K", 500, false));
		communityChestCards
				.add(new PayCard(
						1,
						"YOUR FANCY DRESS BALL \n PART OF THE RIO CARNIVAL IN \n BRAZIL, NETS YOU 200K PROFIT.",
						200, false));
		communityChestCards
				.add(new CollectCard(
						1,
						"YOUR TRAVEL COMPANY \n REALLY HITS THE BIG TIME! \n COLLECT 2M",
						2000, false));
		communityChestCards
				.add(new CollectCard(
						1,
						"ORGANISE YOUR OWN \n INTERNATIONAL MUSIC FESTIVAL \n AND RECEIVE 100K FROM \n TICKET SALES.",
						100, false));
		communityChestCards
				.add(new PayCard(
						1,
						"PAY 500K TO HOST \n A PRIVATE BEACH PARTY \n ON BONDI BEACH, SYDNEY.",
						500, false));
		communityChestCards
				.add(new CollectCard(
						1,
						"COLLECT 1M BY HIRING OUT \n YOUR INTERNATIONALLY \n ACCLAIMED ART COLLECTION.",
						1000, false));
		communityChestCards
				.add(new GoToJailCard(
						1,
						"YOU ARE INVESTIGATED FOR IDENTITY FRAUD. \n GO TO JAIL. DO NOT PASS GO, \n, DO NOT COLLECTD 2M."));
		communityChestCards
				.add(new GetOutOfJail(1,
						"GET OUT OF JAIL FREE. \n This card may be kept until needed or traded."));
		communityChestCards
				.add(new CollectCard(
						1,
						"EVERYONE CLUBS TOGETHER \n FOR AN ADERALINE RUSH IN THE \n ADVANTURE CAPITAL OF THE WORLD, \n QUEENSTOWN, NEW ZEALAND. \n COLLECT 100K FROM EVERY PLAYER.",
						100, true));
		communityChestCards
				.add(new TaxPerProperty(
						1,
						" A TAX HIKE AFFECTS \n ALL YOUR PROPERTIES. PAY 400K PER HOUSE, \n PAY 1150 PER HOTEL.",
						400, 1150));
		chanceCards
				.add(new PayCard(
						2,
						"PAY 150K TO HAVE \n ALL 32 CAPSULES \n ON THE LONDON EYE \n WASHED AND POLISHED.",
						150, false));
		chanceCards
				.add(new CollectCard(
						2,
						"COLLECT 1500k PROFIT FROM \n YOUR BELLY-DANCING CLUB \n IN THE HEART OF CAIRO.",
						1500, false));
		chanceCards
				.add(new NearestTransport(
						2,
						" ADVANCE TO THE NEAREST \n TRANSPORT SPACE. \n If UNOWNED, you may buy		 it from the Bank. \n If OWNED. pay owner \n twice the rental to which\n they are otherwise entitled. \n If you pass GO, collect 2M."));
		chanceCards
				.add(new GetOutOfJail(2,
						" GET OUT OF JAIL FREE. \n This card may be kept until needed or traded."));
		chanceCards.add(new GoBack(2, "GO BACK \n THREE SPACES."));
		chanceCards
				.add(new CollectCard(
						2,
						"COLLECT 500K PROFIT FROM \n YOUR BICYCLE REPAIR SHOP \n IN BEIJING, CHINA.",
						500, false));
		chanceCards.add(new CollectCard(2, "ADVANCE TO GO. \n (COLLECT 2M)",
				2000, false));
		chanceCards
				.add(new GoToJailCard(
						2,
						" INTERNATIONAL FRAUD SQUAD CATCHES UP WITH \n YOU. GO TO JAIL. DO NOT PASS GO, \n, DO NOT COLLECTD 2M."));
		chanceCards.add(new AdvanceTo(2, "ADVANCE TO MONTREAL", 39));
		chanceCards
				.add(new AdvanceToUtility(
						2,
						" ADVANCE TO \n THE NEAREST UTILITY.\n If UNOWNED you may \n buy it from the Bank. \n If OWNED, throw dice and \n pay owner a total 10,000 \n times amount thrown. \n If you pass GO, collect 2M"));
		chanceCards.add(new PayCard(2,
				"PAY EACH PLAYER 500K TO SAMPLE FOOD FROM ALL OVER THE WORLD",
				500, true));
		chanceCards.add(new AdvanceTo(2,
				"ADVANCE TO ISTANBUL \n IF YOU PASS GO \n COLLECT 2M", 11));
		chanceCards.add(new AdvanceTo(2,
				"ADVANCE TO LONDON. \n IF YOU PASS GO \n COLLECT 2M", 24));
		chanceCards
				.add(new TaxPerProperty(
						2,
						" YOU INVITE A RENOWNED \n INTERNEATIONAL DESIGNER TO \n MAKE YOUR PROPERTIES. \n FOR EACH HOUSE PAY 250K, \n FOR EACH HOTEL PAY 1M",
						250, 1000));
		chanceCards
				.add(new NearestTransport(
						2,
						"ADVANCE TO THE NEAREST \n TRANSPORT SPACE. \n If UNOWNED, you may buy it from the Bank. \n If OWNED. pay owner \n twice the rental to which\n they are otherwise entitled. \n If you pass Go, collect 2M."));
	}

	public void use(int type) {
		if (type == 1) {
			MyPanel.c.communityChestCards
					.add(new GetOutOfJail(1,
							"GET OUT OF JAIL FREE. \n This card may be kept until needed or traded."));
			MyPanel.p.setInJail(false);
			MyPanel.p.setHasJailFreeCard1(false);
			Client.m.jailFreeCardUsed1(true);
		}
		if (type == 2) {
			MyPanel.c.chanceCards
					.add(new GetOutOfJail(2,
							" GET OUT OF JAIL FREE. \n This card may be kept until needed or traded."));
			MyPanel.p.setInJail(false);
			MyPanel.p.setHasJailFreeCard2(false);
			Client.m.jailFreeCardUsed2(true);

		}

	}
}

abstract class Card {
	String txt;
	int type;

	abstract void function(Player playerOnSpace);

	public void drawInFrame(Player playerOnSpace) {
		@SuppressWarnings("unused")
		final ShowCardInfo frame = new ShowCardInfo(this, playerOnSpace);
	}
}

class AdvanceToUtility extends Card {
	AdvanceToUtility(int type, String txt) {
		this.type = type;
		this.txt = txt;
	}

	@Override
	void function(Player playerOnSpace) {
		for (int i = playerOnSpace.getLocation(); i < 40; i++) {
			if (Board.spaces.get(i).getType() == 3) {
				playerOnSpace.setLocation(i);
				Board.spaces.get(i).setPlayerOnSpace(playerOnSpace);
				Board.spaces.get(i).function();
				return;
			}
		}
		for (int i = 0; i < playerOnSpace.getLocation(); i++) {
			if (Board.spaces.get(i).getType() == 3) {
				playerOnSpace.setLocation(i);
				Board.spaces.get(i).setPlayerOnSpace(playerOnSpace);
				Board.spaces.get(i).function();
				playerOnSpace.setMoney(playerOnSpace.getMoney() + 2000);
				return;
			}
		}

	}

}

class GoBack extends Card {
	GoBack(int type, String txt) {
		this.txt = txt;
		this.type = type;
	}

	@Override
	void function(Player playerOnSpace) {
		Board.spaces.get(playerOnSpace.getLocation() - 3).playersOnSpace
				.add(playerOnSpace);
		playerOnSpace.setLocation(playerOnSpace.getLocation() - 3);
		Board.spaces.get(playerOnSpace.getLocation() - 3).function();

	}

}

class AdvanceTo extends Card {
	int space;

	AdvanceTo(int type, String txt, int space) {
		this.txt = txt;
		this.space = space;
		this.type = type;
	}

	@Override
	void function(Player playerOnSpace) {
		Board.spaces.get(space).setPlayerOnSpace(playerOnSpace);
		if (playerOnSpace.getLocation() > space) {
			playerOnSpace.setMoney(playerOnSpace.getMoney() + 2000);
		}
		playerOnSpace.setLocation(space);
		Board.spaces.get(space).function();

	}

}

class TaxPerProperty extends Card {
	int hoteltax, housetax;

	public TaxPerProperty(int type, String txt, int housetax, int hoteltax) {
		this.type = type;
		this.txt = txt;
		this.housetax = housetax;
		this.hoteltax = hoteltax;
	}

	@Override
	void function(Player activePlayer) {
		int tax = 0;
		for (int i = 0; i < Board.spaces.size(); i++) {
			if (Board.spaces.get(i).getType() == 1) {
				if (((Site) Board.spaces.get(i)).getOwner().equals(
						activePlayer.getName())) {
					tax += ((Site) Board.spaces.get(i)).getHouseNum()
							* housetax;
					if (((Site) Board.spaces.get(i)).hasHotel() == true)
						tax += hoteltax;
				}

			}
		}
		MyPanel.p.setMoney(MyPanel.p.getMoney() - tax);

	}

}

class NearestTransport extends Card {
	NearestTransport(int type, String txt) {
		this.txt = txt;
		this.type = type;

	}

	@Override
	void function(Player playerOnSpace) {
		for (int i = playerOnSpace.getLocation(); i < 40; i++) {
			if (Board.spaces.get(i).getType() == 2) {
				playerOnSpace.setLocation(i);
				Board.spaces.get(i).setPlayerOnSpace(playerOnSpace);
				Board.spaces.get(i).function();
				return;
			}
		}
		for (int i = 0; i < playerOnSpace.getLocation(); i++) {
			if (Board.spaces.get(i).getType() == 2) {
				playerOnSpace.setLocation(i);
				Board.spaces.get(i).setPlayerOnSpace(playerOnSpace);
				Board.spaces.get(i).function();
				playerOnSpace.setMoney(playerOnSpace.getMoney() + 2000);
				return;
			}
		}
	}

}

class GetOutOfJail extends Card {
	GetOutOfJail(int type, String txt) {
		this.txt = txt;
		this.type = type;

	}

	@Override
	void function(Player playerOnSpace) {
		if (type == 1) {
			MyPanel.p.setHasJailFreeCard1(true);
			for (int i = 0; i < MyPanel.c.communityChestCards.size(); i++)
				if (MyPanel.c.communityChestCards.get(i).txt
						.equals("GET OUT OF JAIL FREE. \n This card may be kept until needed or traded."))
					MyPanel.c.communityChestCards.remove(i);
		}
		if (type == 2) {
			for (int i = 0; i < MyPanel.c.chanceCards.size(); i++)
				if (MyPanel.c.chanceCards.get(i).txt
						.equals("GET OUT OF JAIL FREE. \n This card may be kept until needed or traded."))
					MyPanel.c.chanceCards.remove(i);
		}

	}

}

class GoToJailCard extends Card {
	GoToJailCard(int type, String txt) {
		this.txt = txt;
		this.type = type;

	}

	@Override
	void function(Player playerOnSpace) {
		MyPanel.p.setInJail(true);

	}

}

class CollectCard extends Card {
	int amount;
	boolean fromEach;

	CollectCard(int type, String txt, int amount, boolean fromEach) {
		this.type = type;
		this.amount = amount;
		this.txt = txt;
		this.fromEach = fromEach;

	}

	@Override
	void function(Player playerOnSpace) {
		playerOnSpace.setMoney(playerOnSpace.getMoney() + amount);
	}

}

class PayCard extends Card {
	int amount;
	boolean toEach;

	PayCard(int type, String txt, int amount, boolean toEach) {
		this.type = type;
		this.amount = amount;
		this.txt = txt;
		this.toEach = toEach;
	}

	@Override
	void function(Player playerOnSpace) {
		if (!toEach)
			playerOnSpace.setMoney(playerOnSpace.getMoney() - amount);
		if (toEach) {
			// TODO
		}

	}

}

class ShowCardInfo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Card c;
	String url = null;
	Player playerOnSpace;

	ShowCardInfo(final Card c, final Player playerOnSpace) {
		this.playerOnSpace = playerOnSpace;
		String title = null;
		this.c = c;
		if (c.type == 1) {
			title = "COMMUNITY CHEST";
			url = "src/images/image/Body_Giving_AOC-Mr_Monopoly_web.png";
		}
		if (c.type == 2) {
			title = "CHANCE";
			url = "src/images/image/MO_CA_05.png";
		}
		this.setTitle(title);
		this.setSize(300, 230);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				c.function(playerOnSpace);
				dispose();
			}

		});
		this.setAlwaysOnTop(true);
		this.setResizable(false);
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
			g.fillRect(0, 0, 300, 230);
			g.setColor(Color.black);
			int a = 10;
			g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
			String s = c.txt;
			for (String line : s.split("\n"))
				g.drawString(line, 10, a += 20);
			try {
				BufferedImage image = ImageIO.read(new File(url));
				Image scaledImage = image.getScaledInstance(100, 100,
						Image.SCALE_SMOOTH);
				g.drawImage(scaledImage, 200, 50, null);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	};

}