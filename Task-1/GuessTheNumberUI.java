import java.util.Random;
import javax.swing.JOptionPane;

public class GuessTheNumberUI {
    public static void main(String[] args) {
        Random random = new Random();
        int minRange = 1; 
        int maxRange = 100; 
        int attemptsLimit = 5; 
        int score = 0; 
        
        boolean playAgain = true;
        
        while (playAgain) {
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            JOptionPane.showMessageDialog(null, "Welcome to Guess the Number Game!\nI have selected a number between "
                    + minRange + " and " + maxRange + ".\nYou have " + attemptsLimit + " attempts to guess the number.");

            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < attemptsLimit && !guessedCorrectly) {
                String guessInput = JOptionPane.showInputDialog("Enter your guess:");
                int guess = Integer.parseInt(guessInput);
                attempts++;

                if (guess == randomNumber) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    score++;
                    guessedCorrectly = true;
                } else if (guess < randomNumber) {
                    JOptionPane.showMessageDialog(null, "Your guess is too low. Try again.");
                } else {
                    JOptionPane.showMessageDialog(null, "Your guess is too high. Try again.");
                }
            }

            if (!guessedCorrectly) {
                JOptionPane.showMessageDialog(null, "Sorry, you have exhausted all your attempts. The correct number was: " + randomNumber);
            }

            JOptionPane.showMessageDialog(null, "Your current score is: " + score);

            String playAgainChoice = JOptionPane.showInputDialog("Do you want to play again? (yes/no)");
            if (!playAgainChoice.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        JOptionPane.showMessageDialog(null, "Thank you for playing Guess the Number!");
    }
}
