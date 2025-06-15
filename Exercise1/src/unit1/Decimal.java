package unit1;

import javax.swing.JOptionPane;

public class Decimal {

	public static void main(String[] args) {
		String input1 = JOptionPane.showInputDialog("Enter number with decimal:");
        float num1 = Float.parseFloat(input1);
       

     // Divide the number by 2 (floating point division)
        float result = num1 / 2;

        // Display the result in a message dialog
        JOptionPane.showMessageDialog(null, "Result after dividing by 2: " + result);
	}

}
