package eel.seprphase2.Utilities;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author David
 */
public class PercentageTest {

    @Test
    public void commonCaseShouldWork() {
        Percentage p = new Percentage(57);
        assertEquals(p.points(), 57.0, 0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowMoreThan100PercentagePoints() {
        Percentage p = new Percentage(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowLessThan0PercentagePoints() {
        Percentage p = new Percentage(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowConstructionFromMalformedString() {
        Percentage p = new Percentage("asdga");
    }

    @Test
    public void ratioConversionShouldWork() {
        Percentage p = new Percentage(57);
        assertEquals(0.57, p.ratio(), 0.5);
    }

    @Test
    public void ratioConstructionShouldWork() {
        Percentage p = new Percentage(0.57);
        assertEquals(0.57, p.points(), 0.1);
    }

    @Test
    public void validPercentageNumbersShouldBeValid() {
        assertTrue(Percentage.isValidPercentage(57));
    }

    @Test
    public void oneHundredPercentShouldBeValid() {
        assertTrue(Percentage.isValidPercentage(100));
    }

    @Test
    public void oneHundredAndOnePercentShouldBeInvalid() {
        assertFalse(Percentage.isValidPercentage(101));
    }

    @Test
    public void validPercentageStringsShouldBeValid() {
        assertTrue(Percentage.isValidPercentage("57%"));
    }

    @Test
    public void oneHundredPercentStringShouldBeValid() {
        assertTrue(Percentage.isValidPercentage("100%"));
    }

    @Test
    public void oneHundredAndOnePercentStringShouldNotBeValid() {
        assertFalse(Percentage.isValidPercentage("101%"));
    }

    @Test
    public void randomStringShouldNotBeValid() {
        assertFalse(Percentage.isValidPercentage("agasdg"));
    }

    @Test
    public void doublePercentageSignsShouldNotBeValid() {
        assertFalse(Percentage.isValidPercentage("57%%"));
    }

    @Test
    public void negativeStringsShouldNotBeValid() {
        assertFalse(Percentage.isValidPercentage("-10%"));
    }

    @Test
    public void constructionFromStringShouldWork() {
        Percentage p = new Percentage("57%");
        assertTrue(p.equals(new Percentage(57)));
    }

    @Test
    public void formattingToStringShouldWork() {
        Percentage p = new Percentage(57);
        assertEquals("57%", p.toString());
    }

    @Test
    public void formattingFloatingPointToStringShouldWork() {
        Percentage p = new Percentage(57.1);
        assertEquals("57.1%", p.toString());
    }

    @Test
    public void equalPercentagesShouldBeEqual() {
        Percentage p1 = new Percentage(57);
        Percentage p2 = new Percentage(57);
        assertTrue(p1.equals(p2));
    }

    @Test
    public void unequalPercentagesShouldBeUnequal() {
        Percentage p1 = new Percentage(57);
        Percentage p2 = new Percentage(58);
        assertFalse(p1.equals(p2));
    }

    @Test
    public void equalPercentagesShouldHaveEqualHashCodes() {
        Percentage p1 = new Percentage(57);
        Percentage p2 = new Percentage(57);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    public void unequalPercentagesShouldHaveUnequalHashCodes() {
        Percentage p1 = new Percentage(57);
        Percentage p2 = new Percentage(58);
        assertFalse(p1.hashCode() == p2.hashCode());
    }

    @Test
    public void shouldNotBeEqualToNull() {
        Percentage p1 = new Percentage(57);
        assertFalse(p1.equals(null));
    }

    @Test
    public void shouldNotBeEqualToWeirdThing() {
        Percentage p1 = new Percentage(57);
        assertFalse(p1.equals((Object)(new String("hello"))));
    }

    @Test
    public void shouldBeEqualToPercentageAsObject() {
        Percentage p1 = new Percentage(57);
        assertTrue(p1.equals((Object)(new Percentage(57))));
    }

    @Test
    public void addition() {
        Percentage p1 = new Percentage(57);
        Percentage p2 = new Percentage(3);
        assertEquals(new Percentage(60), p1.plus(p2));
    }

    @Test
    public void subtraction() {
        Percentage p1 = new Percentage(57);
        Percentage p2 = new Percentage(2);
        assertEquals(new Percentage(55), p1.minus(p2));
    }
    
    @Test
    public void handleFPErrorInAddition() {
        Percentage p1 = new Percentage(50.0000001);
        Percentage p2 = new Percentage(50.0000001);
        assertEquals(new Percentage(100), p1.plus(p2));
    }

    @Test
    public void handleFPErrorInSubtraction() {
        Percentage p1 = new Percentage(50);
        Percentage p2 = new Percentage(50.0000001);
        assertEquals(new Percentage(0), p1.minus(p2));
    }

    
    @Test(expected = IllegalArgumentException.class)
    public void outOfRangeAddition() {
        Percentage p1 = new Percentage(55);
        Percentage p2 = new Percentage(55);
        p1.plus(p2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfRangeSubtraction() {
        Percentage p1 = new Percentage(55);
        Percentage p2 = new Percentage(60);
        p1.minus(p2);
    }
}
