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
    public void wrongNumberOfArgumentsInOpenValveShouldCauseAnErrorWithOneArg() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'openvalve'");
            }
        });
       
            parser.parseCommand("openvalve");
        
    }
    
    @Test
    public void wrongNumberOfArgumentsInOpenValveShouldCauseAnErrorWithThreeArgs() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'openvalve'");
            }
        });
       
            parser.parseCommand("openvalve 0 0");
        
    }
    
    @Test
    public void shouldNotAcceptValvesBelow0() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '-1' is not a valid valve number.");
            }
        });
       
            parser.parseCommand("openvalve -1");
        
    }
    
    @Test
    public void shouldNotAcceptValvesWithStringNames() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: 'foo' is not a valid valve number.");
            }
        });
       
            parser.parseCommand("openvalve foo");
        
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
    public void wrongNumberOfArgumentsInCloseValveShouldCauseAnErrorWithOneArg() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'closevalve'");
            }
        });
       
            parser.parseCommand("closevalve");
        
    }
    
    @Test
    public void wrongNumberOfArgumentsInCloseValveShouldCauseAnErrorWithThreeArgs() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'closevalve'");
            }
        });
       
            parser.parseCommand("closevalve 0 0");
        
    }
    
    @Test
    public void shouldNotAcceptValvesBelow0InCloseValve() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '-1' is not a valid valve number.");
            }
        });
       
            parser.parseCommand("closevalve -1");
        
    }
    
    @Test
    public void shouldNotAcceptValvesWithStringNamesInCloseValve() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: 'foo' is not a valid valve number.");
            }
        });
       
            parser.parseCommand("closevalve foo");
        
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
    public void wrongNumberOfArgumentsInPumpOnShouldCauseAnError0Args() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'pumpon'");
            }
        });
       
        parser.parseCommand("pumpon");
        
    }
    
    @Test
    public void wrongNumberOfArgumentsInPumpOnShouldCauseAnError2Args() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'pumpon'");
            }
        });
       
        parser.parseCommand("pumpon 0 0");
        
    }
    
    
    @Test
    public void shouldNotAcceptPumpsBelow0() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '-1' is not a valid pump number.");
            }
        });
       
        parser.parseCommand("pumpon -1");
        
    }
    
    @Test
    public void shouldNotAcceptStringPumpNames() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: 'foo' is not a valid pump number.");
            }
        });
       
        parser.parseCommand("pumpon foo");
        
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
    public void wrongNumberOfArgumentsInPumpOffShouldCauseAnError0Args() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'pumpoff'");
            }
        });
       
        parser.parseCommand("pumpoff");
        
    }
    
    @Test
    public void wrongNumberOfArgumentsInPumpOffShouldCauseAnError2Args() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'pumpoff'");
            }
        });
       
        parser.parseCommand("pumpoff 0 0");
        
    }
    
    @Test
    public void shoudNotAcceptStringPumpNamesForPumpOff() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: 'foo' is not a valid pump number.");
            }
        });
       
        parser.parseCommand("pumpoff foo");
        
    }
    
    @Test
    public void shouldNotAcceptPumpsBelow0ForPumpOff() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '-1' is not a valid pump number.");
            }
        });
       
        parser.parseCommand("pumpon -1");
        
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
    public void wrongNumberofArgsToSaveShouldCauseError() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'save'");
            }
        });
        
        parser.parseCommand("save 1");
        
    }
    
    @Test
    public void wrongNumberOfArgumentsOnLoad0Args() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'load'");
                
            }
        });
        
        parser.parseCommand("load");
        
    }
     
    @Test
    public void wrongNumberOfArgumentsOnLoad3Args() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'load'");
                
            }
        });
        
        parser.parseCommand("load 0 0");
        
    }
    
    @Test
    public void shouldLoadGameWithNumber() {
        context.checking(new Expectations() {
            {
                
                oneOf(plantController).loadGame(1);
            }
        });
        
        parser.parseCommand("load 1");
        
    }
    
    @Test
    public void shouldNotLoadStringGameNames() {
        context.checking(new Expectations() {
            {
                
                oneOf(textRenderer).outputLine("Error: 'foo' is not a valid number.");
            }
        });
        
        parser.parseCommand("load foo");
        
    }
    
    @Test
    public void shoulNotLoadGameZero() {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: Game '0' does not exist.");
                
            }
        });
        
        parser.parseCommand("load 0");
        
    }
    
    @Test 
    public void shouldRepairPump1() {
        context.checking(new Expectations() {
            {
                oneOf(plantController).repairPump(1);
                
            }
        });
        
        parser.parseCommand("repair pump 1");
    }
    
 
    @Test 
    public void shouldRepairTurbine() {
        context.checking(new Expectations() {
            {
                oneOf(plantController).repairTurbine();
                
            }
        });
        
        parser.parseCommand("repair turbine");
    }
    
    @Test 
    public void shouldRepairCondenser() {
        context.checking(new Expectations() {
            {
                oneOf(plantController).repairCondenser();
                
            }
        });
        
        parser.parseCommand("repair condenser");
    }
    
    
}
