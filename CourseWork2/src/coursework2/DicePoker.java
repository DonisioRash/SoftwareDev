package coursework2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

public class DicePoker {
	static int bank = 6; // Starting balance
	static int attemptsLeft = 5; // Maximum 5 bets
	static final int MAX_ATTEMPTS = 5; // For resets
	static Random rand = new Random(); // Random generator
	static String[] results = new String[5]; // Store up to 5 results
	static int roundCounter = 0; // How many rounds played

	static Map<String, Integer> highScores = new HashMap<>(); // name → score

	public static void main(String[] args) {
		do {
			runGame(); // Run one player game session
		} while (askToAddAnotherPlayer());

		showMessage("👋 Thank you for playing Dice Poker!");
	}

	// Full game session for one player
	public static void runGame() {
		// Reset game state
		bank = 6;
		attemptsLeft = MAX_ATTEMPTS;
		results = new String[MAX_ATTEMPTS];
		roundCounter = 0;

		String playerName = getPlayerName();
		showMessage("🎲 Welcome, " + playerName
				+ "! 🎲\nYou start with £6.\nUp to 5 bets allowed.\nBet between £1 and £4.");

		while (bank > 0 && attemptsLeft > 0) {
			showMessage("💰 Balance: £" + bank + "\nAttempts left: " + attemptsLeft);
			int bet = getValidBet();

			if (bet == 0)
				break; // user canceled

			bank -= bet;
			attemptsLeft--;
			roundCounter++;

			int die1 = rollDie();
			int die2 = rollDie();

			int winnings = calculateWinnings(die1, die2, bet);
			int netChange = winnings - bet;
			bank += winnings;

			results[roundCounter - 1] = String.format("Round %d - Bet £%d - Rolled %d and %d – %s £%d", roundCounter,
					bet, die1, die2, (netChange >= 0 ? "Earned" : "Lost"), Math.abs(netChange));

			String message = String.format("🎲 You rolled: %d and %d\n%s\n💰 New Balance: £%d\n🕐 Attempts Left: %d",
					die1, die2, getOutcomeMessage(die1, die2, winnings, bet), bank, attemptsLeft);
			showMessage(message);
		}

		// Game summary
		StringBuilder summary = new StringBuilder("📊 Game Summary:\n\n");
		for (int i = 0; i < roundCounter; i++) {
			summary.append(results[i]).append("\n");
		}
		summary.append("\n🏁 Final Balance: £").append(bank);
		showMessage(summary.toString());

		// Update high score
		highScores.put(playerName, bank);

		// Show score table
		displayHighScores();
	}

	// Ask if user wants to add another player
	public static boolean askToAddAnotherPlayer() {
		int choice = JOptionPane.showConfirmDialog(null, "Would you like to add another player?", "Add Player",
				JOptionPane.YES_NO_OPTION);
		return choice == JOptionPane.YES_OPTION;
	}

	// Get player name
	public static String getPlayerName() {
		String name = "";
		while (name == null || name.trim().isEmpty()) {
			name = JOptionPane.showInputDialog("Enter your name:");
		}
		return name.trim();
	}

	// Prompt user for a valid bet
	public static int getValidBet() {
		while (true) {
			String input = JOptionPane.showInputDialog("Enter your bet (£1–£4):");
			if (input == null)
				return 0;
			try {
				int bet = Integer.parseInt(input);
				if (bet >= 1 && bet <= 4 && bet <= bank) {
					return bet;
				} else if (bet > bank) {
					showMessage("You cannot bet more than your current balance (£" + bank + ").");
				} else {
					showMessage("Bet must be between £1 and £4.");
				}
			} catch (NumberFormatException e) {
				showMessage("Please enter a valid whole number.");
			}
		}
	}

	// Simulate rolling a die (1–6)
	public static int rollDie() {
		return rand.nextInt(6) + 1;
	}

	// Winnings logic
	public static int calculateWinnings(int d1, int d2, int bet) {
		if (d1 == d2) {
			return bet * 3; // Doubles
		} else if (Math.abs(d1 - d2) == 1 && !(d1 == 6 && d2 == 1) && !(d1 == 1 && d2 == 6)) {
			return bet * 2; // Sequential (no wrap)
		} else {
			return 0; // Loss
		}
	}

	// Outcome message generator
	public static String getOutcomeMessage(int d1, int d2, int winnings, int bet) {
		if (d1 == d2) {
			return "🎉 Doubles! You win £" + winnings + "!";
		} else if (Math.abs(d1 - d2) == 1 && !(d1 == 6 && d2 == 1) && !(d1 == 1 && d2 == 6)) {
			return "✨ Sequential numbers! You win £" + winnings + "!";
		} else {
			return "❌ No match. You win nothing.";
		}
	}

	// Show message dialog
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	// Show high scores sorted
	public static void displayHighScores() {
		StringBuilder table = new StringBuilder("🏆 High Score Table 🏆\n\n");

		highScores.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).limit(5)
				.forEach(entry -> table.append(entry.getKey()).append(" – £").append(entry.getValue()).append("\n"));

		showMessage(table.toString());
	}

}
