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
public class DensityTest {
    
    public DensityTest() {
    }
    


    /**
     * Test of inKilogramsPerCubicMetre method, of class Density.
     */
    @Test
    public void testInKilogramsPerCubicMetre() {
        
        Density instance = new Density();
        double expResult = 1000;
        double result = instance.inKilogramsPerCubicMetre();
        assertEquals(expResult, result, 0.0);
        
        
    }

    /**
     * Test of ofLiquidWater method, of class Density.
     */
    @Test
    public void testOfLiquidWater() {
        Density expResult = new Density(1000);
        Density result = Density.ofLiquidWater();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of minus method, of class Density.
     */
    @Test
    public void testMinus() {
        
        Density other = new Density(10);
        Density instance = new Density(20);
        Density expResult = new Density(10);
        Density result = instance.minus(other);
        assertEquals(expResult, result);
    
    }
   


    /**
     * Test of plus method, of class Density.
     */
    @Test
    public void testPlus() {
        
        Density other = new Density(10);
        Density instance = new Density(10);
        Density expResult = new Density(20);
        Density result = instance.plus(other);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of toString method, of class Density.
     */
    @Test
    public void testToString() {
        
        Density instance = new Density();
        String expResult = "1000kg/m^3";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of equals method, of class Density.
     */
    @Test
    public void testEquals() {
        
        Object obj = new Density(10);
        Density instance = new Density(20);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
  
    }

    @Test
    public void testEquals2() {
        
        Object obj = new Density(20);
        Density instance = new Density(20);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
  
    }

    
    /**
     * Test of hashCode method, of class Density.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Density instance = new Density();
        int expResult = 1000;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
    }
}
