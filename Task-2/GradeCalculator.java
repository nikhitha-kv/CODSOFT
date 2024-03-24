import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input
        System.out.println("Enter marks obtained (out of 100) in each subject:");
        int totalSubjects = 0;
        int totalMarks = 0;
        
        while (true) {
            System.out.print("Enter marks for subject " + (totalSubjects + 1) + " (-1 to stop): ");
            int marks = scanner.nextInt();
            if (marks == -1) {
                break;
            }
            totalMarks += marks;
            totalSubjects++;
        }
        
        // Calculate Total Marks
        System.out.println("Total Marks Obtained: " + totalMarks);
        
        // Calculate Average Percentage
        double averagePercentage = (double) totalMarks / totalSubjects;
        System.out.println("Average Percentage: " + averagePercentage);
        
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
        System.out.println("Grade: " + grade);
        
        scanner.close();
    }
}
