package Controller;

import Model.Bank;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents a Panel with components used to set the bet amount.
 *
 * @author Faisal Nur
 * @version Fall 2023
 */
public class BetPanel {

    /**
     * Field that is used to check if the original bank amount was valid.
     */
    private boolean validOriginalBank = false;



    /**
     * Bet buttons
     */
    private JButton myDollar1Button;
    private JButton myDollar5Button;
    private JButton myDollar10Button;
    private JButton myDollar50Button;
    private JButton myDollar100Button;
    private JButton myDollar500Button;
    private JTextField myBetAmountTextField;

    /**
     * Main panel that holds the Bet components.
     */
    private final JPanel myMainPanel = new JPanel(new BorderLayout());

    /**
     * Constructor used to set the frame, the main panel the bank and the bank text field.
     *
     * @param theFrame           that all components will be added (the main frame).
     * @param thePanel           to add all the bet panels including the main panel.
     * @param theBank            that will be updated by the bet.
     * @param theAmountTextField the bank amount text field.
     */
    public BetPanel(JFrame theFrame, JPanel thePanel, Bank theBank, JTextField theAmountTextField) {
        myMainPanel.setBackground(Color.BLACK);
        myMainPanel.setOpaque(true);
        createBetPanel();
        thePanel.add(myMainPanel, BorderLayout.AFTER_LINE_ENDS);
        theFrame.add(thePanel);
        addActionListeners(theFrame, theBank, theAmountTextField);
        gameNotActive();
    }

    /**
     * Bet panel Method that creates a panel and adds
     * all the components and adds it to main panel.
     */
    private void createBetPanel() {
        JPanel betPanel = new JPanel(new FlowLayout());
        betPanel.setBackground(Color.BLACK);
        betPanel.setOpaque(true);

        JLabel betLabel = new JLabel("$");
        betLabel.setForeground(Color.WHITE);

        myDollar1Button = new JButton("+$1");
        myDollar5Button = new JButton("+$5");
        myDollar10Button = new JButton("+$10");
        myDollar50Button = new JButton("+$50");
        myDollar100Button = new JButton("+$100");
        myDollar500Button = new JButton("+$500");

        myBetAmountTextField = new JTextField();
        myBetAmountTextField.setText("0");
        myBetAmountTextField.setColumns(4);
        myBetAmountTextField.setEditable(false);


        betPanel.add(betLabel);
        betPanel.add(myBetAmountTextField);
        betPanel.add(myDollar1Button);
        betPanel.add(myDollar5Button);
        betPanel.add(myDollar10Button);
        betPanel.add(myDollar50Button);
        betPanel.add(myDollar100Button);
        betPanel.add(myDollar500Button);
        myMainPanel.add(betPanel, BorderLayout.CENTER);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Bet");
        titledBorder.setTitleColor(Color.WHITE);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);

        myMainPanel.setBorder(titledBorder);

        myMainPanel.setPreferredSize(new Dimension(100, 100));
    }


    /**
     * Adds action listeners to the bet buttons for setting the bet amount.
     *
     * @param theFrame           The main JFrame of the application.
     * @param theBank            The Bank object representing the player's bank.
     * @param theAmountTextField The JTextField displaying the current bank amount.
     */
    private void addActionListeners(final JFrame theFrame, final Bank theBank, final JTextField theAmountTextField) {
        ActionListener betButtonListener = event -> {
            JButton betButton = (JButton) event.getSource();
            int betAmount = Integer.parseInt(betButton.getText().substring(2));
            theBank.setMyBetAmount(betAmount);
            checkAndUpdate(theFrame, theBank, betAmount, theAmountTextField);
        };

        myDollar1Button.addActionListener(betButtonListener);
        myDollar5Button.addActionListener(betButtonListener);
        myDollar10Button.addActionListener(betButtonListener);
        myDollar50Button.addActionListener(betButtonListener);
        myDollar100Button.addActionListener(betButtonListener);
        myDollar500Button.addActionListener(betButtonListener);
    }

    /**
     * Checks the validity of the bet amount, updates the bank amount, and sets the game state accordingly.
     *
     * @param theFrame           The main JFrame of the application.
     * @param theBank            The Bank object representing the player's bank.
     * @param theBetAmount       The bet amount selected by the player.
     * @param theAmountTextField The JTextField displaying the current bank amount.
     */
    private void checkAndUpdate(JFrame theFrame, Bank theBank, int theBetAmount, JTextField theAmountTextField) {
        if (theBetAmount > theBank.getMyAmount()) {
            JOptionPane.showMessageDialog(theFrame, "Insufficient funds in the bank please reset the game.", "Bank Balance", JOptionPane.ERROR_MESSAGE);
        } else {
            validOriginalBank = theBank.getMyAmount() > 0;
            theBank.updateAmount(theBetAmount);
            myBetAmountTextField.setText(String.valueOf(theBetAmount));
            gameNotActive();
        }
        int updatedAmount = theBank.getMyAmount();
        theAmountTextField.setText(String.valueOf(updatedAmount));

    }

    /**
     * Sets the game state to not active, updating the bet amount text field and resetting button colors.
     */
    public void gameNotActive() {
        myBetAmountTextField.setText(myBetAmountTextField.getText());
        changeBankButtonColor(myDollar1Button);
        changeBankButtonColor(myDollar5Button);
        changeBankButtonColor(myDollar10Button);
        changeBankButtonColor(myDollar50Button);
        changeBankButtonColor(myDollar100Button);
        changeBankButtonColor(myDollar500Button);
    }

    /**
     * Changes the color of the specified bank button and disables it.
     *
     * @param theButton The bank button to modify.
     */
    private void changeBankButtonColor(final JButton theButton) {
        theButton.setEnabled(false);
        theButton.setPreferredSize(new Dimension(60, 20));
        theButton.setOpaque(true);
    }

    /**
     * Activates the bank buttons.
     */
    public void gameIsActive() {
        activateButtons(myDollar1Button);
        activateButtons(myDollar5Button);
        activateButtons(myDollar10Button);
        activateButtons(myDollar50Button);
        activateButtons(myDollar100Button);
        activateButtons(myDollar500Button);
        activateButtons(myDollar1Button);
    }

    /**
     * Reset the bet field.
     */
    public void resetBet() {
        myBetAmountTextField.setText("0");
    }

    /**
     * Helper method that enables all the buttons.
     *
     * @param theButton to activate
     */
    private void activateButtons(final JButton theButton) {
        theButton.setEnabled(true);
    }

    /**
     * @return the bet amount.
     */
    public int getBetAmount() {
        return Integer.parseInt(myBetAmountTextField.getText());
    }

    /**
     * @return true if the bank had money originally.
     */
    public boolean isValidOriginalBank() {
        return validOriginalBank;
    }

    /**
     * @return the bet field.
     */
    public JTextField getMyBetAmountTextField() {
        return myBetAmountTextField;
    }
}
