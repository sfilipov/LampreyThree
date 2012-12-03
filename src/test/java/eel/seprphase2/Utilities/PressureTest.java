package eel.seprphase2.Utilities;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author drm
 */
public class PressureTest {

    private final Pressure pressure = new Pressure(434900);

    @Test
    public void shouldNotAlterPascals() {
        assertEquals(434900, pressure.inPascals(), 0.5);
    }

    @Test
    public void shouldConvertToAtmospheres() {
        assertEquals(4.29213, pressure.inAtmospheres(), 0.00005);
    }

    @Test
    public void shouldConvertToAtmospheresTo3DecimalPlaces() {
        assertEquals("4.292 atm", pressure.toString());
    }

    @Test
    public void shouldHashAsPressureInPascals() {
        assertEquals(434900, pressure.hashCode());
    }

    @Test
    public void shouldBeEqualToEqualPressure() {
        assertTrue(pressure.equals(new Pressure(434900)));
    }

    @Test
    public void shouldNotBeEqualToUnequalTemperature() {
        assertFalse(pressure.equals(new Pressure(484489)));
    }

    @Test
    public void shouldNotBeEqualToNull() {
        assertFalse(pressure.equals(null));
    }

    @Test
    public void shouldNotBeEqualToWeirdThing() {
        assertFalse(pressure.equals((Object)(new String("hello"))));
    }

    @Test
    public void shouldBeEqualToEqualPressureAsObject() {
        assertTrue(pressure.equals((Object)(new Pressure(434900))));
    }
}
