package coursework2;

public class DicePoker {
	public static void main(String[] args) {
		do {
			new Game().play();
		} while (askToAddAnotherPlayer());
		UI.showMessage("🎮 Thank you for playing Dice Poker!");
	}

	public static boolean askToAddAnotherPlayer() {
		int choice = javax.swing.JOptionPane.showConfirmDialog(null, "Add another player?", "Add Player",
				javax.swing.JOptionPane.YES_NO_OPTION);
		return choice == javax.swing.JOptionPane.YES_OPTION;
	}
}