package unit4;

import javax.swing.JOptionPane;

public class Grades {

	public static void main(String[] args) {
		int[] marks = new int[10]; // Array to store marks

        // Counters for grade frequencies
        int gradeA = 0;
        int gradeB = 0;
        int gradeC = 0;
        int gradeF = 0;

        // Read and validate 10 marks
        for (int i = 0; i < marks.length; i++) {
            while (true) {
                try {
                    String input = JOptionPane.showInputDialog("Enter mark for student " + (i + 1) + " (0-100):");
                    int mark = Integer.parseInt(input);

                    if (mark >= 0 && mark <= 100) {
                        marks[i] = mark;

                        // Determine grade and count
                        if (mark >= 75) {
                            gradeA++;
                        } else if (mark >= 60) {
                            gradeB++;
                        } else if (mark >= 50) {
                            gradeC++;
                        } else {
                            gradeF++;
                        }

                        break; // valid input, break inner loop
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid mark. Must be between 0 and 100.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter an integer.");
                }
            }
        }

        // Show the frequency of grades
        String result = "Grade Frequency:\n" +
                        "Grade A (75–100): " + gradeA + "\n" +
                        "Grade B (60–74): " + gradeB + "\n" +
                        "Grade C (50–59): " + gradeC + "\n" +
                        "Grade F (0–49): " + gradeF;

        JOptionPane.showMessageDialog(null, result);
	}

}
