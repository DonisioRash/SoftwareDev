package unit2;

import javax.swing.JOptionPane;

public class OddOrEven {

	public static void main(String[] args) {
		String input1 = JOptionPane.showInputDialog("Enter a number:");
        int num1 = Integer.parseInt(input1);
        
        // Check if number is even or odd using modulus operator
        String result;
        if (num1 % 2 == 0) {
            result = "The number " + num1 + " is even.";
        } else {
            result = "The number " + num1 + " is odd.";
        }

        // Show the result in a message dialog
        JOptionPane.showMessageDialog(null, result);

	}

}
