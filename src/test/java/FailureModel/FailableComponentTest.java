/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FailureModel;

import eel.seprphase2.Simulator.Reactor;
import eel.seprphase2.Simulator.Turbine;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author Yazidi
 */
public class FailableComponentTest {
    
   
    
   
    
    
    
    public FailableComponentTest() {
    }
    
    @Test
    public void shouldInitializeReactorComponentStateToNotFailed() {
        
        FailableComponent reactor;
        reactor = new Reactor();
        assertEquals(FailureState.Normal,reactor.getFailureState());
    }
    
    @Test
    public void shouldSetReactorFailedStateToFailed() {
        FailableComponent reactor;
        reactor = new Reactor();
        reactor.setFailureState(FailureState.Failed);
        assertEquals(FailureState.Failed, reactor.getFailureState());
    }
    
    @Test
    public void shouldInitializeTurbineComponentStateToNotFailed() {
        FailableComponent turbine;
        turbine = new Turbine();
        assertEquals(FailureState.Normal,turbine.getFailureState());
    }
    
    @Test
    public void shouldSetTurbineFailedStateToFailed() {
        FailableComponent turbine;
        turbine = new Turbine();
        turbine.setFailureState(FailureState.Failed);
        assertEquals(FailureState.Failed, turbine.getFailureState());
    }
    
}
