package TransactionPkg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    Date    a = new Date(2010,1,2),
            b = new Date(2010,1,1),
            c = new Date(2010,1,1),
            d = new Date(2010,1,1),
            e = new Date(2020,2,29),
            f = new Date(2019, 2, 29),
            g = new Date(2020, 13, 10);


    @Test
    void compareTo() {
        assertEquals(1, b.compareTo(a), "a should be a later date than b, returning a 1");
        assertEquals(0, b.compareTo(c), "Same date returns 0");
        assertEquals(-1, a.compareTo(b), "b should be before a, returning a -1");
    }

    @Test
    void testToString() {
        assertEquals("1/1/2010", d.toString(), "Expected date output");
        assertEquals("1/2/2010", a.toString(), "Expected date output");
    }

    @Test
    void isValid() {
        assertTrue(e.isValid(), "2020 is a leap year, returns true");
        assertFalse(f.isValid(), "2019 is not a leap year, returns false");
        assertFalse(g.isValid(), "13 months is not an acceptable date, returns false");
    }
}