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
    MockController plantController;
    MockRenderer textRenderer;
    
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
        textRenderer = new MockRenderer();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldMoveControlRods() {
        Parser p = new Parser(plantController, textRenderer);
        p.parseCommand("movecontrolrods 50");
        assertEquals(50, plantController.controlRodPosition());
    }
    
    @Test
    public void wrongCommandShouldNotMoveControlRods() {
        Parser p = new Parser(plantController, textRenderer);
        plantController.moveControlRods(37);
        p.parseCommand("don'tmovecontrolrods 50");
        assertEquals(37, plantController.controlRodPosition());
    }
    
    @Test
    public void wrongCommandShouldDisplayErrorMessage() {
        Parser p = new Parser(plantController, textRenderer);
        p.parseCommand("flibble 50");
        textRenderer.hasOnly("Error: Unknown command 'flibble'");
    }
    
    @Test
    public void shouldNotAcceptControlRodsAbove100() {
        Parser p = new Parser(plantController, textRenderer);
        plantController.moveControlRods(37);
        p.parseCommand("movecontrolrods 101");
        assertEquals(37, plantController.position);
        textRenderer.hasOnly("Error: Cannot move control rods above 100");
    }
    
    @Test
    public void shouldNotAcceptControlRodsBelow0() {
        Parser p = new Parser(plantController, textRenderer);
        plantController.moveControlRods(37);
        p.parseCommand("movecontrolrods -1");
        assertEquals(37, plantController.position);
        textRenderer.hasOnly("Error: Cannot move control rods below 0");
    }
    
}
