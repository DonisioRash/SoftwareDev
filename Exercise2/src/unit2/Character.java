package unit2;

import javax.swing.JOptionPane;

public class Character {

	public static void main(String[] args) {
		 String output;
	        String charAsString;
	        char theChar;

	        // Get the character input from the user
	        charAsString = JOptionPane.showInputDialog("Please enter a character");
	        theChar = charAsString.charAt(0);

	        // Check if the character is a vowel
	        if (theChar == 'a' || theChar == 'e' || theChar == 'i' || theChar == 'o' || theChar == 'u'  ||
	        	    theChar == 'A' || theChar == 'E' || theChar == 'I' || theChar == 'O' || theChar == 'U') {
	            output = "The character '" + theChar + "' is a vowel.";
	        } else {
	            output = "The character '" + theChar + "' is not a vowel.";
	        }

	        // Display result
	        JOptionPane.showMessageDialog(null, output);
	        
	}

	
}
