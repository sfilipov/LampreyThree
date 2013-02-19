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
 * @author David
 */
public class TemperatureTest {

    private final Temperature temperature = new Temperature(786);

    @Test
    public void degreesKelvinShouldBeUnchanged() {
        assertEquals(786, temperature.inKelvin(), 0.00001);
    }

    @Test
    public void shouldConvertToDegreesCelsius() {
        Temperature t1 = new Temperature(273.15);
        assertEquals(0, t1.inCelsius(), 0.1);
    }

    @Test
    public void shouldConvertToStringWithUnits() {
        assertEquals("512.85 degrees C", temperature.toString());
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
    public void hashCodeShouldBeEqualToDegreesKelvin() {
        assertEquals(786, temperature.hashCode());
    }

    /**
     * Test of plus method, of class Temperature.
     */
    @Test
    public void testPlus() {
        Temperature other = new Temperature(10);
        Temperature instance = new Temperature(10);
        Temperature expResult = new Temperature(20);
        Temperature result = instance.plus(other);
        assertEquals(expResult, result);

    }

    /**
     * Test of minus method, of class Temperature.
     */
    @Test
    public void testMinus() {

        Temperature other = new Temperature(10);
        Temperature instance = new Temperature(10);
        Temperature expResult = new Temperature();
        Temperature result = instance.minus(other);
        assertEquals(expResult, result);
    }
     /**
     * Test of greaterThan method, of class Temperature.
     */
    @Test
    public void greaterThan() {
        Temperature t1 = new Temperature(3);
        Temperature t2 = new Temperature(2);
        assertTrue(t1.greaterThan(t2));
    }
}
