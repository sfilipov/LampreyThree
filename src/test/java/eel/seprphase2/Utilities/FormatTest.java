/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author David
 */
public class FormatTest {

    @Test
    public void floatingPointTo3DP() {
        assertEquals("1.346", Format.toThreeDecimalPlaces(1.3456));
    }

    @Test
    public void floatingPointTo1DP() {
        assertEquals("1.4", Format.toOneDecimalPlace(1.35));
    }

    @Test
    public void integerTo3DP() {
        assertEquals("17", Format.toThreeDecimalPlaces(17));
    }

    @Test
    public void integerTo1DP() {
        assertEquals("17", Format.toOneDecimalPlace(17));
    }
}
