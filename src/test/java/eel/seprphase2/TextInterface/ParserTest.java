package eel.seprphase2.TextInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.FailureModel.CannotControlException;
import eel.seprphase2.Simulator.GameManager;
import eel.seprphase2.Simulator.KeyNotFoundException;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Utilities.Percentage;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static eel.seprphase2.Utilities.Units.percent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Ignore;
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
    private GameManager gameManager;
    @Mock
    private TextRenderer textRenderer;
    private Parser parser;

    @Before
    public void setup() {
        parser = new Parser(plantController, gameManager, textRenderer);
    }

    @Test
    public void shouldMoveControlRods() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(plantController).moveControlRods(percent(50));
            }
        });
        
            parser.parseCommand("movecontrolrods 50");
        
    }

    @Test (expected = DoNotStep.class)
    public void wrongCommandShouldNotMoveControlRods() throws DoNotStep {
        context.checking(new Expectations() {
            {
                allowing(textRenderer);
            }
        });
        
            parser.parseCommand("don'tmovecontrolrods 50");
       
    }

 
    
    @Test (expected = DoNotStep.class)
    public void wrongCommandShouldDisplayErrorMessage() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: Unknown command 'flibble'");
            }
        });
        
            parser.parseCommand("flibble 50");
        
    }

    @Test (expected = DoNotStep.class)
    public void shouldNotAcceptControlRodsAbove100()  throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '101' is not a valid percentage.");
            }
        });
       
            parser.parseCommand("movecontrolrods 101");
        
    }

    @Test (expected = DoNotStep.class)
    public void shouldNotAcceptControlRodsBelow0() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '-1' is not a valid percentage.");
            }
        });
       
            parser.parseCommand("movecontrolrods -1");
        
    }

    @Test (expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInMoveControlRodsShouldCauseAnError() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'movecontrolrods'");
            }
        });
       
            parser.parseCommand("movecontrolrods");
        
    }
    
    @Test
    public void shouldOpenValve() throws DoNotStep, KeyNotFoundException {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changeValveState(1,true);
            }
        });
        
        parser.parseCommand("openvalve 1");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInOpenValveShouldCauseAnErrorWithOneArg() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'openvalve'");
            }
        });
       
            parser.parseCommand("openvalve");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInOpenValveShouldCauseAnErrorWithThreeArgs() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'openvalve'");
            }
        });
       
            parser.parseCommand("openvalve 0 0");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void shouldNotAcceptValvesBelow0()  throws DoNotStep{
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '-1' is not a valid valve number.");
            }
        });
       
            parser.parseCommand("openvalve -1");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void shouldNotAcceptValvesWithStringNames() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: 'foo' is not a valid valve number.");
            }
        });
       
            parser.parseCommand("openvalve foo");
        
    }
    

    
    @Test
    public void shouldCloseValve()  throws DoNotStep, KeyNotFoundException {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changeValveState(1,false);
            }
        });
        
        parser.parseCommand("closevalve 1");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInCloseValveShouldCauseAnErrorWithOneArg() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'closevalve'");
                
            }
        });
       
            parser.parseCommand("closevalve");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInCloseValveShouldCauseAnErrorWithThreeArgs() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'closevalve'");
                
            }
        });
       
            parser.parseCommand("closevalve 0 0");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void shouldNotAcceptValvesBelow0InCloseValve()  throws DoNotStep{
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '-1' is not a valid valve number.");
                
            }
        });
       
            parser.parseCommand("closevalve -1");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void shouldNotAcceptValvesWithStringNamesInCloseValve() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: 'foo' is not a valid valve number.");
                
            }
        });
       
            parser.parseCommand("closevalve foo");
        
    }
    
    


    
    @Test
    public void shouldTurnPumpOn()  throws DoNotStep, KeyNotFoundException, CannotControlException {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changePumpState(1,true);
            }
        });
       
        parser.parseCommand("pumpon 1");
       
    }
    @Test (expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInPumpOnShouldCauseAnError0Args() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'pumpon'");
             
            }
        });
       
        parser.parseCommand("pumpon");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInPumpOnShouldCauseAnError2Args() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'pumpon'");
            }
        });
       
        parser.parseCommand("pumpon 0 0");
        
    }
    
    
    @Test (expected = DoNotStep.class)
    public void shouldNotAcceptPumpsBelow0() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '-1' is not a valid pump number.");
            }
        });
       
        parser.parseCommand("pumpon -1");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void shouldNotAcceptStringPumpNames()  throws DoNotStep{
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: 'foo' is not a valid pump number.");
            }
        });
       
        parser.parseCommand("pumpon foo");
        
    }
    
    
    
    public void shouldTurnPumpOff()  throws DoNotStep, KeyNotFoundException, CannotControlException {
        context.checking(new Expectations() {
            {
                oneOf(plantController).changePumpState(1,false);
            }
        });
       
            parser.parseCommand("pumpoff 1");
       
    }
    @Test (expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInPumpOffShouldCauseAnError0Args()  throws DoNotStep{
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'pumpoff'");
            }
        });
       
        parser.parseCommand("pumpoff");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void wrongNumberOfArgumentsInPumpOffShouldCauseAnError2Args()  throws DoNotStep{
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'pumpoff'");
            }
        });
       
        parser.parseCommand("pumpoff 0 0");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void shoudNotAcceptStringPumpNamesForPumpOff()  throws DoNotStep{
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: 'foo' is not a valid pump number.");
            }
        });
       
        parser.parseCommand("pumpoff foo");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void shouldNotAcceptPumpsBelow0ForPumpOff()  throws DoNotStep{
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: '-1' is not a valid pump number.");
            }
        });
       
        parser.parseCommand("pumpon -1");
        
    }
    
    
    
    @Test (expected = DoNotStep.class)
    public void shouldSaveGame() throws DoNotStep {
        context.checking(new Expectations() {
            {
                try {
                    oneOf(gameManager).saveGame();
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(ParserTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                oneOf(textRenderer).outputLine("Game Saved!");
            }
        });
        
            parser.parseCommand("save");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void wrongNumberofArgsToSaveShouldCauseError() throws DoNotStep {
        context.checking(new Expectations() {
            {
               oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'save'"); 
            }
        });
        
        parser.parseCommand("save 1");
        
    }
    
    @Ignore @Test
    public void wrongNumberOfArgumentsOnLoad0Args() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'load'");
                
            }
        });
        
        parser.parseCommand("load");
        
    }
     
    @Test (expected = DoNotStep.class)
    public void wrongNumberOfArgumentsOnLoad3Args() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: wrong number of arguments to command 'load'");
                
            }
        });
        
        parser.parseCommand("load 0 0");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void shouldLoadGameWithNumber() throws DoNotStep, IOException {
        context.checking(new Expectations() {
            {
                
                allowing(gameManager).loadGame(1);
                
            }
        });
        
        parser.parseCommand("load 1");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void shouldNotLoadStringGameNames()  throws DoNotStep{
        context.checking(new Expectations() {
            {
                
                oneOf(textRenderer).outputLine("Error: 'foo' is not a valid number.");
            }
        });
        
        parser.parseCommand("load foo");
        
    }
    
    @Test (expected = DoNotStep.class)
    public void shoulNotLoadGameZero()  throws DoNotStep{
        context.checking(new Expectations() {
            {
                oneOf(textRenderer).outputLine("Error: Game '0' does not exist.");
                
            }
        });
        
        parser.parseCommand("load 0");
        
    }
    
    @Test 
    public void shouldRepairPump1()  throws DoNotStep, KeyNotFoundException{
        context.checking(new Expectations() {
            {
                oneOf(plantController).repairPump(1);
                
            }
        });
        
        parser.parseCommand("repair pump 1");
    }
    
 
    @Test 
    public void shouldRepairTurbine()  throws DoNotStep{
        context.checking(new Expectations() {
            {
                oneOf(plantController).repairTurbine();
                
            }
        });
        
        parser.parseCommand("repair turbine");
    }
    
    @Test 
    public void shouldRepairCondenser() throws DoNotStep {
        context.checking(new Expectations() {
            {
                oneOf(plantController).repairCondenser();
                
            }
        });
        
        parser.parseCommand("repair condenser");
    }
    
    
}
