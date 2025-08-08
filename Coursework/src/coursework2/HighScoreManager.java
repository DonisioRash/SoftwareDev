package coursework2;

import java.util.HashMap;
import java.util.Map;

public class HighScoreManager {
	private static final Map<String, Integer> scores = new HashMap<>();

	public static void add(String name, int score) {
		String key = name;
		int suffix = 1;
		while (scores.containsKey(key)) {
			key = name + " (" + suffix++ + ")";
		}
		scores.put(key, score);
	}

	public static void display() {
		StringBuilder sb = new StringBuilder(" High Scores\n\n");
		scores.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).limit(5)
				.forEach(e -> sb.append(e.getKey()).append(" – £").append(e.getValue()).append("\n"));
		UI.showMessage(sb.toString());
	}
}