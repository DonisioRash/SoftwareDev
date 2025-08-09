package coursework2;

import java.awt.Point; // Used for tracking dialog position during shaking animation

import javax.swing.JDialog; // Swing dialog component
import javax.swing.JOptionPane; // For message boxes and input dialogs
import javax.swing.JScrollPane; // Scroll bar container
import javax.swing.JTextArea; // Multi-line text display area

public class UI { // Handles all input/output display for the game

	// Shows a simple message box
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message); // Pops up a plain message dialog
	}

	// Shows a scroll-able message box (for long text like game summary)
	public static void showScrollableMessage(String message, String title) {
		JTextArea area = new JTextArea(message); // Creates a multi-line text area
		area.setEditable(false); // Makes it read-only so the player can't edit it
		JScrollPane scroll = new JScrollPane(area); // Adds scroll bars if needed
		JOptionPane.showMessageDialog(null, scroll, title, JOptionPane.INFORMATION_MESSAGE);
		// Displays the scroll-able text inside a dialog
	}

	// Prompts the player to enter their name
	public static String getPlayerName() {
		String name = "";
		// Loop until a non-empty, non-null name is entered
		while (name == null || name.trim().isEmpty()) {
			name = JOptionPane.showInputDialog("Enter your name:"); // Ask for input
		}
		return name.trim(); // Remove leading/trailing spaces and return
	}

	// Gets a valid bet from the player
	public static int getValidBet(int bank) {
		while (true) { // Keep asking until a valid bet is entered
			String input = JOptionPane.showInputDialog("Enter your bet (£1–£4):");

			// If player clicks Cancel, return 0 to exit betting
			if (input == null)
				return 0;

			try {
				int bet = Integer.parseInt(input.trim()); // Convert input to number
				if (bet >= 1 && bet <= 4 && bet <= bank) {
					// If bet is valid then show confirmation with shake effect
					showShakingConfirmation("Bet accepted: £" + bet);
					return bet; // Return the accepted bet
				} else if (bet > bank) {
					// Bet is more than player's balance
					showMessage("You cannot bet more than your current balance (£" + bank + ").");
				} else {
					// Bet is outside 1–4 range
					showMessage("Bet must be between £1 and £4.");
				}
			} catch (NumberFormatException e) {
				// Player entered something that's not a number
				showMessage("Please enter a valid number.");
			}
		}
	}

	// Shows a shaking confirmation dialog
	private static void showShakingConfirmation(String message) {
		JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
		// Create a message pane

		JDialog dialog = pane.createDialog(null, "Confirm Bet");
		// Turn it into a dialog window titled "Confirm Bet"

		dialog.setVisible(true); // Show the dialog so it appears on screen

		// Shake effect: move dialog left and right several times
		try {
			for (int i = 0; i < 10; i++) { // Loop for shake animation
				Point p = dialog.getLocation(); // Get current position
				int dx = (i % 2 == 0) ? 10 : -10; // Move right on even loops, left on odd
				dialog.setLocation(p.x + dx, p.y); // Set new position
				Thread.sleep(40); // Short pause between moves
			}
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt(); // Restore interrupted status if interrupted
		} finally {
			dialog.dispose(); // Close the dialog after shaking
		}
	}
}
