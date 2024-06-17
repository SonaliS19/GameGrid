import java.util.*;

public class GameCenter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Welcome to the Game Center!\n");

        menu();

        int choice = -1;

        System.out.print("\nEnter your choice: ");
        choice = sc.nextInt();
        System.out.println();

        while (choice != 5) {
            switch (choice) {
                case 1:
                    GuessNumber.welcomeMessage();
                    GuessNumber.run();
                    break;
                case 2:
                    TicTacToe.welcomeMessage();
                    TicTacToe.run();
                    break;
                case 3:
                    RockPaperScissors.welcomeMessage();
                    RockPaperScissors.run();
                    break;
                case 4:
                    MathQuiz.welcomeMessage();
                    MathQuiz.run();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

            menu();
            choice = getChoice(sc);
        }

        // if choice is 5, exit the program with a goodbye message
        System.out.println("\nGoodbye!");
        sc.close();
    }

    public static int getChoice(Scanner inp) {
        int choice = -1;
        System.out.print("Enter your choice: ");
        choice = inp.nextInt();
        System.out.println();
        return choice;
    }

    public static void menu() {
        // display a horizontal line with edged corners using unicode characters
        System.out.println("MENU");
        System.out.println("    1) Guess a number     ");
        System.out.println("    2) Tic Tac Toe        ");
        System.out.println("    3) Rock Paper Scissors");
        System.out.println("    4) Math Quiz          ");
        System.out.println("    5) Exit               ");
    }
}
