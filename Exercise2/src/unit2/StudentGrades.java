package unit2;

import javax.swing.JOptionPane;

public class StudentGrades {

	public static void main(String[] args) {
		int[] marks = new int[10];  // Array to store marks
        int passCount = 0;
        int failCount = 0;

        // Read 10 marks
        for (int i = 0; i < 10; i++) {
            String input = JOptionPane.showInputDialog("Enter mark for student " + (i + 1) + ":");
            marks[i] = Integer.parseInt(input);

            // Check if mark is a pass or fail
            if (marks[i] >= 50) {
                passCount++;
            } else {
                failCount++;
            }
        }

        // Display the result
        JOptionPane.showMessageDialog(null,
            "Total students passed: " + passCount +
            "\nTotal students failed: " + failCount);

	}

}
