package unit1;

import javax.swing.JOptionPane;

public class Age {

	public static void main(String[] args) {
		 // Step 1: Ask for the user's name
        String name = JOptionPane.showInputDialog("Please enter your First name:");
        
        String name2 = JOptionPane.showInputDialog("Please enter your last name:");
        
        //step 2: Ask for the user's age
        String ageInput = JOptionPane.showInputDialog("Please enter your current age:");

         // Step 3: Convert the age input to an integer
        int age = Integer.parseInt(ageInput);

        // Step 4: Calculate age next year
        int ageNextYear = age + 1;

        // Step 5: Display the message
        JOptionPane.showMessageDialog(null,
            name + " " + name2 + ", next year you will be " + ageNextYear + " years old.");

	}

}
