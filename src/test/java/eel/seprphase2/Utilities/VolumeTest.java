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
public class VolumeTest {

    /**
     * Test of inCubicMetres method, of class Volume.
     */
    @Test
    public void testInCubicMetres() {
        System.out.println("inCubicMetres");
        Volume instance = new Volume();
        double expResult = 0.0;
        double result = instance.inCubicMetres();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of plus method, of class Volume.
     */
    @Test
    public void testPlus() {
        
        Volume other = new Volume(10);
        Volume instance = new Volume(10);
        Volume expResult = new Volume(20);
        Volume result = instance.plus(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of minus method, of class Volume.
     */
    @Test
    public void testMinus() {
        
        Volume other = new Volume(10);
        Volume instance = new Volume(10);
        Volume expResult = new Volume(0);
        Volume result = instance.minus(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of massAt method, of class Volume.
     */
    @Test
    public void testMassAt() {
        
        Density density = new Density(1);
        Volume instance = new Volume(1);
        Mass expResult = new Mass(1);
        Mass result = instance.massAt(density);
        assertEquals(expResult, result);
  
    }

    /**
     * Test of toString method, of class Volume.
     */
    @Test
    public void testToString() {
        Volume instance = new Volume();
        String expResult = "0m^3";
        String result = instance.toString();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of hashCode method, of class Volume.
     */
    @Test
    public void testHashCode() {
        
        Volume instance = new Volume();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of equals method, of class Volume.
     */
    @Test
    public void testEquals() {
        Object obj = null;
        Volume instance = new Volume();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
    
        /**
     * Test of equals method, of class Volume.
     */
    @Test
    public void testEquals2() {
        Object obj = new Volume(20);
        Volume instance = new Volume(10);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of equals method, of class Volume.
     */
    @Test
    public void testEquals3() {
        Object obj = new Volume(10);
        Volume instance = new Volume(10);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
    /**
     * Test of equals method, of class Volume.
     */
    @Test
    public void testEquals4() {
        Object obj = new Density(10);
        Volume instance = new Volume(10);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
}
