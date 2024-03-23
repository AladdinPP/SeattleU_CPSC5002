package hhe_p3;

import java.util.Scanner;

/**
 * Prompt for user inputs and run the Silly Card Game as
 * many times as users want.
 *
 * @author Hongru He
 * @version 1.0
 */

public class SillyCardGame
{
    /**
     * Prompt for user inputs, display all the information,
     * and run the Silly Card Game as many times as users want.
     *
     * @param args The string array containing the command line arguments.
     */
    public static void main(String[] args)
    {
        // Create variable and object
        final String STOP = "no";
        String player1, player2, stop;
        Scanner keyboard = new Scanner(System.in);

        // Welcome message
        welcome();

        // Start game
        do
        {
            // Prompt for users' names
            System.out.print("\nEnter the player 1's name: ");
            player1 = keyboard.nextLine();
            System.out.print("\nEnter the player 2's name: ");
            player2 = keyboard.nextLine();

            // Create the GameModel instance
            GameModel game = new GameModel(player1, player2);

            // Begin to play
            game.startGame();

            // Take turns to play until one player won
            while(!game.playerWin(game.player1) &&
                    !game.playerWin(game.player2))
            {
                // Display the information
                System.out.println("\n" + player1 + "'s turn, cards:");
                System.out.println(game.player1);
                System.out.println("Discard pile card: " +
                        game.discard.peek());
                System.out.println("Your current card: " +
                        game.player1.peek());

                // Run the game turn
                String result = game.playerTurn(game.player1, player1);
                displayResult(result);

                // If player1 didn't win, it's player2's turn
                if(!game.playerWin(game.player1))
                {
                    System.out.println("\n" + player2 + "'s turn, cards:");
                    System.out.println(game.player2);
                    System.out.println("Discard pile card: " +
                            game.discard.peek());
                    System.out.println("Your current card: " +
                            game.player2.peek());

                    result = game.playerTurn(game.player2, player2);
                    displayResult(result);
                }
            }

            System.out.println("\nThe game has finished.");

            // Prompt for users' choice to stop or not
            System.out.print("\nPlay again? (no to quit) ");
            stop = keyboard.nextLine();
        }
        while(stop.compareTo(STOP) != 0);

        // Close keyboard
        keyboard.close();

        // Goodbye message
        goodbye();
    }

    /**
     * Display the welcome message.
     */
    public static void welcome()
    {
        System.out.println("Welcome to the Silly Card Game!");
    }

    /**
     * Display the goodbye message.
     */
    public static void goodbye()
    {
        System.out.println("\nThank you for playing our game!");
    }

    /**
     * Display the result of a player's turn.
     *
     * @param result The result of a player's turn.
     */
    public static void displayResult(String result)
    {
        switch(result)
        {
            case "HIGHER":
                System.out.println("Your card is HIGHER, turn is over.");
                break;
            case "EQUAL":
                System.out.println("Your card is EQUAL, pick up 1 card.");
                break;
            case "LOWER":
                System.out.println("Your card is LOWER, pick up 2 cards.");
                break;
            case "WON":
                System.out.println("You have won the game!");
                break;
        }
    }
}
