/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author drm
 */
public class ArgumentTest {
    
    @Test
    public void shouldConvertToInteger() throws ArgumentConversionException {
        Argument a = new Argument("10");
        assertEquals(10, a.asInteger());
    }
    
    @Test(expected=ArgumentConversionException.class)
    public void shouldThrowArgumentConversionExceptionOnConversionToInteger() throws ArgumentConversionException {
        Argument a = new Argument("boo");
        a.asInteger();
    }
    
    @Test
    public void shouldConvertToPercentage() throws ArgumentConversionException {
        Argument a = new Argument("10%");
        assertEquals(10, a.asPercentage().points(), 0.0);
    }
    
    @Test(expected=ArgumentConversionException.class)
    public void shouldThrowArgumentConversionExceptionOnConversionToPercentage() throws ArgumentConversionException {
        Argument a = new Argument("boo");
        a.asPercentage();
    }
    
}
