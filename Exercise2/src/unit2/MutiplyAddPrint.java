package unit2;

import javax.swing.JOptionPane;

public class MutiplyAddPrint {

	public static void main(String[] args) {
		String input1 = JOptionPane.showInputDialog("Enter first number:");
        int num1 = Integer.parseInt(input1);
        
        String input2 = JOptionPane.showInputDialog("Enter second number:");
        int num2 = Integer.parseInt(input2);
        
        int result1 = num1 + num2;
        double result2 = num1 * num2;
        
        String comparisonMessage;
        if (num1 > num2) {
            comparisonMessage = "The first number (" + num1 + ") is larger.";
        } else if (num2 > num1) {
            comparisonMessage = "The second number (" + num2 + ") is larger.";
        } else {
            comparisonMessage = "Both numbers are equal.";
        }
        

        JOptionPane.showMessageDialog(null, "•	the value of them added together : " + result1 + "\n•	the value of them multiplied together : " + result2 +
        		"\n• " + comparisonMessage);

	}

}
