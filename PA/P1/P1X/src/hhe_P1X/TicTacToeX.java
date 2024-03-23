package hhe_P1X;

import java.util.Scanner;
/**
 * This program simulates the TicTacToe game board.
 *
 * @author Hongru He
 * @version 1.0
 */
public class TicTacToeX {
    private char[][] board;     // 2D array to store the game board
    private int size;           // The range of the game board
    private int[] scores;       // Array store the game stats

    /**
     * Constructor.
     */

    public TicTacToeX()
    {
        size = validSize();
        board = new char[size][size];
        scores = new int[3];

        // Initiate the game board with blank
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                board[row][col] = ' ';
            }
        }
    }

    /**
     * Prompt for user input as the size.
     */
    private int validSize()
    {
        Scanner input = new Scanner(System.in);
        boolean valid = false;

        // Prompt for user input and check the input validation.
        while(!valid)
        {
            System.out.print("\nEnter an odd number between 3 to 25: ");
            size = input.nextInt();
            input.nextLine();
            if (size >= 3 && size <= 25 && size % 2 == 1) {
                valid = true;
            }
        }

        return size;
    }

    /**
     * Place the assigned character.
     *
     * @param row The row where the character being placed.
     * @param col The column where the character being placed.
     * @param p   The assigned character.
     */

    private void setPlace(int row, int col, char p)
    {
        board[row][col] = p;
    }

    /**
     * Start a game.
     */
    public void playGame()
    {
        // Create variables and objects
        Scanner keyboard = new Scanner(System.in);

        // Loop between two players until the board is full
        // or there is a winner.
        do
        {
            boolean end = false;        // Decide whether to end the game.
            boolean validMove = false;  // Decide whether a valid move.

            // X's turn
            while (!validMove)
            {
                System.out.println('X' + ", it is your turn.");

                // Prompt for user input
                System.out.print("Which row? ");
                int row = keyboard.nextInt();
                keyboard.nextLine();

                System.out.print("Which column? ");
                int col = keyboard.nextInt();
                keyboard.nextLine();

                // Check if a valid input
                validMove = placeMove(row, col, 'X');

                // if not, ask to input again
                if (!validMove)
                {
                    System.out.println("Bad location, try again...");
                    printBoard();
                }

                // Check if this is a win move
                else if (checkWin(row, col))
                {
                    end = checkWin(row, col);
                    System.out.println('X' + " wins!");

                    // Display the board
                    printBoard();

                    // Record the score
                    scores[0]++;

                    // Reset the board
                    resetBoard();

                    break;
                }

                // Check if this is a draw move
                else if (isFull() && !checkWin(row, col))
                {
                    end = isFull();
                    System.out.println("No winner - it was a tie!");

                    printBoard();

                    scores[2]++;

                    resetBoard();

                    break;
                }
            }

            // If it is a draw or win, end the game.
            if (end)
            {
                break;
            }
            printBoard();

            // Repeat the previous process for O's turn
            validMove = false;
            while (!validMove)
            {
                System.out.println('O' + ", it is your turn.");

                System.out.print("Which row? ");
                int row = keyboard.nextInt();
                keyboard.nextLine();

                System.out.print("Which column? ");
                int col = keyboard.nextInt();
                keyboard.nextLine();

                validMove = placeMove(row, col, 'O');

                if (!validMove)
                {
                    System.out.println("Bad location, try again...");
                    printBoard();
                }

                else if (checkWin(row, col))
                {
                    end = checkWin(row, col);
                    System.out.println('O' + " wins!");
                    printBoard();
                    scores[1]++;
                    resetBoard();
                    break;
                }

                else if (isFull() && !checkWin(row, col))
                {
                    end = isFull();
                    System.out.println("No winner - it was a tie!");
                    printBoard();
                    scores[2]++;
                    resetBoard();
                    break;
                }
            }

            if (end)
            {
                break;
            }

            printBoard();
        }
        while (!isFull());

        // Display the scoreboard.
        System.out.println("\nGame Stats");
        System.out.printf("X has won %d games.", scores[0]);
        System.out.printf("\nO has won %d games.", scores[1]);
        System.out.printf("\nThere have been %d tie games.", scores[2]);
    }

    /**
     * Check if the board is full.
     *
     * @return Whether the board is full.
     */
    private boolean isFull()
    {
        // Check if there is an unfilled position within the board.
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                if (board[row][col] == ' ')
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if a move is valid, which means the place is empty
     * and within the range of the game board.
     *
     * @param row The row player inputs.
     * @param col The column player inputs.
     * @param player The character player inputs.
     * @return Whether this move is valid.
     */
    private boolean placeMove(int row, int col, char player)
    {
        // Check if this is an empty place and within the range
        // of the game board.
        if (row < 0 || col < 0 || row >= size || col >= size ||
                board[row][col] != ' ')
        {
            return false;
        }

        // Fill this position.
        setPlace(row, col, player);

        return true;
    }

    /**
     * Display the game board in proper format.
     */
    public void printBoard()
    {
        // Leave the blank above
        System.out.println();

        // Print the first line
        for (int i = 0; i < size; i++)
        {
            System.out.printf("%3d", i);
        }

        System.out.println();

        // Print the rest of the board
        for (int i = 0; i < size; i++)
        {
            System.out.printf("%d", i);
            for (int j = 0; j < size; j++)
            {
                System.out.printf("%2c|", board[i][j]);
            }
            System.out.print("\n ");
            for (int k = 0; k < size; k++)
            {
                System.out.printf("%s", "---");
            }
            System.out.print("\n");
        }
    }

    /**
     * Check if a move makes the player win.
     *
     * @param row The row this move places.
     * @param col The column this move places.
     * @return If this move makes the player win.
     */
    private boolean checkWin(int row, int col)
    {
        // Create variable to store the checked value
        char symbol = board[row][col];

        // Check the entire row
        for (int i = 0; i < size; i++)
        {
            // When find one exception, stop and move to the next row
            if (board[i][col] != symbol)
                break;

            // When run through to the end, win.
            if (i == size - 1)
            {
                return true;
            }
        }

        // Check the entire column
        for (int i = 0; i < size; i++)
        {
            if (board[row][i] != symbol)
                break;

            if (i == size - 1) {
                return true;
            }
        }

        // Check the diagonal
        if (row == col)
        {
            for (int i = 0; i < size; i++)
            {
                if (board[i][i] != symbol)
                    break;

                if (i == size - 1)
                {
                    return true;
                }
            }
        }

        // Check the anti-diagonal
        if (row + col == size - 1)
        {
            for (int i = 0; i < size; i++)
            {
                if (board[i][size - 1 - i] != symbol)
                    break;

                if (i == size - 1)
                    return true;
            }
        }

        return false;
    }

    /**
     * Wipe out all inputs and reset the board to empty.
     */
    private void resetBoard()
    {
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                board[i][j] = ' ';
            }
        }
    }
}