package unit2;

import javax.swing.JOptionPane;

public class PayForChildren {

	public static void main(String[] args) {
		 String input = JOptionPane.showInputDialog("Enter the number of newspapers delivered:");
	        int copiesDelivered = Integer.parseInt(input);

	        int totalPence;

	        // Calculate pay based on rules
	        if (copiesDelivered <= 100) {
	            totalPence = copiesDelivered * 10;
	        } else {
	            int first100 = 100 * 10;
	            int remaining = (copiesDelivered - 100) * 15;
	            totalPence = first100 + remaining;
	        }

	        // Convert pence to pounds and pence
	        int pounds = totalPence / 100;
	        int pence = totalPence % 100;

	        // Display result
	        JOptionPane.showMessageDialog(null,
	            "Total pay: " + totalPence + " pence\n(" + pounds + " pounds and " + pence + " pence)");
	}

}
