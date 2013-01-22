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
public class VelocityTest {

    /**
     * Test of inMetresPerSecond method, of class Velocity.
     */
    @Test
    public void testInMetresPerSecond() {
        Velocity instance = new Velocity();
        double expResult = 0.0;
        double result = instance.inMetresPerSecond();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of plus method, of class Velocity.
     */
    @Test
    public void testPlus() {
        Velocity other = new Velocity(10);
        Velocity instance = new Velocity(10);
        Velocity expResult = new Velocity(20);
        Velocity result = instance.plus(other);
        assertEquals(expResult, result);

    }

    /**
     * Test of minus method, of class Velocity.
     */
    @Test
    public void testMinus() {

        Velocity other = new Velocity(10);
        Velocity instance = new Velocity(10);
        Velocity expResult = new Velocity();
        Velocity result = instance.minus(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Velocity.
     */
    @Test
    public void testToString() {
        Velocity instance = new Velocity();
        String expResult = "0 m/s";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Velocity.
     */
    @Test
    public void testHashCode() {

        Velocity instance = new Velocity();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Velocity.
     */
    @Test
    public void testEquals() {

        Object obj = null;
        Velocity instance = new Velocity();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    @Test
    public void testEquals2() {

        Object obj = new Velocity(10);
        Velocity instance = new Velocity();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    @Test
    public void testEquals3() {
        Object obj = new Velocity(10);
        Velocity instance = new Velocity(10);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
}
