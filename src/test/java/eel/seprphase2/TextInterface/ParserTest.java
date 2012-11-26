/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class ParserTest {

    private class MockController implements PlantController {

        public int position;

        public void moveControlRods(int position) {
            this.position = position;
        }

        public int controlRodPosition() {
            return position;
        }
    }
    MockController plantController;

    public ParserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        plantController = new MockController();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTBD() {
        Parser p = new Parser(plantController);
        p.parseCommand("movecontrolrods 50%");
        assertEquals(50, plantController.controlRodPosition());
    }
}
