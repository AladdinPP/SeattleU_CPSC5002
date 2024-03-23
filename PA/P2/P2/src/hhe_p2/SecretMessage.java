package hhe_p2;

import java.io.*;
import java.util.Scanner;

/**
 * This program calls the MessageDecoder class and decode
 * the specific input file.
 *
 * @author Hongru He
 * @version 1.0
 */
public class SecretMessage
{
    /**
     * Calls for the MessageDecoder class and decode the input
     * file, then print it out.
     *
     * @param args The string array containing the command line arguments.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        // Creates variables and objects
        String repeat;
        final String REPEAT = "yes";
        Scanner keyboard = new Scanner(System.in);

        // Calls for welcome method
        welcome();

        // Run the message decoder and ask user if repeat
        do
        {
            // Creates MessageDecoder instance
            MessageDecoder secretM = new MessageDecoder();

            // Decode the file
            secretM.fileAdd();

            // Display the decoded message
            System.out.println("Decoded: " + secretM.toString());

            // Prompt for user input if repeat
            System.out.print("\nWould you like to try again? (no to exit): ");
            repeat = keyboard.nextLine();
        }
        while(repeat.equalsIgnoreCase(REPEAT));

        // Close the keyboard
        keyboard.close();

        // Calls for goodbye method
        goodbye();
    }

    /**
     * Display the welcome message.
     */
    public static void welcome()
    {
        System.out.println("This program reads an encoded message " +
                            "from a file supplied by the user and\n" +
                            "displays the contents of the message " +
                            "before it was encoded.\n");
    }

    /**
     * Display the goodbye message.
     */
    public static void goodbye()
    {
        System.out.println("\nThank you for using the message decoder.");
    }
}
