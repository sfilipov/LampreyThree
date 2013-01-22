/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Simulator.IValve;
import eel.seprphase2.Simulator.Valve;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author james
 */
public class ValveTest {

    @Test
    public void shouldInitialiseValve() {
        IValve valve = new Valve();
        assertEquals(true, valve.getOpen());

    }

    @Test
    public void shouldSetClosed() {
        IValve valve = new Valve();
        valve.setOpen(false);
        assertEquals(false, valve.getOpen());


    }

    @Test
    public void shouldSetOpen() {
        IValve valve = new Valve();
        valve.setOpen(true);
        assertEquals(true, valve.getOpen());


    }
}
