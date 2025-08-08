package coursework2;

public class Game {
	private int bank = 6;
	private int attempts = 5;
	private static final int MAX_ATTEMPTS = 5;
	private Player player;
	private String[] results = new String[MAX_ATTEMPTS];
	private int round = 0;

	public void play() {
		UI.showMessage("Welcome to Dice Poker! \nYou start with £6.\nUp to 5 bets allowed.\nBet between £1 and £4.");
		player = new Player(UI.getPlayerName());
		UI.showMessage(" Hello, " + player + "! Let's begin your game"); // Personalized greeting

		while (bank > 0 && attempts > 0) {
			UI.showMessage(" Balance: £" + bank + ", Attempts left: " + attempts);
			int bet = UI.getValidBet(bank);
			if (bet == 0)
				break;

			bank -= bet;
			round++;
			attempts--;

			Dice d1 = new Dice();
			Dice d2 = new Dice();

			int winnings = Rules.calculateWinnings(d1.getValue(), d2.getValue(), bet);
			bank += winnings;

			results[round - 1] = String.format("Round %d - Bet £%d - Rolled %d & %d - %s £%d", round, bet,
					d1.getValue(), d2.getValue(), (winnings >= bet ? "Earned" : "Lost"), Math.abs(winnings - bet));

			UI.showMessage(String.format(" You rolled %d and %d\n%s\n New Balance: £%d", d1.getValue(), d2.getValue(),
					Rules.getOutcomeMessage(d1.getValue(), d2.getValue(), winnings, bet), bank));
		}

		UI.showScrollableMessage(buildSummary(), " Game Summary");
		HighScoreManager.add(player.getName(), bank);
		HighScoreManager.display();
	}

	private String buildSummary() {
		StringBuilder sb = new StringBuilder();
		for (String result : results) {
			if (result != null)
				sb.append(result).append("\n");
		}
		sb.append("\n Final Balance: £").append(bank);
		return sb.toString();
	}
}