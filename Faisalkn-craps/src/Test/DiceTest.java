package Test;

import Model.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    private Dice dice;

    @BeforeEach
    void setUp() {
        dice = new Dice();
    }

    @Test
    void rollDice() {
        dice.rollDice();
        assertTrue(dice.getMyDie1Value() >= 1 && dice.getMyDie1Value() <= 6);
        assertTrue(dice.getMyDie2Value() >= 1 && dice.getMyDie2Value() <= 6);
    }

    @Test
    void resetGame() {
        dice.rollDice();
        dice.resetGame();
        assertEquals(0, dice.getPlayerWins());
        assertEquals(0, dice.getHouseWins());
        assertEquals(0, dice.getMyPoint());
        assertEquals(0, dice.getMyDie1Value());
        assertEquals(0, dice.getMyDie2Value());
        assertEquals("die0.png", dice.getMyDieImage1());
        assertEquals("die0.png", dice.getMyDieImage1());
    }

    @Test
    void playGameFirstRollWin() {
        dice.setMyDie1Value(6);
        dice.setMyDie2Value(1);
        dice.playGame();
        assertEquals(1, dice.getPlayerWins());

        dice.resetGame();
        dice.setMyDie1Value(5);
        dice.setMyDie2Value(6);
        dice.playGame();
        assertEquals(1, dice.getPlayerWins());

        dice.resetGame();
        dice.setMyDie1Value(6);
        dice.setMyDie2Value(2);
        dice.playGame();
        assertEquals(0, dice.getPlayerWins());

    }

    @Test
    void playGameFirstRollLoss() {
        dice.setMyDie1Value(1);
        dice.setMyDie2Value(2);
        dice.playGame();
        assertFalse(dice.playerWonFirstRoll);
        assertTrue(dice.houseWonFirstRoll);

        resetGame();

        dice.setMyDie1Value(5);
        dice.setMyDie2Value(4);
        assertFalse(dice.playerWonFirstRoll);
        assertTrue(dice.houseWonFirstRoll);

        resetGame();

        dice.setMyDie1Value(6);
        dice.setMyDie2Value(6);
        assertFalse(dice.playerWonFirstRoll);
        assertTrue(dice.houseWonFirstRoll);

    }

    @Test
    void playGameKeepRollingWin() {
        dice.setMyDie1Value(3);
        dice.setMyDie2Value(4);
        dice.setMyPoint(7);
        dice.playGame();
        assertFalse(dice.playerWonFirstRoll);
        assertFalse(dice.houseWonFirstRoll);



        dice.setMyDie1Value(5);
        dice.setMyDie2Value(6);
        dice.setMyPoint(7);
        dice.playGame();
        assertFalse(dice.playerWonFirstRoll);
        assertFalse(dice.houseWonFirstRoll);
    }

    @Test
    void playGameKeepRollingLoss() {
        dice.setMyDie1Value(4);
        dice.setMyDie2Value(3);
        dice.setMyPoint(8);
        dice.playGame();
        assertFalse(dice.playerWonFirstRoll);
        assertFalse(dice.houseWonFirstRoll);
        assertFalse(dice.playerWon);
        assertTrue(dice.houseWon);
    }

    @Test
    void testToString(){
        dice.setMyDie1Value(3);
        dice.setMyDie1Value(3);
        assertEquals("Dice{" +
                "myDie1Value=" + dice.getMyDie1Value() +
                ", myDie2Value=" + dice.getMyDie2Value() +
                ", myPoint=" + dice.getMyPoint() +
                ", myDieImage1='" + dice.getMyDieImage1() + '\'' +
                ", myDieImage2='" + dice.getMyDieImage2() + '\'' +
                ", playerWins=" + dice.getPlayerWins() +
                ", houseWins=" + dice.getHouseWins() +
                '}', dice.toString());
    }

    @Test
    void testSetMyDies(){
        dice.setMyDie1Value(5);
        dice.setMyDie2Value(6);
        assertEquals(5, dice.getMyDie1Value());
        assertEquals(6, dice.getMyDie2Value());

        assertThrows(IllegalArgumentException.class, () -> dice.setMyDie1Value(-1));
        assertThrows(IllegalArgumentException.class, () -> dice.setMyDie2Value(0));

    }
}