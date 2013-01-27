/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import org.junit.Test;
import static org.junit.Assert.*;
import static eel.seprphase2.Utilities.Units.*;

/**
 *
 * @author Marius
 */
public class ConnectionTest {

    Port input = new Port();
    Port output = new Port();
    Connection c1 = new Connection(input, output, 1);

    @Test
    public void shouldFlowWhenOpen() {
        input.mass = kilograms(10);
        input.flow = kilograms(7);
        c1.setOpen(true);
        c1.step();
        assertEquals(kilograms(10), output.mass);
        assertEquals(kilograms(7), output.flow);
    }

    @Test
    public void shouldNotFlowWhenClosed() {
        input.mass = kilograms(10);
        input.flow = kilograms(7);
        c1.setOpen(false);
        c1.step();
        assertEquals(kilograms(0), output.mass);
        assertEquals(kilograms(0), output.flow);
    }
}
