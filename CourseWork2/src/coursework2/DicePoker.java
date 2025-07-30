package coursework2;

import java.util.Random;

import javax.swing.JOptionPane;

public class DicePoker {

	static int bank = 6; // Start with £6
	static int attemptsLeft = 5; // Max 5 attempts
	static Random rand = new Random(); // Random number generator

	// Array to keep track of results for 5 bets max
	static String[] results = new String[5];
	static int roundCounter = 0; // Tracks how many rounds played

	public static void main(String[] args) {
		showMessage("🎲 Welcome to Dice Poker 🎲\nYou start with £6.\nEach bet costs £1.\nYou have 5 total attempts.");

		while (bank > 0 && attemptsLeft > 0) {
			showMessage("💰 Balance: £" + bank + "\nAttempts left: " + attemptsLeft);

			int confirm = JOptionPane.showConfirmDialog(null, "Would you like to place a £1 bet?", "Place Bet",
					JOptionPane.YES_NO_OPTION);

			if (confirm != JOptionPane.YES_OPTION) {
				break;
			}

			bank -= 1; // Deduct £1 to place bet
			attemptsLeft--; // Use up one attempt
			roundCounter++; // Count this round

			int die1 = rollDie();
			int die2 = rollDie();

			int winnings = calculateWinnings(die1, die2); // Either 3, 2, or 0
			int netChange = winnings - 1; // Gain/loss after £1 bet
			bank += winnings;

			// Format: Round X - Rolled X and Y – Earned/Lost £Z
			results[roundCounter - 1] = String.format("Round %d - Rolled %d and %d – %s £%d", roundCounter, die1, die2,
					(netChange >= 0) ? "Earned" : "Lost", Math.abs(netChange));

			// Optional per-round feedback
			String message = String.format("🎲 You rolled: %d and %d\n%s\n💰 New Balance: £%d\n🕐 Attempts Left: %d",
					die1, die2, getOutcomeMessage(die1, die2, winnings), bank, attemptsLeft);

			showMessage(message);
		}

		// Compile all results
		StringBuilder summary = new StringBuilder("📊 Game Summary:\n\n");

		for (int i = 0; i < roundCounter; i++) {
			summary.append(results[i]).append("\n");
		}

		summary.append("\n🏁 Final Balance: £").append(bank);

		showMessage(summary.toString());
	}

	// Simulates rolling one 6-sided die
	public static int rollDie() {
		return rand.nextInt(6) + 1;
	}

	// Calculate winnings based on rules
	public static int calculateWinnings(int d1, int d2) {
		if (d1 == d2) {
			return 3; // Doubles
		} else if (Math.abs(d1 - d2) == 1 && !(d1 == 6 && d2 == 1) && !(d1 == 1 && d2 == 6)) {
			return 2; // Sequential
		} else {
			return 0; // No win
		}
	}

	// Descriptive outcome message
	public static String getOutcomeMessage(int d1, int d2, int winnings) {
		if (d1 == d2) {
			return "🎉 You rolled doubles! You win £3!";
		} else if (Math.abs(d1 - d2) == 1 && !(d1 == 6 && d2 == 1) && !(d1 == 1 && d2 == 6)) {
			return "✨ Sequential numbers! You win £2!";
		} else {
			return "❌ No match. You win nothing.";
		}
	}

	// Show any message in a dialog box
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

}
