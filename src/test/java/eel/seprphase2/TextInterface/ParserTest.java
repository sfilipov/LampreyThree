/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantController;
import java.util.ArrayList;
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

    private class MockRenderer implements TextRenderer {
        private ArrayList<String> strings;
        public MockRenderer() {
            strings = new ArrayList<String>();
        }
        public void output(String s) {
            strings.add(s);
        }
        public void hasOnly(String expected) {
            assertEquals(1, strings.size());
            assertEquals(expected, strings.get(0));
        }
    }
    MockRenderer renderer;
    
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
        renderer = new MockRenderer();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldMoveControlRods() {
        Parser p = new Parser(plantController, renderer);
        p.parseCommand("movecontrolrods 50");
        assertEquals(50, plantController.controlRodPosition());
    }
    
    @Test
    public void wrongCommandShouldNotMoveControlRods() {
        Parser p = new Parser(plantController, renderer);
        plantController.moveControlRods(37);
        p.parseCommand("don'tmovecontrolrods 50");
        assertEquals(37, plantController.controlRodPosition());
    }
    
    @Test
    public void wrongCommandShouldDisplayErrorMessage() {
        Parser p = new Parser(plantController, renderer);
        p.parseCommand("flibble 50");
        renderer.hasOnly("Error: Unknown command 'flibble'");
    }
}
