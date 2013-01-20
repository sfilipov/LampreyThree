/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator.PhysicalModel;

import eel.seprphase2.Simulator.PhysicalModel.Turbine;
import eel.seprphase2.Utilities.Pressure;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static eel.seprphase2.Utilities.Units.*;
import static org.hamcrest.Matchers.*;

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
        turbine.inputPort().pressure = pascals(101325);
        turbine.outputPort().pressure = pascals(101325);
        turbine.step();
        assertEquals(0, turbine.outputPower(), 0.1);
    }

    @Test
    public void someMassGeneratesPower() {
        Turbine turbine = new Turbine();
        turbine.inputPort().mass = kilograms(5);
        turbine.step();
        assertThat(turbine.outputPower(), greaterThan(0.0));
    }
}
