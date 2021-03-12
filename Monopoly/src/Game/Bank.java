package Game;

import java.util.Random;

import Player.Player;

public class Bank {
	// type: 1= site, 2= transport, 3= utility
	public boolean canReleaseMortgage(int type, Player p, int space) {
		// only the owner can release the mortgage, and only if the space is
		// mortgaged
		switch (type) {
		case 1:
			if (((Site) Board.spaces.get(space)).getOwner() == null)
				return false;
			if (((Site) Board.spaces.get(space)).isMortgaged() == false)
				return false;
			if (((Site) Board.spaces.get(space)).getOwner().getName()
					.equals(p.getName()))
				return true;
			else
				return false;
		case 2:
			if (((Transport) Board.spaces.get(space)).getOwner() == null)
				return false;
			if (((Transport) Board.spaces.get(space)).isMortgaged() == false)
				return false;
			if (((Transport) Board.spaces.get(space)).getOwner().getName()
					.equals(p.getName()))
				return true;
			else
				return false;

		case 3:
			if (((Utility) Board.spaces.get(space)).getOwner() == null)
				return false;
			if (((Utility) Board.spaces.get(space)).getOwner().getName()
					.equals(p.getName()))
				return true;
			else
				return false;

		}

		return false;
	}

	public boolean releaseMortgage(int type, Player p, int space) {
		// only if the owner has enough money can release mortgage
		switch (type) {
		case 1:
			if (p.getMoney() >= ((Site) Board.spaces.get(space))
					.getMortgageValue()) {
				p.setDebt(p.getDebt()
						- ((Site) Board.spaces.get(space)).getMortgageValue());
				p.setMoney(p.getMoney() - 11 / 10
						* (((Site) Board.spaces.get(space)).getMortgageValue()));
				((Site) Board.spaces.get(space)).setMortgaged(false);
				return true;
			} else
				return false;
		case 2:
			if (p.getMoney() >= ((Transport) Board.spaces.get(space))
					.getMortgageValue()) {
				p.setDebt(p.getDebt()
						- ((Transport) Board.spaces.get(space))
								.getMortgageValue());
				p.setMoney(p.getMoney()
						- 110
						/ 100
						* (((Transport) Board.spaces.get(space))
								.getMortgageValue()));
				((Transport) Board.spaces.get(space)).setMortgaged(false);
				return true;
			} else
				return false;
		case 3:
			if (p.getMoney() >= ((Utility) Board.spaces.get(space))
					.getMortgageValue()) {
				p.setDebt(p.getDebt()
						- ((Utility) Board.spaces.get(space))
								.getMortgageValue());
				p.setMoney(p.getMoney()
						- 110
						/ 100
						* (((Utility) Board.spaces.get(space))
								.getMortgageValue()));
				((Utility) Board.spaces.get(space)).setMortgaged(false);
				return true;
			} else
				return false;
		}
		return false;
	}

	public boolean setMortgage(int type, Player p, int space) {
		// the space will be set as mortgaged and the bank will give the owner
		// the mortgage value and it will be added to the owner's debt
		switch (type) {
		case 1:
			((Site) Board.spaces.get(space)).setMortgaged(true);
			p.setMoney(p.getMoney()
					+ ((Site) Board.spaces.get(space)).getMortgageValue());
			p.setDebt(((Site) Board.spaces.get(space)).getMortgageValue());
			break;
		case 2:
			((Transport) Board.spaces.get(space)).setMortgaged(true);
			p.setMoney(p.getMoney()
					+ ((Transport) Board.spaces.get(space)).getMortgageValue());
			p.setDebt(((Transport) Board.spaces.get(space)).getMortgageValue());
			break;
		case 3:
			((Utility) Board.spaces.get(space)).setMortgaged(true);
			p.setMoney(p.getMoney()
					+ ((Utility) Board.spaces.get(space)).getMortgageValue());
			p.setDebt(((Utility) Board.spaces.get(space)).getMortgageValue());
			break;

		}

		return false;
	}

	public boolean canSetMortgage(int type, Player p, int space) {

		// only if the player is the owner and the space is not mortgaged and if
		// the owner doesn't have any houses or hotels on any sites can set the
		// space on mortgage
		for (int i = 0; i < Board.spaces.size(); i++)
			if (Board.spaces.get(i).getType() == 1) {
				if (((Site) Board.spaces.get(i)).getOwner() == null)
					continue;
				if (((Site) Board.spaces.get(i)).getOwner().getName() == p
						.getName()
						&& (((Site) Board.spaces.get(i)).hasHotel() == true || ((Site) Board.spaces
								.get(i)).getHouseNum() > 0))
					return false;
			}
		if (type == 1) {
			if (((Site) Board.spaces.get(space)).getOwner() == null)
				return false;
			if (((Site) Board.spaces.get(space)).isMortgaged() == true) {
				return false;
			}
			if (((Site) Board.spaces.get(space)).getOwner().getName()
					.equals(p.getName()))
				return true;
			else
				return false;
		}
		if (type == 2) {
			if (((Transport) Board.spaces.get(space)).getOwner() == null)
				return false;
			if (((Transport) Board.spaces.get(space)).getOwner().getName()
					.equals(p.getName()))
				return true;
			else
				return false;

		}
		if (type == 3) {
			if (((Utility) Board.spaces.get(space)).getOwner() == null)
				return false;
			if (((Utility) Board.spaces.get(space)).getOwner().getName()
					.equals(p.getName()))
				return true;
			else
				return false;

		}

		return false;
	}

	public boolean canBuyHouse(Player p, int space) {
		// only the owner can buy house on the space: if the owner has all sites
		// in the color of the space and the houses must be bought in order and
		// if the space has hotel no more houses can be bought
		if (((Site) Board.spaces.get(space)).getOwner() == null)
			return false;
		if (((Site) Board.spaces.get(space)).getHouseNum() == 4
				|| !((Site) Board.spaces.get(space)).getOwner().getName()
						.equals(p.getName()))
			return false;

		int color = ((Site) Board.spaces.get(space)).getColor();
		for (int i = 0; i < Board.spaces.size(); i++)
			if (Board.spaces.get(i).getType() == 1)
				if (((Site) Board.spaces.get(i)).getColor() == color
						&& ((Site) Board.spaces.get(i)).isMortgaged())
					return false;

		if (((Site) Board.spaces.get(space)).getHouseNum() == 0
				&& ((Site) Board.spaces.get(space)).hasHotel() == false) {
			for (int i = 0; i < Board.spaces.size(); i++)
				if (Board.spaces.get(i).getType() == 1)
					if (((Site) Board.spaces.get(i)).getColor() == color) {
						if (((Site) Board.spaces.get(i)).getOwner() == null)
							return false;
						if (!((Site) Board.spaces.get(i)).getOwner().getName()
								.equals(p.getName()))
							return false;
					}

			return true;

		}
		if (((Site) Board.spaces.get(space)).getHouseNum() == 1) {
			for (int i = 0; i < Board.spaces.size(); i++)
				if (Board.spaces.get(i).getType() == 1)
					if (((Site) Board.spaces.get(i)).getColor() == color
							&& ((Site) Board.spaces.get(i)).getHouseNum() == 0)
						return false;
			return true;
		}
		if (((Site) Board.spaces.get(space)).getHouseNum() == 2) {
			for (int i = 0; i < Board.spaces.size(); i++)
				if (Board.spaces.get(i).getType() == 1)
					if (((Site) Board.spaces.get(i)).getColor() == color
							&& ((Site) Board.spaces.get(i)).getHouseNum() == 1)
						return false;
			return true;

		}
		if (((Site) Board.spaces.get(space)).getHouseNum() == 3) {
			for (int i = 0; i < Board.spaces.size(); i++)
				if (Board.spaces.get(i).getType() == 1)
					if (((Site) Board.spaces.get(i)).getColor() == color
							&& ((Site) Board.spaces.get(i)).getHouseNum() == 2)
						return false;

			return true;

		}

		return false;
	}

	public int getActiveRent(int space) {
		// if a player has all sites with the same color of the space (without
		// any houses or hotels) the rent is doubled, and the rent is varied
		// based on the number of houses in each space
		if (Board.spaces.get(space).getType() == 1) {
			if (((Site) Board.spaces.get(space)).isMortgaged())
				return 0;
			if (((Site) Board.spaces.get(space)).getOwner() == null)
				return 0;
			if (((Site) Board.spaces.get(space)).hasHotel() == true)
				return ((Site) Board.spaces.get(space)).rents[5];
			if (((Site) Board.spaces.get(space)).getHouseNum() == 0) {
				int color = ((Site) Board.spaces.get(space)).getColor();
				for (int i = 0; i < Board.spaces.size(); i++)
					if (Board.spaces.get(i).getType() == 1) {
						if (((Site) Board.spaces.get(i)).getOwner() == null)
							break;
						if (((Site) Board.spaces.get(i)).getColor() == color
								&& !((Site) Board.spaces.get(i))
										.getOwner()
										.getName()
										.equals(((Site) Board.spaces.get(space))
												.getOwner().getName()))
							return ((Site) Board.spaces.get(space)).rents[0];
					}

				return ((Site) Board.spaces.get(space)).rents[0] * 2;
			}
			if (((Site) Board.spaces.get(space)).getHouseNum() == 1)
				return ((Site) Board.spaces.get(space)).rents[1];
			if (((Site) Board.spaces.get(space)).getHouseNum() == 2)
				return ((Site) Board.spaces.get(space)).rents[2];
			if (((Site) Board.spaces.get(space)).getHouseNum() == 3)
				return ((Site) Board.spaces.get(space)).rents[3];
			if (((Site) Board.spaces.get(space)).getHouseNum() == 4)
				return ((Site) Board.spaces.get(space)).rents[4];
		}
		if (Board.spaces.get(space).getType() == 2) {
			if (((Transport) Board.spaces.get(space)).getOwner() == null)
				return 0;
			int number = 0;
			for (int i = 0; i < Board.spaces.size(); i++)
				if (Board.spaces.get(i).getType() == 2) {
					if (((Transport) Board.spaces.get(i)).getOwner() == null)
						continue;
					if (((Transport) Board.spaces.get(i))
							.getOwner()
							.getName()
							.equals(((Transport) Board.spaces.get(space))
									.getOwner().getName()))
						number++;
				}
			switch (number) {
			case 1:
				return ((Transport) Board.spaces.get(space)).rents[0];
			case 2:
				return ((Transport) Board.spaces.get(space)).rents[1];
			case 3:
				return ((Transport) Board.spaces.get(space)).rents[2];
			case 4:
				return ((Transport) Board.spaces.get(space)).rents[3];
			}
		}
		if (Board.spaces.get(space).getType() == 3) {
			if (((Utility) Board.spaces.get(space)).getOwner() == null)
				return 0;
			int number = 0;
			for (int i = 0; i < Board.spaces.size(); i++)
				if (Board.spaces.get(i).getType() == 3)
					if (((Utility) Board.spaces.get(i))
							.getOwner()
							.getName()
							.equals(((Transport) Board.spaces.get(space))
									.getOwner().getName()))
						number++;
			int one = randInt(1, 6);
			int two = randInt(1, 6);
			int sum = one + two;
			switch (number) {
			case 1:
				return sum * 40;
			case 2:
				return sum * 100;
			}

		}

		return -1;
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public boolean buyHouse(Player p, int space) {
		// if the owner has enough money can buy house
		if (p.getMoney() >= ((Site) Board.spaces.get(space)).getHousePrice()) {
			((Site) Board.spaces.get(space)).setHouseNum(((Site) Board.spaces
					.get(space)).getHouseNum() + 1);
			p.setMoney(p.getMoney()
					- ((Site) Board.spaces.get(space)).getHousePrice());
			return true;
		}
		return false;
	}

	public boolean canSellSite(Player p, int space) {
		// TODO auction
		// the site can't be sold if there are any houses or hotels on any sites
		// of the group similar to space
		if (Board.spaces.get(space).getType() == 1) {
			if (((Site) Board.spaces.get(space)).getOwner() == null)
				return false;
			int color = ((Site) Board.spaces.get(space)).getColor();
			for (int i = 0; i < Board.spaces.size(); i++)
				if (Board.spaces.get(i).getType() == 1)
					if (((Site) Board.spaces.get(i)).getColor() == color
							&& (((Site) Board.spaces.get(i)).getHouseNum() > 0 || ((Site) Board.spaces
									.get(i)).hasHotel() == true))
						return false;
		}
		return true;
	}

	public boolean buySpace(Player p, int space) {
		// if the space doesn't have any owner and the player has enough money
		// can buy that space
		if (Board.spaces.get(space).getType() == 1)
			if (((Site) Board.spaces.get(space)).getPrice() <= p.getMoney()) {
				((Site) Board.spaces.get(space)).setOwner(p);
				p.setMoney(p.getMoney()
						- ((Site) Board.spaces.get(space)).getPrice());
				return true;
			}
		if (Board.spaces.get(space).getType() == 2)
			if (((Transport) Board.spaces.get(space)).getPrice() <= p
					.getMoney()) {
				((Transport) Board.spaces.get(space)).setOwner(p);
				p.setMoney(p.getMoney()
						- ((Transport) Board.spaces.get(space)).getPrice());
				return true;
			}
		if (Board.spaces.get(space).getType() == 3)
			if (((Utility) Board.spaces.get(space)).getPrice() <= p.getMoney()) {
				((Utility) Board.spaces.get(space)).setOwner(p);
				p.setMoney(p.getMoney()
						- ((Utility) Board.spaces.get(space)).getPrice());
				return true;
			}

		return false;
	}

	public boolean canSellHouse(Player p, int space) {
		// only the owner can sell house and the houses must be sold in order,
		// you can't sell any house if you don't have any
		if (((Site) Board.spaces.get(space)).getOwner() == null)
			return false;
		if (((Site) Board.spaces.get(space)).getHouseNum() == 0
				|| !((Site) Board.spaces.get(space)).getOwner().getName()
						.equals(p.getName()))
			return false;
		for (int i = 0; i < Board.spaces.size(); i++)
			if (Board.spaces.get(i).getType() == 1)
				if (((Site) Board.spaces.get(i)).hasHotel() == true)
					return false;
		int color = ((Site) Board.spaces.get(space)).getColor();
		if (((Site) Board.spaces.get(space)).getHouseNum() == 4)
			return true;
		if (((Site) Board.spaces.get(space)).getHouseNum() == 1) {
			for (int i = 0; i < Board.spaces.size(); i++)
				if (Board.spaces.get(i).getType() == 1)
					if (((Site) Board.spaces.get(i)).getColor() == color
							&& ((Site) Board.spaces.get(i)).getHouseNum() == 2)
						return false;
			return true;
		}
		if (((Site) Board.spaces.get(space)).getHouseNum() == 2) {
			for (int i = 0; i < Board.spaces.size(); i++)
				if (Board.spaces.get(i).getType() == 1)
					if (((Site) Board.spaces.get(i)).getColor() == color
							&& ((Site) Board.spaces.get(i)).getHouseNum() == 3)
						return false;
			return true;

		}
		if (((Site) Board.spaces.get(space)).getHouseNum() == 3) {
			for (int i = 0; i < Board.spaces.size(); i++)
				if (Board.spaces.get(i).getType() == 1)
					if (((Site) Board.spaces.get(i)).getColor() == color
							&& ((Site) Board.spaces.get(i)).getHouseNum() == 4)
						return false;

			return true;

		}

		return false;
	}

	public void sellHouse(Player p, int space) {
		// houses are sold to the bank by their half price
		((Site) Board.spaces.get(space)).setHouseNum(((Site) Board.spaces
				.get(space)).getHouseNum() - 1);
		p.setMoney(p.getMoney()
				- ((Site) Board.spaces.get(space)).getHousePrice() / 2);
	}

	public boolean canBuyHotel(Player p, int space) {
		// the hotel can be bought if there were 4 houses before, only the owner
		// can buy hotel
		if (((Site) Board.spaces.get(space)).getOwner() == null)
			return false;
		if (!p.getName().equals(
				((Site) Board.spaces.get(space)).getOwner().getName()))
			return false;
		if (((Site) Board.spaces.get(space)).getHouseNum() != 4)
			return false;
		if (((Site) Board.spaces.get(space)).hasHotel() == true)
			return false;
		int color = ((Site) Board.spaces.get(space)).getColor();
		for (int i = 0; i < Board.spaces.size(); i++)
			if (Board.spaces.get(i).getType() == 1)
				if (((Site) Board.spaces.get(i)).getColor() == color
						&& ((Site) Board.spaces.get(i)).getHouseNum() == 3)
					return false;
		return true;

	}

	public boolean canSellHotel(Player p, int space) {
		// only the owner can sell hotel and you can sell hotel if you have any
		if (((Site) Board.spaces.get(space)).getOwner() == null)
			return false;
		if (!p.getName().equals(
				((Site) Board.spaces.get(space)).getOwner().getName()))
			return false;
		if (((Site) Board.spaces.get(space)).hasHotel() == false)
			return false;
		return true;

	}

	public boolean sellHotel(Player p, int space) {
		// hotels are sold in half price and 4 houses will be returned instead
		// of hotel
		((Site) Board.spaces.get(space)).setHotel(false);
		((Site) Board.spaces.get(space)).setHouseNum(4);
		p.setMoney(p.getMoney()
				- ((Site) Board.spaces.get(space)).getHotelPrice() / 2);
		return false;
	}

	public boolean buyHotel(Player p, int space) {
		// only if the owner has enough money, can buy hotel, 4 houses will be
		// returned to bank and 1 hotel is given to the owner at that space
		if (((Site) Board.spaces.get(space)).getHotelPrice() <= p.getMoney()) {
			p.setMoney(p.getMoney()
					- ((Site) Board.spaces.get(space)).getHotelPrice());
			((Site) Board.spaces.get(space)).setHouseNum(0);
			((Site) Board.spaces.get(space)).setHotel(true);
			return true;
		}
		return false;
	}

	public boolean isBankrupt(Player p) {
		// if the sum of the debt of the player is more than the sum of the
		// player's money (site, transport, utility, hotels, houses and cash)
		// the player is bankrupt and will be removed from the game
		int property = 0;
		for (int i = 0; i < Board.spaces.size(); i++) {
			switch (Board.spaces.get(i).getType()) {
			case 1:
				if (((Site) Board.spaces.get(i)).getOwner().getName()
						.equals(p.getName())) {
					if (((Site) Board.spaces.get(i)).hasHotel())
						property += ((Site) Board.spaces.get(i))
								.getHotelPrice() / 2;
					if (((Site) Board.spaces.get(i)).getHouseNum() > 0
							&& ((Site) Board.spaces.get(i)).hasHotel() == false)
						property += ((Site) Board.spaces.get(i)).getHouseNum()
								* ((Site) Board.spaces.get(i)).getHousePrice();
					property += ((Site) Board.spaces.get(i)).getPrice();
				}
				break;
			case 2:
				if (((Transport) Board.spaces.get(i)).getOwner().getName()
						.equals(p.getName()))
					property += ((Site) Board.spaces.get(i)).getPrice();
				break;
			case 3:
				if (((Utility) Board.spaces.get(i)).getOwner().getName()
						.equals(p.getName()))
					property += ((Site) Board.spaces.get(i)).getPrice();
				break;

			}

		}
		if (p.getDebt() > property)
			return true;
		return false;
	}

	public void getRent(int type, Player playerOnSpace, int num) {
		// if the player stands in someone else's property, should pay its
		// active rent to the owner
		playerOnSpace.setMoney(playerOnSpace.getMoney() - getActiveRent(num));
		switch (type) {
		case 1:
			((Site) Board.spaces.get(num)).getOwner().setMoney(
					((Site) Board.spaces.get(num)).getOwner().getMoney()
							+ getActiveRent(num));
			break;
		case 2:
			((Transport) Board.spaces.get(num)).getOwner().setMoney(
					((Transport) Board.spaces.get(num)).getOwner().getMoney()
							+ getActiveRent(num));
			break;
		case 3:
			((Utility) Board.spaces.get(num)).getOwner().setMoney(
					((Utility) Board.spaces.get(num)).getOwner().getMoney()
							+ getActiveRent(num));
			break;
		}

	}

}
