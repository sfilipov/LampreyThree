/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;
import eel.seprphase2.Utilities.Percentage;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Yazidi
 */
@RunWith(JMock.class)
public class TextInterfaceTest {

    private final Mockery context = new JUnit4Mockery();
    private final PlantController plantController = context
            .mock(PlantController.class);
    private final PlantStatus plantStatus = context.mock(PlantStatus.class);
    private final TextRenderer textRenderer = context.mock(TextRenderer.class);
    private final LineReader textReader = context.mock(LineReader.class);
    private final TextInterface textInterface =
                                new TextInterface(plantController, plantStatus,
                                                  textRenderer, textReader);

    @Test
    public void shouldShowStatus() {
        context.checking(new Expectations() {
            {
                allowing(plantStatus).controlRodPosition();
                will(returnValue(new Percentage(37)));
                oneOf(textRenderer).outputLine("Control Rod Position: 37%");
            }
        });
        textInterface.showStatus();
    }

    @Test
    public void shouldProcessACommand() {
        context.checking(new Expectations() {
            {
                oneOf(textReader).readLine();
                will(returnValue("movecontrolrods 50"));
                oneOf(plantController).moveControlRods(new Percentage(50));
            }
        });
        textInterface.processCommand();
    }
}
