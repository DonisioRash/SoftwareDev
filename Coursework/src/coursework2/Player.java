package coursework2;

public class Player { // Represents a player in the Dice Poker game

	// Stores the player's name (final means it cannot be changed after creation)
	private final String name;

	// Called when a new Player object is created
	public Player(String name) {
		// Store the player's name and remove leading/trailing spaces
		this.name = name.trim();
	}

	// Returns the player's name
	public String getName() {
		return name;
	}
}
