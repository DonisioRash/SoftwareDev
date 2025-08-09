package coursework2;

public class Rules { // Holds the game rules and win/loss logic

	// Calculates how much the player wins based on the dice values and bet
	public static int calculateWinnings(int d1, int d2, int bet) {
		// Rule 1: Doubles will triple the bet
		if (d1 == d2)
			return bet * 3;

		// Rule 2: Sequential numbers (difference of 1), but not wrapping 6&1
		if (Math.abs(d1 - d2) == 1 && !(d1 == 6 && d2 == 1) && !(d1 == 1 && d2 == 6))
			return bet * 2;

		// Rule 3: All other cases you win nothing and lose bet
		return 0;
	}

	// ==== Creates a text message describing the result of a roll ====
	public static String getOutcomeMessage(int d1, int d2, int winnings, int bet) {
		// If doubles, return a "Doubles!" win message
		if (d1 == d2)
			return " Doubles! You win £" + winnings + "!";

		// If sequential numbers (but not wrapping around), return "Sequential!" message
		if (Math.abs(d1 - d2) == 1 && !(d1 == 6 && d2 == 1) && !(d1 == 1 && d2 == 6))
			return " Sequential! You win £" + winnings + "!";

		// Otherwise, no win message
		return " No match. You win nothing.";
	}
}
