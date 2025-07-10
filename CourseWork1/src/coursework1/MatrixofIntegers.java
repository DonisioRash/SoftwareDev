package coursework1;

import javax.swing.JOptionPane; //This import allows us to use dialog boxes for input and output.

import java.util.Random; //This import allows us to use the Random class to generate random numbers.

public class MatrixofIntegers {

	public static void main(String[] args) {
		int rows = 0; //Declare a variable to store the number of rows in the matrix
        int column = 0; //Declare a variable to store the number of columns in the matrix
        Random rand = new Random(); //Create a Random object to generate random numbers later in the program

        //Ask for number of rows (3–10)
        while (rows < 3 || rows > 10)
        {
            String rowInput = JOptionPane.showInputDialog("Enter number of rows (3 to 10):");
            rows = Integer.parseInt(rowInput);
        }

        //Ask for number of columns (3–10)
        while (column < 3 || column > 10) 
        {
            String columnInput = JOptionPane.showInputDialog("Enter number of columns (3 to 10):");
            column = Integer.parseInt(columnInput);
        }

        //Create matrix
        int[][] matrix = new int[rows][column];

        //Fill matrix with random even/odd values based on row index
        for (int n = 0; n < rows; n++) 
        {
        	boolean isEvenRow = (n % 2 == 0); //true for even-numbered rows
            for (int m = 0; m < column; m++)
            {
                int number;
                //Keep generating until it matches the rule (even or odd)
                do 
                {
                  number = rand.nextInt(1001); //random number from 0 to 1000
                } while ((isEvenRow && number % 2 != 0) || (!isEvenRow && number % 2 == 0));
                matrix[n][m] = number;
            }
        }
        
        //Create a StringBuilder to store the final formatted matrix output   
        StringBuilder result = new StringBuilder();       

        //Loop through each row in the matrix
        for (int n = 0; n < rows; n++)
        {           
        	//Loop through each column in the current row
            for (int m = 0; m < column; m++)
            {
                result.append(String.format("| %-6d", matrix[n][m])); //formatted alignment
            }
            result.append("|\n"); //After each row, close the row with a '|' and move to a new line
        }
        
        //row averages
        double[] rowAverage = new double[rows]; //array to hold the average of each row
        //Loop over each row to compute its average
        for (int n = 0; n < rows; n++)
        {
            int sum = 0;
            //Add up all the values in row i
            for (int m = 0; m < column; m++)
            {
                sum += matrix[n][m];
            }
            //calculate the average for row i
            rowAverage[n] = (double) sum / column;
        }

        //column averages
        double[] columnAverage = new double[column]; //array to hold the average of each column.
        //Loop over each column to compute its average
        for (int m = 0; m < column; m++)
        {
            int sum = 0;
            //Add up all the values in column j 
            for (int n = 0; n < rows; n++)
            {
                sum += matrix[n][m];
            }
            //calculate the average for column j
            columnAverage[m] = (double) sum / rows;
        }

        //whole-matrix average
        double totalSum = 0; //hold running total
        for (int n = 0; n < rows; n++) //go through each row
        { 
            for (int m = 0; m < column; m++) //check each column
            { 
                totalSum += matrix[n][m]; //add the value to [i][j] to total sum
            }
        }
        
        //calculate the average of the whole matrix
        double matrixAvg = totalSum / (rows * column);
        
        //Find the row with the second-largest average
        int LargestAvInRow = 0;            //index of row with largest average
        int secondLargestAvInRow = -1;     //index of row with second-largest average
        for (int n = 1; n < rows; n++)
        {
            if (rowAverage[n] > rowAverage[LargestAvInRow]) 
            {
            	secondLargestAvInRow  = LargestAvInRow; //previous max becomes second
                LargestAvInRow = n;            //new max found
            } else if (secondLargestAvInRow  == -1 || rowAverage[n] > rowAverage[secondLargestAvInRow ])
            {
            	secondLargestAvInRow  = n;      //update second max
            }
        }

        //Find the column with the second-largest average
        int LargestAvInColumn = 0;            //index of column with largest average
        int secondLargestAvInColumn = -1;     //index of column with second-largest average
        for (int m = 1; m < column; m++)
        {
            if (columnAverage[m] > columnAverage[LargestAvInColumn])
            {
            	secondLargestAvInColumn = LargestAvInColumn; // previous max becomes second
                LargestAvInColumn = m;            // new max found
            } else if (secondLargestAvInColumn == -1 || columnAverage[m] > columnAverage[secondLargestAvInColumn])
            {
            	secondLargestAvInColumn = m;      // update second max
            }
        }
        
        //Build the modified matrix and count +1, 0, -1 
        int countPlus1  = 0;
        int countMinus1 = 0;
        int countZero   = 0;

        for (int n = 0; n < rows; n++)
        {
            for (int m = 0; m < column; m++)
            {
                int value = matrix[n][m];
                if (value > rowAverage[n] && value > columnAverage[m])
                {
                    //above both row & column average
                    matrix[n][m] = 1;
                    countPlus1++;
                }
                else if (value < rowAverage[n] && value < columnAverage[m])
                {
                    //below both row & column average
                    matrix[n][m] = -1;
                    countMinus1++;
                }
                else
                {
                    //otherwise
                    matrix[n][m] = 0;
                    countZero++;
                }
            }
        }
        
        StringBuilder result1 = new StringBuilder();
        
        //modified matrix
        for (int n = 0; n < rows; n++)
        {
            for (int m = 0; m < column; m++)
            {
                result1.append(String.format("| %-6d", matrix[n][m]));
            }
            result1.append("|\n");
        }

        //Show the counts of +1, 0, and -1
        result1.append(String.format(
            "\nThe program prints out the following lines:\nThe number of cells with values +1: %d\nThe number of cells with values 0: %d\nThe number of cells with values -1: %d\n",
            countPlus1, countZero, countMinus1
        ));
                   
        // output row averages
        result.append("\nRow averages:\n");
        for (int n = 0; n < rows; n++)
        {
            result.append(String.format("Row %-2d: %.2f\n", n, rowAverage[n]));
        }

        // output column averages
        result.append("\nColumn averages:\n");
        for (int m = 0; m < column; m++)
        {
        	result.append(String.format("Column %-2d: %.2f\n", m, columnAverage[m]));
        }
               
        //output overall matrix average
        result.append(String.format("\nOverall matrix average: %.2f", matrixAvg));
        
        //output second-largest row
        result.append(String.format("\nSecond-largest row average: Row %d = %.2f\n",
        		secondLargestAvInRow , rowAverage[secondLargestAvInRow]));
        
        //output second-largest column
        result.append(String.format("Second-largest column average: Column %d = %.2f\n",
        		secondLargestAvInColumn, columnAverage[secondLargestAvInColumn]));
                
        // Show everything in one dialog box
        JOptionPane.showMessageDialog(null, "\nMatrix of integer numbers:\n" + result.toString() + "\nModified Matrix:\n"+ result1);
	}

}
