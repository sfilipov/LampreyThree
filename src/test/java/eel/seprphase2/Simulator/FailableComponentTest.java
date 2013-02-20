///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package eel.seprphase2.Simulator;
//
//import eel.seprphase2.Simulator.FailableComponent;
//import eel.seprphase2.Simulator.Reactor;
//import eel.seprphase2.Simulator.Turbine;
//import eel.seprphase2.Utilities.Percentage;
//import eel.seprphase2.Utilities.Pressure;
//import eel.seprphase2.Utilities.Temperature;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import static eel.seprphase2.Utilities.Units.*;
//import org.junit.Ignore;
//import static org.hamcrest.Matchers.*;
//
///**
// *
// * @author Marius
// */
//public class FailableComponentTest {
//
//    public FailableComponentTest() {
//    }
//
//    @Test
//    public void shouldInitializeReactorComponentStateToNotFailed() {
//
//        FailableComponent reactor;
//        reactor = new Reactor();
//        assertFalse(reactor.hasFailed());
//    }
//
//    @Test
//    public void shouldSetReactorFailedStateToFailed() {
//        FailableComponent reactor;
//        reactor = new Reactor();
//        reactor.fail();
//        assertTrue(reactor.hasFailed());
//    }
//
//    @Test
//    public void shouldInitializeReactorWearTo0() {
//        FailableComponent reactor = new Reactor();
//        reactor.wear();
//        assertEquals(percent(0), reactor.wear());
//    }
//
//    @Test
//    public void shouldInitializeTurbineComponentStateToNotFailed() {
//        FailableComponent turbine;
//        turbine = new Turbine();
//        assertFalse(turbine.hasFailed());
//    }
//
//    @Test
//    public void shouldSetTurbineFailedStateToFailed() {
//        FailableComponent turbine;
//        turbine = new Turbine();
//        turbine.fail();
//        assertTrue(turbine.hasFailed());
//    }
//
//    @Test
//    public void shouldInitializeTurbineWearTo0() {
//        FailableComponent turbine = new Turbine();
//        turbine.wear();
//        assertEquals(percent(0), turbine.wear());
//    }
//
//    @Test
//    @Ignore
//    public void shouldIncreaseWearOfReactorWhenRunning() throws GameOverException {
//        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
//                                      new Temperature(400), new Pressure(101325));
//        reactor.step(1);
//        assertThat(reactor.wear().ratio(), greaterThan(0.0));
//    }
//
//    @Test
//    public void shouldIncreaseWearOfTurbineWhenRunning() {
//        Turbine turbine = new Turbine();
//        turbine.step(1);
//        assertThat(turbine.wear().ratio(), greaterThan(0.0));
//    }
//
//    @Test
//    @Ignore
//    public void shouldNotIncreaseWearOver100() throws GameOverException {
//        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
//                                      new Temperature(400), new Pressure(101325));
//        Turbine turbine = new Turbine();
//
//        for (int i = 0; i < 150; i++) {
//            reactor.step(1);
//            turbine.step(1);
//        }
//        assertEquals(percent(100), reactor.wear());
//        assertEquals(percent(100), turbine.wear());
//    }
//    
//    @Test
//    public void shouldIncreaseReactorWear() {
//        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
//                                      new Temperature(400), new Pressure(101325));  
//        
//        Percentage damage = new Percentage(10);
//        reactor.addWear(damage);
//        assertEquals(percent(10), reactor.wear());
//    }
//    @Test
//    public void shouldIncreaseTurbineWear() {     
//        
//        Turbine turbine = new Turbine();        
//        
//        Percentage damage = new Percentage(15);
//        
//        turbine.addWear(damage);
//        assertEquals(percent(15), turbine.wear());
//    }
//    
//    @Test
//    public void shouldIncreasePumpWear() {          
//       
//        Pump pump = new Pump();
//        
//        Percentage damage = new Percentage(10);
//        pump.addWear(damage);
//        assertEquals(percent(10), pump.wear());
//    }
//    @Test
//    public void shouldIncreaseCondenserWear() {      
//        
//        Condenser condenser = new Condenser(); 
//        Percentage damage = new Percentage(20);
//        condenser.addWear(damage);
//        assertEquals(percent(20), condenser.wear());
//    }         
//       
//    @Test
//    public void shouldNotAddWearOver100() {
//        
//        Condenser condenser = new Condenser();
//        Turbine turbine = new Turbine();
//        Percentage damage = new Percentage(70);
//        /*
//         * // Use for loop because Percentages cannot be set over 100,
//         * so need to add several to make the wear of a component potentially over 100
//         */
//        for (int i = 0; i < 10; i++) {
//            condenser.addWear(damage);
//            turbine.addWear(damage);
//        }
//        assertEquals(percent(100), condenser.wear());
//        assertEquals(percent(100), turbine.wear());
//        
//    }
//}
