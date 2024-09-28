import java.util.*;

public class SimpleQuiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] questions = {
            "What is the capital of France?",
            "Which is the largest planet?",
            "Which is the fastest animal?"
        };
        String[][] options = {
            {"1. London", "2. Berlin", "3. Paris", "4. Rome"},
            {"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"},
            {"1. Cheetah", "2. Lion", "3. Eagle", "4. Shark"}
        };
        int[] correctAnswers = {3, 3, 1};
        int score = 0;

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            // Timer for the question
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("\nTime's up!");
                    System.exit(0);
                }
            };
            timer.schedule(task, 10000); // 10 seconds timer

            System.out.print("Your answer: ");
            int answer = scanner.nextInt();
            timer.cancel();  // Stop the timer if the user answers within time

            if (answer == correctAnswers[i]) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Incorrect.\n");
            }
        }
        System.out.println("Your final score is: " + score + "/" + questions.length);
        scanner.close();
    }
}