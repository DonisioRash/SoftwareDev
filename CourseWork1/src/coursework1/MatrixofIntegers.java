package coursework1;

import javax.swing.JOptionPane; // This import allows us to use dialog boxes for input and output.

import java.util.Random; // This import allows us to use the Random class to generate random numbers.

public class MatrixofIntegers {

	public static void main(String[] args) {
		int rows = 0; // Declare a variable to store the number of rows in the matrix
        int column = 0; // Declare a variable to store the number of columns in the matrix
        Random rand = new Random(); // Create a Random object to generate random numbers later in the program

        // Ask for number of rows (3–10)
        while (rows < 3 || rows > 10) {
            String rowInput = JOptionPane.showInputDialog("Enter number of rows (3 to 10):");
            rows = Integer.parseInt(rowInput);
        }

        // Ask for number of columns (3–10)
        while (column < 3 || column > 10) {
            String colInput = JOptionPane.showInputDialog("Enter number of columns (3 to 10):");
            column = Integer.parseInt(colInput);
        }

        // Create matrix
        int[][] matrix = new int[rows][column];

        // Fill matrix with random even/odd values based on row index
        for (int i = 0; i < rows; i++) {
            boolean isEvenRow = (i % 2 == 0); // true for even-numbered rows

            for (int j = 0; j < column; j++) {
                int number;

                // Keep generating until it matches the rule (even or odd)
                do {
                    number = rand.nextInt(1001); // random number from 0 to 1000
                } while ((isEvenRow && number % 2 != 0) || (!isEvenRow && number % 2 == 0));

                matrix[i][j] = number;
            }
        }
     // Create a StringBuilder to store the final formatted matrix output   
        StringBuilder result = new StringBuilder();
        

     // Loop through each row in the matrix
        for (int i = 0; i < rows; i++) {
           
        	// Loop through each column in the current row
            for (int j = 0; j < column; j++) {
                result.append(String.format("| %-6d", matrix[i][j])); //formatted alignment
            }
            result.append("|\n"); // After each row, close the row with a '|' and move to a new line
        }
        
        // row averages
        double[] rowAverage = new double[rows]; // array to hold the average of each row
        // Loop over each row to compute its average
        for (int i = 0; i < rows; i++) {
            int sum = 0;
            // Add up all the values in row i
            for (int j = 0; j < column; j++) {
                sum += matrix[i][j];
            }
            // calculate the average for row i
            rowAverage[i] = (double) sum / column;
        }

        //column averages
        double[] colAvg = new double[column]; //array to hold the average of each column.
        // Loop over each column to compute its average
        for (int j = 0; j < column; j++) {
            int sum = 0;
            // Add up all the values in column j 
            for (int i = 0; i < rows; i++) {
                sum += matrix[i][j];
            }
            // calculate the average for column j
            colAvg[j] = (double) sum / rows;
        }

        // whole-matrix average
        double totalSum = 0; // hold running total
        for (int i = 0; i < rows; i++) { // go through each row
            for (int j = 0; j < column; j++) { // check each column
                totalSum += matrix[i][j]; // add the value to [i][j] to total sum
            }
        }
        // calculate the average of the whole matrix
        double matrixAvg = totalSum / (rows * column);
        
        // output row averages
        result.append("\nRow averages:\n");
        for (int i = 0; i < rows; i++) {
            result.append(String.format("Row %-2d: %.2f\n", i, rowAverage[i]));
        }

        // output column averages
        result.append("\nColumn averages:\n");
        for (int j = 0; j < column; j++) {
            result.append(String.format("Column %-2d: %.2f\n", j, colAvg[j]));
        }

        // output overall matrix average
        result.append(String.format("\nOverall matrix average: %.2f", matrixAvg));

                
        // Show matrix in dialog box
        JOptionPane.showMessageDialog(null, "\nMatrix of integer numbers:\n" + result.toString());
	}

}
