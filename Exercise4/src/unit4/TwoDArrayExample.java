package unit4;

import javax.swing.JOptionPane;

public class TwoDArrayExample {

	public static void main(String[] args) {
		  int[][] numbers = new int[3][3];  // 3x3 grid

	        // Input values
	        for (int row = 0; row < 3; row++) {
	            for (int col = 0; col < 3; col++) {
	                while (true) {
	                    try {
	                        String input = JOptionPane.showInputDialog("Enter value for [" + row + "][" + col + "]:");
	                        numbers[row][col] = Integer.parseInt(input);
	                        break;
	                    } catch (NumberFormatException e) {
	                        JOptionPane.showMessageDialog(null, "Invalid input. Enter an integer.");
	                    }
	                }
	            }
	        }

	        // Print array to console
	        System.out.println("The 2D array is:");
	        for (int row = 0; row < 3; row++) {
	            for (int col = 0; col < 3; col++) {
	                System.out.print(numbers[row][col] + "\t");
	            }
	            System.out.println();  // new line after each row
	        }

	}

}
