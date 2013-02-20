/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Simulator.FuelPile;
import eel.seprphase2.Utilities.Percentage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marius
 */
public class FuelPileTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldOutput3MWattsWhenControlRodsAt0() {
        FuelPile fuelPile = new FuelPile();
        fuelPile.moveControlRods(new Percentage(0));
        assertEquals(3000000.0, fuelPile.output(1));
    }

    @Test
    public void shouldOutput23MegaWattsWhenControlRodsAt100() {
        FuelPile fuelPile = new FuelPile();
        fuelPile.moveControlRods(new Percentage(100));
        assertEquals(23000000.0, fuelPile.output(1));
    }
}
