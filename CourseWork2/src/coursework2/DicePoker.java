package coursework2;

//Import necessary classes
import java.util.Random; // Allows us to generate random numbers (for rolling dice)

import javax.swing.JOptionPane;

public class DicePoker {

	static int bank = 100; // Starting bank balance
	static Random rand = new Random(); // Random generator for dice rolls

	public static void main(String[] args) {
		showMessage("Welcome to Dice Poker!\nYou start with $" + bank);

		// Game loop
		while (bank > 0) {
			showMessage("Current Balance: $" + bank);
			int bet = getBet();

			if (bet == 0) {
				break; // User chooses to quit
			}

			int die1 = rollDie();
			int die2 = rollDie();

			String roundResult = "You rolled: " + die1 + " and " + die2 + "\n";
			int winnings = calculateWinnings(die1, die2, bet);

			bank += winnings;

			roundResult += getOutcomeMessage(die1, die2, winnings);
			roundResult += "\nNew Balance: $" + bank;

			showMessage(roundResult);
		}

		// Final message after game ends
		String finalMsg = (bank <= 0) ? "You lost all your money!" : "You ended the game with $" + bank;
		showMessage("Game Over!\n" + finalMsg);
	}

	// Roll a single six-sided die
	public static int rollDie() {
		return rand.nextInt(6) + 1;
	}

	// Prompt user for a valid bet
	public static int getBet() {
		while (true) {
			String input = JOptionPane.showInputDialog("Enter your bet (0 to quit):");
			if (input == null)
				return 0; // If user presses Cancel

			try {
				int bet = Integer.parseInt(input);
				if (bet == 0 || (bet > 0 && bet <= bank)) {
					return bet;
				} else {
					showMessage("Invalid bet. Enter a value between 1 and " + bank + ", or 0 to quit.");
				}
			} catch (NumberFormatException e) {
				showMessage("Please enter a valid number.");
			}
		}
	}

	// Determine winnings based on the dice rolled and bet
	public static int calculateWinnings(int d1, int d2, int bet) {
		if (d1 == 6 && d2 == 6) {
			return bet * 5; // Jackpot
		} else if (d1 == d2) {
			return bet * 2; // Pair
		} else if (d1 + d2 == 7) {
			return bet * 3; // Lucky Seven
		} else if ((d1 + d2) % 2 == 0) {
			return bet; // Even total
		} else {
			return -bet; // Odd total - lose
		}
	}

	// Create a descriptive message based on the result
	public static String getOutcomeMessage(int d1, int d2, int winnings) {
		String outcome;
		if (d1 == 6 && d2 == 6) {
			outcome = "JACKPOT! Double sixes!";
		} else if (d1 == d2) {
			outcome = "You rolled a pair!";
		} else if (d1 + d2 == 7) {
			outcome = "Lucky Seven!";
		} else if ((d1 + d2) % 2 == 0) {
			outcome = "Even total!";
		} else {
			outcome = "Odd total. You lose!";
		}

		String result = outcome + "\n";
		result += (winnings >= 0) ? "You won $" + winnings : "You lost $" + Math.abs(winnings);
		return result;
	}

	// Show a message using a dialog box
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

}
