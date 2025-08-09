package coursework2;

import java.util.Random; // Import Random class to generate random numbers

public class Dice { // Represents a single six-sided dice

	// Class variables
	private static final Random rand = new Random(); // One shared Random object for all Dice objects
	private int value; // Stores the current value of the dice roll (1–6)

	// Constructor
	public Dice() {
		// When a Dice object is created, it "rolls" immediately
		// rand.nextInt(6) generates 0–5, adding 1 makes it 1–6
		value = rand.nextInt(6) + 1;
	}

	// Getter method
	public int getValue() {
		// Returns the current value of the dice
		return value;
	}
}
