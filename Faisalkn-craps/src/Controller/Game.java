package Controller;

/**
 * Game class that activates and deactivates components.
 *
 * @author Faisal Nur
 * @version Fall 2023
 */
public class Game {
    /**
     * The score Panel
     */
    private final ScorePanel myScorePanel;
    /**
     * The bank panel
     */
    private final BankPanel myBankPanel;

    /**
     * The play again panel
     */
    private final PlayAgainPanel myPlayAgainPanel;

    /**
     * Constructs a new Game with the specified ScorePanel, BankPanel, and PlayAgainPanel.
     *
     * @param theScorePanel     The ScorePanel to use in the game.
     * @param theBankPanel      The BankPanel to use in the game.
     * @param thePlayAgainPanel The PlayAgainPanel to use in the game.
     */

    public Game(final ScorePanel theScorePanel, final BankPanel theBankPanel, final PlayAgainPanel thePlayAgainPanel) {
        myScorePanel = theScorePanel;
        myBankPanel = theBankPanel;
        myPlayAgainPanel = thePlayAgainPanel;
        resetGame();
    }


    /**
     * Resets the game by resetting the ScorePanel, BankPanel, and deactivating the PlayAgainPanel.
     */

    public void resetGame() {
        myScorePanel.resetScorePanel();
        myBankPanel.resetBank();
        myPlayAgainPanel.deactivatePlayButton();
    }

    /**
     * Activates the game by setting the BankPanel and ScorePanel to an active state,
     * and deactivating the PlayAgainPanel.
     */

    public void gameIsActive() {
        myBankPanel.gameIsActive();
        myScorePanel.gameIsActive();
        myPlayAgainPanel.deactivatePlayButton();
    }


    /**
     * Deactivates the game by setting the BankPanel and ScorePanel to an inactive state.
     */
    public void gameIsNotActive() {
        myBankPanel.gameIsNotActive();
        myScorePanel.gameIsNotActive();
    }


    /**
     * Checks if the bet amount in the BankPanel's BetPanel is greater than zero.
     *
     * @return true if the bet amount is greater than zero, false otherwise.
     */
    public boolean checkBetField() {
        return myBankPanel.getMyBetPanel().getBetAmount() > 0;
    }

    /**
     * Checks if the original bank amount in the BankPanel's BetPanel is valid.
     *
     * @return true if the original bank amount is valid, false otherwise.
     */
    public boolean checkOriginal() {
        return myBankPanel.getMyBetPanel().isValidOriginalBank();
    }

    /**
     * Checks if the bank amount in the BankPanel is valid.
     *
     * @return true if the bank amount is valid, false otherwise.
     */
    public boolean checkBankField() {
        return myBankPanel.getBankAmount() > 0;
    }

}
