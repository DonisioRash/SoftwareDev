package coursework1;

import javax.swing.*; //import all classes and interfaces from javax.swing package.

import java.util.Random; //import allows us to use the Random class to generate random numbers.

public class MatrixofIntegers {

	public static void main(String[] args) {
		int rows = 0; //declare a variable to store the number of rows in the matrix
        int column = 0; //declare a variable to store the number of columns in the matrix
        Random rand = new Random(); //create a Random object to generate random numbers later in the program

        //Ask for number of rows (3–10)
        while (true) {
            String rowInput = JOptionPane.showInputDialog("Enter number of rows (3 to 10):");
            try {
                rows = Integer.parseInt(rowInput);
                //check if the input is in the asked range
                if (rows >= 3 && rows <= 10)
                {
                	break; //if correct exit the loop
                }
                else
                { 	//not in range show the message, show error
                	JOptionPane.showMessageDialog(null, "Please enter a whole number between 3 and 10!");
                }
                } catch (NumberFormatException e)
            {
                    //If the input is wrong show error message
                	JOptionPane.showMessageDialog(null, "Invalid input! Please enter a whole number (no decimals or letters).");
            }
            
        }
        
        //Ask for number of columns (3–10)
        while (true) {
            String columnInput = JOptionPane.showInputDialog("Enter number of columns (3 to 10):");
            try {
            	column = Integer.parseInt(columnInput);
            	//check if the input is in the asked range
            	if (column >= 3 && column <= 10)
            	{
            		break;//if correct exit the loop
            	}
            	else
            	{
            		//not in range show the message, show error
            		JOptionPane.showMessageDialog(null, "Please enter a whole number between 3 and 10!");
                }
            	} catch (NumberFormatException e)
            {
            		//If the input is wrong show error message
            		JOptionPane.showMessageDialog(null, "Invalid input! Please enter a whole number (no decimals or letters).");
            }
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
                totalSum += matrix[n][m]; //add the value to [n][m] to total sum
            }
        }
        
        //calculate the average of the whole matrix
        double matrixAvg = totalSum / (rows * column);
        
        //Find the row with the second-largest average
        int largestAverageInRow = 0;            //index of row with largest average
        int secondLargestAvInRow = -1;     //index of row with second-largest average
        for (int n = 1; n < rows; n++)
        {
            if (rowAverage[n] > rowAverage[largestAverageInRow]) 
            {
            	secondLargestAvInRow  = largestAverageInRow; //previous max becomes second
                largestAverageInRow = n;            //new max found
            } else if (secondLargestAvInRow  == -1 || rowAverage[n] > rowAverage[secondLargestAvInRow ])
            {
            	secondLargestAvInRow  = n;      //update second max
            }
        }

        //find the column with the second-largest average
        int LargestAverageInColumn = 0;            //index of column with largest average
        int secondLargestAverageInColumn = -1;     //index of column with second-largest average
        for (int m = 1; m < column; m++)
        {
            if (columnAverage[m] > columnAverage[LargestAverageInColumn])
            {
            	secondLargestAverageInColumn = LargestAverageInColumn; // previous max becomes second
                LargestAverageInColumn = m;            // new max found
            } else if (secondLargestAverageInColumn == -1 || columnAverage[m] > columnAverage[secondLargestAverageInColumn])
            {
            	secondLargestAverageInColumn = m;      // update second max
            }
        }
        
        //build the modified matrix and count +1, 0, -1 
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

        //show the counts of +1, 0, and -1
        result1.append(String.format(
            "\nThe program prints out the following lines:\nThe number of cells with values +1: %d\nThe number of cells with values 0: %d\nThe number of cells with values -1: %d\n",
            countPlus1, countZero, countMinus1
        ));
                   
        //output row averages
        result.append("\nRow averages:\n");
        for (int n = 0; n < rows; n++)
        {
            result.append(String.format("Row %-2d: %.2f\n", n, rowAverage[n]));
        }

        //output column averages
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
        		secondLargestAverageInColumn, columnAverage[secondLargestAverageInColumn]));
        
        //show the entire output      
        JTextArea textArea = new JTextArea
        ("Matrix of integer numbers:\n" + result.toString() + "\nModified Matrix:\n" + result1.toString());
        //text area set as read-only
        textArea.setEditable(false);
        //ensures the scroll starts at the top of the text
        textArea.setCaretPosition(0);
        //allows Scrolling
        JScrollPane scrollPane = new JScrollPane(textArea);
        //default size for scroll window
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 450));
        //dialog box with scroll
        JOptionPane.showMessageDialog(null, scrollPane, "Matrix Results", JOptionPane.INFORMATION_MESSAGE);
        
	}

}
