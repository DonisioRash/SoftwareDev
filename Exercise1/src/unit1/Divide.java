package unit1;

import javax.swing.JOptionPane;

public class Divide {

	public static void main(String[] args) {
					String input1 = JOptionPane.showInputDialog("Enter number:");
	        int num1 = Integer.parseInt(input1);
	       

	     // Divide the number by 2 (floating point division)
	        double result = num1 / 2.0;

	        // Display the result in a message dialog
	        JOptionPane.showMessageDialog(null, "Result after dividing by 2: " + result);

	}

}
