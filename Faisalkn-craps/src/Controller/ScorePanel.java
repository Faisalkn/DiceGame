package Controller;

import Model.Bank;
import Model.Dice;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Represents the panel that displays the scores of the player and the house in the Craps game.
 *
 * @author Faisal Nur
 * @version Fall 2023
 */
public class ScorePanel {
    private final JPanel myMainPanel = new JPanel(new GridLayout(2, 1));
    private final JTextField myPlayerWinsTextField = new JTextField();
    private final JTextField myHouseWinsTextField = new JTextField();

    private final RollPanel myRollPanel;


    /**
     * Initializes the ScorePanel with the specified components.
     *
     * @param theFrame          The main JFrame of the Craps game.
     * @param thePanel          The main JPanel of the Craps game.
     * @param theDice           The Dice object used in the Craps game.
     * @param theBank           The Bank object used in the Craps game.
     * @param theBankPanel      The BankPanel object used in the Craps game.
     * @param thePlayAgainPanel The PlayAgainPanel object used in the Craps game.
     */

    public ScorePanel(JFrame theFrame, JPanel thePanel, Dice theDice, Bank theBank, BankPanel theBankPanel, PlayAgainPanel thePlayAgainPanel) {
        myPlayerWinsTextField.setText("0");
        myHouseWinsTextField.setText("0");
        myRollPanel = new RollPanel(theFrame, thePanel, theDice, myHouseWinsTextField, myPlayerWinsTextField, theBank, theBankPanel, thePlayAgainPanel);
        thePlayAgainPanel.setRollPanel(myRollPanel);
        myMainPanel.setBackground(new Color(0x123456));
        myMainPanel.setOpaque(true);
        createScorePanel();
        thePanel.add(myMainPanel, BorderLayout.WEST);
        theFrame.add(thePanel);
    }

    /**
     * Creates the score panel containing the player's and house's win totals.
     */
    private void createScorePanel() {

        JPanel playerPanel = new JPanel();
        JPanel housePanel = new JPanel();
        housePanel.setBackground(Color.BLACK);
        housePanel.setOpaque(true);
        playerPanel.setBackground(Color.BLACK);
        playerPanel.setOpaque(true);

        JLabel myPlayerWinsLabel = new JLabel("Player wins: ");
        myPlayerWinsLabel.setForeground(Color.WHITE);
        myPlayerWinsTextField.setEditable(false);
        myPlayerWinsTextField.setColumns(2);

        JLabel myHouseWinsLabel = new JLabel("House wins: ");
        myHouseWinsLabel.setForeground(Color.WHITE);
        myHouseWinsTextField.setEditable(false);
        myHouseWinsTextField.setColumns(2);

        playerPanel.add(myPlayerWinsLabel);
        playerPanel.add(myPlayerWinsTextField);
        housePanel.add(myHouseWinsLabel);
        housePanel.add(myHouseWinsTextField);

        // Add a titled border with the title "Win Total" and a top margin
        int topMargin = 10;
        String playerWin = "Player Total Win";
        String HouseWin = "House Total Win";
        TitledBorder titledBorderPlayer = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), playerWin);
        titledBorderPlayer.setTitleColor(Color.WHITE);
        titledBorderPlayer.setTitleJustification(TitledBorder.CENTER);
        titledBorderPlayer.setTitlePosition(TitledBorder.TOP);

        TitledBorder titledBorderHouse = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), HouseWin);
        titledBorderHouse.setTitleColor(Color.WHITE);
        titledBorderHouse.setTitleJustification(TitledBorder.CENTER);
        titledBorderHouse.setTitlePosition(TitledBorder.TOP);

        playerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(topMargin, 10, 10, 10),
                titledBorderPlayer
        ));
        housePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(topMargin, 10, 10, 10),
                titledBorderHouse
        ));

        myMainPanel.add(playerPanel);
        myMainPanel.add(housePanel);
    }

    /**
     * Resets the ScorePanel, including the RollPanel and score displays.
     */
    public void resetScorePanel() {
        myRollPanel.resetRoll();
        myHouseWinsTextField.setText("0");
        myPlayerWinsTextField.setText("0");
    }

    /**
     * Activates the ScorePanel, allowing user interaction.
     */
    public void gameIsActive() {
        myRollPanel.gameIsActive();
    }

    /**
     * Deactivates the ScorePanel, disabling user interaction.
     */
    public void gameIsNotActive() {
        myRollPanel.gameIsNotActive();
    }


}
