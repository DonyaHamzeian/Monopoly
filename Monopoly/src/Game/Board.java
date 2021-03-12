package Game;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.Controller;
import Network.Client;
import View.MyPanel;

public class Board {
	int ans;
	public static ArrayList<Space> spaces = new ArrayList<Space>();
	boolean once = true;

	public Board() {
		// the board will be set in the beginning of the game with the
		// components, the rents, the price, type and name
		if (once) {
			spaces.add(new Go(spaces.size(), 483, 506 - 26, 553, 578 - 26));
			spaces.add(new Site(438, 506 - 26, 478, 578 - 26, "GDYNIA", spaces
					.size(), 1, 600, 300, 500, 500, 20, 100, 300, 900, 1600,
					2500));
			spaces.add(new CommunityChest(spaces.size(), 393, 506 - 26, 434,
					578 - 26));
			spaces.add(new Site(346, 506 - 26, 391, 578 - 26, "TAPEI", spaces
					.size(), 1, 600, 300, 500, 500, 40, 200, 600, 1800, 3200,
					4500));
			spaces.add(new IncomeTax(spaces.size(), 303, 506 - 26, 345,
					578 - 26));
			spaces.add(new Transport(258, 506 - 26, 300, 578 - 26,
					"MONOPOLY RAIL", spaces.size(), 2000, 1000, 250, 500, 1000,
					2000));
			spaces.add(new Site(213, 506 - 26, 255, 578 - 26, "TOKYO", spaces
					.size(), 2, 1000, 500, 500, 500, 60, 300, 900, 2700, 4000,
					5500));
			spaces.add(new Chance(spaces.size(), 169, 506 - 26, 213, 578 - 26));
			spaces.add(new Site(124, 506 - 26, 167, 578 - 26, "BARCELONA",
					spaces.size(), 2, 1000, 500, 500, 500, 60, 300, 900, 2700,
					4000, 5500));
			spaces.add(new Site(79, 506 - 26, 122, 578 - 26, "ATHENS", spaces
					.size(), 2, 1200, 600, 500, 500, 80, 400, 1000, 3000, 4500,
					6000));
			spaces.add(new Jail(spaces.size(), 6, 506 - 26, 77, 578 - 26));
			spaces.add(new Site(6, 467 - 26, 77, 505 - 26, "ISTANBUL", spaces
					.size(), 3, 1400, 700, 1000, 1000, 100, 500, 1500, 4500,
					6250, 7500));
			spaces.add(new Utility(6, 417 - 26, 77, 460 - 26, "SOLAR ENERGY",
					1500, 750));
			spaces.add(new Site(6, 372 - 26, 77, 415 - 26, "KYIV", spaces
					.size(), 3, 1400, 700, 1000, 1000, 100, 500, 1500, 4500,
					6250, 7500));
			spaces.add(new Site(6, 329 - 26, 77, 371 - 26, "TORONTO", spaces
					.size(), 3, 1600, 800, 1000, 1000, 120, 600, 1800, 5000,
					7000, 9000));
			spaces.add(new Transport(6, 283 - 26, 77, 327 - 26, "MONOPOLY AIR",
					spaces.size(), 2000, 1000, 250, 500, 1000, 2000));
			spaces.add(new Site(6, 240 - 26, 77, 283 - 26, "ROME", spaces
					.size(), 4, 1800, 900, 1000, 1000, 140, 700, 2000, 5500,
					7500, 9500));
			spaces.add(new CommunityChest(spaces.size(), 6, 195 - 26, 77,
					236 - 26));
			spaces.add(new Site(6, 149 - 26, 77, 191 - 26, "SHANGHAI", spaces
					.size(), 4, 1800, 900, 1000, 1000, 140, 700, 2000, 5500,
					7500, 9500));
			spaces.add(new Site(6, 105 - 26, 77, 145 - 26, "VANCOUVER", spaces
					.size(), 4, 2000, 1000, 1000, 1000, 160, 800, 2200, 6000,
					8000, 10000));
			spaces.add(new FreeParking(spaces.size(), 7, 30 - 26, 78, 103 - 26));
			spaces.add(new Site(80, 30 - 26, 124, 103 - 26, "SYDNEY", spaces
					.size(), 5, 2200, 1100, 1500, 1500, 180, 900, 2500, 7000,
					8750, 10500));
			spaces.add(new Chance(spaces.size(), 124, 30 - 26, 167, 103 - 26));
			spaces.add(new Site(170, 7, 214, 80, "NEW YORK", spaces.size(), 5,
					2200, 1100, 1500, 1500, 180, 900, 2500, 7000, 8750, 10500));
			spaces.add(new Site(216, 7, 260, 78, "LONDON", spaces.size(), 5,
					2400, 1200, 1500, 1500, 200, 1000, 3000, 7500, 9250, 11000));
			spaces.add(new Transport(258, 30 - 26, 304, 103 - 26,
					"MONOPOLY CRUISE", spaces.size(), 2000, 1000, 250, 500,
					1000, 2000));
			spaces.add(new Site(303, 30 - 26, 347, 103 - 26, "BEIJING", spaces
					.size(), 6, 2600, 1300, 1500, 1500, 220, 1100, 3300, 8000,
					9750, 11500));
			spaces.add(new Site(348, 30 - 26, 393, 103 - 26, "HONG KONG",
					spaces.size(), 6, 2600, 1300, 1500, 1500, 220, 1100, 3300,
					8000, 9750, 11500));
			spaces.add(new Utility(392, 30 - 26, 438, 103 - 26, "WIND ENERGY",
					1500, 750));
			spaces.add(new Site(439, 30 - 26, 484, 103 - 26, "JERUSALEM",
					spaces.size(), 6, 2800, 1400, 1500, 1500, 240, 1200, 3600,
					8500, 10250, 12000));
			spaces.add(new GoToJail(spaces.size(), 483, 30 - 26, 577, 103 - 26));
			spaces.add(new Site(483, 105 - 26, 554, 148 - 26, "PARIS", spaces
					.size(), 7, 3000, 1500, 2000, 2000, 260, 1300, 3900, 9000,
					11000, 12750));
			spaces.add(new Site(483, 148 - 26, 554, 193 - 26, "BELGRADE",
					spaces.size(), 7, 3000, 1500, 2000, 2000, 260, 1300, 3900,
					9000, 11000, 12750));
			spaces.add(new CommunityChest(spaces.size(), 483, 194 - 26, 554,
					236 - 26));
			spaces.add(new Site(483, 238 - 26, 554, 280 - 26, "CAPE TOWN",
					spaces.size(), 7, 3200, 1600, 2000, 2000, 280, 1500, 4500,
					10000, 12000, 14000));
			spaces.add(new Transport(483, 283 - 26, 554, 326 - 26,
					"MONOPOLY SPACE", spaces.size(), 2000, 1000, 250, 500,
					1000, 2000));
			spaces.add(new Chance(spaces.size(), 483, 329 - 26, 553, 370 - 26));
			spaces.add(new Site(483, 371 - 26, 554, 417 - 26, "RIGA", spaces
					.size(), 8, 3500, 1750, 2000, 2000, 350, 1750, 5000, 11000,
					13000, 15000));
			spaces.add(new SuperTax(spaces.size(), 483, 419 - 26, 553, 460 - 26));
			spaces.add(new Site(498, 459 - 26, 553, 505 - 26, "MONTREAL",
					spaces.size(), 8, 4000, 2000, 2000, 2000, 500, 2000, 6000,
					14000, 17000, 20000));

			once = false;
		}

	}

}

class Go extends Space {

	Go(int num, int x, int y, int w, int h) {
		this.num = num;
		this.name = "GO";
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	@Override
	public void drawInFrame() {

	}

	@Override
	public void function() {
		Controller.repaint();

	}

	@Override
	public void showMessage() {
		String players = "";
		if (this.playersOnSpace.size() != 0)
			for (int i = 0; i < this.playersOnSpace.size(); i++)
				players += "\n" + this.playersOnSpace.get(i).getName();

		JOptionPane.showMessageDialog(null, "Players on this space:   "
				+ players, this.name, JOptionPane.CLOSED_OPTION);
	}

}

class Chance extends Space {

	Chance(int num, int x, int y, int w, int h) {
		this.num = num;
		this.name = "CHANCE";
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	@Override
	public void drawInFrame() {

	}

	@Override
	public void function() {
		// chooses one of the chances cards
		MyPanel.c.chanceCards.get(
				MyPanel.c.currentChance % MyPanel.c.chanceCards.size())
				.drawInFrame(MyPanel.p);
		MyPanel.c.currentChance++;
		Controller.repaint();
		Client.m.chanceCardUsed= true;
		Controller.repaint();

	}

	@Override
	public void showMessage() {
		String players = "";
		if (this.playersOnSpace.size() != 0)
			for (int i = 0; i < this.playersOnSpace.size(); i++)
				players += "\n" + this.playersOnSpace.get(i).getName();

		JOptionPane.showMessageDialog(null, "Players on this space:   "
				+ players, this.name, JOptionPane.CLOSED_OPTION);
	}
}

class CommunityChest extends Space {
	int location;

	CommunityChest(int location, int x, int y, int w, int h) {
		this.location = location;
		this.name = "COMMUNITY CHEST";
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

	}

	@Override
	public void drawInFrame() {

	}

	@Override
	public void function() {
		// chooses one of the community chest cards
		MyPanel.c.communityChestCards.get(
				MyPanel.c.currentCommunityChest
						% MyPanel.c.communityChestCards.size()).drawInFrame(
				MyPanel.p);
		MyPanel.c.currentCommunityChest++;
		
		Controller.repaint();
		Client.m.communityCardUsed=true;
		Controller.repaint();
	}

	@Override
	public void showMessage() {
		String players = "";
		if (this.playersOnSpace.size() != 0)
			for (int i = 0; i < this.playersOnSpace.size(); i++)
				players += "\n" + this.playersOnSpace.get(i).getName();

		JOptionPane.showMessageDialog(null, "Players on this space:   "
				+ players, this.name, JOptionPane.CLOSED_OPTION);
	}
}

class IncomeTax extends Space {
	int location;

	IncomeTax(int location, int x, int y, int w, int h) {
		this.location = location;
		this.name = "INCOME TAX";
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

	}

	@Override
	public void drawInFrame() {

	}

	@Override
	public void function() {
		// the player pays the money
		MyPanel.p.setMoney(MyPanel.p.getMoney() - 2000);
		Controller.repaint();
	}

	@Override
	public void showMessage() {
		String players = "";
		if (this.playersOnSpace.size() != 0)
			for (int i = 0; i < this.playersOnSpace.size(); i++)
				players += "\n" + this.playersOnSpace.get(i).getName();

		JOptionPane.showMessageDialog(null, "Players on this space:   "
				+ players, this.name, JOptionPane.CLOSED_OPTION);
	}
}

class FreeParking extends Space {
	FreeParking(int num, int x, int y, int w, int h) {
		this.num = num;
		this.name = "FREE PARKING";
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;

	}

	@Override
	public void drawInFrame() {

	}

	@Override
	public void function() {
		Controller.repaint();
	}

	@Override
	public void showMessage() {
		String players = "";
		if (this.playersOnSpace.size() != 0)
			for (int i = 0; i < this.playersOnSpace.size(); i++)
				players += "\n" + this.playersOnSpace.get(i).getName();

		JOptionPane.showMessageDialog(null, "Players on this space:   "
				+ players, this.name, JOptionPane.CLOSED_OPTION);
	}
}

class GoToJail extends Space {
	GoToJail(int num, int x, int y, int w, int h) {
		this.name = "GO TO JAIL";
		this.num = num;
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;

	}

	@Override
	public void drawInFrame() {

	}

	@Override
	public void function() {
		// send the player to the jail
		MyPanel.p.setInJail(true);

		Controller.repaint();
	}

	@Override
	public void showMessage() {
		String players = "";
		if (this.playersOnSpace.size() != 0)
			for (int i = 0; i < this.playersOnSpace.size(); i++)
				players += "\n" + this.playersOnSpace.get(i).getName();

		JOptionPane.showMessageDialog(null, "Players on this space:   "
				+ players, this.name, JOptionPane.CLOSED_OPTION);
	}

}

class SuperTax extends Space {
	SuperTax(int num, int x, int y, int w, int h) {
		this.num = num;
		this.name = "SUPER TAX";
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;

	}

	@Override
	public void drawInFrame() {

	}

	@Override
	public void function() {
		// the player pays the money
		MyPanel.p.setMoney(MyPanel.p.getMoney() - 1000);
		Controller.repaint();
	}

	@Override
	public void showMessage() {
		String players = "";
		if (this.playersOnSpace.size() != 0)
			for (int i = 0; i < this.playersOnSpace.size(); i++)
				players += "\n" + this.playersOnSpace.get(i).getName();

		JOptionPane.showMessageDialog(null, "Players on this space:   "
				+ players, this.name, JOptionPane.CLOSED_OPTION);
	}

}