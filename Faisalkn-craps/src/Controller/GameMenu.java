package Controller;

import Model.Dice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameMenu {

    /**
     * Start menu item.
     */
    private JMenuItem myStartItem;

    /**
     * Exit menu item
     */
    private JMenuItem myExitItem;

    /**
     * Rest menu item.
     */
    private JMenuItem myResetItem;

    /**
     * About menu item.
     */

    private JMenuItem myAboutItem;

    /**
     * Rules menu item.
     */
    private JMenuItem myRulesItem;

    /**
     * Shortcut menu item.
     */
    private JMenuItem myShortCutsItems;


    /**
     * Represents the game instance.
     */

    private final Game myGame;

    /**
     * Main application frame.
     */
    private final JFrame myFrame;

    /**
     * Game menu title.
     */
    private final static String MY_GAME_MENU_TITLE = "Game";

    /**
     * Help menu item.
     */
    private final static String MY_HELP_MENU_TITLE = "Help";

    /**
     * Represent the Dice instance.
     */
    private final Dice myDice;


    /**
     * Constructs the game menu.
     *
     * @param theFrame     The main JFrame of the application.
     * @param theMenuBar   The menu bar to which the game menu will be added.
     * @param theDice      The dice object used in the game.
     * @param theResetGame The game instance for resetting.
     */
    public GameMenu(final JFrame theFrame, final JMenuBar theMenuBar, final Dice theDice, final Game theResetGame) {
        myGame = theResetGame;
        myFrame = theFrame;
        myDice = theDice;
        initGameMenu(theMenuBar);
    }


    /**
     * Initializes the game menu by creating the necessary menus, adding action listeners,
     * tooltip listeners, and displaying bank-related messages.
     *
     * @param theMenuBar The menu bar to which the game menu will be added.
     */
    private void initGameMenu(JMenuBar theMenuBar) {
        createGameMenu(theMenuBar);
        createHelpMenu(theMenuBar);
        addActionListeners();
        addTollTipListeners();
        showBankMessage();
    }

    /**
     * Creates the game menu with its items, sets tooltips, and adds it to the menu bar.
     *
     * @param theMenuBar The menu bar to which the game menu will be added.
     */
    private void createGameMenu(JMenuBar theMenuBar) {
        JMenu myGameMenu = new JMenu(MY_GAME_MENU_TITLE);

        //Game menu items
        myStartItem = new JMenuItem("Start");
        myExitItem = new JMenuItem("Exit");
        myResetItem = new JMenuItem("Reset");

        //add all the items to game menu
        myGameMenu.add(myStartItem);
        myGameMenu.add(myResetItem);
        myGameMenu.add(myExitItem);


        //set tools tips
        myStartItem.setToolTipText("CTRL + S");
        myResetItem.setToolTipText("CTRL + R");
        myExitItem.setToolTipText("CTRL + E");


        theMenuBar.add(myGameMenu);
        myFrame.setJMenuBar(theMenuBar);
    }


    /**
     * Creates the help menu with its items, sets tooltips, and adds it to the menu bar.
     *
     * @param theMenuBar The menu bar to which the help menu will be added.
     */
    private void createHelpMenu(JMenuBar theMenuBar) {
        JMenu myHelpMenu = new JMenu(MY_HELP_MENU_TITLE);

        // Help menu items
        myShortCutsItems = new JMenuItem("Shortcuts");
        myRulesItem = new JMenuItem("Rules");
        myAboutItem = new JMenuItem("About");

        // Add all the items to the help menu
        myHelpMenu.add(myShortCutsItems);
        myHelpMenu.add(myRulesItem);
        myHelpMenu.add(myAboutItem);

        // Set tooltips
        myAboutItem.setToolTipText("CTRL + A");
        myRulesItem.setToolTipText("CTRL + F");
        myShortCutsItems.setToolTipText("CTRL + N");

        theMenuBar.add(myHelpMenu);
        myFrame.setJMenuBar(theMenuBar);
    }

    /**
     * Adds action listeners to the menu items.
     */
    private void addActionListeners() {
        myStartItem.addActionListener(e -> startItem());


        myExitItem.addActionListener(e -> exitGame());

        myResetItem.addActionListener(e -> {
            myGame.resetGame();
            myGame.gameIsNotActive();
            myDice.resetGame();
        });

        myAboutItem.addActionListener(e -> aboutItem());

        myShortCutsItems.addActionListener(e -> shortCutItems());

        myRulesItem.addActionListener(e -> rulesItem());

    }


    /**
     * Adds Key Event Dispatchers to the current KeyboardFocusManager to handle keyboard shortcuts.
     * Shortcuts include:
     * - CTRL + E: Exits the game
     * - CTRL + S: Starts the game
     * - CTRL + R: Resets the game and deactivates it
     * - CTRL + A: Displays information about the game
     * - CTRL + N: Displays shortcuts available in the game
     * - CTRL + F: Displays rules of the game
     */

    private void addTollTipListeners() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

        manager.addKeyEventDispatcher(createDispatcher(KeyEvent.VK_E, this::exitGame));
        manager.addKeyEventDispatcher(createDispatcher(KeyEvent.VK_S, this::startItem));
        manager.addKeyEventDispatcher(createDispatcher(KeyEvent.VK_R, this::resetGameAndDeactivate));
        manager.addKeyEventDispatcher(createDispatcher(KeyEvent.VK_A, this::aboutItem));
        manager.addKeyEventDispatcher(createDispatcher(KeyEvent.VK_N, this::shortCutItems));
        manager.addKeyEventDispatcher(createDispatcher(KeyEvent.VK_F, this::rulesItem));
    }

    /**
     * Creates a KeyEventDispatcher for a specific key code and associated action.
     *
     * @param theKeyCode The key code to listen for.
     * @param theAction  The action to perform when the key combination is detected.
     * @return A KeyEventDispatcher for the specified key code and action.
     */
    private KeyEventDispatcher createDispatcher(int theKeyCode, Runnable theAction) {
        return e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == theKeyCode && e.isControlDown()) {
                theAction.run();
            }
            return false;
        };
    }

    /**
     * Resets the game and deactivates game-related components.
     * This method is typically used to start a new game or reset the current game state.
     */
    private void resetGameAndDeactivate() {
        myGame.resetGame();
        myGame.gameIsNotActive();
    }

    /**
     * Displays an informational message dialog indicating that the game has started.
     * The dialog includes a custom icon (luffyStart.png) to visually represent the game start.
     */
    private void gameStartedMessage() {
        ImageIcon myStartImageIcon = new ImageIcon("src/icons/luffyStart.png");
        JOptionPane optionPane = new JOptionPane("Game Started", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, myStartImageIcon);
        JDialog dialog = optionPane.createDialog(myFrame, "Game Started");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(myFrame);
        dialog.setVisible(true);
    }

    /**
     * Displays a confirmation dialog to confirm whether the user wants to exit the game.
     * The dialog includes a custom icon (luffyExit.png) for a visual element.
     * If the user chooses to exit (by clicking 'Yes'), the application is terminated.
     */
    private void exitGame() {
        ImageIcon myExitImageIcon = new ImageIcon("src/icons/luffyExit.png");
        int confirm = JOptionPane.showConfirmDialog(myFrame, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, myExitImageIcon);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }


    /**
     * Displays an informational message reminding the player to set a bank account before starting the game.
     * The message is shown in a dialog with a custom icon (bank.png) for visual representation.
     * The dialog includes an 'OK' button, and it can be closed by clicking the close button.
     * This method should be used to prompt the player to set a bank account before playing.
     */
    private void showBankMessage() {
        SwingUtilities.invokeLater(() -> {
            String message = "Set a bank account before you start the game";
            JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);


            optionPane.setIcon(new ImageIcon("src/icons/bank.png"));

            JDialog dialog = optionPane.createDialog(myFrame, "Bank");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        });
    }


    /**
     * Displays an informational message reminding the player to set a bet amount before starting the game.
     * The message is shown in a dialog with a custom icon (bank.png) for visual representation.
     * The dialog includes an 'OK' button, and it can be closed by clicking the close button.
     * This method should be used to prompt the player to set a bet amount before playing.
     */
    private void showBetMessage() {
        SwingUtilities.invokeLater(() -> {
            String message = "Set a bet amount before you start the game";
            JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);

            optionPane.setIcon(new ImageIcon("src/icons/bank.png"));

            JDialog dialog = optionPane.createDialog(myFrame, "Bet and Bank");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        });
    }


    /**
     * Initiates the start sequence for the game.
     * Checks if the original bank amount is set and if there are funds in the bank.
     * If the original bank and bank field are valid, it checks for a valid bet amount.
     * If all conditions are met, displays a game-started message and activates the game.
     * If the original bank is not set or insufficient funds exist, shows a bank message.
     * If the bet field is not valid, shows a bet message.
     */
    private void startItem() {
        if (myGame.checkOriginal() || myGame.checkBankField()) {
            if (myGame.checkBetField()) {
                gameStartedMessage();
                myGame.gameIsActive();
            } else {
                showBetMessage();
            }
        } else {
            showBankMessage();
        }
    }


    /**
     * Displays an "About" dialog with information about the author, app version, and Java version.
     */
    private void aboutItem() {
        String myName = "Faisal Nur";
        String gameVersion = "1.0";
        String javaVersion = "19.0.1";

        String message = "About\n\n" +
                "Author: " + myName + "\n" +
                "App Version: " + gameVersion + "\n" +
                "Java Version: " + javaVersion;
        JOptionPane.showMessageDialog(myFrame, message, "About", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Displays a dialog with a list of game shortcuts and their corresponding key combinations.
     */
    private void shortCutItems() {
        String message = """
                Game Shortcuts

                START CTRL + S
                EXIT CTRL + E
                RESET CTRL + R
                SHORTCUTS CTRL + N
                RULES CTRL + F
                ROLL BUTTON CTRL + ENTER
                PLAY AGAIN CTRL + SPACE
                ABOUT CTRL + A""";
        JOptionPane.showMessageDialog(myFrame, message, "Shortcuts", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays a dialog with the rules of the dice game.
     * The rules include setting a bank amount, the conditions for winning or losing on the first roll, and information about running out of money.
     */
    private void rulesItem() {
        String message = """
                Dice Game Rules:
                1. The player needs to set a bank amount first.
                                
                2. The First Roll/Throw:
                - If the sum is 7 or 11, the roller/player wins.
                - If the sum is 2, 3, or 12, the roller/player loses (the house wins).
                - Otherwise, there is no winner or loser on the first roll, and the player can continue playing.
                3. If the player runs out of money, they can no longer continue playing.""";

        JOptionPane.showMessageDialog(myFrame, message, "Dice Game Rules", JOptionPane.INFORMATION_MESSAGE);
    }
}
