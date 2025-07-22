//Donisio Rash

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

	// Receive input from user
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

	// Create a matrix of integer numbers with N rows and M column
	// and fill it up by random integers ranging from 0 to 1000 such that odd rows
	// contain only odd numbers while even rows contain only even numbers.
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

	// modify the cells in the matrix
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

	// Compute the average of each row of the whole matrix
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

	// Computer the average of each column of the whole matrix
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

	// The average of the whole matrix
	public static double calculateMatrixAverage(int[][] matrix) {
		int total = 0;
		for (int[] row : matrix) {
			for (int value : row) {
				total += value; // Add each element to total
			}
		}
		return (double) total / (matrix.length * matrix[0].length); // Return overall average
	}

	// Used for both row averages and column averages to find which row/column has
	// the second-highest average
	public static int findSecondLargestAverage(double[] arr) {
		int largest = 0; // Assume the first element (index 0) is the largest to start
		int second = -1; // Start with 'second' as -1 (means "not found yet")

		for (int sla = 1; sla < arr.length; sla++) { // Start from the second element (index 1)
			if (arr[sla] > arr[largest]) {
				// If current element is bigger than the largest so far,
				// move the current 'largest' to 'second', and update 'largest'
				second = largest;
				largest = sla;
			} else if (second == -1 || arr[sla] > arr[second]) {
				// If 'second' hasn't been set yet, or current is bigger than 'second'
				// (but not bigger than 'largest'), update 'second'
				second = sla;
			}
		}
		return second; // Return the index of the second-largest value
	}

	// It creates a new matrix of the same size as the original.
	// Each cell in the new matrix gets a value:
	// 1 if the original value is greater than both its row and column average,
	// -1 if the original value is less than both its row and column average,
	// 0 otherwise.
	// It also counts how many times each of these values (1, 0, -1) occurs.
	public static int[][] modifyMatrix(int[][] matrix, double[] rowAverage, double[] columnAverage, int[] counts) {
		int rows = matrix.length;
		int columns = matrix[0].length;
		int[][] modified = new int[rows][columns]; // Create new matrix for results

		for (int n = 0; n < rows; n++) { // For each row
			for (int m = 0; m < columns; m++) { // For each column
				int val = matrix[n][m]; // Get the original matrix value

				// If value is greater than both its row and column average
				if (val > rowAverage[n] && val > columnAverage[m]) {
					modified[n][m] = 1; // Set to 1 in new matrix
					counts[0]++; // Count another +1
				}
				// If value is less than both its row and column average
				else if (val < rowAverage[n] && val < columnAverage[m]) {
					modified[n][m] = -1; // Set to -1 in new matrix
					counts[2]++; // Count another -1
				}
				// If value is neither, set to 0
				else {
					modified[n][m] = 0; // Set to 0 in new matrix
					counts[1]++; // Count another 0
				}
			}
		}
		return modified; // Return the new matrix with +1, 0, -1 values
	}

	// Display Results
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
