package unit4;

import javax.swing.JOptionPane;

public class NumbersLessThanTen {

	public static void main(String[] args) {
		        int[] numbers = new int[5];  // Array to store 5 numbers

		        // Read 5 numbers into the array
		        for (int i = 0; i < numbers.length; i++) {
		            while (true) {
		                try {
		                    String input = JOptionPane.showInputDialog("Enter number " + (i + 1) + ":");
		                    numbers[i] = Integer.parseInt(input);
		                    break;  // Exit loop if input is valid
		                } catch (NumberFormatException e) {
		                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter an integer.");
		                }
		            }
		        }

		        // Build a message for numbers less than 10
		        StringBuilder output = new StringBuilder("Numbers less than 10:\n");
		        boolean found = false;

		        for (int number : numbers) {
		            if (number < 10) {
		                output.append(number).append("\n");
		                found = true;
		            }
		        }

		        if (!found) {
		            output = new StringBuilder("No numbers less than 10 were entered.");
		        }

		        // Display the result
		        JOptionPane.showMessageDialog(null, output.toString());

	}

}
