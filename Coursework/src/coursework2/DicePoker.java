package coursework2;

public class DicePoker { // Main entry point for the Dice Poker game

	public static void main(String[] args) {
		// Main game loop for handling multiple players
		do {
			// Create a new Game object and start playing for the current player
			new Game().play();
		}
		// After the game ends, ask if another player wants to play
		while (askToAddAnotherPlayer());

		// Show a final thank-you message after all players have finished
		UI.showMessage(" Thank you for playing Dice Poker!");
	}

	// ==== Method to ask if a new player should be added ====
	public static boolean askToAddAnotherPlayer() {
		// Show a Yes/No confirmation dialog to the user
		int choice = javax.swing.JOptionPane.showConfirmDialog(null, "Add another player?", "Add Player",
				javax.swing.JOptionPane.YES_NO_OPTION);

		// Return true if player clicked "Yes", otherwise false
		return choice == javax.swing.JOptionPane.YES_OPTION;
	}
}
