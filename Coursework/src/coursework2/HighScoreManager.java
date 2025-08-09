package coursework2;

import java.util.HashMap; // Import HashMap for storing name/score pairs
import java.util.Map; // Import Map interface for key-value storage

public class HighScoreManager { // Manages and displays the high score table

	// Map to store player names (key) and their scores (value)
	// shared across all games/players in the program
	private static final Map<String, Integer> scores = new HashMap<>();

	// Adds a player's score to the high score list
	public static void add(String name, int score) {
		String key = name; // Store the initial player name
		int suffix = 1; // Suffix counter to make duplicate names unique

		// If the name already exists in the high score list,
		// append a suffix like " (1)", " (2)", etc.
		while (scores.containsKey(key)) {
			key = name + " (" + suffix++ + ")";
		}

		// Add the (possibly modified) name and score to the table
		scores.put(key, score);
	}

	// Displays the top 5 high scores
	public static void display() {
		// StringBuilder to store the table text
		StringBuilder sb = new StringBuilder(" High Scores\n\n");

		// Stream through all scores, sort from highest to lowest,
		// limit to top 5, and append each to the table text
		scores.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()) // Sort by score descending
				.limit(5) // Keep only top 5
				.forEach(e -> sb.append(e.getKey()) // Player name
						.append(" - £") // Separator and currency symbol
						.append(e.getValue()) // Score
						.append("\n")); // New line for next entry

		// Show the high score table in a message dialog
		UI.showMessage(sb.toString());
	}
}
