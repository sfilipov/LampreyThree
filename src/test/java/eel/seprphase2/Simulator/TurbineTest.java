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

/**
 *
 * @author Yazidi
 */
public class TurbineTest {
    
    public TurbineTest() {
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
    public void noPressureDeltaEqualsNoPower() {
        Turbine turbine = new Turbine();
        turbine.setFlowVelocity(metresPerSecond(0));
        turbine.step();
        assertEquals(0, turbine.outputPower(), 0.1);
    }
    
    @Test
    public void somePressureDeltaGeneratesPower() {
        Turbine turbine = new Turbine();
        turbine.setFlowVelocity(metresPerSecond(10));
        turbine.step();
        assertEquals(100, turbine.outputPower(), 0.1);
    }
    
}
