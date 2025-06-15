package unit1;

import javax.swing.JOptionPane;

public class Increase {

	public static void main(String[] args) {
		String numInput = JOptionPane.showInputDialog("Please enter your a number:");

        float num = Float.parseFloat(numInput);
        
        double numIncrease = num * 0.25;
                
        double finalNum = numIncrease + num;
        
     // Round all values to nearest whole number
        long roundedOriginal = Math.round(num);
        long roundedIncrease = Math.round(numIncrease);
        long roundedFinal = Math.round(finalNum);
        
        JOptionPane.showMessageDialog(null, "Number entered (rounded): " + roundedOriginal +
                "\n25% increase (rounded): " + roundedIncrease +
                "\n25% added (rounded): " + roundedFinal);
                
	}

}
