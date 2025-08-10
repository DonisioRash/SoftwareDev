package coursework2; // Defines the package this class belongs to

public class Game { // Class that controls the main game logic

	// Instance variables
	private int bank = 6; // Player's current money (starts at £6)
	private int attempts = 5; // Number of bets the player can make (starts at 5)
	private static final int MAX_ATTEMPTS = 5; // Constant for maximum rounds per game
	private Player player; // The player object holding player details
	private String[] results = new String[MAX_ATTEMPTS]; // Stores text summaries for each round
	private int round = 0; // Tracks the current round number

	// Main game loop method
	public void play() {
		// Show initial welcome and game rules
		UI.showMessage(
				"Welcome to Donisio Rash Dice Poker!\nYou start with £6.\nUp to 5 bets allowed.\nBet between £1 and £4.");

		// Ask the player for their name and create a Player object
		player = new Player(UI.getPlayerName());

		// Greet the player personally using their name
		UI.showMessage("Hello, " + player.getName() + "! Let's begin your game");

		// Repeat game rounds while the player still has money and attempts left
		while (bank > 0 && attempts > 0) {

			// Show current balance and remaining attempts
			UI.showMessage("Balance: £" + bank + "\nAttempts left: " + attempts);

			// Ask the player to enter a bet amount
			int bet = UI.getValidBet(bank);

			// If player cancels (returns 0), exit the loop early
			if (bet == 0)
				break;

			// Deduct the bet from the bank
			bank -= bet;

			// Move to the next round
			round++;

			// Reduce remaining attempts
			attempts--;

			// Create two new dice and roll them automatically
			Dice d1 = new Dice();
			Dice d2 = new Dice();

			// Calculate how much the player wins based on dice results and bet
			int winnings = Rules.calculateWinnings(d1.getValue(), d2.getValue(), bet);

			// Add winnings back to the bank
			bank += winnings;

			// Save a summary of this round into the results array
			results[round - 1] = String.format("Round %d - Bet £%d - Rolled %d & %d - %s £%d", round, bet,
					d1.getValue(), d2.getValue(), (winnings >= bet ? "Earned" : "Lost"), Math.abs(winnings - bet));

			// Show the outcome of the round, including new balance
			UI.showMessage(String.format("You rolled %d and %d\n%s\n New Balance: £%d", d1.getValue(), d2.getValue(),
					Rules.getOutcomeMessage(d1.getValue(), d2.getValue(), winnings, bet), bank));
		}

		// Show a scroll able summary of all rounds played
		UI.showScrollableMessage(buildSummary(), "Game Summary");

		// Add player's name and final bank value to high score table
		HighScoreManager.add(player.getName(), bank);

		// Display the high score table
		HighScoreManager.display();
	}

	// Helper method to create game summary text
	private String buildSummary() {
		StringBuilder sb = new StringBuilder(); // Create an empty string builder

		// Loop through results and append each round's summary
		for (String result : results) {
			if (result != null)
				sb.append(result).append("\n");
		}

		// Append the player's final balance at the end
		sb.append("\n Final Balance: £").append(bank);

		// Return the complete summary as a string
		return sb.toString();
	}
}
