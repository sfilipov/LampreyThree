/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Percentage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yazidi
 */
public class ReactorTest {
    
    public ReactorTest() {
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
    public void controlRodsShouldMove() {
        Reactor r = new Reactor();
        r.moveControlRods(new Percentage(50));
        assertEquals(new Percentage(50), r.controlRodPosition());
    }
    
    @Test
    public void shouldHaveWaterLevel() {
        Reactor r = new Reactor();
        r.waterLevel();
    }
    
}
