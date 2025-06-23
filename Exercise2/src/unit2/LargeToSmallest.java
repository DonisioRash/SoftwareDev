package unit2;

import javax.swing.JOptionPane;

public class LargeToSmallest {

	public static void main(String[] args) {
		int[] numbers = new int[10]; // Array to store 10 numbers
        int largest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;

	        // Loop to read 10 numbers
	        for (int i = 0; i < 10; i++) {
	            String input = JOptionPane.showInputDialog("Enter number " + (i + 1) + ":");
	            numbers[i] = Integer.parseInt(input);

	            // Update largest and smallest
	            if (numbers[i] > largest) {
	                largest = numbers[i];
	            }
	            if (numbers[i] < smallest) {
	                smallest = numbers[i];
	            }
	        }
	        
	        // Build a string of all numbers entered
	        StringBuilder allNumbers = new StringBuilder("Numbers entered:\n");
	        for (int num : numbers) {
	            allNumbers.append(num).append(" ");
	        }

	        // Display results
	        JOptionPane.showMessageDialog(null,
	        		allNumbers.toString() +
	            "\n\nThe largest number is: " + largest +
	            "\nThe smallest number is: " + smallest);

	}

}
