

public class PrestoCard 
{
    // Variables
    private double balance;
    private String name;


    /**
     * Constructor - method that creates object of PrestoCard 
     * @param name - Name in presto card
     * @param balance - Balance in presto card
     */
    public PrestoCard(String name , double balance)
    {
        this.name = name;
        this.balance = balance;
    }


    /**
     * Getter method to retrive the information regarding the name in presto card.
     * @return the name of presto card
     */
    public String getName() 
    {
        return name;
    }


     /**
     * Getter method to retrive the information regarding balance of presto card.
     * @return the balance of presto card.
     */
    public double getBalance() 
    {
        return balance;
    }

    /**
     * Tap method will deduct the fare from the card if the user has sufficient balance.
     * @param fare - The amount user will require to pay while tapping presto.
     * @return the fare
     */
    public boolean tap(double fare)
    {
        // This condition will deduct the fare if the card has sufficient balance.
        if (balance >= fare)
        {
            balance = balance - fare;
            return true;
        }
        // If there is insufficient balance in card it will return false.
        else
        {
            return false;
        }
        
    }

    /**
     * TopUp the funds to the presto card. 
     * @param funds - It is the amount user wants to add in their presto card.
     */
    public void topUp(double funds)
    {
        balance += funds;

    }
    
}
