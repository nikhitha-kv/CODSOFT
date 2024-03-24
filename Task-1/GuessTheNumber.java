import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1; // Minimum value of the range
        int maxRange = 100; // Maximum value of the range
        int attemptsLimit = 5; // Maximum number of attempts allowed
        int score = 0; // Initialize score
        
        boolean playAgain = true;
        
        while (playAgain) {
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("Welcome to Guess the Number Game!");
            System.out.println("I have selected a number between " + minRange + " and " + maxRange + ".");
            System.out.println("You have " + attemptsLimit + " attempts to guess the number.");

            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < attemptsLimit && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    score++;
                    guessedCorrectly = true;
                } else if (guess < randomNumber) {
                    System.out.println("Your guess is too low. Try again.");
                } else {
                    System.out.println("Your guess is too high. Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you have exhausted all your attempts. The correct number was: " + randomNumber);
            }

            System.out.println("Your current score is: " + score);

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainChoice = scanner.next();
            if (!playAgainChoice.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Thank you for playing Guess the Number!");
        scanner.close();
    }
}
