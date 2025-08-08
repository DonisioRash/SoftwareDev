package coursework2;

import java.awt.Point;
//Imports specific utility classes from the java.util package
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//Imports GUI components from the javax.swing package
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DicePokeroriginal {
	static int bank = 6; // Starting money for the player at the beginning of the game
	static int attemptsLeft = 5; // Maximum number of rounds (bets) allowed per game
	static final int MAX_ATTEMPTS = 5; // Constant used to reset attempts for each new game
	static Random rand = new Random(); // Random number generator used to simulate dice rolls
	static String[] results = new String[5]; // Array to store summary strings for up to 5 game rounds
	static int roundCounter = 0; // Keeps track of how many rounds have been played in the current game

	static Map<String, Integer> highScores = new HashMap<>(); // Stores player names and their final bank scores

	public static void main(String[] args) {
		// Runs the game for one player
		do {
			runGame();
		} while (askToAddAnotherPlayer()); // Repeats the game if the user wants to add another player

		showMessage("Thank you for playing Dice Poker!"); // Shows a goodbye message after all players have played
	}

	public static void runGame() { // Runs a full game session for a single player
		bank = 6; // Reset player's bank to the starting amount (£6)
		attemptsLeft = MAX_ATTEMPTS; // Reset number of attempts (bets) allowed
		results = new String[MAX_ATTEMPTS]; // Reset the results array to store round summaries
		roundCounter = 0; // Reset the round counter to track how many rounds have been played

		// Show the game instructions and welcome message before asking for the player's
		// name
		showMessage("Welcome to Dice Poker! \nYou start with £6.\nUp to 5 bets allowed.\nBet between £1 and £4.");
		String playerName = getPlayerName(); // Prompt the user to enter their name
		showMessage(" Hello, " + playerName + "! Let's begin your game"); // Personalized greeting

		// Main game loop runs while the player has money and rounds left
		while (bank > 0 && attemptsLeft > 0) {
			showMessage("Balance: £" + bank + "\nAttempts left: " + attemptsLeft); // Display the current balance and
																					// number of attempts left

			int bet = getValidBet(); // Prompt user for a bet amount (between £1 and £4)
			if (bet == 0)
				break; // If user cancels or exits the input dialog, end the game early

			bank -= bet; // Deduct the bet amount from the bank
			attemptsLeft--; // Decrease the number of remaining attempts
			roundCounter++; // Move to the next round

			int dice1 = rollDice(); // Roll the first dice (1–6)
			int dice2 = rollDice(); // Roll the second dice (1–6)

			int winnings = calculateWinnings(dice1, dice2, bet); // Determine winnings based on the dice roll and rules
			int netChange = winnings - bet; // Net change in money after accounting for the initial bet
			bank += winnings; // Add winnings back to the bank

			// Store a summary of this round in the results array
			results[roundCounter - 1] = String.format("Round %d - Bet £%d - Rolled %d and %d – %s £%d", roundCounter,
					bet, dice1, dice2, (netChange >= 0 ? "Earned" : "Lost"), Math.abs(netChange));

			// Build the message showing roll results, outcome, updated bank and remaining
			// attempts
			String message = String.format(" You rolled: %d and %d\n%s\n New Balance: £%d\n Attempts Left: %d", dice1,
					dice2, getOutcomeMessage(dice1, dice2, winnings, bet), bank, attemptsLeft);
			showMessage(message); // Display the round result to the player
		}

		StringBuilder summary = new StringBuilder("Game Summary:\n\n"); // Multi-line summary message for the player
																		// after the game ends
		for (int i = 0; i < roundCounter; i++) { // Loops through the number of rounds played, and appends each round's
													// result
			summary.append(results[i]).append("\n");
		}
		summary.append("\n Final Balance: £").append(bank); // Appends the player's final balance to the bottom of the
															// summary
		showScrollableMessage(summary.toString(), "Game Summary"); // Displays the full summary in a scalable dialog
																	// box,

		addToHighScores(playerName, bank); // Adds the player's name and final score
		displayHighScores();
	}

	public static boolean askToAddAnotherPlayer() { // This shows a Yes/No confirmation dialog
		int choice = JOptionPane.showConfirmDialog(null, "Would you like to add another player?", "Add Player",
				JOptionPane.YES_NO_OPTION);
		return choice == JOptionPane.YES_OPTION;
	}

	public static String getPlayerName() { // Prompt Player to enter name
		String name = ""; // Initialize the name variable as an empty string to enter the while loop
		while (name == null || name.trim().isEmpty()) { // Keep looping until the player enters a valid (non-empty,
														// non-null) name
			name = JOptionPane.showInputDialog("Enter your name:");
			// Show an input dialog asking the user for their name
			// If the user clicks "Cancel", name will be null
		}
		return name.trim(); // Removes any leading/trailing spaces
	}

	public static int getValidBet() { // This method repeatedly prompts the user to enter a valid bet between £1–£4
		// It returns the valid bet as an integer, or 0 if the user cancels
		while (true) { // Infinite loop to keep asking until a valid bet is entered or the user cancels
			String input = JOptionPane.showInputDialog(null, "Enter your bet (£1–£4):", "Place Your Bet",
					JOptionPane.QUESTION_MESSAGE);

			if (input == null)
				return 0; // Cancel pressed

			try {
				int bet = Integer.parseInt(input.trim());
				// Attempt to convert the entered text to an integer after trimming spaces
				if (bet >= 1 && bet <= 4 && bet <= bank) { // Valid bet: between £1–£4 and not more than the current
															// balance
					triggerPostInputShake("Bet accepted: £" + bet); // Show a confirmation message and shake the dialog
																	// after a valid input
					return bet;
				} else if (bet > bank) { // If bet is greater than the player's balance, show an error
					showMessage("You cannot bet more than your current balance (£" + bank + ").");
				} else { // If the bet is outside 1–4 range, show an error
					showMessage("Bet must be between £1 and £4.");
				}
			} catch (NumberFormatException e) { // If the user enters non-numeric input (e.g., letters or symbols), show
												// an error
				showMessage("Please enter a valid whole number.");
			}
		}
	}

	public static void triggerPostInputShake(String message) { // This method displays a confirmation dialog with a
																// given message,
		// and shakes the dialog left and right after it's shown
		JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = pane.createDialog(null, "Processing..."); // Convert the JOptionPane into a JDialog with the
																	// title "Processing..."

		// Shake after dialog is shown
		dialog.setVisible(true);

		// Then shake it
		try {
			for (int i = 0; i < 10; i++) {
				Point loc = dialog.getLocation(); // Get the current position of the dialog on the screen
				int offset = (i % 2 == 0) ? 10 : -10; // Alternate offset: +10px or -10px depending on even/odd
														// iteration
				dialog.setLocation(loc.x + offset, loc.y); // Move the dialog left or right by the offset while keeping
															// the vertical position unchanged
				Thread.sleep(40); // Pause briefly before the next shake movement
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt(); // If the thread is interrupted while sleeping, restore the interrupt
												// status
		}
		dialog.dispose(); // Close after shaking
	}

	public static int rollDice() { // This method simulates rolling a six-sided die (values 1 to 6)
		return rand.nextInt(6) + 1; // Generates a random number between 0 and 5, then adds 1 to get a value between
									// 1 and 6
	}

	public static int calculateWinnings(int d1, int d2, int bet) { // Calculates how much the player wins based on two
																	// dice values (d1 and d2) and the bet amount
		if (d1 == d2) {
			return bet * 3; // If both dice show the same number (doubles), return triple the bet
		} else if (isSequential(d1, d2)) {
			return bet * 2; // If the dice values are sequential (e.g., 3 and 4), return double the bet
		} else {
			return 0; // Otherwise, no winnings (player loses the bet)
		}
	}

	public static boolean isSequential(int a, int b) { // Checks if two dice values are sequential (e.g., 2 and 3, or 5
														// and 4), but not wrapping (1 and 6)
		return Math.abs(a - b) == 1 && !(a == 6 && b == 1) && !(a == 1 && b == 6);
	}

	public static String getOutcomeMessage(int d1, int d2, int winnings, int bet) { // Returns a message to the player
																					// based on the outcome of the dice
																					// roll
		if (d1 == d2) {
			return " Doubles! You win £" + winnings + "!"; // If the dice are the same, return message indicating a win
															// by rolling doubles
		} else if (isSequential(d1, d2)) {
			return " Sequential numbers! You win £" + winnings + "!"; // If the dice are sequential (e.g. 2 & 3), return
																		// message for sequential win
		} else {
			return " No match. You win nothing."; // If the dice are neither the same nor sequential, return loss
													// message
		}
	}

	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public static void showScrollableMessage(String message, String title) { // Displays a scroll able dialog box for
																				// game summary)
		JTextArea textArea = new JTextArea(message); // Create a text area to display the multi-line message
		textArea.setEditable(false); // Make sure the user cannot modify the text
		JScrollPane scrollPane = new JScrollPane(textArea); // Wrap the text area in a scroll pane
		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE); // Show the scroll able
																									// dialog
	}

	public static void addToHighScores(String name, int score) { // Adds the player's name and final score to the high
																	// score table,
		// ensuring that duplicate names get a suffix to make them unique
		String key = name;
		int suffix = 1;
		while (highScores.containsKey(key)) {
			key = name + " (" + suffix++ + ")";
		} // If the same name already exists in the high score list,
			// append a number (e.g., "E (1)", "E (2)", etc.)
		highScores.put(key, score); // Add the final name and score to the highScores map
	}

	public static void displayHighScores() { // Displays the top 5 high scores in a message dialog box
		StringBuilder table = new StringBuilder(" High Score Table \n\n"); // Start building the string with a heading
																			// for the high score table

		highScores.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).limit(5) // Limit to the top 5
																								// scores only
				.forEach(entry -> table.append(entry.getKey()).append(" – £").append(entry.getValue()).append("\n")); // Formulate
																														// Player
																														// information

		showMessage(table.toString()); // Display the formatted high score table
	}
}
