/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
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
                final Sequence lines = context.sequence("lines");
                allowing(plantStatus).controlRodPosition();
                will(returnValue(new Percentage(37)));
                allowing(plantStatus).temperature();
                will(returnValue(new Temperature(25)));
                allowing(plantStatus).pressure();
                will(returnValue(new Pressure(101325)));
                allowing(plantStatus).waterLevel();
                will(returnValue(new Percentage(100)));
                
                oneOf(textRenderer).outputLine("Control Rod Position: 37%");
                inSequence(lines);
                oneOf(textRenderer).outputLine("Reactor Temperature: 25 degrees C");
                inSequence(lines);
                oneOf(textRenderer).outputLine("Reactor Pressure: 1 atm"); inSequence(lines);
                oneOf(textRenderer).outputLine("Water Level: 100%"); inSequence(lines);

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
