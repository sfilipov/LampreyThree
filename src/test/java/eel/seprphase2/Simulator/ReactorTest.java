//package eel.seprphase2.Simulator;
//
//import eel.seprphase2.Simulator.Reactor;
//import eel.seprphase2.Utilities.Percentage;
//import eel.seprphase2.Utilities.Pressure;
//import eel.seprphase2.Utilities.Temperature;
//import static org.junit.Assert.*;
//import org.junit.Ignore;
//import org.junit.Test;
//import static org.hamcrest.Matchers.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//import static eel.seprphase2.Utilities.Units.*;
//
///**
// *
// * @author Marius
// */
//public class ReactorTest {
//
//    private final Reactor reactor = new Reactor();
//
//    @Test
//    public void controlRodsShouldStartAt0Percent() {
//        assertEquals(percent(0), reactor.controlRodPosition());
//    }
//
//    @Test
//    public void controlRodsShouldMove() {
//        reactor.moveControlRods(new Percentage(50));
//        assertEquals(percent(50), reactor.controlRodPosition());
//    }
//
//    @Test
//    public void initialWaterLevelShouldBe100() {
//        assertEquals(percent(100), reactor.waterLevel());
//    }
//
//    @Test
//    public void initialTemperatureShouldBe350Degrees() {
//        assertEquals(kelvin(350), reactor.temperature());
//    }
//
//    @Test
//    public void initialPressureShouldBe() {
//        assertEquals(pascals(101325), reactor.pressure());
//    }
//
//    @Test
//    @Ignore
//    public void reactorShouldStayInEquilibriumWithLowControlRods() throws GameOverException {
//        Reactor instanceOfReactor = new Reactor(percent(0), percent(100),
//                                                kelvin(350), pascals(101325));
//        instanceOfReactor.step(1);
//        assertEquals(percent(0), instanceOfReactor.controlRodPosition());
//        assertEquals(percent(100), instanceOfReactor.waterLevel());
//        assertEquals(kelvin(350), instanceOfReactor.temperature());
//        assertEquals(pascals(101325), instanceOfReactor.pressure());
//    }
//
//    @Test
//    public void shouldHeatUpWhenControlRodsExtracted() throws GameOverException {
//        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
//                                      new Temperature(350), new Pressure(101325));
//        reactor.step(1);
//        assertThat(reactor.temperature().inKelvin(), greaterThan(350.0));
//    }
//
//    @Test
//    public void shouldNotHeatBeyondBoilingPoint() throws GameOverException {
//        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
//                                      new Temperature(373.15), new Pressure(101325));
//        reactor.step(1);
//        assertEquals(373.15, reactor.temperature().inKelvin(), 0.005);
//    }
//
//    @Test
//    public void shouldIncreasePressureAtBoilingPoint() throws GameOverException {
//        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
//                                      new Temperature(373.15), new Pressure(101325));
//        reactor.step(1);
//        assertThat(reactor.pressure().inPascals(), greaterThan(101325.0));
//    }
///*
//    @Test
//    public void shouldSetOutputPressure() throws GameOverException {
//        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
//                                      new Temperature(373.15), new Pressure(101325));
//        reactor.step(1);
//        assertEquals(reactor.pressure(), reactor.outputPort().pressure);
//    }
//
//    @Test
//    public void shouldSetOutputFlowRate() throws GameOverException {
//        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
//                                      new Temperature(373.15), new Pressure(101325));
//        reactor.step(1);
//        assertThat(reactor.outputFlowVelocity().inMetresPerSecond(), greaterThan(0.0));
//    }
//    */
//
//    @Test
//    public void shouldHaveMinimumWaterLevelAtEightyInitially() {
//        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
//                                      new Temperature(373.15), new Pressure(101325));
//        assertEquals(percent(80), reactor.minimumWaterLevel());
//    }
//
//    @Test
//    public void WaterLevelShouldBeGreaterThanMinimumWaterLevelAfterStep() throws GameOverException {
//        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
//                                      new Temperature(373.15), new Pressure(101325));
//
//        reactor.step(1);
//        assertThat(reactor.waterLevel().points(), greaterThan(reactor.minimumWaterLevel().points()));
//    }
//
//    @Test
//    public void WaterLevelShouldDecreaseAfterStep() throws GameOverException {
//        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
//                                      new Temperature(373.15), new Pressure(101325));
//        reactor.step(1);
//        assertThat(reactor.waterLevel().points(), not(equalTo(100.0)));
//
//    }
//    
//    @Test
//    public void ShouldReturnMaxTemp(){
//        Temperature check = kelvin(500);
//        assertEquals(check.toString(), reactor.maximumTemperature().toString());
//    }
//    
//    @Test
//    public void ShouldReturnMaxPressure(){
//        Pressure check = new Pressure(330662500);
//        assertEquals(check.toString(), reactor.maximumPressure().toString());
//    }
//    
//}
