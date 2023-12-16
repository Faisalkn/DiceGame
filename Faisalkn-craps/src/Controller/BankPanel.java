package Controller;

import Model.Bank;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Represents a  bank Panel that is used to set the bank amount.
 *
 * @author Faisal Nur
 * @version fall 2023
 *
 */
public class BankPanel {

    /**
     * Panel that will be used to put every component in this class.
     */
    private final JPanel myMainPanel = new JPanel();
    /**
     * Bank amount text field.
     */
    private final JTextField myAmountTextField;
    /**
     * Button to set the amount.
     */
    private Button mySetBankButton;

    /**
     * Bet panel.
     */
    public BetPanel myBetPanel;


    /**
     * Checks if there is a valid amount in the bank.
     */
    private boolean validAmount = false;

    /**
     * Main Frame to add all the components.
     */
    private final JFrame myFrame;

    /**
     * Constructor used to set the frame, the main panel and the bank.
     * @param theFrame to add all the components.
     * @param theMainPanel to add all the panels.
     * @param theBank that is used to update amount.
     */

    public BankPanel(final JFrame theFrame, final JPanel theMainPanel, final Bank theBank) {
        myAmountTextField = new JTextField("0");
        createBankPanel();
        myFrame = theFrame;
        theMainPanel.add(myMainPanel, BorderLayout.NORTH);
        theFrame.add(theMainPanel);
        addActionListener(theBank);
        myBetPanel = new BetPanel(theFrame, theMainPanel,  theBank, myAmountTextField);
    }

    /**
     * Method that creates a Bank panel and adds
     * all the components for the bank and adds it to main panel.
     */
    private void createBankPanel() {
        JPanel bankPanel = new JPanel(new FlowLayout());
        bankPanel.setBackground(Color.BLACK);
        bankPanel.setOpaque(true);

        JLabel dollarSign = new JLabel("$");
        myAmountTextField.setColumns(4);
        mySetBankButton = new Button("Set Bank");
        bankPanel.add(dollarSign);
        bankPanel.add(myAmountTextField);
        bankPanel.add(mySetBankButton);



        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Bank");
        titledBorder.setTitleColor(Color.WHITE);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);

        bankPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 50, 50, 50),
                titledBorder
        ));

        myMainPanel.setLayout(new BorderLayout());
        myMainPanel.add(bankPanel, BorderLayout.NORTH);
    }

    /**
     * Method that adds a listeners to the bank button and the text field.
     * @param theBank that will be used to update the field.
     */

    private void addActionListener(final Bank theBank){
        mySetBankButton.addActionListener(e -> {
            setMyAmountTextField(theBank);
            if(validAmount){
                myBetPanel.gameIsActive();
            }else{
                myBetPanel.gameNotActive();
                myAmountTextField.setEnabled(true);
                mySetBankButton.setEnabled(true);
            }
        });
        myAmountTextField.addActionListener(e -> {
            setMyAmountTextField(theBank);
            if (validAmount){
                myBetPanel.gameIsActive();
            }else{
                myBetPanel.gameNotActive();
                myAmountTextField.setEnabled(true);
                mySetBankButton.setEnabled(true);
            }
        });
    }

    /**
     * A helper method that checks if the amount that will be set in to the bank is valid.
     * Displays JoptionPane to the user if there amount is not valid.
     * @param theBank that will be used to update the text field.
     */
    private void setMyAmountTextField(final Bank theBank) {
        try {
            int amount = Integer.parseInt(myAmountTextField.getText());
            if (amount > 0) {
                validAmount = true;
                myAmountTextField.setText(String.valueOf(amount));
                theBank.setMyAmount(amount);
            } else {
                validAmount = false;
                JOptionPane.showMessageDialog(myFrame, "Amount has to be greater than 0.", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(myFrame, "Invalid input. Please enter a valid number.", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
            validAmount = false;
        }
        myAmountTextField.setEnabled(false);
        mySetBankButton.setEnabled(false);
        SwingUtilities.invokeLater(this::changeBankButtonColor);
    }

    /**
     * Helper method that changes the button and the filed color when its disabled.
     */
    private void changeBankButtonColor() {
        if (!mySetBankButton.isEnabled()) {
            myAmountTextField.setPreferredSize(new Dimension(20,15));
            mySetBankButton.setForeground(Color.BLACK);
            mySetBankButton.setBackground(Color.WHITE);
        }
        if(!myAmountTextField.isEnabled()){
            myAmountTextField.setPreferredSize(new Dimension(20,30));
            myAmountTextField.setOpaque(true);
        }
    }

    /**
     * Resets the bank and enables the button and the field.
     *
     */
    public void resetBank(){
        myAmountTextField.setText("0");
        mySetBankButton.setEnabled(true);
        myAmountTextField.setEnabled(true);
        myBetPanel.resetBet();
        validAmount = false;
    }

    /**
     * Method that disables the bank field and button when the game is active.
     * This method also disables bet buttons.
     */
    public void gameIsActive(){
        myAmountTextField.setEnabled(false);
        mySetBankButton.setEnabled(false);
        changeBankButtonColor();
        myBetPanel.gameNotActive();
    }

    /**
     *  A getter method
     * @return bank field.
     */
    public JTextField getBankField(){
        return myAmountTextField;
    }

    /**
     * Method is calls the bet panel game not active disables all the bet buttons.
     */
    public void gameIsNotActive(){
        myBetPanel.gameNotActive();
    }

    /**
     *
     * @return the bank field amount.
     */

    public int getBankAmount() {
        return Integer.parseInt(myAmountTextField.getText());
    }

    /**
     *
     * @return the bet panel.
     */
    public BetPanel getMyBetPanel(){
        return myBetPanel;
    }
}

