/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Percentage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static eel.seprphase2.Utilities.Units.*;

/**
 *
 * @author Yazidi
 */
public class PumpTest {

    public PumpTest() {
    }

    @Test
    public void shouldNotPumpWaterOverCapacity() {
        Port input = new Port();
        Port output = new Port();
        input.mass = kilograms(10);
        Pump pump = new Pump(input, output);
        pump.setCapacity(kilograms(2));
        pump.step();
        assertEquals(kilograms(2), output.mass);
    }

    @Test
    public void shouldPumpAllWaterIfUnderCapacity() {
        Port input = new Port();
        Port output = new Port();
        input.mass = kilograms(2);
        Pump pump = new Pump(input, output);
        pump.setCapacity(kilograms(10));
        pump.step();
        assertEquals(kilograms(2), output.mass);
    }

    @Test
    public void shouldSendWaterAtSameTemperature() {
        
        Port input = new Port();
        Port output = new Port();
        input.mass = kilograms(2);
        input.temperature = kelvin(100);
        Pump pump = new Pump(input, output);
        pump.setCapacity(kilograms(10));
        pump.step();
        assertEquals(kelvin(100), output.temperature);
    }
}
