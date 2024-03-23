package hhe_p2x;

import java.util.*;
import java.io.*;

/**
 * This program creates a secret message decoder which will
 * take into a text file and return a fully-decoded message.
 *
 * @author Hongru He
 * @version 1.0
 */
public class MessageDecoder
{
    private boolean validInput = true;  // Check if the content is good.
    public String target;              // Store the final string.
    private class Node
    {
        char value;
        int position;
        Node next;

        /**
         * Constructor.
         *
         * @param val The element to store in this node.
         * @param n   The reference to the next node.
         */

        Node(char val, int p, Node n)
        {
            value = val;
            position = p;
            next = n;
        }

        /**
         * Constructor.
         *
         * @param val The element to store in this node.
         */

        Node(char val, int p)
        {
            value = val;
            position = p;
            next = null;
        }
    }

    private Node first;     // list head
    private Node last;      // last element in the list

    /**
     * Constructor.
     */
    public MessageDecoder()
    {
        first = null;
        last = null;
        target = "";
    }

    /**
     * The isEmpty method checks to see
     * if the list is empty.
     *
     * @return true if list is empty.
     * false otherwise.
     */

    public boolean isEmpty()
    {
        return first == null;
    }

    /**
     * The size method returns the length of the list.
     *
     * @return The number of elements in the list.
     */

    public int size()
    {
        int count = 0;
        Node p = first;
        while (p != null)
        {
            // There is an element at p
            count ++;
            p = p.next;
        }
        return count;
    }

    /**
     * This function prompts for user input of file name, read
     * from the file and add the element into the correct position.
     *
     * @return The final message or the error information
     * @throws IOException
     */
    public String fileAdd(String filename) throws IOException
    {
        while(!isValidFile(filename));

        // Read through the file and sorted add into the list
        File file = new File(filename);
        Scanner input = new Scanner(file);
        while(input.hasNext())
        {
            String e = input.nextLine();

            // Check if missing integer position
            if (e.length() <= 2)
            {
                validInput = false;
                return "missing position";
            }

            char val = e.charAt(0);
            int p = Integer.valueOf(e.substring(2));

            // Check if a negative position
            if(p < 0)
            {
                validInput = false;
                return "negative position";
            }

            // Check if a duplicate
            boolean isDup = sortedAdd(val, p);
            if (!isDup)
            {
                validInput = false;
                return "duplicate position";
            }
        }
        return toString();
    }

    /**
     * Checks to see that the user-specified file name refers to a valid
     * file on the disk and not a directory. Displays an error message to the
     * user if that is not the case.
     *
     * @param fname file name string to check
     * @return true if file exists on disk and is not a directory
     */
    private static boolean isValidFile(String fname)
    {
        File path = new File(fname);
        boolean isValid = path.exists() && !path.isDirectory();
        if (!isValid) {
            System.out.println("This is an invalid file name, " +
                    "please enter another file name.");
        }
        return isValid;
    }

    /**
     * The add method adds an element to
     * the end of the list.
     *
     * @param val The value to add to the end of the list.
     * @param p The position to add the element.
     */

    public void add(char val, int p)
    {
        if (isEmpty())
        {
            first = new Node(val, p);
            last = first;
        }
        else
        {
            // Add to end of existing list
            last.next = new Node(val, p);
            last = last.next;
        }
    }

    /**
     * The add method adds an element based on the
     * ascend numerical order.
     *
     * @param val The element to add to the list.
     * @param p The position to add the element.
     * @return If the position is duplicate.
     */

    public boolean sortedAdd(char val, int p)
    {
        // Check if this is an empty list
        if (isEmpty())
        {
            // New element goes at beginning
            first = new Node(val, p, first);
            last = first;
            return true;
        }

        // Check if the new element's position is smaller than first
        if (p < first.position)
        {
            first = new Node(val, p, first);
            return true;
        }

        // Check if this is a duplicate position
        else if (p == first.position)
        {
            return false;
        }

        // Check if the new element's position is larger than last
        if (p > last.position)
        {
            last.next = new Node(val, p);
            last = last.next;
            return true;
        }

        else if (p == last.position)
        {
            return false;
        }

        // Set a reference pred to point to the node that
        // will be the predecessor of the new node
        Node pred = first;

        // Check if there is element behind first
        if (pred.next == null)
        {
            pred.next = new Node(val, p);
        }

        // Check through the list to find the correct position
        // for the new element.
        for (int k = 1; k <= size() - 1; k++)
        {
            // Check if the element's position is smaller than the next
            if (p < pred.next.position)
            {
                pred.next = new Node(val, p, pred.next);

                if (pred.next.next == null)
                    last = pred.next;
                return true;
            }

            else if (p == pred.next.position)
            {
                return false;
            }

            // Move on if this is not the correct position
            // for the new element.
            pred = pred.next;
        }
        return true;
    }

    /**
     * Extract the spaced unit and merge to one string.
     *
     * @return The merged string.
     */
    public String mergeMessage(MessageDecoder md2)
    {
        StringBuilder mergeStr = new StringBuilder();

        while (target != "" && md2.target != "")
        {
            mergeStr.append(getUnit() + ' ');
            mergeStr.append(md2.getUnit() + ' ');
        }
        if (target == "")
        {
            mergeStr.append(md2.target);
        }
        else if (md2.target == "")
        {
            mergeStr.append(target);
        }
        return mergeStr.toString();
    }

    /**
     * Extract unit with a space from the original string.
     *
     * @return The spaced unit from the original string.
     */
    public String getUnit()
    {
        String unit;
        for (int i = 0; i < target.length(); i++)
        {
            // If space found, extract the unit and return
            if (target.charAt(i) == ' ')
            {
                unit = target.substring(0, i);
                target = target.substring(i + 1);
                return unit;
            }
        }
        // If there is no space left, return the original string
        unit = target;
        target = "";
        return unit;
    }

    /**
     * The toString method computes the string
     * representation of the list.
     *
     * @return The string form of the list.
     */

    public String toString()
    {
        StringBuilder strBuilder = new StringBuilder();

        // Use p to walk down the linked list
        Node p = first;
        while (p != null)
        {
            strBuilder.append(p.value);
            p = p.next;
        }
        target = strBuilder.toString();
        return target;
    }
}