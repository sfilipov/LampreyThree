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
        turbine.setInputPressure(pascals(0));
        turbine.setOutputPressure(pascals(0));
        assertEquals(0, turbine.outputPower());
    }
    
    @Test
    public void somePressureDeltaGeneratesPower() {
        Turbine turbine = new Turbine();
        turbine.setInputPressure(pascals(101325));
        turbine.setOutputPressure(pascals(0));
        
    }
    
}
