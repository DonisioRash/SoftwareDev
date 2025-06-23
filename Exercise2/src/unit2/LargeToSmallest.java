package unit2;

import javax.swing.JOptionPane;

public class LargeToSmallest {

	public static void main(String[] args) {
		  int number;
	        int largest = Integer.MIN_VALUE;  // Start with the smallest possible value
	        int smallest = Integer.MAX_VALUE; // Start with the largest possible value

	        // Loop to read 10 numbers
	        for (int i = 1; i <= 10; i++) {
	            String input = JOptionPane.showInputDialog("Enter number " + i + ":");
	            number = Integer.parseInt(input);

	            // Update largest and smallest
	            if (number > largest) {
	                largest = number;
	            }
	            if (number < smallest) {
	                smallest = number;
	            }
	        }

	        // Display results
	        JOptionPane.showMessageDialog(null,
	            "The largest number is: " + largest +
	            "\nThe smallest number is: " + smallest);

	}

}
