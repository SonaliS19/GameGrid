import java.util.Scanner;

public class MathQuiz {

    // data members
    private int score;
    private int questionNumber;
    private int answer;
    private int firstNumber;
    private int secondNumber;
    private int operation;
    private int[] options = new int[4];

    // scanner object
    static Scanner scanner = new Scanner(System.in);

    // possible operations
    char[] operations = { '+', '-', '*', '/', '^' };
    private int correctAnswer;

    // default constructor
    public MathQuiz() {
        // initialize score and question number
        score = 0;
        questionNumber = 1;
    }

    // function to display the welcome message
    public static void welcomeMessage() {
        // welcome message
        System.out.println("Welcome to the Math Quiz!");

        // display information about the game
        System.out.println("You will be asked to answer 10 questions.");

        System.out
                .println("\n\nAddition, subtraction, multiplication, power and division of 2 double digit numbers");
    }

    private int[] generateOptions() {
        // generate random options
        options[0] = correctAnswer + 5;
        options[1] = correctAnswer - 5;
        options[2] = correctAnswer + 10;
        options[3] = correctAnswer - 10;

        // check if the array contains the correct answer
        boolean containsCorrectAnswer = false;
        for (int i = 0; i < 4; i++) {
            if (options[i] == correctAnswer) {
                containsCorrectAnswer = true;
                break;
            }
        }

        // if the array does not contain the correct answer, replace one of the
        // options with the correct answer
        if (!containsCorrectAnswer) {
            int randomIndex = (int) (Math.random() * 4);
            options[randomIndex] = correctAnswer;
        }

        return options;
    }

    public boolean play() {
        try {
            // generate random operation
            operation = (int) (Math.random() * 5);

            // generate random numbers
            firstNumber = (int) (Math.random() * 100);
            secondNumber = (int) (Math.random() * 100);

            // if operation is division, make sure that the result is an integer
            if (operation == 3) {
                while ((firstNumber % secondNumber != 0) && secondNumber != 0) {
                    firstNumber = (int) (Math.random() * 100);
                    secondNumber = (int) (Math.random() * 100);
                }
            }

            // if operation is power, make sure that the exponent is less than equal to 4
            if (operation == 4) {
                while (secondNumber > 4) {
                    secondNumber = (int) (Math.random() * 10);
                }
            }

            // generate correct answer
            switch (operation) {
                case 0:
                    correctAnswer = firstNumber + secondNumber;
                    break;
                case 1:
                    correctAnswer = firstNumber - secondNumber;
                    break;
                case 2:
                    correctAnswer = firstNumber * secondNumber;
                    break;
                case 3:
                    correctAnswer = firstNumber / secondNumber;
                    break;
                case 4:
                    correctAnswer = (int) Math.pow(firstNumber, secondNumber);
                    break;
            }

            // generate options
            generateOptions();

            // display question
            System.out.println("\nQuestion " + questionNumber + ":");
            System.out.println(firstNumber + " " + operations[operation] + " "
                    + secondNumber + " = ?");

            // display options
            System.out.println("\n1) " + options[0]);
            System.out.println("2) " + options[1]);
            System.out.println("3) " + options[2]);
            System.out.println("4) " + options[3]);

            // ask user for answer
            System.out.print("\nYour answer: ");

            // get user input
            answer = scanner.nextInt();

            // check if user input is valid
            while (answer < 1 || answer > 4) {
                System.out.println("Invalid input. Please try again.");
                System.out.print("Your option: ");
                answer = scanner.nextInt();
            }

            // check if user answer is correct
            if (options[answer - 1] == correctAnswer) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
                System.out.println("The correct answer is " + correctAnswer);
            }
            // increment question number
            questionNumber++;

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void displayScore() {
        // display score
        System.out.println("\n\nYour score is " + score + " out of 10.");
        // display message based on score
        if (score == 10) {
            System.out.println("Congratulations! You got a perfect score!");
        } else if (score >= 7) {
            System.out.println("Good job!");
        } else if (score >= 5) {
            System.out.println("You can do better!");
        } else {
            System.out.println("You need to study more!");
        }

        System.out.println("\nThank you for playing the Math Quiz!\n");
    }

    public static void run() {
        // create new object
        MathQuiz mathQuiz = new MathQuiz();
        for (int i = 0; i < 10; i++) {
            // if play returns false, again ask the question
            if (!mathQuiz.play()) {
                i--;
            }
        }
        mathQuiz.displayScore();
    }
}
