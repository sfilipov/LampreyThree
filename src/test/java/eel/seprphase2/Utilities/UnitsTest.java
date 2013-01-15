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
 * @author James
 */
public class UnitsTest {
    
    public UnitsTest() {
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

    /**
     * Test of percent method, of class Units.
     */
    @Test
    public void testPercent() {
        int percentagePoints = 0;
        Percentage expResult = new Percentage(0);
        Percentage result = Units.percent(percentagePoints);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of pascals method, of class Units.
     */
    @Test
    public void testPascals() {
        
        double pascals = 0.0;
        Pressure expResult = new Pressure(0);
        Pressure result = Units.pascals(pascals);
        assertEquals(expResult, result);
        
       
    }

    /**
     * Test of kelvin method, of class Units.
     */
    @Test
    public void testKelvin() {
        
        double degrees = 0.0;
        Temperature expResult = new Temperature(0);
        Temperature result = Units.kelvin(degrees);
        assertEquals(expResult, result);
    }

    /**
     * Test of kilograms method, of class Units.
     */
    @Test
    public void testKilograms() {
        
        double kilograms = 0.0;
        Mass expResult = new Mass(0);
        Mass result = Units.kilograms(kilograms);
        assertEquals(expResult, result);
    }

    /**
     * Test of molesOfWater method, of class Units.
     */
    @Test
    public void testMolesOfWater() {
        
        double moles = 0.0;
        Mass expResult = new Mass(0);
        Mass result = Units.molesOfWater(moles);
        assertEquals(expResult, result);
    }

    /**
     * Test of cubicMetres method, of class Units.
     */
    @Test
    public void testCubicMetres() {
        double cubicMetres = 0.0;
        Volume expResult = new Volume(0);
        Volume result = Units.cubicMetres(cubicMetres);
        assertEquals(expResult, result);
    }

    /**
     * Test of joules method, of class Units.
     */
    @Test
    public void testJoules() {
        
        double joules = 0.0;
        Energy expResult = new Energy(0);
        Energy result = Units.joules(joules);
        assertEquals(expResult, result);
    }

    /**
     * Test of kilogramsPerCubicMetre method, of class Units.
     */
    @Test
    public void testKilogramsPerCubicMetre() {
        
        double kilogramsPerCubicMetre = 0.0;
        Density expResult = new Density(0);
        Density result = Units.kilogramsPerCubicMetre(kilogramsPerCubicMetre);
        assertEquals(expResult, result);
    }

    /**
     * Test of metresPerSecond method, of class Units.
     */
    @Test
    public void testMetresPerSecond() {
        
        double metresPerSecond = 0.0;
        Velocity expResult = new Velocity(0);
        Velocity result = Units.metresPerSecond(metresPerSecond);
        assertEquals(expResult, result);
    }
}
