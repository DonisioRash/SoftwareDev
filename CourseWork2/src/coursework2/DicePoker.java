package coursework2;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DicePoker {
	static int bank = 6;
	static int attemptsLeft = 5;
	static final int MAX_ATTEMPTS = 5;
	static Random rand = new Random();
	static String[] results = new String[5];
	static int roundCounter = 0;

	static Map<String, Integer> highScores = new HashMap<>();

	public static void main(String[] args) {
		do {
			runGame();
		} while (askToAddAnotherPlayer());

		showMessage("🏿Thank you for playing Dice Poker!");
	}

	public static void runGame() {
		bank = 6;
		attemptsLeft = MAX_ATTEMPTS;
		results = new String[MAX_ATTEMPTS];
		roundCounter = 0;

		showMessage(" Welcome to Dice Poker! \nYou start with £6.\nUp to 5 bets allowed.\nBet between £1 and £4.");
		String playerName = getPlayerName();
		showMessage(" Hello, " + playerName + "! Let's begin your game 😀");

		while (bank > 0 && attemptsLeft > 0) {
			showMessage("🤑 Balance: £" + bank + "\nAttempts left: " + attemptsLeft);

			int bet = getValidBet();
			if (bet == 0)
				break;

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

		StringBuilder summary = new StringBuilder("📊 Game Summary:\n\n");
		for (int i = 0; i < roundCounter; i++) {
			summary.append(results[i]).append("\n");
		}
		summary.append("\n🏁 Final Balance: £").append(bank);
		showScrollableMessage(summary.toString(), "Game Summary");

		addToHighScores(playerName, bank);
		displayHighScores();
	}

	public static boolean askToAddAnotherPlayer() {
		int choice = JOptionPane.showConfirmDialog(null, "Would you like to add another player?", "Add Player",
				JOptionPane.YES_NO_OPTION);
		return choice == JOptionPane.YES_OPTION;
	}

	public static String getPlayerName() {
		String name = "";
		while (name == null || name.trim().isEmpty()) {
			name = JOptionPane.showInputDialog("Enter your name:");
		}
		return name.trim();
	}

	public static int getValidBet() {
		while (true) {
			String input = JOptionPane.showInputDialog(null, "Enter your bet (£1–£4):", "Place Your Bet",
					JOptionPane.QUESTION_MESSAGE);

			if (input == null)
				return 0; // Cancel pressed

			try {
				int bet = Integer.parseInt(input.trim());
				if (bet >= 1 && bet <= 4 && bet <= bank) {
					// Shake after successful input
					triggerPostInputShake("Bet accepted: £" + bet);
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

	public static void triggerPostInputShake(String message) {
		JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = pane.createDialog(null, "Processing...");

		// Shake after dialog is shown
		dialog.setVisible(true);

		// Then shake it
		try {
			for (int i = 0; i < 10; i++) {
				Point loc = dialog.getLocation();
				int offset = (i % 2 == 0) ? 10 : -10;
				dialog.setLocation(loc.x + offset, loc.y);
				Thread.sleep(40);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		dialog.dispose(); // Close after shaking
	}

	public static int rollDie() {
		return rand.nextInt(6) + 1;
	}

	public static int calculateWinnings(int d1, int d2, int bet) {
		if (d1 == d2) {
			return bet * 3;
		} else if (isSequential(d1, d2)) {
			return bet * 2;
		} else {
			return 0;
		}
	}

	public static boolean isSequential(int a, int b) {
		return Math.abs(a - b) == 1 && !(a == 6 && b == 1) && !(a == 1 && b == 6);
	}

	public static String getOutcomeMessage(int d1, int d2, int winnings, int bet) {
		if (d1 == d2) {
			return "🎉 Doubles! You win £" + winnings + "!";
		} else if (isSequential(d1, d2)) {
			return "✨ Sequential numbers! You win £" + winnings + "!";
		} else {
			return "❌ No match. You win nothing.";
		}
	}

	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public static void showScrollableMessage(String message, String title) {
		JTextArea textArea = new JTextArea(message);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void addToHighScores(String name, int score) {
		String key = name;
		int suffix = 1;
		while (highScores.containsKey(key)) {
			key = name + " (" + suffix++ + ")";
		}
		highScores.put(key, score);
	}

	public static void displayHighScores() {
		StringBuilder table = new StringBuilder("🏆 High Score Table 🏆\n\n");

		highScores.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).limit(5)
				.forEach(entry -> table.append(entry.getKey()).append(" – £").append(entry.getValue()).append("\n"));

		showMessage(table.toString());
	}
}
