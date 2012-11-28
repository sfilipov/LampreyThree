/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Utilities.Percentage;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author david
 */
@RunWith(JMock.class)
public class ParserTest {

    private final Mockery context = new JUnit4Mockery();
    private final PlantController plantController = context
            .mock(PlantController.class);
    private final TextRenderer textRenderer = context.mock(TextRenderer.class);
    private final Parser parser =
                         new Parser(plantController, textRenderer);

    @Test
    public void shouldMoveControlRods() {
        context.checking(new Expectations() {
            {
                oneOf(plantController).moveControlRods(new Percentage(50));
            }
        });
        parser.parseCommand("movecontrolrods 50");
    }

    @Test
    public void wrongCommandShouldNotMoveControlRods() {
        context.checking(new Expectations() {
            {
                allowing(textRenderer);
            }
        });
        parser.parseCommand("don'tmovecontrolrods 50");
    }

    @Test
    public void wrongCommandShouldDisplayErrorMessage() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: Unknown command 'flibble'");
            }
        });
        parser.parseCommand("flibble 50");
    }

    @Test
    public void shouldNotAcceptControlRodsAbove100() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '101' is not a valid percentage.");
            }
        });
        parser.parseCommand("movecontrolrods 101");
    }

    @Test
    public void shouldNotAcceptControlRodsBelow0() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '-1' is not a valid percentage.");
            }
        });
        parser.parseCommand("movecontrolrods -1");
    }

    @Test
    public void wrongNumberOfArgumentsShouldCauseAnError() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'movecontrolrods'");
            }
        });
        parser.parseCommand("movecontrolrods");
    }
}
