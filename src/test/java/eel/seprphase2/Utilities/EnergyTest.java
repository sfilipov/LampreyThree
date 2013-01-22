/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class EnergyTest {
    
    Energy e1 = new Energy(1);
    Energy e2 = new Energy(2);
    Energy eK = new Energy(10000);
    Energy eM = new Energy(10000000.0);
    Energy eG = new Energy(10000000000.0);
    
    @Test
    public void stringConversion() {
        assertEquals("1 J", e1.toString());
    }
    
    @Test
    public void stringConversionKPrefix() {
        assertEquals("10 kJ", eK.toString());
    }
    
    @Test
    public void stringConversionMPrefix() {
        assertEquals("10 MJ", eM.toString());
    }
    
    @Test
    public void stringConversionGPrefix() {
        assertEquals("10 GJ", eG.toString());
    }
}
