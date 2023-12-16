package Model;
import java.util.Random;

/**
 * Represents a pair of dice used in the Craps game.
 *
 * @author Faisal Nur.
 * @version Fall 2023.
 */
public class Dice {

    /**
     * Dice 1 value.
     */
    private int myDie1Value;

    /**
     * Dice 2 value.
     */
    private int myDie2Value;

    /**
     * Point.
     */
    private int myPoint;

    /**
     * Dice 1 image.
     */
    private String myDieImage1;

    /**
     * Die 2 image
     */
    private String myDieImage2;

    /**
     * Random object.
     */
    private final Random myRandom = new Random();

    /**
     * Player wins count.
     */
    private int playerWins = 0;

    /**
     * House wins count.
     */
    private int houseWins = 0;



    /**
     * player first roll win.
     */
    public boolean playerWonFirstRoll = false;
    /**
     * House first roll win.
     */
    public boolean houseWonFirstRoll = false;

    /**
     * Player won flag.
     */
    public boolean playerWon = false;
    /**
     * House won flag.
     */
    public boolean houseWon = false;


    /**
     * Gets the number of times the player has won.
     *
     * @return The number of player wins.
     */

    public int getPlayerWins() {
        return playerWins;
    }



    /**
     * Gets the number of times the house has won.
     *
     * @return The number of house wins.
     */
    public int getHouseWins() {
        return houseWins;
    }

    /**
     * Gets the value of the first die.
     *
     * @return The value of the first die.
     */
    public int getMyDie1Value() {
        return myDie1Value;
    }

    /**
     * Gets the value of the second die.
     *
     * @return The value of the second die.
     */
    public int getMyDie2Value() {
        return myDie2Value;
    }


    /**
     * Gets the point established in the game.
     *
     * @return The game point.
     */
    public int getMyPoint() {
        return myPoint;
    }

    /**
     *
     * @return random number between 1 and 6.
     */
    private int rollDie() {
        return myRandom.nextInt(6) + 1;
    }


    /**
     * Resets the game state.
     */
    public void resetGame() {
        playerWins = 0;
        houseWins = 0;
        myPoint = 0;
        myDie1Value = 0;
        myDie2Value = 0;
        setImages();
    }


    /**
     * Rolls the dice and updates their values.
     */
    public void rollDice() {
        myDie1Value = rollDie();
        myDie2Value = rollDie();
        setImages();
    }


    /**
     * Simulates a round of the craps game by rolling the dice and determining the outcome.
     * Handles different scenarios based on the current state of the game, such as the first roll or subsequent rolls.
     */

    public void playGame(){
        if(myPoint == 0){
            if(firstRollWin()){
                handleFirstRollWin();
            }else if(firstRollLoss()){
                handleFirstRollLoss();
            }else{
                myPoint = getSumOfDie();
                houseWonFirstRoll = false;
                playerWonFirstRoll = false;
                houseWon = false;
                playerWon = false;
            }
        }else {
            keepRolling();
        }
    }

    /**
     * Handles the scenario where the player wins on the first roll.
     * Resets the point, increments player wins, and updates flags accordingly.
     */
    private void handleFirstRollWin(){
        myPoint = 0;
        playerWins++;
        playerWonFirstRoll = true;
        houseWon = false;
    }

    /**
     * Handles the scenario where the player loses on the first roll.
     * Resets the point, increments house wins, and updates flags accordingly.
     */
    private void handleFirstRollLoss(){
        myPoint = 0;
        houseWins++;
        houseWonFirstRoll = true;
        playerWon = false;
    }

    /**
     * Handles the scenario where the player wins or loses on subsequent rolls.
     * Resets the point and updates flags based on the outcome.
     */
    private void keepRolling(){
        if(getSumOfDie() == myPoint || getSumOfDie() == 11){
            myPoint = 0;
            playerWins++;
            playerWonFirstRoll = false;
            houseWonFirstRoll = false;
            playerWon = true;
            houseWon = false;
        }else if(getSumOfDie() == 7){
            myPoint = 0;
            houseWins++;
            playerWonFirstRoll = false;
            houseWonFirstRoll = false;
            playerWon = false;
            houseWon = true;
        }
    }

    /**
     *
     * @return true if its first roll and sum is 7 or 11.
     */
    private boolean firstRollWin(){
        return getSumOfDie() == 7 || getSumOfDie() == 11;
    }

    /**
     *
     * @return true if the first roll is 2 or 3 or 12
     */
    private boolean firstRollLoss(){
        return getSumOfDie() == 2 || getSumOfDie() == 3 || getSumOfDie() == 12;
    }


    /**
     * Sets the file names for the images representing the dice.
     */
    public void setImages() {
        myDieImage1 = "die" + getMyDie1Value() + ".png";
        myDieImage2 = "die" + getMyDie2Value() + ".png";
    }


    /**
     * Gets the file name of the image for the first die.
     *
     * @return The image file name.
     */

    public String getMyDieImage1() {
        return myDieImage1;
    }


    /**
     * Gets the file name of the image for the second die.
     *
     * @return The image file name.
     */
    public String getMyDieImage2() {
        return myDieImage2;
    }

    /**
     * Gets the sum of the values of both dice.
     *
     * @return The sum of the dice values.
     */
    public int getSumOfDie() {
        return getMyDie1Value() + getMyDie2Value();
    }

    /**
     *
     * @param theDie1value value to set my die 1.
     */
    public void setMyDie1Value(int theDie1value) {
        if(theDie1value <= 0 || theDie1value > 6){
            throw new IllegalArgumentException("Dice value has to be between 1 and 6 you passed " + theDie1value);
        }
        myDie1Value = theDie1value;
    }


    /**
     *
     * @param theDie2Value the value to set to die 2.
     */
    public void setMyDie2Value(int theDie2Value) {
        if(theDie2Value <= 0 || theDie2Value > 6){
            throw new IllegalArgumentException("Dice value has to be between 1 and 6 you passed " + theDie2Value);
        }
        myDie2Value = theDie2Value;
    }

    /**
     *
     * @param thePoint to set.
     */
    public void setMyPoint(int thePoint) {
        myPoint = thePoint;
    }

    /**
     *
     * @return string representation of the dice.
     */
    @Override
    public String toString() {
        return "Dice{" +
                "myDie1Value=" + myDie1Value +
                ", myDie2Value=" + myDie2Value +
                ", myPoint=" + myPoint +
                ", myDieImage1='" + myDieImage1 + '\'' +
                ", myDieImage2='" + myDieImage2 + '\'' +
                ", playerWins=" + playerWins +
                ", houseWins=" + houseWins +
                '}';
    }
}

