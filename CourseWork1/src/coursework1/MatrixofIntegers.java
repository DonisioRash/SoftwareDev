package coursework1;

import javax.swing.JOptionPane;

import java.util.Random;

public class MatrixofIntegers {

	public static void main(String[] args) {
		int rows = 0;
        int column = 0;
        Random rand = new Random();

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

        

        // Show matrix in dialog box
        JOptionPane.showMessageDialog(null, result.toString());
	}

}
