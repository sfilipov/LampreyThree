/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator.PhysicalModel;

import eel.seprphase2.Simulator.PhysicalModel.PhysicalModel;
import eel.seprphase2.Simulator.FailureModel.FailureState;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static eel.seprphase2.Utilities.Units.*;
import java.util.Calendar;
import static org.hamcrest.Matchers.*;
import org.junit.Ignore;
/**
 *
 * @author Yazidi
 */
public class PhysicalModelTest {
    @Test
    public void runningStepIncreasesReactorTemperature() {
        PhysicalModel model = new PhysicalModel();
        model.moveControlRods(percent(100));
        model.step(10);
        assertThat(model.reactorTemperature().inKelvin(), greaterThan(350.0));
    }
    
    @Test
    public void reactorMovesTurbine() {
        PhysicalModel model = new PhysicalModel();
        model.moveControlRods(percent(100));
        model.step(100);
        assertThat(model.energyGenerated().inJoules(), greaterThan(0.0));
    }
    
    @Test
    public void shouldSetConnectionToClosed(){
        PhysicalModel model = new PhysicalModel();
        model.setReactorToTurbine(false);
        assertEquals(false, model.getReactorToTurbine());
        
    }
    
    @Ignore @Test
    public void shouldSerializeToFile() {
        Calendar cal = Calendar.getInstance();
        String time = String.valueOf(cal.getTimeInMillis());
        PhysicalModel model = new PhysicalModel();
        //model.setUsername(time);
        
        try
        {
            //model.saveGame();
        }
        catch(Exception e)
        {
            
            fail("Error occure while saving file");
        }
    }
    
    @Test
    public void shouldSetCondenserBackToNormalFailureState() {
        PhysicalModel model = new PhysicalModel();
        model.failCondenser();
        model.repairCondenser();
        assertFalse(model.components().get(2).hasFailed());
    }
    
    
    @Test
    public void shouldSetTurbineBackToNormalFailureState() {
        PhysicalModel model = new PhysicalModel();
        model.components().get(0).fail();
        model.repairTurbine();
        assertFalse(model.components().get(0).hasFailed());
    }
    
    /*@Test
    public void shouldSetPumpBackToPumping() {
        PhysicalModel model = new PhysicalModel();
        model.changePumpState(1, false);
        model.repairPump(1);
        assertEquals(true, model.);
    }*/
}
