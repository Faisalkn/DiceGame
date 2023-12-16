package Model;

/**
 * Represents the bank in the Craps game, managing the bank amount and bet amount.
 *
 * @author Faisal Nur
 * @version Fall 2023
 */
public class Bank {

    /**
     * The bank amount.
     */
    private int myBankAmount;

    /**
     * The bet amount.
     */
    private int myBetAmount;

    /**
     *
     * @return the bank amount.
     */
    public int getMyAmount() {
        return myBankAmount;
    }

    /**
     * Sets the bank amount to the specified value.
     *
     * @param theAmount The new bank amount.
     */
    public void setMyAmount(final int theAmount) {
        if(theAmount < 0){
            throw new IllegalArgumentException("The amount can not be negative you passed : " +  theAmount);
        }else{
            myBankAmount = theAmount;
        }
    }

    /**
     * Sets the bet amount to the specified value.
     *
     * @param theAmount The new bet amount.
     */
    public void setMyBetAmount(final int theAmount){
        if(theAmount < 0){
            throw new IllegalArgumentException("The bet amount can not be negative you passed : " +  theAmount);
        }else {
            myBetAmount = theAmount;
        }
    }

    /**
     * Updates the bank amount based on the given bet amount.
     *
     * @param theBetAmount The amount to bet.
     */
    public void updateAmount(int theBetAmount){
        if (theBetAmount > myBankAmount){
            myBankAmount = 0;
        }else {
            myBankAmount = myBankAmount - theBetAmount;
        }
    }

    /**
     * Increases the bank amount based on the given bet amount.
     *
     * @param theBetAmount The amount to bet.
     */
    public void increaseAmount(int theBetAmount){
        theBetAmount = theBetAmount * 2;
        myBankAmount += theBetAmount;
    }

    /**
     * Gets the current bet amount.
     *
     * @return The current bet amount.
     */
    public int getMyBetAmount(){
        return myBetAmount;
    }
}
