package Controller;

import Model.Bank;
import Model.Dice;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Represents the panel responsible for rolling the dice and displaying results in the Craps Game GUI.
 */
public class RollPanel {


    // Constants for winner and loser messages
    private static final String WINNER_MESSAGE = "Yay! You won";
    private static final String LOSER_MESSAGE = "You lost";


    /**
     * Panel components
     */
    private final JPanel myMainPanel = new JPanel(new FlowLayout());
    private JTextField myTotalTextField;
    private JButton myRollButton;
    private JTextField myPointTextField;
    private JLabel myDie1Label;
    private JLabel myDie2Label;

    /**
     * Icons for representing dice faces
     */
    private ImageIcon myDie1Icon;
    private ImageIcon myDie2Icon;

    /**
     * Game components
     */
    private final Dice myDice;
    private final Bank myBank;

    private final PlayAgainPanel myPlayAgainPanel;
    private final BankPanel myBankPanel;
    private final JFrame myFrame;

    /**
     * Constructor for the RollPanel class.
     *
     * @param theFrame          The main frame of the Craps Game GUI.
     * @param thePanel          The panel to which this RollPanel will be added.
     * @param theDice           The Dice object representing the game dice.
     * @param theHouseWins      The JTextField displaying house wins in the ScorePanel.
     * @param thePlayerWins     The JTextField displaying player wins in the ScorePanel.
     * @param theBank           The Bank object representing the game bank.
     * @param theBankPanel      The BankPanel object representing the bank panel.
     * @param thePlayAgainPanel The PlayAgainPanel object representing the play-again panel.
     */
    public RollPanel(final JFrame theFrame, final JPanel thePanel, final Dice theDice,
                     final JTextField theHouseWins, final JTextField thePlayerWins,
                     final Bank theBank, final BankPanel theBankPanel,
                     final PlayAgainPanel thePlayAgainPanel) {
        myMainPanel.setBackground(Color.BLACK);
        myPlayAgainPanel = thePlayAgainPanel;
        myBankPanel = theBankPanel;
        myBank = theBank;
        myFrame = theFrame;
        myDice = theDice;
        myMainPanel.setOpaque(true);
        createDiePanel();
        createPointPanel();
        thePanel.add(myMainPanel, BorderLayout.CENTER);
        theFrame.add(thePanel);
        addActionListener(theHouseWins, thePlayerWins);
        addToolTipListener(theHouseWins, thePlayerWins);
        resetRoll();
    }


    /**
     * Creates and initializes the panel displaying the dice roll information.
     */
    private void createDiePanel() {
        JPanel diePanel = new JPanel(new FlowLayout());
        diePanel.setBackground(Color.BLACK);
        diePanel.setOpaque(true);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Current Roll");
        titledBorder.setTitleColor(Color.WHITE);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        diePanel.setBorder(titledBorder);

        JLabel plus = new JLabel("+");
        plus.setForeground(Color.WHITE);
        JLabel equals = new JLabel("=");
        equals.setForeground(Color.WHITE);
        myTotalTextField = new JTextField();
        myTotalTextField.setEditable(false);
        myTotalTextField.setText("0");
        myTotalTextField.setPreferredSize(new Dimension(30, 20));

        myDie1Label = new JLabel("Die 1 ");
        myDie1Label.setForeground(Color.WHITE);
        myDie2Label = new JLabel("Die 2 ");
        myDie2Label.setForeground(Color.WHITE);
        myDie1Icon = new ImageIcon("src/icons/die6.png");
        myDie2Icon = new ImageIcon("src/icons/die6.png");

        myDie1Label.setIcon(myDie1Icon);
        myDie2Label.setIcon(myDie2Icon);

        diePanel.add(myDie1Label);
        diePanel.add(plus);
        diePanel.add(myDie2Label);
        diePanel.add(equals);
        diePanel.add(myTotalTextField);

        myMainPanel.add(diePanel);
    }


    /**
     * Creates and initializes the panel containing the 'Roll' button and the point information.
     */
    private void createPointPanel() {
        myRollButton = new JButton("Roll");
        myRollButton.setPreferredSize(new Dimension(100, 50));

        myRollButton.setToolTipText("CTRL + ENTER");

        JLabel myPointLabel = new JLabel("Point: ");
        myPointLabel.setForeground(Color.WHITE);
        myPointTextField = new JTextField();
        myPointTextField.setText("0");
        myPointTextField.setEditable(false);
        myPointTextField.setPreferredSize(new Dimension(30, 30));

        myRollButton.setEnabled(false);
        myMainPanel.add(myRollButton);
        myMainPanel.add(myPointLabel);
        myMainPanel.add(myPointTextField);
    }


    /**
     * Adds an action listener to the Roll button.
     *
     * @param theHouseWinsField  The JTextField displaying the house wins information.
     * @param thePlayerWinsField The JTextField displaying the player wins information.
     */

    private void addActionListener(JTextField theHouseWinsField, JTextField thePlayerWinsField) {
        myRollButton.addActionListener(e -> rollDice(theHouseWinsField, thePlayerWinsField));
    }

    /**
     * Adds a tool tip listener to the 'Roll' button, enabling a keyboard shortcut (CTRL + ENTER).
     *
     * @param theHouseWinsField  The JTextField displaying the house wins information.
     * @param thePlayerWinsField The JTextField displaying the player wins information.
     */
    private void addToolTipListener(JTextField theHouseWinsField, JTextField thePlayerWinsField) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown()) {
                if (myRollButton.isEnabled()) {
                    rollDice(theHouseWinsField, thePlayerWinsField);
                }
            }
            return false;
        });
    }

    /**
     * Displays a result dialog with the given message and title.
     *
     * @param message  The message to be displayed in the dialog.
     * @param title    The title of the dialog.
     * @param theFrame The parent JFrame of the dialog.
     */
    private void showResultDialog(String message, String title, JFrame theFrame) {
        JOptionPane.showMessageDialog(theFrame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Updates the icons of the die labels based on the current dice images.
     *
     * @param theDice The Dice object containing the current die images.
     */

    private void updateDieIcon(Dice theDice) {
        String imagePath1 = "src/icons/" + theDice.getMyDieImage1();
        String imagePath2 = "src/icons/" + theDice.getMyDieImage2();
        myDie1Icon = new ImageIcon(imagePath1);
        myDie2Icon = new ImageIcon(imagePath2);
        myDie1Label.setIcon(myDie1Icon);
        myDie2Label.setIcon(myDie2Icon);
    }

    /**
     * Resets the roll panel to its initial state.
     * Sets the point and total text fields to 0 and updates die icons to show initial die images.
     * Deactivates the roll button.
     */
    public void resetRoll() {
        myPointTextField.setText("0");
        myTotalTextField.setText("0");
        myDie1Icon = new ImageIcon("src/icons/die6.png");
        myDie2Icon = new ImageIcon("src/icons/die6.png");
        myDie1Label.setIcon(myDie1Icon);
        myDie2Label.setIcon(myDie2Icon);
        gameIsNotActive();
    }

    /**
     * Activates the roll button and updates the appearance of the roll panel to indicate an active game.
     * Enables the roll button, sets its foreground color to black, and updates the main panel's background color to black.
     */
    public void gameIsActive() {
        myRollButton.setEnabled(true);
        myRollButton.setForeground(Color.BLACK);
        myMainPanel.setBackground(Color.BLACK);
    }

    /**
     * Deactivates the roll button and updates the appearance of the roll panel to indicate an inactive game.
     * Disables the roll button and sets its preferred size to a smaller dimension. Additionally, makes the button opaque.
     */
    public void gameIsNotActive() {
        myRollButton.setEnabled(false);
        myRollButton.setPreferredSize(new Dimension(90, 40));
        myRollButton.setOpaque(true);
    }


    /**
     * Rolls the dice, updates the UI components based on the game outcome, and plays sound effects.
     * Invoked when the player clicks the "Roll" button or uses the corresponding keyboard shortcut.
     *
     * @param theHouseWinsField  JTextField displaying the number of wins for the house.
     * @param thePlayerWinsField JTextField displaying the number of wins for the player.
     */

    private void rollDice(JTextField theHouseWinsField, JTextField thePlayerWinsField) {
        myDice.rollDice();
        myDice.playGame();
        playSound("src/sounds/rollDice.wav");
        updateDieIcon(myDice);

        SwingUtilities.invokeLater(() -> {
            myTotalTextField.setText(String.valueOf(myDice.getSumOfDie()));
            if (myDice.playerWonFirstRoll) {
                showMessage();
                if (myPlayAgainPanel.playButton()) {
                    resetRoll();
                }
            } else if (myDice.houseWonFirstRoll) {
                gameIsNotActive();
                playSound("src/sounds/gameLost.wav");
                showResultDialog(LOSER_MESSAGE, "Loser", myFrame);
                myBankPanel.getBankField().setText(String.valueOf(myBank.getMyAmount()));
                myBankPanel.getMyBetPanel().getMyBetAmountTextField().setText("0");
                myPlayAgainPanel.activatePlayButton();
            } else if (myDice.playerWon) {
                showMessage();
            } else if (myDice.houseWon) {
                gameIsNotActive();
                playSound("src/sounds/gameLost.wav");
                showResultDialog(LOSER_MESSAGE, "Loser", myFrame);
                myPlayAgainPanel.activatePlayButton();
                myBankPanel.getMyBetPanel().getMyBetAmountTextField().setText("0");
            } else {
                myPointTextField.setText(String.valueOf(myDice.getMyPoint()));
            }
            theHouseWinsField.setText(String.valueOf(myDice.getHouseWins()));
            thePlayerWinsField.setText(String.valueOf(myDice.getPlayerWins()));
        });
    }

    /**
     * Displays a winner message, updates the UI components, and plays a sound effect.
     * Invoked when the player wins the game.
     */
    private void showMessage() {
        gameIsNotActive();
        showResultDialog(WINNER_MESSAGE, "Winner", myFrame);
        playSound("src/sounds/gameWin.wav");
        myBank.increaseAmount(myBank.getMyBetAmount());
        myBankPanel.getBankField().setText(String.valueOf(myBank.getMyAmount()));
        myBankPanel.getMyBetPanel().getMyBetAmountTextField().setText("0");
        myPlayAgainPanel.activatePlayButton();

    }

    /**
     * Plays a sound effect using the provided file path.
     *
     * @param thePathName The path to the sound file.
     */
    private void playSound(final String thePathName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(thePathName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
