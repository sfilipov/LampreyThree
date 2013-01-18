package eel.seprphase2.TextInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Utilities.Percentage;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static eel.seprphase2.Utilities.Units.percent;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author david
 */
public class ParserTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    @Mock
    private PlantController plantController;
    @Mock
    private TextRenderer textRenderer;
    private Parser parser;

    @Before
    public void setup() {
        parser = new Parser(plantController, textRenderer);
    }

    @Test
    public void shouldMoveControlRods() {
        context.checking(new Expectations() {
            {
                oneOf(plantController).moveControlRods(percent(50));
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
    public void wrongNumberOfArgumentsInMoveControlRodsShouldCauseAnError() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'movecontrolrods'");
            }
        });
       
            parser.parseCommand("movecontrolrods");
        
    }
    
    @Test
    public void shouldOpenValve() {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changeValveState(1,true);
            }
        });
        
        parser.parseCommand("openvalve 1");
        
    }
    @Test
    public void wrongNumberOfArgumentsInOpenValveShouldCauseAnError() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'openvalve'");
            }
        });
       
            parser.parseCommand("openvalve");
        
    }
    @Test
    public void shouldNotAcceptValvesBelow0() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '-1' is not a valid percentage.");
            }
        });
       
            parser.parseCommand("openvalve -1");
        
    }
    @Test
    public void shouldCloseValve() {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changeValveState(1,false);
            }
        });
        
        parser.parseCommand("closevalve 1");
        
    }

    @Test
    public void wrongNumberOfArgumentsInCloseValveShouldCauseAnError() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'closevalve'");
            }
        });
        parser.parseCommand("closevalve");
        
    }
    
    
    @Test
    public void shouldTurnPumpOn() {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changePumpState(1,true);
            }
        });
       
        parser.parseCommand("pumpon 1");
       
    }
    @Test
    public void wrongNumberOfArgumentsInPumpOnShouldCauseAnError() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'pumpon'");
            }
        });
       
        parser.parseCommand("pumpon");
        
    }
    @Test
    public void shouldNotAcceptPumpsBelow0() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '-1' is not a valid pump.");
            }
        });
       
            parser.parseCommand("pumpon -1");
        
    }
    @Test
    public void shouldTurnPumpOff() {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changePumpState(1,false);
            }
        });
       
            parser.parseCommand("pumpoff 1");
       
    }
    @Test
    public void wrongNumberOfArgumentsInPumpOffShouldCauseAnError() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'pumpoff'");
            }
        });
       
            parser.parseCommand("pumpoff");
        
    }
    @Test
    public void shouldSaveGame() {
        context.checking(new Expectations() {
            {
                try {
                    oneOf(plantController).saveGame();
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(ParserTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
            parser.parseCommand("save");
        
    }
    
    @Test
    public void shouldLoadGame() {
        context.checking(new Expectations() {
            {
                oneOf(plantController).loadGame();
            }
        });
        
        parser.parseCommand("load");
        
    }
}
