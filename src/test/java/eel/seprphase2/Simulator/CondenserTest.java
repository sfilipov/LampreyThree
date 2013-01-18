/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Pressure;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static eel.seprphase2.Utilities.Units.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author Yazidi
 */
public class CondenserTest {

    public CondenserTest() {
    }
/*
    @Test
    public void shouldReduceTemperatureInsideCondenser() {

        Condenser condenser = new Condenser();
        condenser.setWaterMass(kilograms(2));
        condenser.setTemperature(kelvin(380));
        condenser.reduceTemperature(10);
        assertEquals(kelvin(370), condenser.getTemperature());
    }

    @Test
    public void shouldEqualiseTemperatureIfInputFromTurbine() {
        Condenser condenser = new Condenser();
        condenser.inputPort.mass = kilograms(2);
        condenser.outputPort().mass = kilograms(2);
        condenser.reactorInputPort().mass = kilograms(0);
        condenser.inputPort().temperature = kelvin(473.15);
        condenser.step();
        assertEquals(kelvin(413.15), condenser.getTemperature());

    }
    
    @Test
    public void shouldEqualiseTemperatureIfInputFromReactor() {
        Condenser condenser = new Condenser();
        condenser.inputPort.mass = kilograms(0);
        condenser.outputPort.mass = kilograms(2);
        condenser.reactorInputPort.mass = kilograms(2);
        condenser.reactorInputPort.temperature = kelvin(473.15);
        condenser.step();
        assertEquals(kelvin(413.15), condenser.getTemperature());

    }
    
    @Test
    public void shouldSequentiallyEqualiseTemperatureFromTurbineAndReactorInput() {
        Condenser condenser = new Condenser();
        condenser.inputPort.mass = kilograms(2);
        condenser.outputPort.mass = kilograms(2);
        condenser.reactorInputPort.mass = kilograms(4);
        condenser.reactorInputPort.temperature = kelvin(473.15);
        condenser.inputPort.temperature = kelvin(473.15);
        condenser.step();
        assertEquals(kelvin(438.15), condenser.getTemperature());
        
    }
    
    @Test
    public void shouldSequentiallyIncreaseWaterMassFromInputIfExistent()
    {
        Condenser condenser = new Condenser();
        condenser.inputPort.mass = kilograms(2);
        condenser.outputPort.mass = kilograms(2);
        condenser.reactorInputPort.mass = kilograms(4);
        condenser.reactorInputPort.temperature = kelvin(473.15);
        condenser.inputPort.temperature = kelvin(473.15);
        condenser.step();
        condenser.inputPort.mass = kilograms(2);
        condenser.reactorInputPort.mass = kilograms(4);
        condenser.step(); 
        assertEquals(kilograms(14), condenser.getWaterMass());
    }
    
    @Test
    public void shouldAddToWaterMassAfterTempChange() {
        Condenser condenser = new Condenser();
        condenser.inputPort.mass = kilograms(2);
        condenser.outputPort.mass = kilograms(2);
        condenser.reactorInputPort.mass = kilograms(0);
        condenser.inputPort.temperature = kelvin(473.15);
        condenser.step();
        assertEquals(kilograms(4), condenser.getWaterMass());
        
    }
    
    @Test
    public void pressureShouldIncreaseIfWaterMassIncreases() {
         Condenser condenser = new Condenser();
        condenser.inputPort.mass = kilograms(20);
        condenser.outputPort.mass = kilograms(2);
        condenser.reactorInputPort.mass = kilograms(0);
        condenser.inputPort.temperature = kelvin(473.15);
        condenser.step();
        Pressure first = condenser.getPressure();
        condenser.inputPort.mass = kilograms(20);
        condenser.reactorInputPort.mass = kilograms(0);
        condenser.step();
        Pressure second = condenser.getPressure();
        assertTrue(second.greaterThan(first));
   }
    */
}
