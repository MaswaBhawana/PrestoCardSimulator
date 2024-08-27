// This is Lab 4.

// Name: Bhawana Maswa
// Date: November 17th, 2023
// App Name: Presto Simulator
// Description: Program that will simulates the Presto card system.


import java.util.ArrayList;
import java.util.Scanner;
/**
 * Machine
 */
public class PrestoSimulator
{

    // Constants
    static final String SET_TITLE = "\033]0;%s\007";
    static final String CLEAR_TERMINAL = "\033c";
    static final String BANNER = """
        +-+-+-+-+-+-+ +-+-+-+-+
        |P|R|E|S|T|O| |C|A|R|D|
        +-+-+-+-+-+-+ +-+-+-+-+  

    Program that simulates Presto cards 

    """;
    static final double FARE = 2.50;
    

    /**
     * Add the new presto card.
     * @param card - The arraylist holding all card
     * @param scanner - For user input
     */
    static void add(Scanner scanner, ArrayList<PrestoCard>card)
    {
        // Variables
        String cardName;
        double cardBalance = 0;
        boolean valid;

        // Get the card's name
        cardName = scanner.next(); // just 1 word

        // Check whether the entered balance is double or not.
        try 
        {
            cardBalance = scanner.nextDouble(); // just the price
            valid = true;
        } catch (Exception e) 
        {
            valid = false;
        }
        // Error in case balance is not numeric
        if (valid == false)
            System.out.println("Invalid price !");
        // Add the new card.
        else
            card.add(new PrestoCard(cardName, cardBalance));
    }

    /**
     * Delete the card on the basis of the card index number.
     * @param card - The arraylist holding all card
     * @param scanner - For user input
     */
    static void del(Scanner scanner, ArrayList<PrestoCard>card)
    {
        int cardID = 0;
        boolean valid;

        // Get the card ID and check whether the entered ID is integer or not if not raise expection.
        try 
        {
            cardID = scanner.nextInt(); // just the ID
            valid = true;
        } catch (Exception e) 
        {
            valid = false;
        }
        

        // Error in case ID is not numeric or in case it's not in the valid range
        if (valid == false ||  cardID < 1 || cardID > card.size())
            System.out.println("Invalid ID !");

        // Remove the card.
        else
            // Converts the ID into index
            card.remove(cardID-1);

    }

    /**
     * Deduct the Fare from the card when it is tapped in presto machine.
     * @param card - The arraylist holding all card
     * @param scanner - For user input
     */
    static void tap(Scanner scanner, ArrayList<PrestoCard>card)
    {
        int cardID = 0;
        boolean valid;

        // Get the card ID and check whether the entered ID is integer or not if not raise expection.
        try
        {
            cardID = scanner.nextInt(); // just the ID
            valid = true;
        } catch (Exception e)
        {
            valid = false;
        }

        // Error in case ID is not numeric or in case it's not in the valid range
        if (!valid || cardID < 1 || cardID > card.size())
            System.out.println("Invalid ID !");
        else
        {
            // Sufficient balance then fare will be deducted and user will get their new balance.
            if (card.get(cardID - 1).tap(FARE))
            {
                System.out.println("Tap successful! New balance : $" + card.get(cardID - 1).getBalance());
            } 
            // Incase the balance is lesser than FARE user will get message for insufficient balance.
            else
            {
                // Insufficient balance
                System.out.println("Insufficient balance for tapping!");
            }
        }
    }

    /**
     * Add the topup amount to the card.
     * @param card - The arraylist holding all card
     * @param scanner - For user input
     */
    static void topup(Scanner scanner, ArrayList<PrestoCard>card)
    {
        int cardID = 0;
        double amount = 0;
        boolean validID, validAmount;

        // Get the card ID
        try
        {
            cardID = scanner.nextInt(); // just the ID
            validID = true;
        } catch (Exception e)
        {
            validID = false;
        }

        // Get the top-up amount by checking if the amount is double or not otherwise raise exception.
        try
        {
            amount = scanner.nextDouble(); // just the amount
            validAmount = true;
        } catch (Exception e)
        {
            validAmount = false;
        }

        // Error in case ID or amount is not numeric or in case ID is not in the valid range
        if (!validID || !validAmount || cardID < 1 || cardID > card.size())
            System.out.println("Invalid ID or amount!");
        else
        {
            // Add the amount to the existing balance of card.
            card.get(cardID - 1).topUp(amount);
            System.out.println("Top-up successful!");
        }
    }
    
    /**
     * Quit the app.
     */
    static void quit()
    {
        System.out.println("Quit the app.");
    }


    public static void main(String[] args) 
    {
        // Variables
        String command;
        boolean running = true;

        // User input
        Scanner scanner = new Scanner(System.in);

        // Create an arraylist
        ArrayList<PrestoCard> card = new ArrayList<PrestoCard> ();

        // Adding card information to the array list
        card.add(new PrestoCard("Fred", 5.00));    // Index 0
        card.add(new PrestoCard("Lilly", 15.00));  // Index 1
        card.add(new PrestoCard("Jimmy", 2.00));   // Index 2

        // Set console title
        System.out.printf(SET_TITLE, "Presto Card - Bhawana");

        //  // Main app loop will break when the user chooses "quit"
        while (running) 
        {
            // Print the banner and clear terminal
            System.out.println(CLEAR_TERMINAL + BANNER);
    
            
            // Print the number of elements in present in arraylist.
            System.out.printf("We currently have %s cards!\n\n", card.size());
    
            // Print the content of Arraylist including the name and balance of each presto card.
            //1 - Fred - Balance: $ 5.00
            for (int index = 0; index < card.size(); index++ )
            {
                System.out.printf("%s - %s - Balance - $ %.2f\n", index + 1, card.get(index).getName(), card.get(index).getBalance());
            }
    
            // Prompt the user to write a command.
            System.out.print("\nEnter a command: ");
            command = scanner.next(); // Just one word
            
            // Closes the app
            if (command.equalsIgnoreCase("quit")) 
            {
                running = false;
            }

            // Add a new balance
            else if(command.equalsIgnoreCase("add")) 
            {
                add(scanner, card);
            }

            // Remove a card by their ID
            else if(command.equalsIgnoreCase("del")) 
            {
                del(scanner, card);
            }

            else if (command.equalsIgnoreCase("tap"))
            {
                tap(scanner, card);

            }

            else if (command.equalsIgnoreCase("topup"))
            {
                topup(scanner, card);
                
            }

            else 
            {
                System.out.println("Error - Invalid command !");
            }

            // Get rid of any extra invalid input
            scanner.nextLine();

            // Allow user to continue 
            System.out.println("Press [enter] to continue: ");
            scanner.nextLine();

        }
        // Exit prompt
        System.out.print("\nPress [enter] to return to Main Menu:");
        scanner.nextLine();
        scanner.close();

    }
    
}