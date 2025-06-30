package unit4;

import javax.swing.JOptionPane;

public class SalaryTaxCalculator {

	public static void main(String[] args) {
		 double[] salaries = new double[5];  // Array to store 5 salaries
	        double taxRate = 0.25;

	        // Read 5 salaries from the user
	        for (int i = 0; i < salaries.length; i++) {
	            while (true) {
	                try {
	                    String input = JOptionPane.showInputDialog("Enter salary for person " + (i + 1) + ":");
	                    salaries[i] = Double.parseDouble(input);

	                    if (salaries[i] < 0) {
	                        JOptionPane.showMessageDialog(null, "Salary cannot be negative. Try again.");
	                    } else {
	                        break; // Exit loop on valid input
	                    }
	                } catch (NumberFormatException e) {
	                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
	                }
	            }
	        }

	        // Build tax report
	        StringBuilder output = new StringBuilder("Tax to be paid (25%) for each salary:\n");

	        for (int i = 0; i < salaries.length; i++) {
	            double tax = salaries[i] * taxRate;
	            output.append("Person ").append(i + 1)
	                  .append(": $").append(String.format("%.2f", tax)).append("\n");
	        }

	        // Display the tax for each salary
	        JOptionPane.showMessageDialog(null, output.toString());

	}

}
