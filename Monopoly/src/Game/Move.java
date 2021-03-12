package Game;

import java.awt.Color;
import java.util.ArrayList;

import Player.Player;
import View.MyPanel;

public class Move {
	ArrayList<VariableSite> variedSites = new ArrayList<VariableSite>();
	ArrayList<VariableSpace> variedSpace = new ArrayList<VariableSpace>();
	public int activeSpace = 0;
	public boolean isBankrupt = false, isInJail = false,
			jailfreeCardUsed1 = false, jailfreeCardUsed2 = false,
			chanceCardUsed = false, communityCardUsed = false;
	ArrayList<Integer> spacesBought = new ArrayList<Integer>();

	void addVariedSite(int space) {
		variedSites.add(new VariableSite(space));

	}

	void addvariedSpace(int space) {
		variedSpace.add(new VariableSpace(space));
	}

	void setActiveSpace(int activeSpace) {
		this.activeSpace = activeSpace;
	}

	void isBankrupt(boolean isBankrupt) {
		this.isBankrupt = isBankrupt;
	}

	void isInJail(boolean isInJail) {
		this.isInJail = isInJail;
	}

	void jailFreeCardUsed1(boolean jailfreeCardUsed) {
		this.jailfreeCardUsed1 = jailfreeCardUsed;

	}

	void jailFreeCardUsed2(boolean jailfreeCardUsed2) {
		this.jailfreeCardUsed2 = jailfreeCardUsed2;
	}

	void chanceCardUsed(boolean chanceCardUsed) {
		this.chanceCardUsed = chanceCardUsed;
	}

	void communityCardUsed(boolean communityCardused) {
		this.communityCardUsed = communityCardused;
	}

	void spaceBought(int space) {
		spacesBought.add(new Integer(space));

	}

	public String giveInfo() {
		String str = "GIVE";

		str += "*ACTIVESPACE*" + activeSpace;

		str += "*ISBANKRUPT*" + isBankrupt;

		str += "*ISINJAIL*" + isInJail;

		str += "*JAILFREECARDUSED1*" + jailfreeCardUsed1;

		str += "*JAILFREECARDUSED2*" + jailfreeCardUsed2;
		str += "*SPACEBOUGHT*";
		if (spacesBought.size() == 0)
			str += "false";
		else
			for (int i = 0; i < spacesBought.size(); i++)
				str += "?" + spacesBought.get(i);
		str += "*COMMUNITYCARDUSED*" + communityCardUsed;

		str += "*CHANCECARDUSED*" + chanceCardUsed;

		str += "*SITE*" + variedSites.size();

		for (int i = 0; i < variedSites.size(); i++) {
			str += "*SPACE*" + variedSites.get(i).space;

			str += "*HOTELBOUGHT*" + variedSites.get(i).hotelBought;

			str += "*HOTELSOLD*" + variedSites.get(i).hotelSold;

			str += "*HOUSE*" + variedSites.get(i).house;

			str += "*ISMORTGAGED*" + variedSites.get(i).isMortgaged;

			str += "*ISSOLD*" + variedSites.get(i).isSold;

		}
		str += "*PROPERTY*" + variedSpace.size();

		for (int i = 0; i < variedSpace.size(); i++) {

			str += "*SPACE*" + variedSites.get(i).space;

			str += "*ISMORTGAGED*" + variedSites.get(i).isMortgaged;

			str += "*ISSOLD*" + variedSites.get(i).isSold;

		}
		return str;
	}

	public void analyseMove(String info) {
		info = info.substring(info.indexOf("%") + 1);
		String[] str;
		str = info.split("\\%");

		for (int x = 0; x < str.length; x++) {
			String[] infos = str[x].split("\\*");
			String name = infos[0];
			Player p = null;
			boolean cmp = false;
			for (int i = 0; i < MyPanel.players.size(); i++) {
				if (MyPanel.players.get(i).getName().equals(name)) {
					p = MyPanel.players.get(i);
					cmp = true;
					break;
				}
			}
			if (!cmp) {
				MyPanel.players.add(p = new Player(name));
				MyPanel.players.get(MyPanel.players.size() - 1).setColor(
						ColorHandler.getColor());
			}
			for (int i = 0; i < MyPanel.players.size(); i++)
				System.err.println(MyPanel.players.get(i).getName());

			for (int i = 0; i < infos.length; i++) {

				if (infos[i].equals("ACTIVESPACE")) {
					activeSpace = Integer.valueOf(infos[i + 1]);
					for (int j = 0; j < Board.spaces.size(); j++)
						for (int k = 0; k < Board.spaces.get(j).playersOnSpace
								.size(); k++)
							if (Board.spaces.get(j).playersOnSpace.get(k)
									.equals(name)) {
								Board.spaces.get(j).playersOnSpace.remove(k);
								break;
							}
					Board.spaces.get(Integer.parseInt(infos[i + 1]))
							.setPlayerOnSpace(p);

				}

				if (infos[i].equals("ISBANKRUPT")) {
					if (infos[i + 1].equals("true")) {
						for (int y = 0; y < MyPanel.players.size(); y++)
							if (MyPanel.players.get(y).getName().equals(name))
								MyPanel.players.remove(y);
					}
				}
				if (infos[i].equals("ISINJAIL")) {
					for (int y = 0; y < MyPanel.players.size(); y++)
						if (MyPanel.players.get(y).getName().equals(name))
							MyPanel.players.get(y).setInJail(true);

				}
				if (infos[i].equals("JAILFREECARDUSED1")) {
					for (int y = 0; y < MyPanel.players.size(); y++)
						if (MyPanel.players.get(y).getName().equals(name))
							MyPanel.players.get(y).setInJail(false);
					MyPanel.c.use(1);
				}
				if (infos[i].equals("JAILFREECARDUSED2")) {
					for (int y = 0; y < MyPanel.players.size(); y++)
						if (MyPanel.players.get(y).getName().equals(name))
							MyPanel.players.get(y).setInJail(false);
					MyPanel.c.use(2);

				}
				if (infos[i].equals("SPACEBOUGHT")) {
					System.err.println("i" + i);
					if (!infos[i + 1].equals("false")) {
						String[] spaces = infos[i + 1].split("\\?");
						for (int t = 0; t < spaces.length; t++)
							System.err.println("space" + spaces[t]);
						for (int h = 0; h < spaces.length; h++) {
							System.err.println("SpaceBOught" + h);
							if (spaces[h].equals(""))
								continue;
							int type = Board.spaces.get(
									Integer.valueOf(spaces[h])).getType();
							if (type == 1) {

								((Site) Board.spaces.get(Integer
										.valueOf(spaces[h]))).updateOwner(p);

							}
							if (type == 2) {
								System.err.println("player" + p.getName());
								((Transport) Board.spaces.get(Integer
										.valueOf(spaces[h]))).updateOwner(p);
								System.err.println("type==2");

							}
							if (type == 3) {
								((Utility) Board.spaces.get(Integer
										.valueOf(spaces[h]))).updateOwner(p);

							}
						}
					}

				}
				if (infos[i].equals("COMMUNITYCARDUSED")) {
					MyPanel.c.currentCommunityChest++;

				}
				if (infos[i].equals("CHANCECARDUSED")) {
					MyPanel.c.currentChance++;

				}
				if (infos[i].equals("SITE")) {
					int space = 0;
					for (; i < infos.length; i++) {
						{
							if (infos[i].equals("SPACE")) {
								space = Integer.valueOf(infos[i + 1]);
							}
							if (infos[i].equals("HOTELBOUGHT")) {
								((Site) Board.spaces.get(space)).setHotel(true);
							}

							if (infos[i].equals("HOTELSOLD")) {
								((Site) Board.spaces.get(space))
										.setHotel(false);

							}
							if (infos[i].equals("HOUSE")) {
								((Site) Board.spaces.get(space))
										.setHouseNum(Integer
												.valueOf(infos[i + 1]));

							}
							if (infos[i].equals("ISMORTGAGED")) {
								((Site) Board.spaces.get(space))
										.setMortgaged(Boolean
												.valueOf(infos[i + 1]));
							}
							if (infos[i].equals("ISSOLD")) {
								((Site) Board.spaces.get(space)).setOwner(null);
							}
							if (infos[i].equals("PROPERTY"))
								break;
						}
					}
				}
				if (infos[i].equals("PROPERTY")) {
					int space = 0;
					int type = 0;

					if (infos[i].equals("SPACE")) {
						space = Integer.valueOf(infos[i + 1]);
						type = Board.spaces.get(space).getType();
					}
					if (infos[i].equals("ISSOLD")) {

						if (type == 2)
							((Transport) Board.spaces.get(space))
									.setOwner(null);
						if (type == 3)
							((Utility) Board.spaces.get(space)).setOwner(null);

					}
					if (infos[i].equals("ISMORTGAGED")) {
						if (type == 2)
							((Transport) Board.spaces.get(space))
									.setMortgaged(Boolean.valueOf(infos[i + 1]));
						if (type == 3)
							((Utility) Board.spaces.get(space))
									.setMortgaged(Boolean.valueOf(infos[i + 1]));

					}
				}

			}
		}
	}

}

class VariableSite {
	int space = 0, house = 0;
	boolean hotelBought = false, hotelSold = false, isMortgaged = false,
			isSold = false;

	VariableSite(int space) {
		this.space = space;

	}

	public void setHouse(int house) {
		this.house = house;

	}

	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
	}

	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}

	public void setHotelBought(boolean hotelBought) {
		this.hotelBought = hotelBought;

	}

	public void setHotelSold(boolean hotelSold) {
		this.hotelSold = hotelSold;

	}
}

class VariableSpace {
	int space = 0;
	boolean isMortgaged, isSold;

	public VariableSpace(int space) {
		this.space = space;
	}

	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
	}

	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}
}

class ColorHandler {
	static int counter = 0;

	public static Color getColor() {
		Color c = null;
		switch (counter) {
		case 1:
			c = Color.black;
			break;
		case 2:
			c = Color.yellow;
			break;
		case 3:
			c = Color.blue;
			break;
		case 4:
			c = Color.green;
			break;
		case 5:
			c = Color.red;
			break;
		case 6:
			c = Color.white;
			break;
		case 7:
			c = Color.GRAY;
			break;
		case 8:
			c = Color.orange;
			break;
		}
		counter++;
		return c;
	}
}
