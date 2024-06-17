import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        // display welcome message
        welcomeMessage();

        // run game
        run();
    }

    public static void welcomeMessage() {
        // display game instructions
        System.out.println("Welcome to Tic Tac Toe Game!");
        System.out.println("\nYou are playing against the computer.");
        System.out.println("\nRules: Enter square number (1-9) to place your mark.");
    }

    public static void run() {
        // create scanner object
        Scanner scanner = new Scanner(System.in);

        // create game object
        TicTacToeGame game = new TicTacToeGame();

        // variable to keep track of whose turn it is
        boolean userTurn = true;

        // display board
        game.printBoard();

        while (true) {
            // display turn
            if (userTurn) {
                System.out.println("              YOUR TURN              \n");

            } else {
                System.out.println("             COMPUTER'S TURN           \n");
                // wait for 1 second
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (userTurn) {
                System.out.print("Enter square number (1-9) to place your mark: ");
                int square = scanner.nextInt();
                int row = (square - 1) / 3;
                int col = (square - 1) % 3;

                while (row < 0 || row > 2 || col < 0 || col > 2 || !game.move(row, col, 1)) {
                    System.out.println("Invalid move. Try again.\n");
                    System.out.print("Enter square number (1-9) to place your mark: ");
                    square = scanner.nextInt();
                    row = (square - 1) / 3;
                    col = (square - 1) % 3;
                }
                game.printBoard();
                if (game.checkWinner(1)) {
                    System.out.println("Congratulations! You won!\n");
                    break;
                }
                userTurn = false;
            }
            // computer's turn
            else {
                int square = game.nextMove(2);
                int row = (square - 1) / 3;
                int col = (square - 1) % 3;
                game.move(row, col, 2);

                System.out.println("Computer placed O at square " + square);
                game.printBoard();

                // check if computer won
                if (game.checkWinner(2)) {
                    System.out.println("Computer won!\n");
                    break;
                }
                userTurn = true;
            }

            // check if game is draw
            if (game.isDraw()) {
                System.out.println("Game is draw!\n");
                break;
            }
        }
        scanner.close();
    }
}

// using magic square approach
class TicTacToeGame {
    final int N = 3;
    int[][] board = new int[N][N];
    int[][] magicSquare = {
            { 8, 1, 6 },
            { 3, 5, 7 },
            { 4, 9, 2 }
    };
    public HashMap<Integer, Integer> map = new HashMap<>();

    public TicTacToeGame() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }

        // adding values to map
        map.put(8, 1);
        map.put(1, 2);
        map.put(6, 3);
        map.put(3, 4);
        map.put(5, 5);
        map.put(7, 6);
        map.put(4, 7);
        map.put(9, 8);
        map.put(2, 9);
    }

    public boolean move(int row, int col, int player) {
        if (board[row][col] != 0) {
            return false;
        }
        // mark the square
        board[row][col] = player;
        return true;
    }

    // function to check if player can win
    boolean canWin(int player) {
        ArrayList<Integer> p1Moves = new ArrayList<>();
        // add moves of player
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    p1Moves.add(magicSquare[i][j]);
                }
            }
        }

        ArrayList<Integer> p2Moves = new ArrayList<>();
        // add moves of computer
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 2) {
                    p2Moves.add(magicSquare[i][j]);
                }
            }
        }

        // checking for player
        if (player == 1) {
            // if size is less than 2, return false
            if (p1Moves.size() < 2) {
                return false;
            }

            // now check if the 2 moves are collinear
            for (int i = 0; i < p1Moves.size(); i++) {
                for (int j = i + 1; j < p1Moves.size(); j++) {
                    int thirdSquare = 15 - p1Moves.get(i) - p1Moves.get(j);
                    if (thirdSquare >= 1 && thirdSquare <= 9) {
                        // check if that square is empty
                        if (!p1Moves.contains(thirdSquare) && !p2Moves.contains(thirdSquare)) {
                            return true;
                        }
                    }
                }
            }
        }

        // checking for computer
        else {
            // if size is less than 2, return false
            if (p2Moves.size() < 2) {
                return false;
            }

            // now check if the 2 moves are collinear
            for (int i = 0; i < p2Moves.size(); i++) {
                for (int j = i + 1; j < p2Moves.size(); j++) {
                    int thirdSquare = 15 - p2Moves.get(i) - p2Moves.get(j);
                    if (thirdSquare >= 1 && thirdSquare <= 9) {
                        // check if that square is empty
                        if (!p1Moves.contains(thirdSquare) && !p2Moves.contains(thirdSquare)) {
                            return true;
                        }
                    }
                }
            }
        }

        // none of the player can win
        return false;
    }

    // function to tell the computer where to move next
    public int nextMove(int player) {
        // player = 1
        // computer = 2

        // check if computer can win
        if (canWin(2)) {
            // if computer can win, then find the third square

            // get the moves of computer
            ArrayList<Integer> p2Moves = new ArrayList<>();

            // add moves
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 2) {
                        p2Moves.add(magicSquare[i][j]);
                    }
                }
            }

            // now find the third square
            for (int i = 0; i < p2Moves.size(); i++) {
                for (int j = i + 1; j < p2Moves.size(); j++) {
                    int thirdSquare = 15 - p2Moves.get(i) - p2Moves.get(j);
                    if (thirdSquare >= 1 && thirdSquare <= 9 && !p2Moves.contains(thirdSquare)) {
                        // this will return the square number which computer should play
                        return map.get(thirdSquare);
                    }
                }
            }
        } else if (canWin(1)) {
            // get the moves of player
            ArrayList<Integer> p1Moves = new ArrayList<>();

            // add moves
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 1) {
                        p1Moves.add(magicSquare[i][j]);
                    }
                }
            }

            // now find the third square
            for (int i = 0; i < p1Moves.size(); i++) {
                for (int j = i + 1; j < p1Moves.size(); j++) {
                    int thirdSquare = 15 - p1Moves.get(i) - p1Moves.get(j);
                    if (thirdSquare >= 1 && thirdSquare <= 9 && !p1Moves.contains(thirdSquare)) {
                        // this will return the square number which computer should play to block the
                        // player
                        return map.get(thirdSquare);
                    }
                }
            }
        } else {
            // if computer can't win, then play the center square
            if (board[1][1] == 0) {
                return 5;
            }
            // if center square is already played, then play the corner square
            else {
                if (board[0][0] == 0) {
                    return 1;
                } else if (board[0][2] == 0) {
                    return 3;
                } else if (board[2][0] == 0) {
                    return 7;
                } else if (board[2][2] == 0) {
                    return 9;
                }
            }

            // otherwise return any empty square
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 0) {
                        return 3 * i + j + 1;
                    }
                }
            }
        }
        return 0;
    }

    public boolean checkWinner(int player) {
        if (player == 1) {
            ArrayList<Integer> player1Squares = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 1) {
                        player1Squares.add(magicSquare[i][j]);
                    }
                }
            }

            // sort the list
            Collections.sort(player1Squares);

            // check if sum of three squares is 15
            return threeSum(player1Squares, 15);
        } else {
            ArrayList<Integer> player2Squares = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 2) {
                        player2Squares.add(magicSquare[i][j]);
                    }
                }
            }

            // sort the list
            Collections.sort(player2Squares);

            // check if sum of three squares is 15
            return threeSum(player2Squares, 15);
        }

    }

    public static boolean threeSum(ArrayList<Integer> list, int sum) {
        for (int i = 0; i < list.size() - 2; i++) {
            for (int j = i + 1; j < list.size() - 1; j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    if (list.get(i) + list.get(j) + list.get(k) == sum) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void printBoard() {
        // using - | + characters print board
        System.out.println("---------");
        for (int i = 0; i < N; i++) {
            System.out.print("| ");
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    System.out.print("X ");
                } else if (board[i][j] == 2) {
                    System.out.print("O ");
                } else {
                    // display square number
                    System.out.print((i * 3 + j + 1) + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
        System.out.println();
    }

    // function to check if game is draw
    public boolean isDraw() {
        // check if all squares are filled
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}