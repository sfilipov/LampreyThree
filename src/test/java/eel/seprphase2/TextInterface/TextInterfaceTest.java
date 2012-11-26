/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Yazidi
 */
public class TextInterfaceTest {

    MockController plantController;
    MockRenderer textRenderer;

    public TextInterfaceTest() {
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
    public void shouldShowStatusOnStartup() {
        TextInterface ti = new TextInterface(plantController, textRenderer);
        plantController.moveControlRods(37);
        ti.begin();
        textRenderer.hasOnly("Control Rod Position: 37\n");
    }
}
