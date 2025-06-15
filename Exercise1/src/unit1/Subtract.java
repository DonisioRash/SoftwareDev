package unit1;

import javax.swing.JOptionPane;

public class Subtract {

	public static void main(String[] args) {
		String input1 = JOptionPane.showInputDialog("Enter first number:");
        String input2 = JOptionPane.showInputDialog("Enter second number:");

        int num1 = Integer.parseInt(input1);
        int num2 = Integer.parseInt(input2);

        int result = num1 - num2;

        JOptionPane.showMessageDialog(null, "Result: " + result);

	}

}
