package unit3;

import javax.swing.JOptionPane;

public class StudentMarks {

	public static void main(String[] args) {
		int[] marks = new int[10];  // Array to store student marks
        int passCount = 0;
        int failCount = 0;

        // Loop to input marks for 10 students
        for (int i = 0; i < marks.length; i++) {
            while (true) {
                try {
                    String input = JOptionPane.showInputDialog("Enter mark for student " + (i + 1) + " (0 to 100):");
                    int mark = Integer.parseInt(input);

                    // Validate range
                    if (mark >= 0 && mark <= 100) {
                        marks[i] = mark;

                        // Count pass/fail
                        if (mark >= 50) {
                            passCount++;
                        } else {
                            failCount++;
                        }
                        break;  // Exit loop if input is valid
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid mark. Please enter a number between 0 and 100.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
                }
            }
        }

        // Show final result
        JOptionPane.showMessageDialog(null,
            "Total students passed: " + passCount +
            "\nTotal students failed: " + failCount);

	}

}
