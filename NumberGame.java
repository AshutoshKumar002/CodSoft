import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int rounds = 0;
        int totalScore = 0;

        System.out.println("Your Welcome to the Number Game!");

        while (true) {
            rounds++;
            int randomNumber = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 7;

            System.out.println("Round " + rounds + ": Guess the number between 1 and 100");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    totalScore += (maxAttempts - attempts + 1);
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Your guess is too high.");
                }

                if (attempts == maxAttempts) {
                    System.out.println("You've reached the maximum number of attempts. The correct number was " + randomNumber);
                }
            }

            System.out.println("Your score for this round: " + (maxAttempts - attempts + 1));
            System.out.println("Your total score after " + rounds + " rounds: " + totalScore);

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                System.out.println("Thank you for playing! Your final score is: " + totalScore);
                break;
            }
        }

        scanner.close();
    }
}
