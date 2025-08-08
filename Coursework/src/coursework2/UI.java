package coursework2;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UI {
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public static void showScrollableMessage(String message, String title) {
		JTextArea area = new JTextArea(message);
		area.setEditable(false);
		JScrollPane scroll = new JScrollPane(area);
		JOptionPane.showMessageDialog(null, scroll, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static String getPlayerName() {
		String name = "";
		while (name == null || name.trim().isEmpty()) {
			name = JOptionPane.showInputDialog("Enter your name:");
		}
		return name.trim();
	}

	public static int getValidBet(int bank) {
		while (true) {
			String input = JOptionPane.showInputDialog("Enter your bet (£1–£4):");
			if (input == null)
				return 0;
			try {
				int bet = Integer.parseInt(input.trim());
				if (bet >= 1 && bet <= 4 && bet <= bank)
					return bet;
				showMessage("Invalid bet. Must be £1–£4 and not exceed your balance (£" + bank + ").");
			} catch (NumberFormatException e) {
				showMessage("Please enter a valid number.");
			}
		}
	}
}