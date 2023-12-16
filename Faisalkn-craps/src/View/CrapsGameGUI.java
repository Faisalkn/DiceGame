package View;

import Controller.*;
import Model.Bank;
import Model.Dice;

import javax.swing.*;
import java.awt.*;

/**
 * Main class representing the Craps Game GUI.
 *
 * @author Faisal Nur.
 * @version Fall 2023
 */
public class CrapsGameGUI extends JFrame {
    /**
     * Frame title
     */
    private static final String MY_TITLE = "Craps Game";

    /**
     * Frame dimension.
     */
    private static final Dimension MY_DIMENSION = new Dimension(600, 600);

    /**
     * Default constructor for the CrapsGameGUI class.
     */
    public CrapsGameGUI() {
        initializeComponents();
    }

    /**
     * Initializes the components of the Craps Game GUI.
     */
    private void initializeComponents() {
        JFrame myFrame = new JFrame(MY_TITLE);
        myFrame.setSize(MY_DIMENSION);

        Dice myDice = new Dice();
        Bank myBank = new Bank();

        JPanel myMainPanel = new JPanel(new BorderLayout());

        BankPanel myBankPanel = new BankPanel(myFrame, myMainPanel, myBank);
        PlayAgainPanel myPlayAgainPanel = new PlayAgainPanel(myFrame, myMainPanel, myBankPanel);
        myPlayAgainPanel.deactivatePlayButton();

        ScorePanel myScorePanel = new ScorePanel(myFrame, myMainPanel, myDice, myBank, myBankPanel, myPlayAgainPanel);
        Game myResetGame = new Game(myScorePanel, myBankPanel, myPlayAgainPanel);
        new GameMenu(myFrame, new JMenuBar(), myDice, myResetGame);

        myFrame.add(myMainPanel);
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
