package hhe_p3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Simulates the game logic of the Silly Card Game.
 *
 * @author Hongru He
 * @version 1.0
 */
public class GameModel
{
    // The arraylist holding all the cards
    private final ArrayList<Integer> CARDS = new ArrayList<>();
    private String name1;
    private String name2;
    private Stack<Integer> deal;        // The deal pile
    public Stack<Integer> discard;     // The discard pile
    public Queue<Integer> player1;     // The player 1's cards
    public Queue<Integer> player2;     // The player 2's cards

    /**
     * Constructor of a game model.
     *
     * @param name1 The name of player1.
     * @param name2 The name fo player2.
     */
    public GameModel(String name1, String name2)
    {
        this.name1 = name1;
        this.name2 = name2;

        for(int i = 1; i <= 13; i++)
        {
            CARDS.add(i);
            CARDS.add(i);
            CARDS.add(i);
            CARDS.add(i);
        }
        deal = new Stack<Integer>();
        discard = new Stack<Integer>();
        player1 = new Queue<Integer>();
        player2 = new Queue<Integer>();
    }

    /**
     * Simulate the start of a game.
     */
    public void startGame()
    {
        // Shuffle cards and push into deal pile
        shuffleDeck();
        for (int e : CARDS)
        {
            deal.push(e);
        }

        // Fill the players' queues
        int i = 0;
        while(i < 7)
        {
            player1.enqueue(deal.pop());
            i++;
        }
        int j = 0;
        while(j < 7)
        {
            player2.enqueue(deal.pop());
            j++;
        }

        // Put the next card into the discard pile
        discard.push(deal.pop());
    }

    /**
     * Shuffles the cards using the
     * <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">
     * Fisher-Yates algorithm</a>
     */
    private void shuffleDeck()
    {
        Random rand = new Random();
        for (int i = CARDS.size(); i > 1; i--) {
            int j = rand.nextInt(i);
            int temp = CARDS.get(i - 1);
            CARDS.set(i - 1, CARDS.get(j));
            CARDS.set(j, temp);
        }
    }

    /**
     * Simulate each turn of a game round.
     *
     * @param player The queue which stores cards(integer) for a player.
     * @param name The name of this player.
     * @return The result of this player's turn, a comparison result or win.
     */
    public String playerTurn(Queue<Integer> player, String name)
    {
        // Check the player's next card with
        if (player.peek() > discard.peek())
        {
            discard.push(player.dequeue());
            if(!playerWin(player))
            {
                return "HIGHER";
            }
            else
            {
                return "WON";
            }
        }
        else if (player.peek() == discard.peek())
        {
            discard.push(player.dequeue());
            player.enqueue(deal.pop());
            if(deal.empty())
            {
                turnOver();
            }
            return "EQUAL";
        }
        else
        {
            discard.push(player.dequeue());
            player.enqueue(deal.pop());
            if(deal.empty())
            {
                turnOver();
            }
            player.enqueue(deal.pop());
            if(deal.empty())
            {
                turnOver();
            }
            return "LOWER";
        }
    }

    /**
     * Turn over the discard stack and refill the deal stack.
     */
    private void turnOver()
    {
        int temp = discard.pop();
        while(!discard.empty())
        {
            deal.push(discard.pop());
        }
        discard.push(temp);
    }

    /**
     * Check if the player wins.
     *
     * @return Whether this player won the game.
     */
    public boolean playerWin(Queue<Integer> player)
    {
        return player.empty();
    }
}
