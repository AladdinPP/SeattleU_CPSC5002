package hhe_p1;
import java.util.Scanner;
/**
 * This program simulates the process to play the TicTacToe game.
 *
 * @author Hongru He
 * @version 1.0
 */

public class P1 {

    /**
     * Simulates the process to play a TicTacToe game.
     *
     * @param args The string array containing the command line arguments.
     */
    public static void main(String[] args)
    {
        // Create variable, constant, and object
        final int SIZE = 3;                         // The board size
        final String REPEAT = "y";
        String repeat;                              // Play again or not
        TicTacToe ttt = new TicTacToe(3);
        Scanner input = new Scanner(System.in);

        // Welcome method
        welcome();

        // Play the game in a do-while loop to make player
        // decide whether to play again after each game.
        do {
            ttt.printBoard();

            ttt.playGame();

            System.out.print("\nDo you want to play again? ");
            repeat = input.nextLine();
        }
        while (repeat.equalsIgnoreCase(REPEAT));

        // Close the Scanner;
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
