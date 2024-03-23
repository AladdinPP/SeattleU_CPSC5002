package hhe_P1X;

import java.util.Scanner;
/**
 * This program simulates the process to play the TicTacToe game.
 *
 * @author Hongru He
 * @version 1.0
 */

public class P1X {

    /**
     * Simulates the process to play a TicTacToe game.
     *
     * @param args The string array containing the command line arguments.
     */
    public static void main(String[] args)
    {
        // Create variable, constant, and object
        int size = 0;                               // The board size
        final String REPEAT = "y";
        String repeat;                              // Play again or not
        Scanner input = new Scanner(System.in);

        // Welcome method
        welcome();

        // Create the game board
        TicTacToeX ttt = new TicTacToeX();

        // Play the game in a do-while loop to make player
        // decide whether to play again after each game.
        do {
            ttt.printBoard();

            ttt.playGame();

            System.out.print("\nDo you want to play again? ");
            repeat = input.nextLine();
        }
        while (repeat.equalsIgnoreCase(REPEAT));

        // Close the Scanner
        input.close();

        // Goodbye method
        goodbye();
    }

    /**
     * Display the welcome message.
     */
    public static void welcome()
    {
        System.out.println("Welcome to TicTacToe!");
    }

    /**
     * Display the goodbye message.
     */
    public static void goodbye()
    {
        System.out.println("\nThanks for playing TicTacToe!");
    }
}