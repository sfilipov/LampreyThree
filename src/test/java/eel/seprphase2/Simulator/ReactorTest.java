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

/**
 *
 * @author Yazidi
 */
public class ReactorTest {
    private final Reactor reactor = new Reactor();
    
    public ReactorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void controlRodsShouldStartAt0Percent() {
        assertEquals(new Percentage(0), reactor.controlRodPosition());
    }
    
    @Test
    public void controlRodsShouldMove() {
        reactor.moveControlRods(new Percentage(50));
        assertEquals(new Percentage(50), reactor.controlRodPosition());
    }
    
    @Test
    public void initialWaterLevelShouldBe100() {
        assertEquals(new Percentage(100), reactor.waterLevel());
    }
    
    @Test
    public void initialTemperatureShouldBe25Degrees() {
        assertEquals(new Temperature(25), reactor.temperature());
    }
    
    @Test
    public void initialPressureShouldBe() {
        assertEquals(new Pressure(101325), reactor.pressure());
    }
    
}
