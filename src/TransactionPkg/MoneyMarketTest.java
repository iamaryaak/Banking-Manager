package TransactionPkg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyMarketTest {

    Account a = new MoneyMarket(new Profile("Jesse", "Barbieri"),500, new Date(2010,1,1)),
            b = new MoneyMarket(new Profile("Arya", "Kulkarni"),1000, new Date(2010,1,1)),
            c = new MoneyMarket(new Profile("Thomas", "Jefferson"),2500, new Date(2013,3,27)),
            d = new MoneyMarket(new Profile("Jesse", "Barbieri"),2500, new Date(2010,1,1)),
            e = new Savings(new Profile("Jesse", "Barbieri"),500, new Date(2010,1,1),true);

    /**
     * Tests if equals works in MoneyMarket between two MoneyMarket accounts
     */
    @Test
    void testEquals() {
        assertTrue(a.equals(d), "Equal names should return true");
        assertFalse(a.equals(e), "Should return false, different account types, same name");
    }


    /**
     * Tests MoneyMarket toString method
     */
    @Test
    void testToString() {
        assertEquals("*Money Market*Arya Kulkarni* $1,000.00*1/1/2010*0 withdrawals*", b.toString());
    }

    /**
     * Tests monthly Interest for MoneyMarket class
     */
    @Test
    void monthlyInterest() {
        assertEquals((0.0065/12.00), a.monthlyInterest(), "All interest should be equal");
        assertEquals((0.0065/12.00), b.monthlyInterest(), "All interest should be equal");
        assertEquals((0.0065/12.00), c.monthlyInterest(), "All interest should be equal");
    }

    /**
     * Tests monthly fee plus it's constraints in MoneyMarket
     */
    @Test
    void monthlyFee() {
        assertEquals(0, c.monthlyFee());
        assertEquals(0, d.monthlyFee());
    }
}