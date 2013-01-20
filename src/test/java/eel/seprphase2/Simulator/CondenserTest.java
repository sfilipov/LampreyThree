/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
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
    
        @Test
    public void shouldInitializeWithZeroWaterLevel() {
        
        Condenser instance = new Condenser();
        Percentage expResult = new Percentage(0);
        Percentage result = instance.getWaterLevel();
        assertEquals(expResult, result);
       
    }
    
    

    /**
     * Test of temperature method, of class Condenser.
     */
    @Test
    public void shouldInitializeAtRoomTemperature() {

        Condenser instance = new Condenser();
        Temperature expResult = new Temperature(298.15);
        Temperature result = instance.getTemperature();
        assertEquals(expResult, result);

    }
    
    @Test
    public void shouldKeepConstantTemperatureWithNoCoolantOrSteam(){
        Condenser instance = new Condenser();
        Temperature expResult = new Temperature(298.15);
        Temperature result = instance.getTemperature();
        assertEquals(expResult, result);
        for(int i=0; i<100; i++)
        {
            instance.step();
            assertEquals(expResult, result);
        }
    }
    
    @Test
    public void shouldIncreaseTemperatureWhenNoCoolantPresentAndHasSteam()
    {
        Condenser instance = new Condenser();
        instance.inputPort().pressure = new Pressure(201325);
        instance.inputPort().mass =  kilograms(5);
        instance.inputPort().temperature = kelvin(373.15);
        instance.step();  
        double previous = instance.getTemperature().inKelvin();
        for(int i=0; i<100; i++)
        {
            instance.step();
            assertTrue(instance.getTemperature().inKelvin()>previous);
            previous = instance.getTemperature().inKelvin();
        }
    
    }
    
    
    
    @Test
    public void shouldDecreaseTemperatureWhenOnlyHasCoolant()
    {
        Condenser instance = new Condenser();
        instance.coolantInputPort().mass =  kilograms(5);
        instance.coolantInputPort().temperature = kelvin(285.15);
        instance.step();  
        double previous = instance.getTemperature().inKelvin();
        for(int i=0; i<100; i++)
        {
            instance.step();
            assertTrue(instance.getTemperature().inKelvin()<previous);
            previous = instance.getTemperature().inKelvin();
        }
    
    }

    /**
     * Test of pressure method, of class Condenser.
     */
    @Test
    public void shouldInitializeWith1AtmPressure() {
        Condenser instance = new Condenser();
        Pressure expResult = new Pressure(101325);
        Pressure result = instance.getPressure();
        assertEquals(expResult, result);
       
    }
    
    

    /**
     * Test of step method, of class Condenser.
     */
    @Test
    public void shouldIncreaseWaterMassWithMoreSteam() {
        
        Condenser instance = new Condenser();
        instance.inputPort().pressure = new Pressure(201325);
        instance.inputPort().mass =  kilograms(5);
        instance.step();    
        Percentage p = instance.getWaterLevel();
        assertNotSame(instance.getWaterLevel(), new Percentage(0));
        
    }

    @Test
    public void shouldNotIncreaseWaterMassWithNoMoreSteam() {
        
        Condenser instance = new Condenser();
        instance.step();    
        Percentage p = instance.getWaterLevel();
        assertTrue(instance.getWaterLevel().equals( new Percentage(0)));
    }
    
    
    @Test
    public void shouldPutWaterToOutputPortWithSteam()
    {
        Condenser instance = new Condenser();
        instance.inputPort().pressure = new Pressure(201325);
        instance.inputPort().mass =  kilograms(5);
        instance.step();    
        
        assertTrue(instance.outputPort().mass.inKilograms() > 0);
        
    }
    
    @Test
    public void shouldNotPutWaterToOutputPortWithNoSteam()
    {
        Condenser instance = new Condenser();
        instance.step();    
        
        assertEquals(instance.outputPort().mass, kilograms(0));
        
    }
    
    
    @Test
    public void shouldWear()
    {
        Condenser instance = new Condenser();
        
        double previous = instance.getWear().ratio();
        for(int i=0; i<100; i++)
        {
            instance.step();  
            assertTrue(instance.getWear().ratio()>previous);
            previous = instance.getWear().ratio();
        }
        
    }
    
    @Test
    public void shouldIncreaseWaterMassWithWaterOnInputPort()
    {
        Condenser instance = new Condenser();
        instance.step();
        instance.inputPort().mass = kilograms(10);
        instance.step();
        assertTrue(instance.getWaterLevel().ratio() > 0);
        
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
        condenser.inputPort().mass = kilograms(2);
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
