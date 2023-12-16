package Controller;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * The PlayAgainPanel class represents a panel that allows the player to play the game again.
 * It includes a play button that triggers the game reset and activates the rolling of dice.
 *
 * @author Faisal Nur
 * @version Fall 2023
 */
public class PlayAgainPanel {

    /**
     * Main panel for this clas.
     */
    private final JPanel myMainPanel = new JPanel();

    /**
     * Play button
     */

    private JButton myPlayButton;

    /**
     * Bank panel instance.
     */
    private final BankPanel myBankPanel;

    /**
     * Roll panel stance.
     */
    private RollPanel myRollPanel;


    /**
     * Constructs a new PlayAgainPanel.
     *
     * @param theFrame     The JFrame containing the application.
     * @param thePanel     The main JPanel where this panel is added.
     * @param theBankPanel The BankPanel associated with the game.
     */
    public PlayAgainPanel(JFrame theFrame, JPanel thePanel, BankPanel theBankPanel) {
        myBankPanel = theBankPanel;
        createPlayPanel();
        myMainPanel.setOpaque(true);
        myMainPanel.setBackground(Color.BLACK);
        thePanel.add(myMainPanel, BorderLayout.AFTER_LAST_LINE);
        theFrame.add(thePanel);
        addActionListener();
        addToolTips();
    }


    /**
     * Creates the play panel with a Play Again button.
     * The button is configured with a preferred size, tooltip, and appearance settings.
     */
    private void createPlayPanel() {
        myPlayButton = new JButton("Play Again");
        myPlayButton.setPreferredSize(new Dimension(150, 50));
        myPlayButton.setToolTipText("CTRL + SPACE");
        myPlayButton.setBackground(Color.BLACK);
        myPlayButton.setOpaque(true);
        EmptyBorder border = new EmptyBorder(0, 0, 50, 0);
        myMainPanel.add(myPlayButton);
        myMainPanel.setBorder(border);
    }

    /**
     * Adds an action listener to the Play Again button.
     * The action listener triggers the playButtonListener method when the button is clicked.
     */
    private void addActionListener() {
        myPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButtonListener();
            }
        });
    }


    /**
     * Adds a keyboard focus manager to listen for key events and trigger the playButtonListener
     * when the CTRL + SPACE key combination is pressed while the Play Again button is enabled.
     */
    private void addToolTips() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_SPACE && e.isControlDown()) {
                    if (myPlayButton.isEnabled()) {
                        playButtonListener();
                    }
                }
                return false;
            }
        });
    }


    /**
     * Activates the Play Again button by enabling it, updating its preferred size,
     * and changing its background color to white with opaque settings.
     */
    public void activatePlayButton() {
        myPlayButton.setEnabled(true);
        myPlayButton.setPreferredSize(new Dimension(100, 40));
        myPlayButton.setBackground(Color.WHITE);
        myPlayButton.setOpaque(true);
    }

    /**
     * Deactivates the "Play Again" button by disabling it, updating its preferred size,
     * and changing its background color to white with opaque settings.
     */

    public void deactivatePlayButton() {
        myPlayButton.setEnabled(false);
        myPlayButton.setPreferredSize(new Dimension(100, 40));
        myPlayButton.setBackground(Color.WHITE);
        myPlayButton.setOpaque(true);
    }


    /**
     * @return true if the play button is selected.
     */
    public boolean playButton() {
        return myPlayButton.isSelected();
    }


    /**
     * Sets the RollPanel associated with this PlayAgainPanel.
     *
     * @param rollPanel The RollPanel to be associated with this PlayAgainPanel.
     */
    public void setRollPanel(RollPanel rollPanel) {
        myRollPanel = rollPanel;
    }

    /**
     * Activates the bet panel, resets the bet amount, deactivates the Play Again button,
     * and resets the RollPanel.
     */

    private void playButtonListener() {
        myBankPanel.getMyBetPanel().gameIsActive();
        myBankPanel.getMyBetPanel().getMyBetAmountTextField().setText("0");
        deactivatePlayButton();
        myRollPanel.resetRoll();
    }
}
