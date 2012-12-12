/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.FailureModel;

import eel.seprphase2.FailureModel.FailureModel;
import eel.seprphase2.Simulator.PhysicalModel;
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
public class FailureModelTest {
    
    public FailureModelTest() {
    }
    
    @Test
    public void failureModelShouldChangeStateOfReactorAndTurbineAfter1000Steps() {
        PhysicalModel phys = new PhysicalModel();
        FailureModel failureModel = new FailureModel(phys);
        for (int i = 0; i < 1000; i++) {
             failureModel.step();
        }
        assertEquals(FailureState.Failed, phys.components.get(0).getFailureState());
        assertEquals(FailureState.Failed, phys.components.get(1).getFailureState());
    }
    
}
