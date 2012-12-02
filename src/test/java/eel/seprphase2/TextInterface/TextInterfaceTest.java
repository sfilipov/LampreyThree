package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.auto.Auto;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author Yazidi
 */
public class TextInterfaceTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    @Mock
    private PlantController plantController;
    @Mock
    private PlantStatus plantStatus;
    @Mock
    private TextRenderer textRenderer;
    @Mock
    private LineReader lineReader;
    @Auto
    private Sequence lines;
    private TextInterface textInterface;

    @Before
    public void setup() {
        textInterface = new TextInterface(plantController, plantStatus,
                                          textRenderer, lineReader);
    }

    @Test
    public void shouldShowStatus() {
        context.checking(new Expectations() {
            {
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
                oneOf(textRenderer).outputLine("Reactor Pressure: 1 atm");
                inSequence(lines);
                oneOf(textRenderer).outputLine("Water Level: 100%");
                inSequence(lines);
            }
        });
        textInterface.showStatus();
    }

    @Test
    public void shouldProcessACommand() {
        context.checking(new Expectations() {
            {
                oneOf(lineReader).readLine();
                will(returnValue("movecontrolrods 50"));
                oneOf(plantController).moveControlRods(new Percentage(50));
            }
        });
        textInterface.processCommand();
    }
}
