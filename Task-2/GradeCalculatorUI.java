import javax.swing.JOptionPane;

public class GradeCalculatorUI {
    public static void main(String[] args) {
        int totalSubjects = 0;
        int totalMarks = 0;

        // Input marks for each subject
        while (true) {
            String input = JOptionPane.showInputDialog("Enter marks for subject " + (totalSubjects + 1) + " (-1 to stop):");
            if (input.equals("-1")) {
                break;
            }
            int marks = Integer.parseInt(input);
            totalMarks += marks;
            totalSubjects++;
        }

        // Calculate Total Marks
        JOptionPane.showMessageDialog(null, "Total Marks Obtained: " + totalMarks);

        // Calculate Average Percentage
        double averagePercentage = (double) totalMarks / totalSubjects;
        JOptionPane.showMessageDialog(null, "Average Percentage: " + averagePercentage);

        // Grade Calculation
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        JOptionPane.showMessageDialog(null, "Grade: " + grade);
    }
}
