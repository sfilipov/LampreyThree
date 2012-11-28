/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantStatus;
import eel.seprphase2.Utilities.Percentage;
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
    
    private MockController plantController = new MockController();
    private MockStatus plantStatus = new MockStatus();
    private MockRenderer textRenderer = new MockRenderer();
    private MockReader textReader = new MockReader();
    private final TextInterface textInterface =
                                new TextInterface(plantController, plantStatus, textRenderer, textReader);

    @Test
    public void shouldShowStatus() {
        plantStatus.controlRodPosition = new Percentage(37);
        textInterface.showStatus();
        textRenderer.hasOnly("Control Rod Position: 37%");
    }

    @Test
    public void shouldProcessACommand() {
        textReader.giveLine("movecontrolrods 50");
        textInterface.processCommand();
        assertEquals(new Percentage(50), plantController.controlRodPosition);
    }
}
