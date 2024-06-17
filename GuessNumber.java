import java.util.Scanner;

public class GuessNumber {

    public static void main(String[] args) {
        welcomeMessage();
        run();
    }

    // function to print welcome message along with instructions
    public static void welcomeMessage() {
        System.out.println("<------------- Welcome to Guess a Number! ------------->\n");
        System.out.println("The computer will generate a random number between 0 and 500.");
        System.out.println("You will have to guess the number.");
        System.out.println("\nIf your guess is too high, the computer will tell you.");
        System.out.println("If your guess is too low, the computer will tell you.");
        System.out.println("If you guess the number, the computer will tell you.");
        System.out.println("You will have to guess the number in the least amount of guesses.");
        System.out.println("\nGood luck!\n");
    }

    // function to play game
    public static void run() {
        // generate random number between 0 and 500 using Math.random()
        // Math.random() returns a double between 0.0 and 1.0
        // typecast to int to get a whole number
        int number = (int) (Math.random() * 500);

        // create a Scanner object to read input from the user
        Scanner input = new Scanner(System.in);

        // initialize guess{variable to store the user guess} to -1
        int guess = -1;

        // variable to store the number of guesses made by the user
        int guessCount = 0;

        // while the user has not guessed the number correctly
        // execute the following block of code
        while (guess != number) {
            // ask the user to enter a guess
            System.out.print("Enter your guess: ");
            // store the user's guess in the guess variable
            guess = input.nextInt();

            // increment the guessCount variable
            guessCount++;

            // if the user has guessed the number correctly
            if (guess == number) {
                // print a message to the user
                System.out.println("\nYes, the number is " + number);
                // print the number of guesses made by the user
                System.out.println("You guessed the correct number in " + guessCount + " times");
            }
            // if the user's guess is greater than the number
            else if (guess > number) {
                System.out.println("Your guess is too high\n");
            }
            // if the user's guess is less than the number
            else {
                System.out.println("Your guess is too low\n");
            }
        }

        // close the Scanner object
        input.close();
    }
}
