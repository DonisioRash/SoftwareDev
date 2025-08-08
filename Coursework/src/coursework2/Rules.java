package coursework2;

public class Rules {
	public static int calculateWinnings(int d1, int d2, int bet) {
		if (d1 == d2)
			return bet * 3;
		if (Math.abs(d1 - d2) == 1 && !(d1 == 6 && d2 == 1) && !(d1 == 1 && d2 == 6))
			return bet * 2;
		return 0;
	}

	public static String getOutcomeMessage(int d1, int d2, int winnings, int bet) {
		if (d1 == d2)
			return " Doubles! You win £" + winnings + "!";
		if (Math.abs(d1 - d2) == 1 && !(d1 == 6 && d2 == 1) && !(d1 == 1 && d2 == 6))
			return " Sequential! You win £" + winnings + "!";
		return " No match. You win nothing.";
	}
}