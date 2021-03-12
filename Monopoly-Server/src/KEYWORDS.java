public enum KEYWORDS {

	DICE {

	},
	SPACE {

	},
	TURN {

	},
	JAILFREEC1 {

	},
	JAILFREEC2 {

	},
	BUYHOUSE {

	},
	BUYHOTEL {

	},
	BANKRUPT {

	},
	SETMORTGAGE {

	},
	RELEASEMORTGAGE {

	};
	public static KEYWORDS getKeyWord(String str) {
		for (KEYWORDS k : KEYWORDS.values()) {
			if (k.toString().equals(str))
				return k;
		}
		return null;
	}
}
