/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.GameOverException;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Marius
 */
public class FailureModelTest {

    public FailureModelTest() {
    }

    @Test
    @Ignore
    public void failureModelShouldChangeStateOfReactorAndTurbineAfter1000Steps() throws GameOverException {
        PhysicalModel phys = new PhysicalModel();
        FailureModel failureModel = new FailureModel(phys, phys);
        for (int i = 0; i < 1000; i++) {
            failureModel.step();
        }
        assertTrue(phys.components().get(0).hasFailed());
        assertTrue(phys.components().get(1).hasFailed());
    }
}
