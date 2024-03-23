package hhe_p3;

/**
 * This program creates a generic linked list based stack.
 *
 * @author Hongru He
 * @version 1.0
 */
public class Stack<T>
{
    /**
     * top of the stack
     */
    private Node head;
    /**
     * The Node class is used to implement the linked list
     */
    private class Node {
        /**
         * Holds stack element.
         */
        public T data;

        /**
         * Points to next node in linked list.
         */
        public Node next;

        /**
         * The Node constructor initializes each instance of the Node class.
         * @param data  The data that is stored in Node
         * @param next  A pointer to the succeeding Node
         */
        public Node (T data, Node next)
        {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * The Stack no-arg constructor initializes new instances of Stack objects.
     */
    public Stack() {
        head = null;
    }

    /**
     * The push method adds an item to the top of the stack.
     * @param data The item that is to be added to top of stack
     */
    public void push(T data) {
        head = new Node (data, head);
    }

    /**
     * The peek method returns the item currently at top of stack but does not
     * remove item from stack. peek throws an exception if it is called when
     * the stack is empty.
     * @return item at top of stack
     * @throws IllegalArgumentException if user tries to peek at empty stack
     */
    public T peek() {
        if (empty())
            throw new IllegalArgumentException("Error: The stack is empty!");
        else
            return head.data;
    }

    /**
     * The pop method removes and returns the item at top of stack. If the
     * stack is empty, pop throws an exception.
     * @return item at top of stack
     */
    public T pop() {
        T data;    //holds item on top of stack

        data = peek();
        head = head.next;
        return data;
    }

    /**
     * The empty method returns true if stack is empty and false otherwise.
     * @return true if stack is empty and false otherwise
     */
    public boolean empty() {
        return head == null;
    }

    /**
     * toString method returns the items in the stack as a String
     * @return String representation of items in the stack.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node i = head; i != null; i = i.next) {
            sb.append(i.data.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * makes a copy of a stack
     * @return copy of a stack
     */
    public Stack<T> copy() {
        Stack<T> copyStack = new Stack<T>();
        if (head == null)
            return copyStack;

        if (copyStack.empty())          //if copyStack is empty
            copyStack.push(head.data);

        Node p = copyStack.head;

        //when stack is not empty
        for (Node i = head.next; i != null; i = i.next) {
            p.next = new Node(i.data, null);
            p = p.next;
        }
        return copyStack;
    }

    /**
     * Compares if two stacks are equal.
     * @param s1 stack to compare
     * @return true if both stacks are equal and false otherwise
     */
    public boolean equals(Stack<T> s1) {
        Node p = head;
        Node p1 = s1.head;

        while (p != null & p1 != null) {
            if (p.data.toString().compareTo(p1.data.toString()) != 0)
                return false;
            p = p.next;
            p1 = p1.next;
        }

        if (p == null && p1 == null)
            return true;
        else
            return false;
    }
}
