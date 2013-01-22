/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Simulator.CannotRepairException;
import eel.seprphase2.Simulator.PhysicalModel;
import eel.seprphase2.Simulator.KeyNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;
import static eel.seprphase2.Utilities.Units.*;
import static org.hamcrest.Matchers.*;

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
    public void shouldSetConnectionToClosed() {
        PhysicalModel model = new PhysicalModel();
        model.setReactorToTurbine(false);
        assertEquals(false, model.getReactorToTurbine());

    }

    @Test
    public void shouldSetCondenserBackToNormalFailureState() {
        PhysicalModel model = new PhysicalModel();
        model.failCondenser();
        try {
            model.repairCondenser();
        } catch (CannotRepairException e) {
            fail(e.getMessage());
        }
        assertFalse(model.components().get(2).hasFailed());
    }

    @Test
    public void shouldSetTurbineBackToNormalFailureState() {
        PhysicalModel model = new PhysicalModel();
        model.components().get(0).fail();
        try {
            model.repairTurbine();
        } catch (CannotRepairException e) {
            fail(e.getMessage());
        }
        assertFalse(model.components().get(0).hasFailed());
    }

    @Test(expected = CannotRepairException.class)
    public void shouldNotSetCondenserBackToNormalFailureState() throws CannotRepairException {
        PhysicalModel model = new PhysicalModel();


        model.repairCondenser();

    }

    @Test(expected = CannotRepairException.class)
    public void shouldNotSetTurbineBackToNormalFailureState() throws CannotRepairException {
        PhysicalModel model = new PhysicalModel();


        model.repairCondenser();

    }

    @Test(expected = CannotRepairException.class)
    public void shouldNotSetPumpBackToNormalFailureState() throws CannotRepairException, KeyNotFoundException {
        PhysicalModel model = new PhysicalModel();


        model.repairPump(1);


    }

    /*@Test
     public void shouldSetPumpBackToPumping() {
     PhysicalModel model = new PhysicalModel();
     model.changePumpState(1, false);
     model.repairPump(1);
     assertEquals(true, model.);
     }*/
    @Test
    public void listNoFailures() {
        PhysicalModel pm = new PhysicalModel();
        assertEquals(0, pm.listFailedComponents().length);
    }

    @Test
    public void listSeveralFailures() {
        PhysicalModel pm = new PhysicalModel();
        pm.failCondenser();
        pm.failReactor();
        String[] expected = {"Reactor", "Condenser"};
        assertArrayEquals(expected, pm.listFailedComponents());
    }
}
