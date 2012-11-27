/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author drm
 */
public class PercentageTest {
    
    @Test
    public void commonCaseShouldWork() {
        Percentage p = new Percentage(57);
        assertEquals(57, p.percentagePoints());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void shouldNotAllowMoreThan100PercentagePoints() {
        Percentage p = new Percentage(101);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void shouldNotAllowLessThan0PercentagePoints() {
        Percentage p = new Percentage(-1);
    }

    @Test
    public void ratioConversionShouldWork() {
        Percentage p = new Percentage(57);
        assertEquals(0.57, p.ratio(), 0.5);
    }
    
    @Test
    public void ratioConstructionShouldWork() {
        Percentage p = new Percentage(0.57);
        assertEquals(57, p.percentagePoints());
    }
    
    @Test
    public void ratioToRatioShouldWork() {
        Percentage p = new Percentage(0.57);
        assertEquals(0.57, p.ratio(), 0.5);
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
        assertEquals(57, p.percentagePoints());
    }
    
    @Test
    public void formattingToStringShouldWork() {
        Percentage p = new Percentage(57);
        assertEquals("57%", p.toString());
    }
    
}
