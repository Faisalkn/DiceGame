package Controller;

import View.CrapsGameGUI;

import javax.swing.*;

/**
 * The main class for the Game of Craps application.
 * It initializes and runs the graphical user interface for the game.
 *
 * @author  Faisal Nur.
 * @version Fall 2023.
 */
public class GameOfCrapsMain {

    /**
     * The entry point for the Game of Craps application.
     * It invokes the creation of the CrapsGameGUI within the Swing event dispatch thread.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CrapsGameGUI::new);
    }
}
