package coursework1;

import java.util.Random; //import allows us to use the Random class to generate random numbers.

//import all classes and interfaces from javax.swing package.
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MatrixofIntegers {
	// Purpose of main is to gather input, generates and processes matrix data,
	// performs calculations and formats and displays all outputs.
	public static void main(String[] args) {
		// Get number of rows from user input
		int rows = input("rows");
		int columns = input("columns");

		int[][] matrix = generateMatrix(rows, columns); // Generate matrix with even/odd rules
		String originalMatrixStr = formatMatrix(matrix); // Format original matrix as string

		double[] rowAverage = calculateRowAverages(matrix); // Calculate row averages
		double[] columnAverage = calculateColumnAverages(matrix); // Calculate column averages
		double matrixAverage = calculateMatrixAverage(matrix); // Calculate average of entire matrix

		int secondRow = findSecondLargestAverage(rowAverage); // Find row with second-largest average
		int secondColumn = findSecondLargestAverage(columnAverage); // Find column with second-largest average

		int[] counts = new int[3]; // Initialize count array for +1, 0, -1 values
		int[][] modifiedMatrix = modifyMatrix(matrix, rowAverage, columnAverage, counts); // Generate modified matrix
																							// based on comparison
		String modifiedMatrixStr = formatMatrix(modifiedMatrix); // Format modified matrix as string

		StringBuilder analysis = new StringBuilder(); // StringBuilder to hold analysis text
		analysis.append("\nRow averages:\n"); // Add heading for row averages
		for (int n = 0; n < rowAverage.length; n++) {
			analysis.append(String.format("Row %-2d: %.2f\n", n, rowAverage[n])); // Add the average of each row
		}
		analysis.append("\nColumn averages:\n"); // Add heading for column averages
		for (int m = 0; m < columnAverage.length; m++) {
			analysis.append(String.format("Column %-2d: %.2f\n", m, columnAverage[m])); // Add the average of each
																						// column
		}
		analysis.append(String.format("\nOverall matrix average: %.2f", matrixAverage)); // Add overall matrix average
		analysis.append(
				String.format("\nSecond-largest row average: Row %d = %.2f\n", secondRow, rowAverage[secondRow])); // Second-largest
																													// row
																													// average
		analysis.append(String.format("Second-largest column average: Column %d = %.2f\n", secondColumn,
				columnAverage[secondColumn])); // Second-largest column average

		String countSummary = String.format( // Format counts of +1, 0, and -1 values
				"\nThe program prints out the following lines:\n" + "The number of cells with values +1: %d\n"
						+ "The number of cells with values 0: %d\n" + "The number of cells with values -1: %d\n",
				counts[0], counts[1], counts[2]);
		showOutput("Matrix Results", "Matrix of integer numbers:\n" + originalMatrixStr + analysis,
				"Modified Matrix:\n" + modifiedMatrixStr + countSummary); // Display output
	}

	public static int input(String name) {
		while (true) { // Loop until valid input is received
			String input = JOptionPane.showInputDialog("Enter number of " + name + " (3 to 10):"); // Prompt user
			try {
				int value = Integer.parseInt(input); // Try to parse input
				if (value >= 3 && value <= 10) {
					return value; // Return if in valid range
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a whole number between 3 and 10!"); // Show error
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null,
						"Invalid input! Please enter a whole number (no decimals or letters)."); // Show error if
																									// parsing fails
			}
		}
	}

	public static int[][] generateMatrix(int rows, int columns) {
		int[][] matrix = new int[rows][columns]; // Create matrix
		Random rand = new Random(); // Create random number generator
		for (int n = 0; n < rows; n++) {
			boolean isEvenRow = (n % 2 == 0); // Determine if row is even numbered
			for (int m = 0; m < columns; m++) {
				int number;
				do {
					number = rand.nextInt(1001); // Generate a random number between 0 and 1000
				} while ((isEvenRow && number % 2 != 0) || (!isEvenRow && number % 2 == 0)); // Ensure number matches
																								// even/odd rule
				matrix[n][m] = number; // Assign number to matrix
			}
		}
		return matrix; // Return the filled matrix
	}

	public static String formatMatrix(int[][] matrix) {
		StringBuilder result = new StringBuilder(); // Initialize result string
		for (int[] row : matrix) {
			for (int value : row) {
				result.append(String.format("| %4d", value)); // Format each number
			}
			result.append("|\n"); // End of row
		}
		return result.toString(); // Return formatted matrix
	}

	public static double[] calculateRowAverages(int[][] matrix) {
		int rows = matrix.length;
		int columns = matrix[0].length;
		double[] averages = new double[rows]; // Create result array
		for (int n = 0; n < rows; n++) {
			int sum = 0;
			for (int m = 0; m < columns; m++) {
				sum += matrix[n][m]; // Add each element in row
			}
			averages[n] = (double) sum / columns; // Compute average
		}
		return averages; // Return row averages
	}

	public static double[] calculateColumnAverages(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		double[] averages = new double[cols]; // Create result array
		for (int m = 0; m < cols; m++) {
			int sum = 0;
			for (int n = 0; n < rows; n++) {
				sum += matrix[n][m]; // Add each element in column
			}
			averages[m] = (double) sum / rows; // Compute average
		}
		return averages; // Return column averages
	}

	public static double calculateMatrixAverage(int[][] matrix) {
		int total = 0;
		for (int[] row : matrix) {
			for (int value : row) {
				total += value; // Add each element to total
			}
		}
		return (double) total / (matrix.length * matrix[0].length); // Return overall average
	}

	public static int findSecondLargestAverage(double[] arr) {
		int largest = 0;
		int second = -1; // Initialize indices
		for (int sla = 1; sla < arr.length; sla++) {
			if (arr[sla] > arr[largest]) {
				second = largest;
				largest = sla; // Update largest and second-largest
			} else if (second == -1 || arr[sla] > arr[second]) {
				second = sla; // Update second-largest
			}
		}
		return second; // Return index of second-largest
	}

	public static int[][] modifyMatrix(int[][] matrix, double[] rowAverage, double[] columnAverage, int[] counts) {
		int rows = matrix.length;
		int columns = matrix[0].length;
		int[][] modified = new int[rows][columns]; // Create modified matrix
		for (int n = 0; n < rows; n++) {
			for (int m = 0; m < columns; m++) {
				int val = matrix[n][m];
				if (val > rowAverage[n] && val > columnAverage[m]) {
					modified[n][m] = 1; // Greater than both averages
					counts[0]++;
				} else if (val < rowAverage[n] && val < columnAverage[m]) {
					modified[n][m] = -1; // Less than both averages
					counts[2]++;
				} else {
					modified[n][m] = 0; // Between the two
					counts[1]++;
				}
			}
		}
		return modified; // Return modified matrix
	}

	public static void showOutput(String title, String original, String modified) {
		JTextArea textArea = new JTextArea(original + "\n" + modified); // Combine original and modified text
		textArea.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 10)); // Force font
		textArea.setEditable(false); // Make text read-only
		textArea.setCaretPosition(0); // Set scroll to top
		JScrollPane scrollPane = new JScrollPane(textArea); // Wrap text area in scroll pane
		scrollPane.setPreferredSize(new java.awt.Dimension(400, 450)); // Set window size
		JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE); // Show dialog
	}

}
