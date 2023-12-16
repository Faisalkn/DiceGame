package Test;

import Model.Bank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    private Bank myBank;
    @BeforeEach
    void setUp() {
        myBank = new Bank();
    }

    @Test
    void getMyAmount() {
        assertEquals(0, myBank.getMyAmount());
        myBank.updateAmount(20);
        // should set back to 0 again.
        assertEquals(0, myBank.getMyAmount());
    }

    @Test
    void setMyAmount() {
        myBank.setMyAmount(1000);
        assertEquals(1000, myBank.getMyAmount());
        assertThrows(IllegalArgumentException.class, () -> myBank.setMyAmount(-100));
    }

    @Test
    void setMyBetAmount() {
        myBank.setMyBetAmount(100);
        assertEquals(100, myBank.getMyBetAmount());
        assertThrows(IllegalArgumentException.class, () -> myBank.setMyBetAmount(-100));
    }

    @Test
    void updateAmount() {
        myBank.updateAmount(100);
        assertEquals(0, myBank.getMyAmount());

        myBank.setMyAmount(1000);
        myBank.updateAmount(100);
        assertEquals(900, myBank.getMyAmount());
    }

    @Test
    void increaseAmount() {

        myBank.setMyAmount(1000);
        myBank.increaseAmount(50);
        assertEquals(1100, myBank.getMyAmount());
    }

    @Test
    void getMyBetAmount() {
        myBank.setMyAmount(1000);
        assertEquals(1000, myBank.getMyAmount());

    }
}