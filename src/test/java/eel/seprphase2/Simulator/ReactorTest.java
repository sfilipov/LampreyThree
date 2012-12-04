package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static eel.seprphase2.Utilities.Units.*;

/**
 *
 * @author Yazidi
 */
public class ReactorTest {

    private final Reactor reactor = new Reactor();

    @Test
    public void controlRodsShouldStartAt0Percent() {
        assertEquals(percent(0), reactor.controlRodPosition());
    }

    @Test
    public void controlRodsShouldMove() {
        reactor.moveControlRods(new Percentage(50));
        assertEquals(percent(50), reactor.controlRodPosition());
    }

    @Test
    public void initialWaterLevelShouldBe100() {
        assertEquals(percent(100), reactor.waterLevel());
    }

    @Test
    public void initialTemperatureShouldBe350Degrees() {
        assertEquals(kelvin(350), reactor.temperature());
    }

    @Test
    public void initialPressureShouldBe() {
        assertEquals(pascals(101325), reactor.pressure());
    }

    @Test
    public void reactorShouldStayInEquilibriumWithLowControlRods() {
        Reactor reactor = new Reactor(percent(0), percent(100),
                                      kelvin(350), pascals(101325));
        reactor.step();
        assertEquals(percent(0), reactor.controlRodPosition());
        assertEquals(percent(100), reactor.waterLevel());
        assertEquals(kelvin(350), reactor.temperature());
        assertEquals(pascals(101325), reactor.pressure());
    }

    @Test
    public void shouldHeatUpWhenControlRodsExtracted() {
        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
                                      new Temperature(350), new Pressure(101325));
        reactor.step();
        assertThat(reactor.temperature().inKelvin(), greaterThan(350.0));
    }

    @Test
    public void shouldNotHeatBeyondBoilingPoint() {
        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
                                      new Temperature(373.15), new Pressure(101325));
        reactor.step();
        assertEquals(373.15, reactor.temperature().inKelvin(), 0.005);
    }

    @Test
    public void shouldIncreasePressureAtBoilingPoint() {
        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
                                      new Temperature(373.15), new Pressure(101325));
        reactor.step();
        assertThat(reactor.pressure().inPascals(), greaterThan(101325.0));
    }

    @Test
    public void shouldSetOutputFlowRate() {
        Reactor reactor = new Reactor(new Percentage(100), new Percentage(100),
                                      new Temperature(373.15), new Pressure(101325));
        reactor.step();
        assertThat(reactor.outputFlowVelocity().inMetresPerSecond(), greaterThan(0.0));
    }
}
