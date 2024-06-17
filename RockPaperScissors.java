import java.util.*;

public class RockPaperScissors {

    public static void main(String[] args) {
        welcomeMessage();
        run();
    }

    // declare and initialize global variables
    // static variables are shared by all instances of the class
    // and are accessible from all methods, the main method, and the constructor
    // only one copy of the static variable exists

    // made static so that they can be accessed from the run() method, which is
    // static method
    static int userScore = 0;
    static int computerScore = 0;
    static int tieScore = 0;
    static int userChoice = 0;
    static int computerChoice = 0;
    static int round = 1;
    static Scanner input = new Scanner(System.in);
    static int totalRounds = 0;

    // function to print welcome message along with instructions
    public static void welcomeMessage() {
        System.out.println("Welcome to Rock, Paper, Scissors!");
        System.out.println("\n     Game Rules     ");
        System.out.println("1. Rock beats Scissors");
        System.out.println("2. Scissors beats Paper");
        System.out.println("3. Paper beats Rock\n");
    }

    // function to run the game
    public static void run() {
        // ask the user how many rounds they would like to play
        System.out.print("How many rounds would you like to play? ");
        totalRounds = input.nextInt(); // take input from the user

        // validate user input for totalRounds
        while (totalRounds <= 0) {
            System.out.print("Please enter a positive number: ");
            totalRounds = input.nextInt();
        }

        // while loop to play the game for the number of rounds specified by the user
        while (round <= totalRounds) {
            // display round number
            System.out.println("\nRound " + round);

            // ask the user to enter their choice
            System.out.println("1. Rock");
            System.out.println("2. Paper");
            System.out.println("3. Scissors");
            System.out.print("\nEnter your choice: ");
            userChoice = input.nextInt(); // take input from the user

            // validate user input for userChoice
            while (userChoice < 1 || userChoice > 3) {
                System.out.print("Please enter a number between 1 and 3: ");
                userChoice = input.nextInt();
            }

            // generate random number between 1 and 3 for computerChoice
            // using Math.random()
            computerChoice = (int) (Math.random() * 3) + 1;

            System.out.println();
            // display computer's choice
            if (computerChoice == 1) {
                System.out.println("Computer chose Rock.");
            } else if (computerChoice == 2) {
                System.out.println("Computer chose Paper.");
            } else {
                System.out.println("Computer chose Scissors.");
            }

            // check who won the round
            if (userChoice == computerChoice) {
                System.out.println("It's a tie!");
                tieScore++;
            } else if (userChoice == 1 && computerChoice == 3) {
                System.out.println("You win!");
                userScore++;
            } else if (userChoice == 2 && computerChoice == 1) {
                System.out.println("You win!");
                userScore++;
            } else if (userChoice == 3 && computerChoice == 2) {
                System.out.println("You win!");
                userScore++;
            } else {
                System.out.println("You lose!");
                computerScore++;
            }
            round++;
        }

        // display game summary
        System.out.println("\n\n     Game Summary\n");
        // display total number of rounds played
        System.out.println("Total number of rounds played: " + totalRounds + "\n");
        System.out.println("You won " + userScore + " times.");
        System.out.println("Computer won " + computerScore + " times.");
        if (tieScore > 0)
            System.out.println("There were " + tieScore + " ties.\n");

        // display who won the game
        if (userScore > computerScore) {
            System.out.println("You won the game!");
        } else if (userScore < computerScore) {
            System.out.println("Computer won the game!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
