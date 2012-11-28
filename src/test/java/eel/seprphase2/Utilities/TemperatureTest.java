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

/**
 *
 * @author drm
 */
public class TemperatureTest {
    private final Temperature temperature =
                              new Temperature(786);
    
    @Test
    public void degreesCelsisusShouldBeUnchanged() {
        assertEquals(786, temperature.degreesCelsius());
    }
    
    @Test
    public void shouldConvertToStringWithUnits() {
        assertEquals("786 degrees C", temperature.toString());
    }
    
    @Test
    public void shouldBeEqualToEqualTemperature() {
        assertTrue(temperature.equals(new Temperature(786)));
    }
    
    @Test
    public void shouldNotBeEqualToUnEqualTemperature() {
        assertFalse(temperature.equals(new Temperature(785)));
    }
    
    @Test
    public void shouldNotBeEqualToNull() {
        assertFalse(temperature.equals(null));
    }
    
    @Test
    public void shouldNotBeEqualToWeirdThing() {
        assertFalse(temperature.equals((Object)(new String("hello"))));
    }
    
    @Test
    public void shouldBeEqualToEqualTemperatureAsObject() {
        assertTrue(temperature.equals((Object)(new Temperature(786))));
    }
    
    @Test
    public void hashCodeShouldBeEqualToDegreesCelsius() {
        assertEquals(786, temperature.hashCode());
    }
    
}
