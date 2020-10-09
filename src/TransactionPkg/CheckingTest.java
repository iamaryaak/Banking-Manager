package TransactionPkg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckingTest {

    Account a = new Checking(new Profile("Jesse", "Barbieri"),500, new Date(2010,1,1),true),
            b = new Checking(new Profile("Arya", "Kulkarni"),1000, new Date(2010,1,1),false),
            c = new Checking(new Profile("Thomas", "Jefferson"),1500, new Date(2013,3,27),false),
            d = new Checking(new Profile("Jesse", "Barbieri"),1500, new Date(2010,1,1),true),
            e = new Savings(new Profile("Jesse", "Barbieri"),500, new Date(2010,1,1),true);


    @Test
    void testEquals() {
        assertTrue(a.equals(d), "Same account type and same name associated with them, should return true");
        assertFalse(a.equals(e), "Different account types, same name, should return false");
    }

    @Test
    void testToString() {
        assertEquals("*Checking*Jesse Barbieri* $500.00*1/1/2010*direct deposit account*", a.toString());
        assertEquals("*Checking*Arya Kulkarni* $1,000.00*1/1/2010", b.toString());
    }

    @Test
    void monthlyInterest() {
        assertEquals((0.0005/12), a.monthlyInterest(), "Interest to direct-deposit");
        assertEquals((0.0005/12), c.monthlyInterest(), "1500 balance means no fee");
        assertEquals((0.0005/12), b.monthlyInterest(), "Monthly interest is found");
    }

    @Test
    void monthlyFee() {
        assertEquals(0, a.monthlyFee());
        assertEquals(25, b.monthlyFee());
        assertEquals(0, c.monthlyFee());
    }
}